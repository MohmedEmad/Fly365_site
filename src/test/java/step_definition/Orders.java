package step_definition;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.openqa.selenium.support.ui.Select;
import step_definition.FlightAndHubAPIs.BookingCycleAPI;

public class Orders extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 30);
    private By airLineref = By.xpath("//div[@class='d-flex text-center flex-column']//h3");
    private By flyref = By.xpath("//span[@class='d-block order-link']");
    private By storeUserid = By.xpath("//small[text()='Store User']/following-sibling::strong");
    private By paymentGatwayid = By.xpath("//small[text()='Payment Gateway']/following-sibling::strong");
    private By displayedPrice = By.xpath("//small[text()='Displayed Price']/following-sibling::strong");
    private By discountCampaign = By.xpath("//small[text()='Campaign']/following-sibling::strong");
    private By discountAmount = By.xpath("//small[text()='Amount']/following-sibling::strong");
    private By editIcon = By.xpath("//span[@class='icon-edit pull-right']");
    private By firstName = By.xpath("//input[@placeholder='First Name']");
    private By lastName = By.xpath("//input[@placeholder='Last Name']");
    private By phoneNumber = By.xpath("//input[@placeholder='Enter a phone number']");
    private By email = By.xpath("//input[@placeholder='Email']");
    private By save = By.xpath("//button[@class='btn btn-lg btn-primary btn-block']");
    private By fullName = By.xpath("//span[@class='information-label'][contains(text(),'Mr')]");
    private By Email = By.xpath("//span[@class='information-label'][contains(text(),'.com')]");
    private By mobileNumber = By.xpath("//span[@class='information-label'][contains(text(),'+1')]");
    private By title = By.xpath("//select[@name='title']");

    BookingCycleAPI bookingApiObj = new BookingCycleAPI();

    @Then("^Assert that Airline reference is correct$")
    public void assertThatAirlineReferenceIsCorrect() throws InterruptedException {
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(airLineref).getText().trim(),bookingApiObj.airLineRef);

    }

    @Then("^Assert that Fly reference is correct$")
    public void assertThatFlyReferenceIsCorrect() throws InterruptedException {
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(flyref).getText().trim(), bookingApiObj.pnrNumberCheckoutResponse);
    }

    @Then("^Assert that store user is correct$")
    public void assertThatStoreUserIsCorrect() throws InterruptedException {
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(storeUserid).getText(),bookingApiObj.storeUser);

    }

    @Then("^Assert that Payment Gateway is correct$")
    public void assertThatPaymentGatewayIsCorrect() throws InterruptedException {
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(paymentGatwayid).getText(),bookingApiObj.paymentGateway);
    }

    @Then("^Assert that total price is correct$")
    public void assertThatTotalPriceIsCorrect() throws InterruptedException {
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(displayedPrice).getText().replaceAll("0*\\D*$",""),bookingApiObj.totalPrice);
    }

    @Then("^Assert that Discount campaign name is correct$")
    public void assertThatDiscountCampaignNameIsCorrect() throws InterruptedException {
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(discountCampaign).getText(),bookingApiObj.discountName);
    }


    @And("^click on edit passenger details$")
    public void clickOnEditPassengerDetails() {
        driver.findElement(editIcon).click();
    }

    @And("^Change passenger name \"([^\"]*)\" and \"([^\"]*)\" email \"([^\"]*)\" and phone number \"([^\"]*)\" and data edited successfully$")
    public void changePassengerNameAndEmailAndPhoneNumber(String fName, String lName, String useremail, String number) throws Throwable {

        WebElement title_dropdown=driver.findElement(title);
        Select Title =new Select(title_dropdown);
        Title.selectByValue("Mr");
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(email).sendKeys(useremail);
        driver.findElement(phoneNumber).sendKeys(number);
        driver.findElement(save).click();
        Thread.sleep(4000);
        Assert.assertEquals(driver.findElement(fullName).getText(),  "Mr"+ fName+lName );
        Assert.assertEquals(driver.findElement(Email).getText(), useremail);
        Assert.assertEquals(driver.findElement(mobileNumber).getText(), "+1 "+number);
        throw new PendingException();
    }


}
