package step_definition;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.GeneralMethods;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.util.Map;

public class PassengersDetailsTest extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 40);

    private GeneralMethods gmObject = new GeneralMethods();


    private By contactTitleField = By.id("contact-fullname");
    private By contactFirstNameTXT = By.xpath("//div[@class='el-form-item col-6']//input[@placeholder='First Name']");
    private By contactLastNameTXT = By.xpath("//div[@class='el-form-item col-6']//input[@placeholder='Family Name']");
    private By contactEmailAddressTXT = By.id("contact-email");
    private By phoneNumbereTXT = By.xpath("//*[@id='contact-details']/div[4]/div[2]/div/div/div[1]/div/input");
    private By nextStepBTN = By.xpath("//button[@class='alert-login-btn btn btn-primary-second font-bold uppercase h-10 w-64 ml-auto']");
    private By firstReadMoreLINK = By.xpath("//p/a[1]");
    private By secondReadMoreLINK = By.xpath("//p/a[2]");
    private By flightDetailsLink = By.xpath("//li/div/div/div[1]/div[3]");
    private By firstReadMoreHDR = By.xpath("//div[@class='el-dialog__wrapper'][1]/div[1]/div[1]/span");
    private By secondReadMoreHDR = By.xpath("//div[@class='el-dialog__wrapper'][2]/div[1]/div[1]/span");
    private By totalTravelTimeHDR = By.xpath("//div[3]//div[2]/div/div/div[1]/div/div[1]");
    private By passengerTitleEmptyErrorMSG = By.xpath("//span[contains(text(),'Please enter title')]");
    private By passenegerFirstNameEmptyErrorMSG = By.xpath("//span[contains(text(),'Please enter first name')]");
    private By passenegerLastNameEmptyErrorMSG = By.xpath("//span[contains(text(),'Please enter family name')]");
    private By PassengerBirthDateEmptyErrorMSG = By.xpath("//div[@id='passenger-form-0']//div//div[@class='row birth-date-form w-full']//span[@class='tooltiptext with-arrow']");
    private By ContactDetialsTitleEmptyErroeMSG = By.xpath("//div[@class='el-form-item col-3 is-error']//span[contains(text(),'Please enter title')]");
    private By ContactdetailsFirstNameEmptyErrorMSG = By.xpath("//div[@class='el-form-item col-6 is-error']//span[contains(text(),'Please enter first name')]");
    private By ContactDetailsLastNameEmptyErrorMSG = By.xpath("//div[@class='el-form-item col-6 is-error']//span[contains(text(),'Please enter family name')]");
    private By ContactDetailsEmailEmptyErrorMSg = By.xpath("//span[contains(text(),'Please enter a valid Email')]");
    private By ContactDetailsPhoneEmptyErrorMSG = By.xpath("//span[contains(text(),'Please enter Phone Number')]");
    private By holdHoursMSG = By.xpath("//small[@class='pl-2 mt-2 text-primary-fourth']");
    private By passportSection = By.xpath("//span[contains(text(), 'Passport Details (optional)')]");
    private By passportNumber = By.xpath("//input[@id='id-number-0']");
    private By passportExpiryDay = By.xpath("//div[@name='idExpiryDate']//input[@placeholder='Day']");
    private By passportExpiryMonth = By.xpath("//div[@name='idExpiryDate']//input[@placeholder='Month']");
    private By passportExpiryYear = By.xpath("//div[@name='idExpiryDate']//input[@placeholder='Year']");
    private By passportCountry = By.xpath("//div[@name='country']//input[@placeholder='Select a Country']");
    private By frequentFlyerSection= By.xpath("//span[contains(text(), 'Frequent Flyer (optional)')]");
    private By frequentFlyerNumber= By.xpath("//div[@class='el-form-item__content']//input[@placeholder='Number']");
    private By serviceSection= By.xpath("//span[contains(text(), 'Service Requests (optional)')]");
    private By seat = By.xpath("//div[@class='el-input el-input--suffix']//input[@placeholder='Select Seat']");
    private By meal = By.xpath("//div[@class='el-input el-input--suffix']//input[@placeholder='Select Meal']");
    private By specialAssist = By.xpath("//div[@class='el-input el-input--suffix']//input[@placeholder='Select Special Assistance']");
    private By specialRequestSection = By.xpath("//span[@class='mr-2']");
    private By specialRequestField= By.xpath("//textarea[@class='el-textarea__inner']");
    private By editTraveler = By.xpath("//i[@class='icon-edit']");
    private By specialRequestData = By.xpath("//div[@class='item']");
    private By specialRequestinHub = By.xpath("//span[contains(text(),'Special request')]");
    private By seatinHub = By.xpath("//*[@id='editTraveller___BV_modal_body_']/div/form/section[4]/div[1]/div/div/input");
    private By mealinHub = By.xpath("//*[@id='editTraveller___BV_modal_body_']/div/form/section[4]/div[2]/div/div/input");
    private By specialAssisstinHub = By.xpath("//*[@id='editTraveller___BV_modal_body_']/div/form/section[4]/div[3]/div/div[1]/input");
    private By passengerComponent = By.xpath("//div[@id='passenger-form-0']//div//div[@class='bg-white px-12 pt-10 pb-5']");







    @And("^Add the following data in the passenger Details$")
    public void addTheFollowingDataInThePassengerDetails(DataTable passengerData) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nextStepBTN));
        int i = 0;

        for (Map<String, String> passengerDetails : passengerData.asMaps(String.class, String.class)) {

            gmObject.selectFromDDL(By.id("title-" + i + ""), passengerDetails.get("Title"));
            driver.findElement(By.id("passenger-firstname-" + i + "")).sendKeys(passengerDetails.get("First Name"));
            driver.findElement(By.id("middlename-" + i + "")).sendKeys(passengerDetails.get("Middle Name"));
            driver.findElement(By.id("lastName-" + i + "")).sendKeys(passengerDetails.get("Last Name"));
            gmObject.selectFromDDL(By.xpath("//*[@id='birthdate-" + i + "']//input[@placeholder='Day']"), passengerDetails.get("Day"));
            gmObject.selectFromDDL(By.xpath("//*[@id='birthdate-" + i + "']//input[@placeholder='Month']"), passengerDetails.get("Month"));
            gmObject.selectFromDDL(By.xpath("//*[@id='birthdate-" + i + "']//input[@placeholder='Year']"), passengerDetails.get("Year"));
            i++;
        }


    }

    @And("^Add the following data in the Contact Details$")
    public void addTheFollowingDataInTheContactDetails(DataTable contactData) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactTitleField));

        for (Map<String, String> contactDetails : contactData.asMaps(String.class, String.class)) {

            gmObject.selectFromDDL(contactTitleField, contactDetails.get("Title"));
            driver.findElement(contactFirstNameTXT).sendKeys(contactDetails.get("First Name"));
            driver.findElement(contactLastNameTXT).sendKeys(contactDetails.get("Last Name"));
            driver.findElement(contactEmailAddressTXT).sendKeys(contactDetails.get("Email"));
            driver.findElement(phoneNumbereTXT).sendKeys(contactDetails.get("Phone Number"));
        }
    }

    @And("^Click on Next Step$")
    public void clickOnNextStep()  {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passengerComponent));
        driver.findElement(nextStepBTN).click();
    }


    @Then("^'Passenger' page will be opened$")
    public void passengerPageWillBeOpened() throws InterruptedException {
        Thread.sleep(1500);
        Assert.assertEquals(driver.getTitle(), "Fly365 - passengers");
    }

    @And("^Press on first 'Read more'$")
    public void pressOnFirstReadMore() {
        driver.findElement(firstReadMoreLINK).click();
    }

    @And("^Press on second 'Read more'$")
    public void pressOnSecondReadMore() {
        driver.findElement(secondReadMoreLINK).click();
    }

    @And("^Press on 'Flights Details'$")
    public void pressOnFlightsDetails() {
        driver.findElement(flightDetailsLink).click();
    }

    @Then("^'If you have one name' pop up will be opened$")
    public void ifYouHaveOneNamePopUpWillBeOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstReadMoreHDR));
        Assert.assertEquals("If you have one name", driver.findElement(firstReadMoreHDR).getText());
    }

    @Then("^'Correct name format' pop up will be opened$")
    public void correctNameFormatPopUpWillBeOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondReadMoreHDR));
        Assert.assertEquals("Correct name format", driver.findElement(secondReadMoreHDR).getText());
    }

    @Then("^'Flights Details' is displayed$")
    public void flightsDetailsIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalTravelTimeHDR));
        Assert.assertTrue(driver.findElement(totalTravelTimeHDR).isDisplayed());
    }

    @Then("^error message appear for each field at fill passenger details$")
    public void errorMessageAppearForEachFieldAtFillPassengerDetails() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passengerTitleEmptyErrorMSG));
        String passnegerEmptyTitle = driver.findElement(passengerTitleEmptyErrorMSG).getText();
        Assert.assertEquals(passnegerEmptyTitle, "Please enter title");

        String passenegeremptyfirstname = driver.findElement(passenegerFirstNameEmptyErrorMSG).getText();
        Assert.assertEquals(passenegeremptyfirstname, "Please enter first name");

        String passenegeremptylastname = driver.findElement(passenegerLastNameEmptyErrorMSG).getText();
        Assert.assertEquals(passenegeremptylastname, "Please enter family name");

        String passenegeremptybirthdate = driver.findElement(PassengerBirthDateEmptyErrorMSG).getText();
        Assert.assertEquals(passenegeremptybirthdate, "!Please enter birth date");

        String ContactEmptyTitle = driver.findElement(ContactDetialsTitleEmptyErroeMSG).getText();
        Assert.assertEquals(ContactEmptyTitle, "Please enter title");

        String ContactemptyFirstname = driver.findElement(ContactdetailsFirstNameEmptyErrorMSG).getText();
        Assert.assertEquals(ContactemptyFirstname, "Please enter first name");

        String ContactemptyLastName = driver.findElement(ContactDetailsLastNameEmptyErrorMSG).getText();
        Assert.assertEquals(ContactemptyLastName, "Please enter family name");

        String Contactemptyemail = driver.findElement(ContactDetailsEmailEmptyErrorMSg).getText();
        Assert.assertEquals(Contactemptyemail, "Please enter a valid Email");

        String ContactemptyPhone = driver.findElement(ContactDetailsPhoneEmptyErrorMSG).getText();
        Assert.assertEquals(ContactemptyPhone, "Please enter Phone Number");

    }

    @Then("^Hold hours is displayed with this value \"([^\"]*)\" correctly in passenger details$")
    public void holdHoursIsDisplayedWithThisValueCorrectlyInPassengerDetails(String holdHours) throws Throwable {
        wait.until(ExpectedConditions.visibilityOfElementLocated(holdHoursMSG));
        WebElement holdHoursmsgElmnt = driver.findElement(holdHoursMSG);
        Assert.assertEquals(holdHoursmsgElmnt.getText() , "(Valid for "+holdHours+" hours )");

    }

    @And("^click on Passport details section$")
    public void clickOnPassportDetailsSection() {
        driver.findElement(passportSection).click();
    }

    @And("^Add passport number$")
    public void addPassportNumber() {
        driver.findElement(passportNumber).sendKeys("A1234567890");
    }

    @And("^Add passport expiry date$")
    public void addPassportExpiryDate() throws InterruptedException {
        gmObject.selectFromDDL(passportExpiryDay,"5");
        gmObject.selectFromDDL(passportExpiryMonth,"September");
        gmObject.selectFromDDL(passportExpiryYear, "2026");

    }

    @And("^Select passport country$")
    public void selectPassportCountry() throws InterruptedException {
        gmObject.selectFromDDL(passportCountry, "Egypt");

    }

    @And("^click on frequent flyer section$")
    public void clickOnFrequentFlyerSection() {
        driver.findElement(frequentFlyerSection).click();
    }

    @And("^Add frequent flyer number$")
    public void addFrequentFlyerNumber() {
        driver.findElement(frequentFlyerNumber).sendKeys("123456");
    }


    @And("^Click on Service Request section$")
    public void clickOnServiceRequestSection() {
        driver.findElement(serviceSection).click();
    }

    @And("^User select preferences$")
    public void userSelectPreferencesAndAnd() throws InterruptedException {
        gmObject.selectFromDDL(seat, "Aisle Seat Request");
        gmObject.selectFromDDL(meal, "SEA FOOD MEAL");
        gmObject.selectFromDDL(specialAssist, "WHEELCHAIR - CANNOT CLIMB STAIRS");

    }

    @And("^Click on Edit Traveler Icon$")
    public void clickOnEditTravelerIcon() {
        driver.findElement(editTraveler).click();
    }


    @And("^Assert that seat is \"([^\"]*)\" and meal \"([^\"]*)\" and assistance is \"([^\"]*)\"$")
    public void assertThatSeatIsAndMealAndAssistanceIs(String seat, String meal, String assist) throws Throwable {
        Thread.sleep(4000);
        String seat1= driver.findElement(seatinHub).getAttribute("value");
        Assert.assertEquals(seat1,seat);
        String meal1 = driver.findElement(mealinHub).getAttribute("value");
        Assert.assertEquals(meal1, meal);
        String assist1 = driver.findElement(specialAssisstinHub).getAttribute("value");
        Assert.assertEquals(assist1, assist);

        throw new PendingException();
    }

    @And("^Click on Write request$")
    public void clickOnWriteRequest() {
        driver.findElement(specialRequestSection).click();
    }

    @And("^Write your request \"([^\"]*)\"$")
    public void writeYourRequest(String request) throws Throwable {
        driver.findElement(specialRequestField).sendKeys(request);
        throw new PendingException();
    }

    @And("^Assert that special request is having \"([^\"]*)\"$")
    public void assertThatSpecialRequestIsHaving(String request) throws Throwable {
        driver.findElement(specialRequestinHub);
       driver.findElement(specialRequestData).getText();
       Assert.assertEquals(request, "Testing Request");

    }

}
