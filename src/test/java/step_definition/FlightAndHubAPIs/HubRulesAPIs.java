package step_definition.FlightAndHubAPIs;

import helper.APIUtility;

import java.io.IOException;
import java.util.List;

public class HubRulesAPIs {



    public static void updateHoldRule(String domain, Integer holdHours, Integer hoursBeforeTicketing, Integer hoursBeforeDeparture, Boolean isHoldEnabled, Integer amount, String StoreId, List<String> excludedAirlines) throws IOException {

        String updateSettingsBody=
                "{\n" +
                        "  \"hoursBeforeFirstDeparture\":" + hoursBeforeDeparture+ "," +"\n" +
                        "  \"HoursBeforeLastTicketingTime\":" + hoursBeforeTicketing+ "," + "\n" +
                        "  \"holdHours\" :" + holdHours + "," + "\n" +
                        "  \"isHoldEnabled\" :" +  isHoldEnabled + "\n" +
                        "}\n";

        APIUtility.sendRequestHub("https://internal.fly365"+ domain +".com/rules/hold/configs",updateSettingsBody, "put");

        String updateHoldFeesBody =
                "{\n" +
                        " \"storeId\":" + "\"" + StoreId + "\""  + ","  +  "\n"+
                        " \"amount\":" + amount  + "\n" +


                        "}\n";
        APIUtility.sendRequestHub("https://internal.fly365"+ domain +".com/rules/hold/fees",updateHoldFeesBody, "put");

        String updateHoldExcludedAirlines =
                "{\n" +
                        "\"excludedAirlines\" :" + excludedAirlines + "\n" +

                        "}\n";

        APIUtility.sendRequestHub("https://internal.fly365"+ domain +".com/rules/hold/excluded-airlines", updateHoldExcludedAirlines, "put");

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
}
