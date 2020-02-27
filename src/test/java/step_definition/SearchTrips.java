package step_definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.APIUtility;
import helper.GeneralMethods;
import helper.TestBase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.ParseException;
import java.util.List;


public class SearchTrips extends TestBase {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    private APIUtility apiObj = new APIUtility();
    private GeneralMethods gmObj = new GeneralMethods();

    private By flightOptionsHDR = By.xpath("//span[text()='FLY365 MIX AND MATCH FLIGHT OPTIONS']");
    private By originAirportCode = By.xpath("//span[contains(normalize-space(text()),'AKL')]");
    private By destinationAirportCode = By.xpath("//span[contains(normalize-space(text()),'DXB')]");
    private By flightDetailsDHR = By.id("itin-gp-0-optionset-0-info ");
    private By flightDetailsBTN = By.xpath("//small[text()='flight details']");
    private By displayedPriceWEB = By.xpath("//div[@id='itin-gp-0-price']");
    private By itineraryCarrierWEB = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0']/div/div[1]/div[1]/div/div/label");
    private By firstSegmentflightNumberWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-carrier");
    private By stopsWEB = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/span[2]/span[1]");
    //private By stopsWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0 > div > div.search-item.px-4.bg-white.flex.items-center.search-item.search-item-active.search-item-selected.search-item > div.col-lg-14 > div > div.flex.col-6.flex-col.items-center.leading-none.mt-1.flight-stop.el-tooltip > span.text-xs.inline-block.text-primary-fourth.font-medium.mt-1 > span");
    // private By durationWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0 > div > div.search-item.px-4.bg-white.flex.items-center.search-item.search-item-active > div.col-lg-14 > div > div.flex.col-6.flex-col.items-center.leading-none.mt-1.flight-stop.el-tooltip > span.text-xs.inline-block.text-primary-fourth.font-medium.mb-1.flight-duration");
    private By durationWEB = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/span[1]");
    private By firstSegmentCarrierNameWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-carrier > div > label.airway-name.text-primary-fourth.font-normal");
    private By firstSegmentAircraftNameWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-carrier-aircraft > label.airway-name.text-primary-fourth");
    private By firstSegmentOriginCodeWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-origin > div > span.text-sm.text-black.font-medium");
    private By firstSegmentDestinationCodeWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-destination > span.text-sm.text-black.font-semibold");
    private By firstSegmentCabinClassWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-carrier-aircraft > label.airway-code.text-black");
    //private By firstSegmentBaggageWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0 > div > div:nth-child(2) > div > div > div > div.col-lg-13.mr-0.xl\\3a mr-16.md\\3a mr-12.col-24 > div:nth-child(1) > div:nth-child(1) > div.col-md-4.md\\3a flex.hidden > div > label.airway-code.text-black");
    private By firstSegmentBaggageWEB = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[1]/li[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/label[2]");
    private By firstSegmentOriginTerminalWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-origin > div > div.flex.md\\3a flex-col.flex-row > span:nth-child(3)");
    private By firstSegmentDestinationTerminalWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-destination > div.flex.md\\3a flex-col.flex-row > span:nth-child(3)");
    private By firstSegmentOriginDepTimeWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-origin > div > div.flex.items-center > span.text-lg.text-black.font-semibold");
    private By firstSegmentDestinationDepTimeWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-destination > div.flex.items-center > span.text-lg.text-black.font-semibold");
    private By firstSegmentOriginDepDateWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-origin > div > div.flex.items-center > span.text-sm.text-black.ml-1");
    private By firstSegmentDestinationDepDateWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-destination > div.flex.items-center > span.text-sm.text-black.ml-1");
    private By firstSegmentFlightTimeWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-0-duration > div");
    private By secondSegmentflightNumberWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-carrier > div > label.airway-code.text-black.font-normal");
    private By secondSegmentCarrierNameWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-carrier > div > label.airway-name.text-primary-fourth.font-normal");
    private By secondSegmentAircraftNameWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-carrier-aircraft > label.airway-name.text-primary-fourth");
    private By secondSegmentOriginCodeWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-origin > div > span.text-sm.text-black.font-medium");
    private By secondSegmentDestinationCodeWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-destination > span.text-sm.text-black.font-semibold");
    private By secondSegmentCabinClassWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-carrier-aircraft > label.airway-code.text-black");
    private By secondSegmentBaggageWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0 > div > div:nth-child(2) > div > div > div > div.col-lg-13.mr-0.xl\\3a mr-16.md\\3a mr-12.col-24 > div:nth-child(2) > div:nth-child(3) > div.col-md-4.md\\3a flex.hidden > div > label.airway-code.text-black");
    private By secondSegmentOriginTerminalWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-origin > div > div.flex.md\\3a flex-col.flex-row > span:nth-child(3)");
    private By secondSegmentDestinationTerminalWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-destination > div.flex.md\\3a flex-col.flex-row > span:nth-child(3)");
    private By secondSegmentOriginDepTimeWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-origin > div > div.flex.items-center > span.text-lg.text-black.font-semibold");
    private By secondSegmentDestinationDepTimeWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-destination > div.flex.items-center > span.text-lg.text-black.font-semibold");
    private By secondSegmentOriginDepDateWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-origin > div > div.flex.items-center > span.text-sm.text-black.ml-1");
    private By secondSegmentDestinationDepDateWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-destination > div.flex.items-center > span.text-sm.text-black.ml-1");
    private By secondSegmentFlightTimeWEB = By.cssSelector("#itin-gp-0-optionset-0-option-0-segment-1-duration > div");
    private By footer = By.xpath("//div[@class='footer-content pt-5 bg-primary-first lg:pt-10 pb-12']");

    @And("^Scroll to the end of the page$")
    public void scrollToTheEndOfThePage() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(flightOptionsHDR));
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        for (int i = 0; i < 70; i++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }

//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        for (int i = 0; i < 18; i++) {
//            js.executeScript("scrollTo(0,10000)");
//        }
    }


    @Then("^Check count of search results$")
    public void checkCountOfSearchResults() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(flightOptionsHDR));
        scrollToTheEndOfThePage();
        String count = driver.findElement(By.xpath("//span[@class='ml-2 text-black text-sm font-medium']")).getText();
        List result = driver.findElements(flightOptionsHDR);
        String displayedCount = String.valueOf(result.size());
        Assert.assertEquals(displayedCount, count);
    }

    @Then("^The system display results as per search criteria$")
    public void theSystemDisplayResultsAsPerSearchCriteria() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(flightOptionsHDR));
        List originResult = driver.findElements(originAirportCode);
        int displayedOrigin = originResult.size();
        List destinationResult = driver.findElements(destinationAirportCode);
        int displayedDestination = destinationResult.size();
        Assert.assertEquals(displayedOrigin, displayedDestination);
    }

    @And("^Open Flight Details$")
    public void openFlightDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(flightDetailsBTN));
        driver.findElement(flightDetailsBTN).click();
    }

    @And("^Check Flight Details from WEB$")
    public String checkFlightDetails() {
        //String flightdetails = driver.findElement(By.xpath("//body/div[@class='app-container']/div[@class='relative router-view-container border-t border-primary-first']/div[@class='search-results bg-secondary-fourth']/div[@class='text-primary-third container lg:px-0 md:px-8 px-2']/div[@class='search-container']/div[@class='result-group relative rounded bg-primary-eighth py-1 relative mb-5']/div[@class='bg-secondary-fourth mx-1 result-group__items pb-3']/div[@class='row']/div[@class='w-full mx-3 bg-white result-group__container']/div[@class='el-radio-group w-full']/ul[@class='list-reset result-group__list m-3']/li/div[@id='itin-gp-0-optionset-0-option-0']/div[@class='search-item-wrapper bg-white']/div/div[@class='rounded w-full rounded-t-none pt-0 border-t-0 details-search-result-selected']/div[@class='flight-details bg-secondary-sixth m-3 mt-0 rounded bg-white']/div[@class='row']/div[@class='col-lg-13 mr-0 xl:mr-16 md:mr-12 col-24']/div[1]")).getText();
       String flightdetails = driver.findElement(By.xpath("//body/div/div[@class='app-container']/div[@class='relative router-view-container border-t border-primary-first flex flex-col']/div[@class='search-results bg-secondary-fourth']/div[@class='text-primary-third container px-0']/div[@class='search-container']/div[@class='result-group relative rounded bg-primary-eighth py-1 relative mb-5']/div[@class='bg-secondary-fourth mx-1 result-group__items']/div[@class='row']/div[@class='w-full mx-3 bg-white result-group__container']/div[@class='el-radio-group w-full']/ul[@class='list-reset result-group__list m-3']/li/div[@id='itin-gp-2-optionset-0-option-0']/div[@class='search-item-wrapper bg-white']/div/div[@class='rounded w-full rounded-t-none pt-0 border-t-0 details-search-result-selected']/div[@class='flight-details bg-secondary-sixth m-3 mt-0 rounded bg-white']/div[1]")).getText();
        return flightdetails;
    }

    private String oneWayTripAPI() {
        String departureDate1 = gmObj.addDateWithCertainPeriodAndFormat(10, "yyyy-MM-dd");
        String oneWayTrip = "{\"legs\": [{\"origin\": \"AKL\",\"destination\": \"DXB\",\"departureDate\": \"" + departureDate1 + "\"}],\n" +
                " \"cabinClass\": \"Economy\",\"infant\": 1,\"child\": 1,\"adult\": 1}";
        return oneWayTrip;
    }

    private String roundTripAPI() {
        String departureDate1 = gmObj.addDateWithCertainPeriodAndFormat(10, "yyyy-MM-dd");
        String departureDate2 = gmObj.addDateWithCertainPeriodAndFormat(15, "yyyy-MM-dd");
        String roundTrip = "{\"legs\": [{\"origin\": \"AKL\",\"destination\": \"DXB\",\"departureDate\": \"" + departureDate1 + "\"\n" +
                "   },{\"origin\": \"DXB\",\"destination\": \"AKL\",\"departureDate\": \"" + departureDate2 + "\"}],\n" +
                " \"cabinClass\": \"Economy\",\"infant\": 1,\"child\": 1,\"adult\": 1}";
        return roundTrip;
    }

    private String multiCityAPI() {
        String departureDate1 = gmObj.addDateWithCertainPeriodAndFormat(10, "yyyy-MM-dd");
        String departureDate2 = gmObj.addDateWithCertainPeriodAndFormat(20, "yyyy-MM-dd");
        String departureDate3 = gmObj.addDateWithCertainPeriodAndFormat(30, "yyyy-MM-dd");
        String multiCity = "{\"legs\": [{\"origin\": \"AKL\",\"destination\": \"DXB\",\"departureDate\": \"" + departureDate1 + "\"\n" +
                "   },{\"origin\": \"DXB\",\"destination\": \"AKL\",\"departureDate\": \"" + departureDate2 + "\"},{\"origin\": \"AKL\",\n" +
                "     \"destination\": \"DXB\",\"departureDate\": \"" + departureDate3 + "\"}],\n" +
                " \"cabinClass\": \"Economy\",\"infant\": 1,\"child\": 1,\"adult\": 1}";
        return multiCity;
    }


    private static String originallegId = null;
    private static String displayedPriceAPI = null;
    private static String itineraryCarrierAPI = null;

    private static String getDetailsOfItinerary(String returnedJsongString, int tripnumber) {
        JSONObject jObject = new JSONObject(returnedJsongString);
        JSONArray arr = jObject.getJSONArray("itineraries");
        for (int i = 0; i < arr.length(); i++) {
            originallegId = arr.getJSONObject(tripnumber - 0).getJSONArray("legs").getString(0);
            displayedPriceAPI = arr.getJSONObject(tripnumber - 0).getJSONObject("displayPricing").get("total").toString();
            itineraryCarrierAPI = arr.getJSONObject(tripnumber - 0).getJSONObject("carrier").get("name").toString();
        }
        return originallegId;
    }

    private static String firstSegmentId = null;
    private static String secondSegmentId = null;
    private static String thirdSegmentId = null;
    private static int durationAPI;
    private static int stopsAPI;


    private void getDetailsOfLegs(String returnedJsong) {
        JSONObject jObject = new JSONObject(returnedJsong);
        JSONArray arr = jObject.getJSONArray("legs");
        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).getString("legId").equals(originallegId)) {
                durationAPI = arr.getJSONObject(i).getInt("duration");
                stopsAPI = arr.getJSONObject(i).getInt("stops");
                JSONArray allSegments = arr.getJSONObject(i).getJSONArray("segments");
                firstSegmentId = allSegments.get(0).toString();
                secondSegmentId = allSegments.get(0).toString();
                //thirdSegmentId = allSegments.get(2).toString();
            }
        }
    }

    private static String firstSegmentCarrierNameAPI = null;
    private static String firstSegmentFlightNumberAPI = null;
    private static String firstSegmentAircraftNameAPI = null;
    private static String firstSegmentOriginCodeAPI = null;
    private static String firstSegmentOriginDepDateAPI = null;
    private static String firstSegmentOriginDepTimeAPI = null;
    private static String firstSegmentOriginTerminalAPI = null;
    private static String firstSegmentDestinationCodeAPI = null;
    private static String firstSegmentDestinationDepDateAPI = null;
    private static String firstSegmentDestinationDepTimeAPI = null;
    private static String firstSegmentDestinationTerminalAPI = null;
    private static String firstSegmentFuellingStopsAPI = null;
    private static String firstSegmentBaggageAPI = null;
    private static String firstSegmentFlightTimeAPI = null;
    private static String firstSegmentCabinClassAPI = null;

    private static String secondSegmentCarrierNameAPI = null;
    private static String secondSegmentFlightNumberAPI = null;
    private static String secondSegmentAircraftNameAPI = null;
    private static String secondSegmentOriginCodeAPI = null;
    private static String secondSegmentOriginDepDateAPI = null;
    private static String secondSegmentOriginDepTimeAPI = null;
    private static String secondSegmentOriginTerminalAPI = null;
    private static String secondSegmentDestinationCodeAPI = null;
    private static String secondSegmentDestinationDepDateAPI = null;
    private static String secondSegmentDestinationDepTimeAPI = null;
    private static String secondSegmentDestinationTerminalAPI = null;
    private static String secondSegmentFuellingStopsAPI = null;
    private static String secondSegmentBaggageAPI = null;
    private static String secondSegmentFlightTimeAPI = null;
    private static String secondSegmentCabinClassAPI = null;


    public void getDetailsOfSegments(String returnedJson) {
        JSONObject jObject = new JSONObject(returnedJson);
        JSONArray arr = jObject.getJSONArray("segments");
        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).getString("segmentId").equals(firstSegmentId)) {
                firstSegmentCarrierNameAPI = arr.getJSONObject(i).getJSONObject("carrier").get("name").toString();
                firstSegmentFlightNumberAPI = arr.getJSONObject(i).getJSONObject("carrier").get("flightNumber").toString();
                firstSegmentAircraftNameAPI = arr.getJSONObject(i).getJSONObject("carrier").getJSONObject("aircraft").get("name").toString();
                firstSegmentOriginCodeAPI = arr.getJSONObject(i).getJSONObject("origin").get("code").toString();
                firstSegmentOriginDepDateAPI = arr.getJSONObject(i).getJSONObject("origin").getJSONObject("departureTime").get("date").toString();
                firstSegmentOriginDepTimeAPI = arr.getJSONObject(i).getJSONObject("origin").getJSONObject("departureTime").get("time").toString();
                firstSegmentOriginTerminalAPI = "Terminal" + arr.getJSONObject(i).getJSONObject("origin").get("terminal").toString();
                firstSegmentDestinationCodeAPI = arr.getJSONObject(i).getJSONObject("destination").get("code").toString();
                firstSegmentDestinationDepDateAPI = arr.getJSONObject(i).getJSONObject("destination").getJSONObject("arrivalTime").get("date").toString();
                firstSegmentDestinationDepTimeAPI = arr.getJSONObject(i).getJSONObject("destination").getJSONObject("arrivalTime").get("time").toString();
                firstSegmentDestinationTerminalAPI = "Terminal" + arr.getJSONObject(i).getJSONObject("destination").get("terminal").toString();
                firstSegmentFuellingStopsAPI = arr.getJSONObject(i).getJSONArray("fuellingStops").toString();
                firstSegmentFlightTimeAPI = arr.getJSONObject(i).getJSONObject("flightInfo").get("flightTime").toString();
                firstSegmentCabinClassAPI = arr.getJSONObject(i).getString("cabinClass");
                firstSegmentBaggageAPI = arr.getJSONObject(i).getString("baggage");
            } else if (arr.getJSONObject(i).getString("segmentId").equals(secondSegmentId)) {
                secondSegmentCarrierNameAPI = arr.getJSONObject(i).getJSONObject("carrier").get("name").toString();
                secondSegmentFlightNumberAPI = arr.getJSONObject(i).getJSONObject("carrier").get("flightNumber").toString();
                secondSegmentAircraftNameAPI = arr.getJSONObject(i).getJSONObject("carrier").getJSONObject("aircraft").get("name").toString();
                secondSegmentOriginCodeAPI = arr.getJSONObject(i).getJSONObject("origin").get("code").toString();
                secondSegmentOriginDepDateAPI = arr.getJSONObject(i).getJSONObject("origin").getJSONObject("departureTime").get("date").toString();
                secondSegmentOriginDepTimeAPI = arr.getJSONObject(i).getJSONObject("origin").getJSONObject("departureTime").get("time").toString();
                secondSegmentOriginTerminalAPI = "Terminal" + arr.getJSONObject(i).getJSONObject("origin").get("terminal").toString();
                secondSegmentDestinationCodeAPI = arr.getJSONObject(i).getJSONObject("destination").get("code").toString();
                secondSegmentDestinationDepDateAPI = arr.getJSONObject(i).getJSONObject("destination").getJSONObject("arrivalTime").get("date").toString();
                secondSegmentDestinationDepTimeAPI = arr.getJSONObject(i).getJSONObject("destination").getJSONObject("arrivalTime").get("time").toString();
                secondSegmentDestinationTerminalAPI = "Terminal" + arr.getJSONObject(i).getJSONObject("destination").get("terminal").toString();
                secondSegmentFuellingStopsAPI = arr.getJSONObject(i).getJSONArray("fuellingStops").toString();
                secondSegmentFlightTimeAPI = arr.getJSONObject(i).getJSONObject("flightInfo").get("flightTime").toString();
                secondSegmentCabinClassAPI = arr.getJSONObject(i).getString("cabinClass");
                secondSegmentBaggageAPI = arr.getJSONObject(i).getString("baggage");
            } else if (arr.getJSONObject(i).getString("segmentId").equals(thirdSegmentId)) {
                System.out.println(arr.getJSONObject(i).getJSONObject("carrier").get("name"));
                System.out.println(arr.getJSONObject(i).getJSONObject("carrier").get("flightNumber"));
                System.out.println(arr.getJSONObject(i).getJSONObject("carrier").getJSONObject("aircraft").get("name"));
                System.out.println(arr.getJSONObject(i).getJSONObject("origin").get("code"));
                System.out.println(arr.getJSONObject(i).getJSONObject("origin").getJSONObject("departureTime").get("date"));
                System.out.println(arr.getJSONObject(i).getJSONObject("origin").getJSONObject("departureTime").get("time"));
                System.out.println(arr.getJSONObject(i).getJSONObject("origin").get("terminal"));
                System.out.println(arr.getJSONObject(i).getJSONObject("destination").get("code"));
                System.out.println(arr.getJSONObject(i).getJSONObject("destination").getJSONObject("arrivalTime").get("date"));
                System.out.println(arr.getJSONObject(i).getJSONObject("destination").getJSONObject("arrivalTime").get("time"));
                System.out.println(arr.getJSONObject(i).getJSONObject("destination").get("terminal"));
                System.out.println(arr.getJSONObject(i).getJSONArray("fuellingStops"));
                System.out.println(arr.getJSONObject(i).getJSONObject("flightInfo").get("flightTime"));
                System.out.println(arr.getJSONObject(i).getString("cabinClass"));
                System.out.println(arr.getJSONObject(i).getString("baggage"));
            }
        }
    }


    @And("^Check Flight Details for one way from API$")
    public void checkFlightDetailsForOneWayFromAPI() {
        String itineraryResults = apiObj.sendPostRequest("https://nz.fly365stage.com/api/flight-search/search", oneWayTripAPI());
        getDetailsOfItinerary(itineraryResults, 0);
        getDetailsOfLegs(itineraryResults);
        getDetailsOfSegments(itineraryResults);
    }

    @And("^Check Flight Details for round trip from API$")
    public void checkFlightDetailsForRoundTripFromAPI() {
        String itineraryResults = apiObj.sendPostRequest("https://www.fly365stage.com/api/flight-search/search", roundTripAPI());
        getDetailsOfItinerary(itineraryResults, 1);
        getDetailsOfLegs(itineraryResults);
        getDetailsOfSegments(itineraryResults);
    }

    @And("^Check Flight Details for multi city trip from API$")
    public void checkFlightDetailsForMultiCityTripFromAPI() {
        String itineraryResults = apiObj.sendPostRequest("https://www.fly365stage.com/api/flight-search/search", multiCityAPI());
        getDetailsOfItinerary(itineraryResults, 1);
        getDetailsOfLegs(itineraryResults);
        getDetailsOfSegments(itineraryResults);
    }

    public String stopCount() {
        String stopsCountAPI = null;
        if (stopsAPI == 0) {
            stopsCountAPI = "Non-Stop";
        } else if (stopsAPI == 1) {
            stopsCountAPI = "One Stop";
        } else {
            stopsCountAPI = stopsAPI + " Stops";
        }
        return stopsCountAPI;
    }


    @And("^Compare the two results$")
    public void compareTheTwoResults() throws ParseException {
        Assert.assertEquals(gmObj.changeFaretoDecimalFormatAPI(displayedPriceAPI), gmObj.changeFaretoDecimalFormat(displayedPriceWEB));
        Assert.assertEquals(itineraryCarrierAPI, driver.findElement(itineraryCarrierWEB).getText());
        Assert.assertEquals(stopCount(), driver.findElement(stopsWEB).getText());
        Assert.assertEquals(durationAPI, gmObj.changeTimeFormatInMinutes(driver.findElement(durationWEB).getText().replaceAll("[\\D]", " ").trim()));
        Assert.assertEquals(firstSegmentCarrierNameAPI, driver.findElement(firstSegmentCarrierNameWEB).getText());
        Assert.assertEquals(firstSegmentFlightNumberAPI, driver.findElement(firstSegmentflightNumberWEB).getText().replaceAll("[a-zA-Z\\s\\-]", ""));
        Assert.assertEquals(firstSegmentAircraftNameAPI, driver.findElement(firstSegmentAircraftNameWEB).getText());
        Assert.assertEquals(firstSegmentCabinClassAPI, driver.findElement(firstSegmentCabinClassWEB).getText());
        Assert.assertEquals(firstSegmentBaggageAPI, driver.findElement(firstSegmentBaggageWEB).getText());
        Assert.assertEquals(firstSegmentOriginCodeAPI, driver.findElement(firstSegmentOriginCodeWEB).getText().replaceAll(".*\\(|\\).*", ""));
        Assert.assertEquals(firstSegmentDestinationCodeAPI, driver.findElement(firstSegmentDestinationCodeWEB).getText().replaceAll(".*\\(|\\).*", ""));
        Assert.assertEquals(firstSegmentOriginTerminalAPI, driver.findElement(firstSegmentOriginTerminalWEB).getText().replaceAll("[\\s]", ""));
        Assert.assertEquals(firstSegmentDestinationTerminalAPI, driver.findElement(firstSegmentDestinationTerminalWEB).getText().replaceAll("[\\s]", ""));
        Assert.assertEquals(firstSegmentOriginDepTimeAPI, driver.findElement(firstSegmentOriginDepTimeWEB).getText().replaceAll("\\s\\-", ""));
        Assert.assertEquals(firstSegmentDestinationDepTimeAPI, driver.findElement(firstSegmentDestinationDepTimeWEB).getText().replaceAll("\\s\\-", ""));
        Assert.assertEquals(firstSegmentOriginDepDateAPI, gmObj.changeDateFormat(driver.findElement(firstSegmentOriginDepDateWEB).getText()));
        Assert.assertEquals(firstSegmentDestinationDepDateAPI, gmObj.changeDateFormat(driver.findElement(firstSegmentDestinationDepDateWEB).getText()));
        //Assert.assertEquals(firstSegmentFlightTimeAPI,gmObj.changeTimeFormatInMinutes(driver.findElement(firstSegmentFlightTimeWEB).getText().replaceAll("[\\D]"," ").trim()));

        Assert.assertEquals(secondSegmentCarrierNameAPI, driver.findElement(secondSegmentCarrierNameWEB).getText());
        Assert.assertEquals(secondSegmentFlightNumberAPI, driver.findElement(secondSegmentflightNumberWEB).getText().replaceAll("[a-zA-Z\\s\\-]", ""));
        Assert.assertEquals(secondSegmentAircraftNameAPI, driver.findElement(secondSegmentAircraftNameWEB).getText());
        Assert.assertEquals(secondSegmentCabinClassAPI, driver.findElement(secondSegmentCabinClassWEB).getText());
        Assert.assertEquals(secondSegmentBaggageAPI, driver.findElement(secondSegmentBaggageWEB).getText());
        Assert.assertEquals(secondSegmentOriginCodeAPI, driver.findElement(secondSegmentOriginCodeWEB).getText().replaceAll(".*\\(|\\).*", ""));
        Assert.assertEquals(secondSegmentDestinationCodeAPI, driver.findElement(secondSegmentDestinationCodeWEB).getText().replaceAll(".*\\(|\\).*", ""));
        Assert.assertEquals(secondSegmentOriginTerminalAPI, driver.findElement(secondSegmentOriginTerminalWEB).getText().replaceAll("[\\s]", ""));
        Assert.assertEquals(secondSegmentDestinationTerminalAPI, driver.findElement(secondSegmentDestinationTerminalWEB).getText().replaceAll("[\\s]", ""));
        Assert.assertEquals(secondSegmentOriginDepTimeAPI, driver.findElement(secondSegmentOriginDepTimeWEB).getText().replaceAll("\\s\\-", ""));
        Assert.assertEquals(secondSegmentDestinationDepTimeAPI, driver.findElement(secondSegmentDestinationDepTimeWEB).getText().replaceAll("\\s\\-", ""));
        Assert.assertEquals(secondSegmentOriginDepDateAPI, gmObj.changeDateFormat(driver.findElement(secondSegmentOriginDepDateWEB).getText()));
        Assert.assertEquals(secondSegmentDestinationDepDateAPI, gmObj.changeDateFormat(driver.findElement(secondSegmentDestinationDepDateWEB).getText()));
        //Assert.assertEquals(secondSegmentFlightTimeAPI,gmObj.changeTimeFormatInMinutes(driver.findElement(secondSegmentFlightTimeWEB).getText().replaceAll("[\\D]"," ").trim()));
    }
}
