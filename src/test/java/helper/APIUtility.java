package helper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//import static org.codehaus.groovy.tools.shell.util.Logger.io;

public class APIUtility extends TestBase {
    public static String depCity = null, arrCity = null, bookingCode = null, storeUser = null, storeId = null, carrierCode = null,
            email = null, mobileNumber = null, airLineRef = null, flyRef = null, paymentGateway = null, discountName = null,orderId = null;
    public static JsonPath jsonPathEvaluator;
    public static String totalPrice;
    private static List<Object> itineraries;


    GeneralMethods gmObject = new GeneralMethods();

    public static String sendPostRequest(String requestUrl, String tripType) {
        /*try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("x-store-id", "fly365_nz");
            connection.setRequestProperty("authorization", "guMRjevTJNNgv49LRTNCTzfp9cWnW6Sj");
            connection.setRequestProperty("x-store-user", "fly365_com_nz");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write(tripType);
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            br.close();
            connection.disconnect();
            System.out.println(jsonString.toString());
            return jsonString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }*/

        RestAssured.baseURI = requestUrl;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("x-store-id", "fly365_nz");
        httpRequest.header("authorization", "guMRjevTJNNgv49LRTNCTzfp9cWnW6Sj");
        httpRequest.header("x-store-user", "fly365_com_nz");
        Response response = httpRequest.body(tripType).post(RestAssured.baseURI);
        ResponseBody body = response.getBody();
        jsonPathEvaluator = response.jsonPath();
        String bodyStringValue = body.asString();
        return bodyStringValue;

    }

    public static String sendGetRequest(String requestUrl) {
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("x-store-id", "fly365_com");
            connection.setRequestProperty("authorization", "5TGy5xPegnkS2ML7pNbSsWJfSywSLQGZ");
            //connection.setRequestProperty("x-store-user", "fly365_com_nz");
            //OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            br.close();
            connection.disconnect();
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public String oneWayAPI() {
        String departureDate1 = gmObject.addDateWithCertainPeriodAndFormat(10, "yyyy-MM-dd");
        String oneWay = "{\"legs\": [{\"origin\": \"DXB\",\"destination\": \"CAI\",\"departureDate\": \"" + departureDate1 + "\"}],\n" +
                " \"cabinClass\": \"Economy\",\"infant\": 0,\"child\": 0,\"adult\": 1}";
        return oneWay;
    }

    public String roundTripAPI() {
        String departureDate1 = gmObject.addDateWithCertainPeriodAndFormat(10, "yyyy-MM-dd");
        String departureDate2 = gmObject.addDateWithCertainPeriodAndFormat(20, "yyyy-MM-dd");
        String roundTrip = "{\"legs\": [{\"origin\": \"DMM\",\"destination\": \"CAI\",\"departureDate\": \"" + departureDate1 + "\"\n" +
                "   },{\"origin\": \"CAI\",\"destination\": \"DMM\",\"departureDate\": \"" + departureDate2 + "\"}],\n" +
                " \"cabinClass\": \"Economy\",\"infant\": 0,\"child\": 0,\"adult\": 1}";
        return roundTrip;
    }

    public String multiCityAPI() {
        String departureDate1 = gmObject.addDateWithCertainPeriodAndFormat(10, "yyyy-MM-dd");
        String departureDate2 = gmObject.addDateWithCertainPeriodAndFormat(20, "yyyy-MM-dd");
        String departureDate3 = gmObject.addDateWithCertainPeriodAndFormat(30, "yyyy-MM-dd");
        String multiCity = "{\"legs\": [{\"origin\": \"DMS\",\"destination\": \"CAI\",\"departureDate\": \"" + departureDate1 + "\"\n" +
                "   },{\"origin\": \"CAI\",\"destination\": \"DMS\",\"departureDate\": \"" + departureDate2 + "\"},{\"origin\": \"CAI\",\n" +
                "     \"destination\": \"DXB\",\"departureDate\": \"" + departureDate3 + "\"}],\n" +
                " \"cabinClass\": \"Economy\",\"infant\": 0,\"child\": 0,\"adult\": 1}";
        return multiCity;
    }

    public String createDiscount(String discountEnteredName) {
        String discountRule = "{\n" +
                "    \"name\": \"" + discountEnteredName + "\",\n" +
                "    \"storeId\": \"fly365_nz\",\n" +
                "    \"storeUser\": \"\",\n" +
                "    \"carrierCode\": \"\",\n" +
                "    \"bookingCode\": \"\",\n" +
                "    \"fareBaseCode\": \"\",\n" +
                "    \"priority\": 0,\n" +
                "    \"cabinClass\": \"\",\n" +
                "    \"flightType\": \"\",\n" +
                "    \"searchType\": \"\",\n" +
                "    \"routeDepartureType\": \"\",\n" +
                "    \"routeDepartureCode\": \"\",\n" +
                "    \"routeDestinationType\": \"\",\n" +
                "    \"routeDestinationCode\": \"\",\n" +
                "    \"discountUpSell\": false,\n" +
                "    \"discountPercent\": 2,\n" +
                "    \"discountAmount\": 0,\n" +
                "    \"taxUpSell\": false,\n" +
                "    \"taxPercent\": 0,\n" +
                "    \"taxAmount\": 0,\n" +
                "    \"taxCode\": \"\",\n" +
                "    \"excludeCountry\": false,\n" +
                "    \"isActive\": true\n" +
                "}";
        return discountRule;
    }

    public static String    getItineraryId(String returnedJsongString, int tripnumber) {
        JSONObject jObject = new JSONObject(returnedJsongString);
        JSONArray arr = jObject.getJSONArray("itineraries");
        String itineraryId = null;
        for (int i = 0; i < arr.length(); i++) {
            itineraryId = arr.getJSONObject(tripnumber - 1).getString("itineraryId");
        }
        return itineraryId;
    }

    public static String getDiscountName(String returnedJsongString, int tripnumber) {
        JSONObject jObject = new JSONObject(returnedJsongString);
        JSONArray arr = jObject.getJSONArray("itineraries");
        String discountName = null;
        for (int i = 0; i < arr.length(); i++) {
            discountName = arr.getJSONObject(tripnumber - 1).getJSONObject("discounts").get("name").toString();
        }
        return discountName;
    }

    public static void getstoreId(String returnedJsonString) {
        ArrayList<String> bookingCodeArr = new ArrayList<String>();
        ArrayList<String> orgDest = new ArrayList<String>();
        int bookingCodeIndex = 0;
        airLineRef = jsonPathEvaluator.get("products[0].confirmation.supplierConfirmationCode").toString();
        flyRef = jsonPathEvaluator.get("products[0].confirmation.vendorConfirmationCode").toString();
        storeId = jsonPathEvaluator.get("storeId").toString();
        email = jsonPathEvaluator.get("customer.email").toString();
        mobileNumber = jsonPathEvaluator.get("customer.mobileNumber").toString();
        storeUser = jsonPathEvaluator.get("storeUser").toString();
        paymentGateway = jsonPathEvaluator.get("payment.additionalInformation.provider").toString();
        totalPrice = jsonPathEvaluator.get("displayTotal.total").toString();
        carrierCode = jsonPathEvaluator.get("products[0].options[1].value.carrier.code").toString();
        discountName = jsonPathEvaluator.get("products[0].options[1].value.discounts.name");
        orderId = jsonPathEvaluator.get("id").toString();
        System.out.println(orderId);
        List<JSONArray> legsArr = jsonPathEvaluator.getList("products[0].options[1].value.legs");
        for (int i = 0; i < legsArr.size(); i++) {
            List<JSONArray> segArr = jsonPathEvaluator.getList("products[0].options[1].value.legs[" + i + "].segments");
            for (int segCount = 0; segCount < segArr.size(); segCount++) {
                bookingCodeArr.add(jsonPathEvaluator.get("products[0].options[1].value.legs[" + i + "].segments[" + segCount + "].bookingInfo.bookingCode").toString());
                bookingCodeIndex++;
            }
        }
        List<String> removeDuplCodes = bookingCodeArr.stream().distinct().collect(Collectors.toList());
        bookingCode = String.join(",", removeDuplCodes);
        List<JSONArray> searchCriteriaArr = jsonPathEvaluator.getList("products[0].options[2].value.legs");
        for (int x = 0; x < searchCriteriaArr.size(); x++) {
            orgDest.add(jsonPathEvaluator.get("products[0].options[2].value.legs[" + x + "].origin").toString());
            orgDest.add(jsonPathEvaluator.get("products[0].options[2].value.legs[" + x + "].destination").toString());
        }
        depCity = orgDest.get(0);
        if (orgDest.get(0).equals(orgDest.get(orgDest.size() - 1))) {
            arrCity = orgDest.get(orgDest.size() - 2);
        } else {
            arrCity = orgDest.get(orgDest.size() - 1);
        }

    }

    public static String getDiscountStatus(String returnedJsongString, int tripnumber) {
        JSONObject jObject = new JSONObject(returnedJsongString);
        JSONArray arr = jObject.getJSONArray("itineraries");
        String discountField = null;
        for (int i = 0; i < arr.length(); i++) {
            discountField = arr.getJSONObject(tripnumber - 1).get("discounts").toString();
        }
        return discountField;
    }

    public static String createCart(String itineraryId, String domain) {

        String createCartAPI = "{\"itineraryId\": \"" + itineraryId + "\"}";
        //String returnedJsonString = sendPostRequest("https://api.fly365" + domain + ".com/flight/cart", createCartAPI);
        String returnedJsonString = sendPostRequest("https://nz.fly365" + domain + ".com/api/flight/cart", createCartAPI);
        JSONObject jObject = new JSONObject(returnedJsonString);
        //return jObject.getString("id");
        return jObject.get("id").toString();
    }

    public static void addPassenger(String cartID, String domain) {

        String addPassengerDetailsAPI = "{\"passengers\": [" +
                "{\"title\": \"Mr\", \"firstName\": \"John\",\"middleName\": \"William\",\"lastName\": \"Smith\",\"dateOfBirth\": \"1991-06-04\"," +
                "\"reference\": \"123\",\"passengerType\": \"ADT\",\"frequentFlyerOptions\": {\"airlineCode\": \"code\",\"number\": \"num\"" +
                "}}],\"customer\": {\"title\": \"Mr\",\"firstName\": \"John\",\"lastName\": \"William\",\"email\": \"john.smith.fly365@gmail.com\"," +
                "\"mobileNumber\": \"0121234567\"}}";
        //sendPostRequest("https://api.fly365" + domain + ".com/flight/cart/" + cartID + "/passenger", addPassengerDetailsAPI);
        sendPostRequest("https://nz.fly365" + domain + ".com/api/flight/cart/" + cartID + "/passenger", addPassengerDetailsAPI);
    }

    public static String[] checkoutTrip(String cartID, String domain) {
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
        //String returnedJsonString = sendPostRequest("https://api.fly365" + domain + ".com/flight/cart/" + cartID + "/checkout", addCardDetailsAPI);
        String returnedJsonString = sendPostRequest("https://nz.fly365" + domain + ".com/api/flight/cart/" + cartID + "/checkout", addCardDetailsAPI);
//To validate that the order no./pnr number is displayed correctly in retrieve my booking
        JSONObject jObject = new JSONObject(returnedJsonString);
        String orderNumber = jObject.getJSONObject("order").get("orderNumber").toString();
        String orderId = jObject.getJSONObject("order").get("orderId").toString();
        String[] fly365AirlineRef = {orderNumber, orderId};
        return fly365AirlineRef;
    }


    public String createRuleAPI(String ruleName, String storeID, String carrierCode, String bookCode, String depType, String depCode,
                                String destType, String destCode, String refundability, String status) {
        int cnacelOption = 0;
        String createRuleAPIBody = null;
        boolean isActive = true;
        if (status.equalsIgnoreCase("Active")) {
            isActive = true;
        } else {
            isActive = false;
        }
        if (refundability.equalsIgnoreCase("refundable")) {
            cnacelOption = 1;
            createRuleAPIBody = "{" +
                    "\"name\" : \"" + ruleName + "\"," +
                    "\"storeId\" : \"" + storeID + "\"," +
                    "\n" +
                    "\"carrierCodeString\" : \"" + carrierCode + "\"," +
                    "\"bookingCodeString\": \"" + bookCode + "\"," +
                    "\n" +
                    "\"departureType\" : \"" + depType + "\"," +
                    "\"departureCodeString\" : \"" + depCode + "\"," +
                    "\n" +
                    "\"destinationType\" : \"" + destType + "\"," +
                    "\"destinationCodeString\" : \"" + destCode + "\"," +
                    "\n" +
                    "\"airlineChangeFees\" : 100," +
                    "\"fly365ChangeFees\" : 200," +
                    "\n" +
                    "\"defaultBaseChangeFees\": 50," +
                    "\"defaultTaxChangeFees\": 50," +
                    "\n" +
                    "\"airlineCancelFees\": 100," +
                    "\"fly365CancelFees\": 200," +
                    "\n" +
                    "\"cancellationOption\": " + cnacelOption + "," +
                    "\n" +
                    "\"isActive\": " + isActive + "\n" +
                    "}";
        } else {
            cnacelOption = 2;
            createRuleAPIBody = "{" +
                    "\"name\" : \"" + ruleName + "\"," +
                    "\"storeId\" : \"" + storeID + "\"," +
                    "\n" +
                    "\"carrierCodeString\" : \"" + carrierCode + "\"," +
                    "\"bookingCodeString\": \"" + bookCode + "\"," +
                    "\n" +
                    "\"departureType\" : \"" + depType + "\"," +
                    "\"departureCodeString\" : \"" + depCode + "\"," +
                    "\n" +
                    "\"destinationType\" : \"" + destType + "\"," +
                    "\"destinationCodeString\" : \"" + destCode + "\"," +
                    "\n" +
                    "\"airlineChangeFees\" : 100," +
                    "\"fly365ChangeFees\" : 200," +
                    "\n" +
                    "\"defaultBaseChangeFees\": 50," +
                    "\"defaultTaxChangeFees\": 50," +
                    "\n" +
                    "\"cancellationOption\": " + cnacelOption + "," +
                    "\n" +
                    "\"isActive\": " + isActive + "\n" +
                    "}";
        }


        return createRuleAPIBody;
    }

    public static String sendPostRequestCreateTicket(String requestUrl, String createRuleAPI) {
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("x-user-token", "iKW0cf68638aeaad43297055aa446693276EcV");
            connection.setRequestProperty("authorization", "XXu5WbKbM6XHbU5VKNETr6AMnNaVNd9E");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write(createRuleAPI);
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            return jsonString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public static String getTripResponse(String email, String orderNumber) {
        String response = sendGetRequests("https://www.fly365stage.com/api/user/order/find?email=" + email + "&orderNumber=" + orderNumber);
        return response;
    }

    public static String sendGetRequests(String url) {

        RestAssured.baseURI = url;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("x-user-token", "iKW0cf68638aeaad43297055aa446693276EcV");
        httpRequest.header("x-store-id", "fly365_com");
        httpRequest.header("authorization", "XXu5WbKbM6XHbU5VKNETr6AMnNaVNd9E");
        Response response = httpRequest.get(RestAssured.baseURI);
        ResponseBody body = response.getBody();
        jsonPathEvaluator = response.jsonPath();
        String bodyStringValue = body.asString();
        return bodyStringValue;
        /*try {
            URL urlReq = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlReq.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-user-token", "iKW0cf68638aeaad43297055aa446693276EcV");
            connection.setRequestProperty("x-store-id", "fly365_com");
            connection.setRequestProperty("authorization", "XXu5WbKbM6XHbU5VKNETr6AMnNaVNd9E");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            return jsonString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }*/
    }

    public String getresult(String orderId, String orderNumber) {
        String finalResponse = sendGetRequest("https://api.fly365stage.com/user/order/find?orderId=" + orderId + "&orderNumber=" + orderNumber + "");
        return finalResponse;
    }




    public static void sendPutRequest(String requestUrl,String body) {
        RestAssured.baseURI = requestUrl;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("x-user-token", "lhA27cd524d4579576316fa4b6c3abfe0d7pSL");
        httpRequest.header("authorization", "4jzthfsrmK9rhgKTr5XkEjeEcZ7kf9eA");
        httpRequest.body(body);
        httpRequest.put(requestUrl);


    }


    public static void updateHoldRule(String domain, Integer holdHours, Integer hoursBeforeTicketing, Integer hoursBeforeDeparture, Boolean isHoldEnabled, Integer amount, String StoreId, List<String> excludedAirlines) {

        String updateSettingsBody=
                "{\n" +
                        "  \"hoursBeforeFirstDeparture\":" + hoursBeforeDeparture+ "," +"\n" +
                        "  \"HoursBeforeLastTicketingTime\":" + hoursBeforeTicketing+ "," + "\n" +
                        "  \"holdHours\" :" + holdHours + "," + "\n" +
                        "  \"isHoldEnabled\" :" +  isHoldEnabled + "\n" +
                        "}\n";

        sendPutRequest("https://internal.fly365"+ domain +".com/rules/hold/configs",updateSettingsBody);

        String updateHoldFeesBody =
                "{\n" +
                        " \"storeId\":" + "\"" + StoreId + "\""  + ","  +  "\n"+
                        " \"amount\":" + amount  + "\n" +


                         "}\n";
        sendPutRequest("https://internal.fly365"+ domain +".com/rules/hold/fees",updateHoldFeesBody);

        String updateHoldExcludedAirlines =
                "{\n" +
                        "\"excludedAirlines\" :" + excludedAirlines + "\n" +

                        "}\n";

        sendPutRequest("https://internal.fly365"+ domain +".com/rules/hold/excluded-airlines", updateHoldExcludedAirlines);

    }

    public static void modifyBookingAPI(String url) {

        String modifyBody = "{\n" +
                "\t\"legs\": [\n" +
                "\t\t{\n" +
                "   \t\t\t\"origin\": \"LHR\",\n" +
                "    \t\t\"destination\": \"CAI\",\n" +
                "    \t\t\"departureDate\": \"2020-02-08\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"cabinClass\": \"economy\",\n" +
                "\t\"infant\": 0,\n" +
                "\t\"child\": 0,\n" +
                "\t\"adult\": 1,\n" +
                "\t\"oldOrderId\": \""+orderId+"\n" +
                "}";
        String returnedJsonString = sendPostRequest(url, modifyBody);
        System.out.println(returnedJsonString);
        JSONObject jObject = new JSONObject(returnedJsonString);

    }

}

