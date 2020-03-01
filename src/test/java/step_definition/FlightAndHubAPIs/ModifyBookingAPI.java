package step_definition.FlightAndHubAPIs;

import helper.APIUtility;
import helper.GeneralMethods;
import org.json.JSONObject;

import java.io.IOException;

public class ModifyBookingAPI {

    APIUtility apiObj = new APIUtility();
    GeneralMethods gmObject = new GeneralMethods();

    public String modifyBookingAPI(String url , String departure , String arrival , int addedDays) throws IOException {
        String departureDate1 = gmObject.addDateWithCertainPeriodAndFormat(addedDays, "yyyy-MM-dd");
        String modifyBody = "{\"child\":0,\"adult\":1,\"infant\":0,\"cabinClass\":\"economy\"," +
                "\"legs\":[{\"origin\":\"" + departure + "\",\"destination\":\"" + arrival + "\",\"departureDate\":\"" + departureDate1 + "\"}]," +
                "\"oldOrderId\":\"" + BookingCycleAPI.orderIdCheckoutResponse + "\"}";
        String returnedJsonString = apiObj.sendRequestFlight(url, modifyBody, "post");
        JSONObject jObject = new JSONObject(returnedJsonString);
        return jObject.toString();
    }

    public String createCartModifiedOrder(String store, String domain , String itineraryId, String ditchedOrderId) throws IOException {

        String createCartModifiedOrderAPI = "{\"itineraryId\": \"" + itineraryId + "\",\"ditchedOrderId\":\"" + ditchedOrderId + "\"}";
        String returnedJsonString = apiObj.sendRequestFlight("https://" + store +".fly365" + domain + ".com/api/flight/cart", createCartModifiedOrderAPI, "post");
        JSONObject jObject = new JSONObject(returnedJsonString);
        return jObject.get("id").toString();
    }
}
