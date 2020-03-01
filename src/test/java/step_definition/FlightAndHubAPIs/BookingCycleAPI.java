package step_definition.FlightAndHubAPIs;

import helper.APIUtility;
import helper.GeneralMethods;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingCycleAPI {

    public static String orderIdCheckoutResponse = null;
    public static String itinerariesSearchRequest = null;
    public static String itinaryIdFromSearchRequest = null;
    public static String cartIdForSelectedItinerary = null;
    public static String orderNumberCheckoutResponse = null;
    public static String pnrNumberCheckoutResponse = null;

    public static String depCity = null, arrCity = null, bookingCode = null, storeUser = null, storeId = null, carrierCode = null,
            email = null, mobileNumber = null, airLineRef = null, paymentGateway = null, discountName = null, orderId = null, totalPrice;
    GeneralMethods gmObject = new GeneralMethods();
    public String oneWayAPI() {
        String departureDate1 = gmObject.addDateWithCertainPeriodAndFormat(10, "yyyy-MM-dd");
        String oneWay = "{\"legs\": [{\"origin\": \"RUH\",\"destination\": \"CAI\",\"departureDate\": \"" + departureDate1 + "\"}],\n" +
                " \"cabinClass\": \"Economy\",\"infant\": 0,\"child\": 0,\"adult\": 1}";
        return oneWay;
    }

    public  String roundTripAPI() {
        String departureDate1 = gmObject.addDateWithCertainPeriodAndFormat(10, "yyyy-MM-dd");
        String departureDate2 = gmObject.addDateWithCertainPeriodAndFormat(20, "yyyy-MM-dd");
        String roundTrip = "{\"legs\": [{\"origin\": \"JED\",\"destination\": \"CAI\",\"departureDate\": \"" + departureDate1 + "\"\n" +
                "   },{\"origin\": \"CAI\",\"destination\": \"DMM\",\"departureDate\": \"" + departureDate2 + "\"}],\n" +
                " \"cabinClass\": \"Economy\",\"infant\": 0,\"child\": 0,\"adult\": 1}";
        return roundTrip;
    }

    public String multiCityAPI() {
        String departureDate1 = gmObject.addDateWithCertainPeriodAndFormat(10, "yyyy-MM-dd");
        String departureDate2 = gmObject.addDateWithCertainPeriodAndFormat(20, "yyyy-MM-dd");
        String departureDate3 = gmObject.addDateWithCertainPeriodAndFormat(30, "yyyy-MM-dd");
        String multiCity = "{\"legs\": [{\"origin\": \"JED\",\"destination\": \"CAI\",\"departureDate\": \"" + departureDate1 + "\"\n" +
                "   },{\"origin\": \"CAI\",\"destination\": \"JED\",\"departureDate\": \"" + departureDate2 + "\"},{\"origin\": \"CAI\",\n" +
                "     \"destination\": \"DXB\",\"departureDate\": \"" + departureDate3 + "\"}],\n" +
                " \"cabinClass\": \"Economy\",\"infant\": 0,\"child\": 0,\"adult\": 1}";
        return multiCity;
    }

    public static String    getItineraryId(String returnedJsongString, int tripnumber) {
        JSONObject jObject = new JSONObject(returnedJsongString);
        JSONArray arr = jObject.getJSONArray("itineraries");
        for (int i = 0; i < arr.length(); i++) {
            itinaryIdFromSearchRequest = arr.getJSONObject(tripnumber - 1).getString("itineraryId");
        }
        return itinaryIdFromSearchRequest;
    }

    public static String createCart(String itineraryId, String domain) throws IOException {

        String createCartAPI = "{\"itineraryId\": \"" + itineraryId + "\"}";
        String returnedJsonString = APIUtility.sendRequestFlight("https://nz.fly365" + domain + ".com/api/flight/cart", createCartAPI,"post");
        JSONObject jObject = new JSONObject(returnedJsonString);
        return jObject.getString("id");

    }

    public static void addPassenger(String cartID, String domain) throws IOException {

        String addPassengerDetailsAPI = "{\"passengers\": [" +
                "{\"title\": \"Mr\", \"firstName\": \"John\",\"middleName\": \"William\",\"lastName\": \"Smith\",\"dateOfBirth\": \"1991-06-04\"," +
                "\"reference\": \"123\",\"passengerType\": \"ADT\",\"frequentFlyerOptions\": {\"airlineCode\": \"code\",\"number\": \"num\"" +
                "}}],\"customer\": {\"title\": \"Mr\",\"firstName\": \"John\",\"lastName\": \"William\",\"email\": \"john.smith.fly365@gmail.com\"," +
                "\"mobileNumber\": \"0121234567\"}}";
        APIUtility.sendRequestFlight("https://nz.fly365" + domain + ".com/api/flight/cart/" + cartID + "/passenger", addPassengerDetailsAPI, "post");
    }

    public static void checkoutTrip(String cartID, String domain) throws IOException {
        String addCardDetailsAPI = "{\n" +
                "    \"method\": \"cc\",\n" +
                "    \"type\": \"direct\",\n" +
                "    \"card\": {\n" +
                "        \"number\": \"5123450000000008\",\n" +
                "        \"cvv\": \"123\",\n" +
                "        \"expiryDate\": \"1122\",\n" +
                "        \"type\": \"cc-visa-credit\",\n" +
                "        \"holderName\": \"Alaa Attya\"\n" +
                "    },\n" +
                "    \"billingAddress\": {\n" +
                "        \"address\": \"Something\",\n" +
                "        \"country\": \"DO\",\n" +
                "        \"city\": \"AZS\",\n" +
                "        \"zipCode\": \"5678\",\n" +
                "        \"state\": \"NY\"\n" +
                "    },\n" +
                "    \"holdFees\":{\n" +
                "    \t\"amount\": 15.8\n" +
                "    }\n" +
                "}";
        String returnedJsonString = APIUtility.sendRequestFlight("https://nz.fly365" + domain + ".com/api/flight/cart/" + cartID + "/checkout", addCardDetailsAPI, "post");
//To validate that the order no./pnr number is displayed correctly in retrieve my booking
        JSONObject jObject = new JSONObject(returnedJsonString);
        orderNumberCheckoutResponse = jObject.getJSONObject("order").get("orderNumber").toString();
        orderIdCheckoutResponse = jObject.getJSONObject("order").get("orderId").toString();
        String finalResponse = getresult(orderIdCheckoutResponse, orderNumberCheckoutResponse);
        getBookingResponse();
    }



    public static void getBookingResponse() {
        ArrayList<String> bookingCodeArr = new ArrayList<String>();
        airLineRef = APIUtility.jsonPathEvaluator.get("products[0].confirmation.supplierConfirmationCode").toString();
        pnrNumberCheckoutResponse = APIUtility.jsonPathEvaluator.get("products[0].confirmation.vendorConfirmationCode").toString();
        storeId = APIUtility.jsonPathEvaluator.get("storeId").toString();
        email = APIUtility.jsonPathEvaluator.get("customer.email").toString();
        mobileNumber = APIUtility.jsonPathEvaluator.get("customer.mobileNumber").toString();
        storeUser = APIUtility.jsonPathEvaluator.get("storeUser").toString();
        paymentGateway = APIUtility.jsonPathEvaluator.get("payment.additionalInformation.provider").toString();
        totalPrice = APIUtility.jsonPathEvaluator.get("displayTotal.total").toString();
        carrierCode = APIUtility.jsonPathEvaluator.get("products[0].options[1].value.carrier.code").toString();
        discountName = APIUtility.jsonPathEvaluator.get("products[0].options[1].value.discounts.name");
        orderId = APIUtility.jsonPathEvaluator.get("id").toString();
        List<JSONArray> legsArr = APIUtility.jsonPathEvaluator.getList("products[0].options[1].value.legs");
        for (int i = 0; i < legsArr.size(); i++) {
            List<JSONArray> segArr = APIUtility.jsonPathEvaluator.getList("products[0].options[1].value.legs[" + i + "].segments");
            for (int segCount = 0; segCount < segArr.size(); segCount++) {
                bookingCodeArr.add(APIUtility.jsonPathEvaluator.get("products[0].options[1].value.legs[" + i + "].segments[" + segCount + "].bookingInfo.bookingCode").toString());
            }
        }
        List<String> removeDuplCodes = bookingCodeArr.stream().distinct().collect(Collectors.toList());
        bookingCode = String.join(",", removeDuplCodes);
        depCity = APIUtility.jsonPathEvaluator.get("products[0].options[2].value.legs.origin[0]").toString();
        List<String> arrivals = APIUtility.jsonPathEvaluator.getList("products[0].options[2].value.legs.destination");
        arrCity = arrivals.get(arrivals.size() - 1);
        /*if (arrivals.get(0).equals(arrivals.get(arrivals.size() - 1))) {
            arrCity = arrivals.get(arrivals.size() - 2);
        } else {
            arrCity = arrivals.get(arrivals.size() - 1);
        }*/
    }

    public static String getresult(String orderId, String orderNumber) throws IOException {
        String finalResponse = APIUtility.sendRequestFlight("https://nz.fly365stage.com/api/user/order/find?orderId=" + orderId + "&orderNumber=" + orderNumber + "","noBody", "get");
        return finalResponse;
    }

    public static String getTripResponse(String email, String orderNumber) throws IOException {
        String response = APIUtility.sendRequestFlight("https://www.fly365stage.com/api/user/order/find?email=" + email + "&orderNumber=" + orderNumber,"no Body", "get");
        return response;
    }


    public String oneWayAPITest(String[] departures, String[] arrivals, String[] depDates, int adults, int child, int infants, String cabinClass ) throws IOException {
        String departureDates []= new String[depDates.length];
        int [] depDatesAfterToInt = new int[depDates.length];
        for(int i= 0 ; i<depDates.length; i++){
            depDatesAfterToInt [i]= Integer.parseInt(depDates[i]);
            departureDates[i] = gmObject.addDateWithCertainPeriodAndFormat(depDatesAfterToInt [i], "yyyy-MM-dd");
        }
        List<String> newStr = new ArrayList<String>(arrivals.length);
        for(int i =0 ; i < departures.length;i++){
            newStr.add("{\"origin\": \""+departures[i]+"\",\"destination\": \""+arrivals[i]+"\",\"departureDate\": \""+departureDates[i]+"\"}");
        }
        String oneWay = "{\"legs\": "+newStr+",\n" +
                " \"cabinClass\": \""+cabinClass+"\",\"infant\": "+infants+",\"child\": "+child+",\"adult\": "+adults+"}";

        //String oneWaytest = "{\"legs\": [{\"origin\": \"JED\",\"destination\": \"CAI\",\"departureDate\": \"" + departureDate1 + "\"}],\n" +
        //        " \"cabinClass\": \"Economy\",\"infant\": 0,\"child\": 0,\"adult\": 1}";
        return oneWay;

    }


    public void addPassengerTest(String cartID, String domain , String[] birthDates, String[] passengerTypes, String[] titles, String[] firstNames, String[] lastNames, String[] passportNumber,
        String[] passportExpiry, String[] passportCountry, String[] frequentFlyer, String[] seats, String[] meals, String[] specialAssist, String customerTitle,
        String customerFirstName, String customerLastName, String phoneNumber, String email, String specialRequest) throws IOException {
        List<String> newStr = new ArrayList<String>(titles.length);
        for(int i=0;i<titles.length;i++){
            newStr.add("{\"dateOfBirth\":\""+birthDates[i]+"\",\"passengerType\":\""+passengerTypes[i]+"\",\"passengerNameByType\":\"\",\"title\":\""+titles[i]+"\",\"firstName\":\""+firstNames[i]+"\",\"middleName\":\"\"," +
            "\"lastName\":\""+lastNames[i]+"\",\"identificationDetail\":{\"idType\":\"passport\",\"idNumber\":\""+passportNumber[i]+"\",\"idExpiryDate\":\""+passportExpiry[i]+"\",\"country\":\""+passportCountry[i]+"\"}," +
            "\"reference\":\"6122\",\"frequentFlyerNumber\":\""+frequentFlyer[i]+"\",\"frequentFlyerOptions\":{},\"preferenceOptions\":{\"meal\":{\"code\":\""+meals[i]+"\"},\"seat\":{\"code\":\""+seats[i]+"\"}," +
            "\"specialAssistance\":{\"code\":\""+specialAssist[i]+"\"}}}");
        }
        String passengerDetailsBody = "{\"customer\":{\"title\":\""+customerTitle+"\"," + "\"firstName\":\""+customerFirstName+"\"," +
            "\"lastName\":\""+customerLastName+"\",\"mobileNumber\":\""+phoneNumber+"\",\"email\":\""+email+"\"},\"passengers\":"+newStr+",\"specialRequest\":\""+specialRequest+"\"}}";
        APIUtility.sendRequestFlight("https://nz.fly365stage.com/api/flight/cart/" + cartID + "/passenger", passengerDetailsBody, "post");
    }

    public void checkoutItinerary(String cartID, String domain, String cardHolderName, String cardExpiryDate, String cardNumber, String cvv) throws IOException {
        String addCardDetailsBody = "{\"type\":\"direct\",\"method\":\"cc\",\"card\":{\"id\":\"\",\"number\":\""+cardNumber+"\",\"cvv\":\""+cvv+"\",\"expiryDate\":\""+cardExpiryDate+"\"," +
                "\"type\":\"cc-visa-credit\",\"cardType\":\"cc-visa-credit\",\"holderName\":\""+cardHolderName+"\",\"issuer\":\"bank\",\"typeLong\":\"\",\"name\":\" Card\",\"typeShort\":\"\"" +
                ",\"default\":\"\"},\"billingAddress\":{\"address\":\"add 11\",\"addressLine2\":\"add 22\",\"zipCode\":\"101010\",\"state\":\"new Cairo\"}," +
                "\"newsletterSubscription\":false,\"acceptTerms\":true,\"acknowledgeRisk\":true,\"holdFees\":{}}";
        String returnedJsonString = APIUtility.sendRequestFlight("https://nz.fly365" + domain + ".com/api/flight/cart/" + cartID + "/checkout", addCardDetailsBody, "post");
//To validate that the order no./pnr number is displayed correctly in retrieve my booking
        JSONObject jObject = new JSONObject(returnedJsonString);
        orderNumberCheckoutResponse = jObject.getJSONObject("order").get("orderNumber").toString();
        orderIdCheckoutResponse = jObject.getJSONObject("order").get("orderId").toString();
        String finalResponse = getresult(orderIdCheckoutResponse, orderNumberCheckoutResponse);
        getBookingResponse();
    }
}
