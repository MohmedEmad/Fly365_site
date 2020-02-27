package step_definition;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.APIUtility;
import helper.DataBase;
import helper.GeneralMethods;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Map;

public class HomeTest extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 30);
    GeneralMethods gmObject = new GeneralMethods();
    APIUtility apiObject = new APIUtility();
    ConfirmationTest ctobject = new ConfirmationTest();
    public static String orderNumber = null;
    public static String pnrNumberCheckoutResponse = null;
    public static String currentWindow = driver.getWindowHandle();



    private By aboutUsLINK = By.xpath("//a[text()='About us']");
    private By firstContactUsLINK = By.linkText("Contact Us");
    private By secondContactUsLINK = By.xpath("//strong[contains(text(),'Contact us')]");
    private By signInLINK = By.xpath("//a[text()='Sign in']");
    private By signUpLINK = By.xpath("//a[text()='Sign up']");
    private By fristSupportCentreLINK = By.xpath("//a[contains(text(),'Support Centre')]");
    private By secondSupportCentreLINK = By.xpath("//div[@class='ml-2']/strong[contains(text(), 'Support Centre')]");
    private By faqsLINK = By.xpath("//a[text()='FAQs']");
    private By termsConditionsLINK = By.xpath("//a[text()='Terms and Conditions']");
    private By privacyPolicyLINK = By.xpath("//a[text()='Privacy policy']");
    private By aftaLINK = By.xpath("//img[@class='image-afta rounded']");
    private By logoFooterLink = By.xpath("//*[@class='footer-content__logoimg -mt-3 text-white svg-icon svg-fill']");
    private By logoHeaderLink = By.xpath("//a[@class='text-white inline-block router-link-active']");
    private By flightsLink = By.xpath("//div[1]/div[1]/div/div/div[1]/div/div/a[1]");
    private By offersLink = By.xpath("//div[1]/div[1]/div/div/div[1]/div/div/a[2]");
    private By signInBTN = By.linkText("SIGN IN");
    private By homePageHDR = By.xpath("//span[@class='text-primary-second']");
    private By oneWayTAB = By.id("tab-oneWay");
    private By roundTripTAB = By.id("tab-roundTrip");
    private By multiCityTAB = By.id("tab-multiStop");
    private By originTXT = By.xpath("//input[@name='origin']");
    private By destinationTXT = By.xpath("//input[@name='destination']");
    private By airportSearchResultOrigin = By.xpath("//li[contains(@id, '-0')]");
    private By airportSearchResultDestination = By.xpath("//li[contains(@id, '-0')]");
    private By departureCalenderDPK = By.xpath("//input[@name='fromDate']");
    private By departureRoundCalenderDPK = By.xpath("//input[@name='d']");
    private By returnRoundCalenderDPK = By.xpath("//input[@name='a']");
    private By passengerCabinTXT = By.xpath("//div[@class='bg-white text-sm h-50 px-3 rounded flex items-center text-primary-third font-medium el-popover__reference']");
    private By passengerCabinPOPOVER = By.xpath("//body/div[contains(@id,'el-popover')][1]");
    private By plusAdultBTN = By.xpath("//*[contains(@id,'el-popover')]//div[1]/div/span[2]");
    private By plusChildBTN = By.xpath("//*[contains(@id,'el-popover')]//div[2]/div/span[2]");
    private By plusInfantBTN = By.xpath("//*[contains(@id,'el-popover')]//div[3]/div/span[2]");
    private By searchNowBTN = By.xpath("//button[@class='btn uppercase btn-search-form font-bold w-full m-auto btn-primary-second h-full']");
    private By findMyBookingLINK = By.xpath("//button[text()='Manage My Booking']");
    private By findMyBookingEmailTXT = By.xpath("//input[@placeholder='Email']");
    private By findMyBookingAirlineFly365OrderTXT = By.xpath("//input[@placeholder='Fly365 Reference']");
    private By findMyBookingFindBookingBTN = By.xpath("//button[text()='FIND BOOKING']");
    private By addMoreCitiesLINK = By.xpath("//div[@class='flex items-center btn-add-more float-left text-white text-xs h-6 px-1 cursor-pointer leading-normal']");
    private By subscriptionTXT = By.xpath("//input[@placeholder='Your Email']");
    private By subscribeBTN = By.xpath("//button[@class='footer-newsletter__button-form btn text-sm font-normal btn-primary-second text-white absolute cursor-pointer']");
    private By subscriptionSuccessfullyMSG = By.xpath("/html/body/div[3]/div/div[1]/p");
    private By alreadySubscribedErrorMSG = By.xpath("//div[3]/div/div[1]/p");
    private By passengerRulesLINK = By.xpath("//*[contains(@id, 'el-popover-')]/div[2]/a");
    private By passengerRulesHDR = By.xpath("//div[@class='el-dialog__wrapper']//div[1]/span");
    private By originEmptyErrorMSG = By.xpath("//span[contains(text(),'Please choose origin from the list')]");
    private By destinationEmptyErrorMSG = By.xpath("//span[contains(text(),'Please choose destination from the list')]");
    private By departureDateEmptyErrorMSG = By.xpath("//span[contains(text(),'Please enter required date')]");
    private By findMyBookingEmptyEmail1ErrorMSG = By.xpath("//div[@class='el-form-item mb-4 is-error']//span[@class='tooltiptext with-arrow']/span[1]");
    private By findMyBookingEmptyEmail2ErrorMSG = By.xpath("//span/span[contains(text(),'Please enter email')]");
    private By findMyBookingEmptyRef1ErrorMSG = By.xpath("//div[@class='el-form-item is-error']//span[@class='tooltiptext with-arrow']/span[1]");
    private By findMyBookingEmptyRef2ErrorMSG = By.xpath("//span[contains(text(),'Please enter Fly365 Ref.')]");
    private By emptyEmailAtSubscribe = By.xpath("//div[@class='form-group']//span[@class='tooltiptext with-arrow']");
    private By MobileappHeaderlink = By.xpath("//span[@class='pl-1 font-normal text-white']");
    private By AppStoreButtonMobileAppPage = By.xpath("//div[@class='text-black ml-3']//strong[@class='text-base font-medium block'][contains(text(),'App Store')]");
    private By GooglePlayButtonMobileAppPage =By.xpath("//div[@class='text-black ml-3']//strong[@class='text-base font-medium block'][contains(text(),'Google Play')]");
    private By AppStoreButtonHomePage = By.xpath("//strong[contains(text(),'App Store')]");
    private By GooglePlayButtonHomePage = By.xpath("//strong[contains(text(),'Google Play')]");
    //private By AppstoreLink =By.xpath("//h1[@class='product-header__title app-header__title']");
    private By StoreSelection =By.xpath("//html/body/div[1]/div/div[1]/div/div/div[2]/div/div[1]/span");//span[@class='el-dropdown-link capitalize font-normal text-xs text-white flex items-center el-dropdown-selfdefine']
    private By AustraliaStore = By.xpath("//li[contains(text(),'Australia')]");//html/body/ul/li[2]
    private By NewzealandStore=By.xpath("//html/body/ul/li[3]");//li[contains(text(),'New Zealand')]
    private By WorldWideStore = By.xpath("//html/body/ul/li[1]");//span[contains(text(),'Worldwide')]
    private By MalaysiaStore = By.xpath("//html/body/ul/li[4]"); //li[contains(text(),'Malaysia')]
    private By GooglePlayHeader =By.xpath("//span[contains(text(),'Fly365, Everyday Low Fares')]");
    private By AppstoreHeader =By.xpath("//h1[@class='product-header__title app-header__title']");

    @Given("^Navigate to \"(.*)\" Fly365 \"(.*)\" site$")
    public void NavigateToFly365Site(String store, String site) {
        driver.navigate().to("https://" + store + ".fly365" + site + ".com/en");
        //driver.navigate().to("https://flydev:flydev@2019@nz.fly365" + site + ".com/en");
    }

    @And("^Press on 'About us'$")
    public void pressOnAboutUs() {
        driver.findElement(aboutUsLINK).click();
    }

    @And("^Press on first 'Contact Us'$")
    public void pressOnFirstContactUs() {
        driver.findElement(firstContactUsLINK).click();
    }

    @And("^Press on second 'Contact Us'$")
    public void pressOnSecondContactUs() {
        driver.findElement(secondContactUsLINK).click();
    }

    @And("^Press on 'Sign in'$")
    public void pressOnSignIn() {
        driver.findElement(signInLINK).click();
    }

    @And("^Press on 'Sign up'$")
    public void pressOnSignUp() {
        driver.findElement(signUpLINK).click();
    }

    @And("^Press on first 'Support Centre'$")
    public void pressOnFirstSupportCenter() {
        driver.findElement(fristSupportCentreLINK).click();
    }

    @And("^Press on second 'Support Centre'$")
    public void pressOnSecondSupportCenter() {
        driver.findElement(secondSupportCentreLINK).click();
    }

    @And("^Press on 'FAQs'$")
    public void pressOnFAQs() {
        driver.findElement(faqsLINK).click();
    }

    @And("^Press on 'Terms and Conditions'$")
    public void pressOnTermsAndConditions() {
        driver.findElement(termsConditionsLINK).click();
    }

    @And("^Press on 'Privacy policy'$")
    public void pressOnPrivacyPolicy() {
        driver.findElement(privacyPolicyLINK).click();
    }

    @And("^Press on 'afta'$")
    public void pressOnAfta() {
        driver.findElement(aftaLINK).click();
    }

    @And("^Press on Logo in footer$")
    public void pressOnLogoInFooter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoFooterLink));
        driver.findElement(logoFooterLink).click();
    }

    @And("^Press on Logo in header$")
    public void pressOnLogoInHeader() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoHeaderLink));
        driver.findElement(logoHeaderLink).click();
    }

    @And("^Press on flights in header$")
    public void pressOnFlightsInHeader() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(flightsLink));
        driver.findElement(flightsLink).click();
    }

    @And("^Press on offers in header$")
    public void pressOnOffersInHeader() {
        driver.findElement(offersLink).click();
    }

    @And("^Press on Sign In button$")
    public void pressOnSignInButton() {
        driver.findElement(signInBTN).click();
    }

    @Then("^Home page is opened$")
    public void homePageIsOpened() {
        String headerText = driver.findElement(homePageHDR).getText();
        Assert.assertEquals(headerText, "Low Fares");
    }

    @And("^Select One Way trip$")
    public void selectOneWayTrip() {
        driver.findElement(oneWayTAB).click();
    }

    @And("^Select Round Trip trip$")
    public void selectRoundTripTrip() {
        driver.findElement(roundTripTAB).click();
    }

    @And("^Select Multi City trip$")
    public void selectMultiCityTrip() {
        driver.findElement(multiCityTAB).click();
    }

    @And("^Add airport to the Origin \"(.*)\"$")
    public void addAirportToTheOrigin(String originAirport) {
        driver.findElement(originTXT).sendKeys(originAirport);
        wait.until(ExpectedConditions.visibilityOfElementLocated(airportSearchResultOrigin));
        driver.findElement(airportSearchResultOrigin).click();
    }

    @And("^Add airport to the Destination \"(.*)\"$")
    public void addAirportToTheDestination(String destinationAirport) {
        driver.findElement(destinationTXT).sendKeys(destinationAirport);
        wait.until(ExpectedConditions.visibilityOfElementLocated(airportSearchResultDestination));
        driver.findElement(airportSearchResultDestination).click();
    }


    @And("^Select the date of the departure, after \"(.*)\" day from today$")
    public void selectTheDateOfTheDepartureAfterDayFromToday(int period) {
        String departureDate = gmObject.addDateWithCertainPeriodAndFormat(period, "dd MMM yyyy");
        driver.findElement(departureCalenderDPK).sendKeys(departureDate);
    }

    @And("^Select the date of the departure for round trip, after \"(.*)\" day from today$")
    public void selectTheDateOfTheDepartureForRoundTripAfterDayFromToday(int period) {
        driver.findElement(roundTripTAB).click();
        String returnDate = gmObject.addDateWithCertainPeriodAndFormat(period, "dd MMM yyyy");
        driver.findElement(departureRoundCalenderDPK).sendKeys(returnDate);
    }

    @And("^Select the date of the return for round trip, after \"(.*)\" day from today$")
    public void selectTheDateOfTheReturnForRoundTripAfterDayFromToday(int period) throws InterruptedException {
        String returnDate = gmObject.addDateWithCertainPeriodAndFormat(period, "dd MMM yyyy");
        driver.findElement(returnRoundCalenderDPK).sendKeys(returnDate);
        driver.findElement(roundTripTAB).click();
        Thread.sleep(500);
    }

    @And("^Press on Search Now$")
    public void pressOnSearchNow() {
        driver.findElement(searchNowBTN).click();
    }


    @And("^Book a \"(.*)\" trip from API for \"(.*)\" and get \"(.*)\"$")
    public void bookATripFromAPIForAndGet(String tripType , String domain, String reference) {
        //String requestUrl = "https://api.fly365" + domain + ".com/flight-search/search";
        String requestUrl = "https://nz.fly365" + domain + ".com/api/flight-search/search";
        String allAvailableTrips = null;
        if(tripType.contains("multi")){
            allAvailableTrips = apiObject.sendPostRequest(requestUrl, apiObject.multiCityAPI());
        }
        else if(tripType.contains("round")){
            allAvailableTrips = apiObject.sendPostRequest(requestUrl, apiObject.roundTripAPI());
        }
        else if(tripType.contains("one")){
            allAvailableTrips = apiObject.sendPostRequest(requestUrl, apiObject.oneWayAPI());
        }
        String itinaryID = apiObject.getItineraryId(allAvailableTrips, 1);
        String cardID = apiObject.createCart(itinaryID, domain);
        apiObject.addPassenger(cardID, domain);
        if (reference.equals("order")) {
            orderNumber = apiObject.checkoutTrip(cardID, domain)[0];
        }
        if (reference.equals("Fly365 Reference")) {
            pnrNumberCheckoutResponse = apiObject.checkoutTrip(cardID, domain)[1];
        }
    }


    @And("^Click on Find My Booking$")
    public void clickOnFindMyBooking() {
        driver.findElement(findMyBookingLINK).click();
    }

    @And("^Add a valid email address \"(.*)\"$")
    public void addAValidEmailAddress(String findMyBookingEmailAddress) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findMyBookingEmailTXT));
        driver.findElement(findMyBookingEmailTXT).sendKeys(findMyBookingEmailAddress);
    }

    @And("^Add a valid \"(.*)\"$")
    public void addAValid(String reference) {
        driver.findElement(findMyBookingAirlineFly365OrderTXT).sendKeys(orderNumber);
        // if (reference.equals("order")) {
        //     driver.findElement(findMyBookingAirlineFly365OrderTXT).sendKeys(orderNumber);
        // }
        // if (reference.equals("order")) {
        //     driver.findElement(findMyBookingAirlineFly365OrderTXT).sendKeys(orderNumber);
        // }
    }

    @And("^Add a valid Reference 'Fly365 Ref'$")
    public void addAValidReferenceFly365Ref()  {
        driver.findElement(findMyBookingAirlineFly365OrderTXT).sendKeys(ctobject.fly356Refernce);

    }

    @And("^Press Find Booking$")
    public void pressFindBooking() {
        driver.findElement(findMyBookingFindBookingBTN).click();
    }


    @And("^Select Passengers: \"(.*)\" adult, \"(.*)\" child, \"(.*)\" infant$")
    public void selectPassengersAdultChildInfant(int adultCount, int childCount, int infantCount) {
        driver.findElement(passengerCabinTXT).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(passengerCabinPOPOVER));

        for (int counter = 0; counter < (adultCount - 1); counter++) {
            driver.findElement(plusAdultBTN).click();
        }

        for (int counter = 0; counter < childCount; counter++) {
            driver.findElement(plusChildBTN).click();
        }

        for (int counter = 0; counter < infantCount; counter++) {
            driver.findElement(plusInfantBTN).click();
        }

    }

    @And("^Select \"(.*)\" Class$")
    public void selectClass(String cabinClass) {

        driver.findElement(By.xpath("//span[text()='" + cabinClass + "']/preceding-sibling::span")).click();
    }


    @And("^Choose the number of flights \"(.*)\"$")
    public void chooseTheNumberOfFlights(int flightcount) {
        for (int counter = 2; counter < flightcount; counter++) {
            driver.findElement(addMoreCitiesLINK).click();
        }
    }

    @And("^Add the following origin, destinations and date periods$")
    public void addTheFollowingOriginDestinationsAndDatePeriods(DataTable multiCityData) throws InterruptedException {
        int i = 1;

        for (Map<String, String> flightDetails : multiCityData.asMaps(String.class, String.class)) {

            driver.findElement(By.xpath("//div[@class='search-form relative row justify-center']//form[" + i + "]//input[@name='origin']")).sendKeys(flightDetails.get("Origin"));
            gmObject.selectFromAutoCompleteDDL(flightDetails.get("Origin"));
            driver.findElement(By.xpath("//div[@class='search-form relative row justify-center']//form[" + i + "]//input[@name='destination']")).sendKeys(flightDetails.get("Destination"));
            gmObject.selectFromAutoCompleteDDL(flightDetails.get("Destination"));
            String returnDate = gmObject.addDateWithCertainPeriodAndFormat(Integer.parseInt(flightDetails.get("Date Period")), "dd MMM yyyy");
            driver.findElement(By.xpath("//div[@class='search-form relative row justify-center']//form[" + i + "]//input[@name='fromDate']")).sendKeys(returnDate);
            i++;
        }
    }


    // Subscription Email

    @And("^Add the email address \"(.*)\" to Subscription Email field$")
    public void addTheEmailAddressToSubscriptionEmailField(String emailAddress) {
        DataBase.execute_query_dbs("k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432", "user_api", "Select * from newsletter_users where email = '" + emailAddress + "'");
        if (DataBase.data != null) {
            DataBase.execute_query_dbs("k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432", "user_api", "delete from newsletter_users where email='" + emailAddress + "'");
        }
        driver.findElement(subscriptionTXT).sendKeys(emailAddress);

    }


    @When("^Press on SUBSCRIBE$")
    public void pressOnSUBSCRIBE() throws InterruptedException {
        driver.findElement(subscribeBTN).click();
        Thread.sleep(2000);

    }

    @Then("^Successfully validation message is displayed$")
    public void successfullyValidationMessageIsDisplayed() {
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionSuccessfullyMSG)).getText(), "You've subscribed successfully. Tune in for our updates and special offers");
    }


    @And("^Add previously subscribed email address \"(.*)\" to Subscription Email field$")
    public void addPreviouslySubscribedEmailAddressToSubscriptionEmailField(String emailAddress) throws Throwable {
        DataBase.execute_query_dbs("k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432", "user_api", "Select * from newsletter_users where email = '" + emailAddress +"'");
        if (DataBase.data == null) {
            DataBase.execute_query_dbs("k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432", "user_api", "insert into newsletter_users (email, \"isSubscribed\",\"storeId\",\"groupId\", \"isRegistered\")values('" + emailAddress + "',True,'fly365_nz','fly365',False)");
        }
        driver.findElement(subscriptionTXT).sendKeys(emailAddress);
    }

    @Then("^Error validation message is displayed$")
    public void errorValidationMessageIsDisplayed() {
        Assert.assertEquals(driver.findElement(alreadySubscribedErrorMSG).getText(),"You have already subscribed fly365");

    }

    @And("^Press on Passenger/Cabin pop over$")
    public void pressOnPassengerCabinPopOver() {
        driver.findElement(passengerCabinTXT).click();
    }

    @And("^Press on Passenger Rules link$")
    public void pressOnPassengerRulesLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passengerRulesLINK));
        driver.findElement(passengerRulesLINK).click();
    }

    @Then("^'Passenger Rules' pop up will be opened$")
    public void passengerRulesPopUpWillBeOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passengerRulesHDR));
        Assert.assertEquals(driver.findElement(passengerRulesHDR).getText(),"Passenger Rules");

    }


    @Then("^error message appear for each field$")
    public void errorMessageAppearForEachField() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(originEmptyErrorMSG));
        String originerrormessage = driver.findElement(originEmptyErrorMSG).getText();
        Assert.assertEquals(originerrormessage, "Please choose origin from the list");

        String destinationerrormessage = driver.findElement(destinationEmptyErrorMSG).getText();
        Assert.assertEquals(destinationerrormessage, "Please choose destination from the list");

        String departureerrormessage = driver.findElement(departureDateEmptyErrorMSG).getText();
        Assert.assertEquals(departureerrormessage, "Please enter required date");
    }

    @Then("^error message appear appear over the two fields$")
    public void errorMessageAppearAppearOverTheTwoFields() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findMyBookingEmptyEmail2ErrorMSG));
        String emailfinderrormessage2 = driver.findElement(findMyBookingEmptyEmail2ErrorMSG).getText();
        String referrormessage2 = driver.findElement(findMyBookingEmptyRef2ErrorMSG).getText();
        Assert.assertEquals(emailfinderrormessage2, "Please enter email");
        Assert.assertEquals(referrormessage2, "Please enter Fly365 Ref.");
    }

    @Then("^empty subscribe error message appear$")
    public void emptySubscribeErrorMessageAppear() {
        String emptysubscriber = driver.findElement(emptyEmailAtSubscribe).getText();
        Assert.assertEquals(emptysubscriber, "!Please enter a valid email");
    }
    @Then("^Google Play page opened correctly$")
    public void googlePlayPageOpenedCorrectly() throws InterruptedException {
        Thread.sleep(5000);

        String OldTab = driver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(OldTab);
        driver.switchTo().window(newTab.get(0));
        String StoreHeader =driver.findElement(GooglePlayHeader).getText();
        Assert.assertEquals(StoreHeader,"Fly365, Everyday Low Fares");
    }
    @Then("^App Store page opened correctly$")
    public void appStorePageOpenedCorrectly() throws InterruptedException {
        Thread.sleep(5000);

        String OldTab = driver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(OldTab);
        driver.switchTo().window(newTab.get(0));
        String StoreHeader =driver.findElement(AppstoreHeader).getText();
        Assert.assertEquals(StoreHeader,"Fly365, Everyday Low Fares 4+");

    }

    @And("^Press on Mobile app$")
    public void pressOnMobileApp() {
        driver.findElement(MobileappHeaderlink).click();
    }

    @Then("^Mobile app Page will be opened link on \"([^\"]*)\" and \"([^\"]*)\"$")
    public void mobileAppPageWillBeOpenedLinkOnAnd(String store, String site) throws Throwable {
        String MobilePageURL = driver.getCurrentUrl();
        Assert.assertEquals(MobilePageURL,"https://" + store.toLowerCase() + ".fly365" + site.toLowerCase() + ".com/en/mobile-app");

    }

    @And("^Press on Available on App store button$")
    public void pressOnAvailableOnAppStoreButton() {
        driver.findElement(AppStoreButtonMobileAppPage).click();
    }

    @And("^Press On Get it on Google Play$")
    public void pressOnGetItOnGooglePlay() {
        driver.findElement(GooglePlayButtonMobileAppPage).click();
    }

    @And("^Press on Available on App store button on Home Page$")
    public void pressOnAvailableOnAppStoreButtonOnHomePage() {
        driver.findElement(AppStoreButtonHomePage).click();
    }

    @And("^Press on Get it on Google Play button on Home Page$")
    public void pressOnGetItOnGooglePlayButtonOnHomePage() {
        driver.findElement(GooglePlayButtonHomePage).click();
    }


    @And("^Select Worldwide from store list and Validate URL on \"([^\"]*)\"$")
    public void selectWorldwideFromStoreListAndValidateURLOn(String site) throws Throwable {
        driver.findElement(StoreSelection).click();
        driver.findElement(WorldWideStore).click();
        String HomeURL = driver.getCurrentUrl();
        Assert.assertEquals(HomeURL,"https://" +"www"+".fly365" + site.toLowerCase() + ".com/en");

    }


    @Then("^Validate URL after changing  to New Zealand store on \"([^\"]*)\"$")
    public void validateURLAfterChangingToNewZealandStoreOn(String site) throws Throwable {
        String HomeURL = driver.getCurrentUrl();
        Assert.assertEquals(HomeURL,"https://" +"nz"+ ".fly365" + site.toLowerCase() + ".com/en");
    }

    @And("^Select Worldwide from store$")
    public void selectWorldwideFromStore() {
        driver.findElement(StoreSelection).click();
        driver.findElement(WorldWideStore).click();
    }


    @Then("^Url shall be changed to the correct Worldwide store URL on \"([^\"]*)\"$")
    public void urlShallBeChangedToTheCorrectWorldwideStoreURLOn(String site) throws Throwable {
        String HomeURL = driver.getCurrentUrl();
        Assert.assertEquals(HomeURL,"https://" +"www"+".fly365" + site.toLowerCase() + ".com/en");
    }

    @And("^Select Malaysia from store list$")
    public void selectMalaysiaFromStoreList() throws InterruptedException {
        driver.findElement(StoreSelection).click();
        Thread.sleep(2000);
        driver.findElement(MalaysiaStore).click();
        Thread.sleep(2000);
    }

    @Then("^Url shall be changed to the correct Malaysia store URL on \"([^\"]*)\"$")
    public void urlShallBeChangedToTheCorrectMalaysiaStoreURLOn(String site) throws Throwable {
        Thread.sleep(2000);
        String HomeURL = driver.getCurrentUrl();
        Thread.sleep(2000);
        Assert.assertEquals(HomeURL,"https://" +"my"+ ".fly365" + site.toLowerCase() + ".com/en");
    }

    @And("^Select Australia from store list$")
    public void selectAustraliaFromStoreList() {
        driver.findElement(StoreSelection).click();
        driver.findElement(AustraliaStore).click();
    }

    @Then("^Url shall be changed to the correct Australia store on \"([^\"]*)\"$")
    public void urlShallBeChangedToTheCorrectAustraliaStoreOn(String site) throws Throwable {
        String HomeURL = driver.getCurrentUrl();
        Assert.assertEquals(HomeURL,"https://" + "au" + ".fly365" + site.toLowerCase() + ".com/en");
    }


}

