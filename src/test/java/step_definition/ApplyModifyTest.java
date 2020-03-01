package step_definition;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.APIUtility;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import step_definition.FlightAndHubAPIs.BookingCycleAPI;
import step_definition.FlightAndHubAPIs.ModifyBookingAPI;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ApplyModifyTest extends TestBase {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    APIUtility apiObj = new APIUtility();
    ModifyBookingAPI modifyApiObj = new ModifyBookingAPI();
    BookingCycleAPI bookingApiObj = new BookingCycleAPI();

    public static String oldOrderNumber = null;
    private static String modifiedItineraryID = null;
    public static String modifiedOrderNumberFromApi = null;
    public static String modifiedPnrNumberFromApi = null;
    public static String modifiedCartId = null;

    private By changeBookBTN = By.xpath("//div[text()='Change Booking']");
    private By selectThisFlightBTN = By.xpath("(//span[contains(text(),'SELECT THIS FLIGHT')])[1]");
    private By modificationConfirmationSuccessfulMSG = By.xpath("//div[text()='Your booking has ben changed successful']");
    private By modifiedBookStatusMSG = By.xpath("//span[@class='text-success']");
    private By thanksMSG = By.xpath("//h2[@class = 'mb-5 text-black text-xl']");
    private By requestSentMSG = By.xpath("//span[@class = 'mb-3 block text-black']");
    private By closeChangeMsgBTN = By.xpath("(//button[@aria-label='Close'])[2]");
    private By oldBookStatusMSG = By.xpath("//span[@class='text-error']");
    private By dimmedChangeBookBTN = By.xpath("//div[text()='Change Booking']/ancestor::li");

    @And("^Modify the booking with this data through api$")
    public void modifyTheBookingWithThisDataThroughApi(DataTable Data) throws IOException {
        String store =null, environment=null, addedDays=null,departure =null,arrival =null;
        int addedDaysToInt =0;
        for (Map<String, String> modifySearchData : Data.asMaps(String.class, String.class)) {
            store = modifySearchData.get("store");
            environment = modifySearchData.get("environment");
            addedDays = modifySearchData.get("SearchDateAfter");
            departure = modifySearchData.get("departure");
            arrival = modifySearchData.get("arrival");
            addedDaysToInt = Integer.parseInt(addedDays);
        }
        String modifyApiUrl = "https://"+store+".fly365"+environment+".com/api/flight-search/modify";
        bookingApiObj.itinerariesSearchRequest = modifyApiObj.modifyBookingAPI(modifyApiUrl , departure , arrival , addedDaysToInt);
        oldOrderNumber = bookingApiObj.orderNumberCheckoutResponse ;
        //bookingApiObj.itinerariesSearchRequest =
        //String modifyApiUrl = "https://"+store+".fly365"+environment+".com/api/flight-search/modify";
        //apiObj.modifyBookingAPI(modifyApiUrl);
    }

    @And("^Book the new order through api on store \"([^\"]*)\" and the environment \"([^\"]*)\" and get \"([^\"]*)\"$")
    public void bookTheNewOrderThroughApiOnStoreAndTheEnvironmentAndGet(String store, String environment, String reference) throws Throwable {
        String cartID = modifyApiObj.createCartModifiedOrder(store , environment , modifiedItineraryID , BookingCycleAPI.orderIdCheckoutResponse);
        BookingCycleAPI.checkoutTrip(cartID , "stage");
        modifiedOrderNumberFromApi = bookingApiObj.orderNumberCheckoutResponse;
        modifiedPnrNumberFromApi = bookingApiObj.pnrNumberCheckoutResponse;
        if (reference.equals("order")) {
            modifiedOrderNumberFromApi = bookingApiObj.orderNumberCheckoutResponse;
        }
        if (reference.equals("Fly365 Reference")) {
            modifiedPnrNumberFromApi = bookingApiObj.pnrNumberCheckoutResponse;
        }
    }

    @And("^Click Change Booking$")
    public void clickChangeBooking() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(changeBookBTN));
        driver.findElement(changeBookBTN).click();
    }

    @And("^Select a flight to be a new booking$")
    public void selectAFlightToBeANewBooking() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectThisFlightBTN));
        driver.findElement(selectThisFlightBTN).click();
    }

    @Then("^'Your booking has ben changed successful' message is displayed$")
    public void yourBookingHasBenChangedSuccessfulMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(modificationConfirmationSuccessfulMSG));
        Assert.assertTrue(driver.findElement(modificationConfirmationSuccessfulMSG).isDisplayed());
    }

    @Then("^Modification Request Is sent Successfully$")
    public void modificationRequestIsSentSuccessfully() throws InterruptedException {
        Thread.sleep(2000);
        WebElement thanksElmnt = driver.findElement(thanksMSG);
        WebElement requestSentElmnt = driver.findElement(requestSentMSG);
        Assert.assertTrue(thanksElmnt.getText().equals("Thank you"));
        Assert.assertTrue(requestSentElmnt.getText().equals("Your request has been sent successfully."));
        driver.findElement(closeChangeMsgBTN).click();
    }

    @Then("^Modified Booking Status Will still confirmed$")
    public void modifiedBookingStatusWillStillConfirmed() throws InterruptedException {
        driver.findElement(closeChangeMsgBTN).click();
        WebElement bookStatusElmnt = driver.findElement(modifiedBookStatusMSG);
        wait.until(ExpectedConditions.textToBe(modifiedBookStatusMSG,"Confirmed"));
        Thread.sleep(1500);
        Assert.assertEquals(bookStatusElmnt.getText() , "Confirmed" );
    }

    @Then("^Old order Status Will be cancelled$")
    public void oldOrderStatusWillBeCancelled() throws InterruptedException {
        WebElement bookStatusElmnt = driver.findElement(oldBookStatusMSG);
        wait.until(ExpectedConditions.textToBe(oldBookStatusMSG,"Cancelled"));
        Thread.sleep(1500);
        Assert.assertEquals(bookStatusElmnt.getText() , "Cancelled" );
    }

    @Then("^Change My Booking is not Clickable$")
    public void changeMyBookingIsNotClickable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(changeBookBTN));
        Assert.assertTrue(driver.findElement(dimmedChangeBookBTN).getAttribute("class").contains("is-disabled"));
    }

    @Then("^Assert 'Can not modify origin country\\.' message is returned in response$")
    public void assertCanNotModifyOriginCountryMessageIsReturnedInResponse() {
        String responseError = apiObj.jsonPathEvaluator.getString("errors.oldOrderId[0]");
        Assert.assertEquals(responseError,"Can not modify origin country.");
    }

    @Then("^Assert all returned itineraries have the same carrier code$")
    public void assertAllReturnedItinerariesHaveTheSameCarrierCode() {
        List<String> carrierCodes = apiObj.jsonPathEvaluator.getList("itineraries.carrier.code");
        for (String carrier : carrierCodes) {
            Assert.assertEquals(carrier ,bookingApiObj.carrierCode);
        }
    }

    @And("^Choose modified trip number \"([^\"]*)\" and create cart$")
    public void chooseModifiedTripNumberAndCreateCart(String tripNumber) throws Throwable {
        int tripNum= Integer.parseInt(tripNumber);
        modifiedItineraryID = bookingApiObj.getItineraryId(bookingApiObj.itinerariesSearchRequest, tripNum);
        bookingApiObj.cartIdForSelectedItinerary = modifyApiObj.createCartModifiedOrder("nz" , "stage" , modifiedItineraryID , BookingCycleAPI.orderIdCheckoutResponse);
    }
}
