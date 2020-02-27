package step_definition;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ConfirmationTest extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 20);
    public static String fly356Refernce;


    By bookingConfirmationSuccessfulMSG = By.xpath("//div[@class='mb-5 text-xl font-bold']");
    By fly365ReferenceHDR = By.xpath("//span[text()='Fly365 Ref.:']/following-sibling::strong");
    By totalPriceAfterConfirmationVAL = By.xpath("//p[contains(text(),'Total')]/following-sibling::p");
    By passengerDetailsSection = By.xpath("//h2[@class='text-sm py-3 pl-4 border-b-2 border-secondary-fifth text-primary-third font-medium bg-secondary-fourth bg-secondary-fourth']");

    @Then("^'Thank you for booking with Fly365' message is displayed$")
    public void thankYouForBookingWithFlyMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookingConfirmationSuccessfulMSG));
        Assert.assertTrue(driver.findElement(bookingConfirmationSuccessfulMSG).isDisplayed());
    }


    @And("^Get the 'Fly365 Ref' code$")
    public void getTheFlyRefCode() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fly365ReferenceHDR));
        fly356Refernce = driver.findElement(fly365ReferenceHDR).getText();
    }


    @Then("^The total fare is the same before and after the booking$")
    public void theTotalFareIsTheSameBeforeAndAfterTheBooking() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalPriceAfterConfirmationVAL));
        String totalPriceAfterConfirmation = driver.findElement(totalPriceAfterConfirmationVAL).getText().trim();
        Assert.assertEquals(totalPriceAfterConfirmation,SearchResultTest.tripPrice);
    }

    @And("^Assert that passport \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" are displayed in confirmation page$")
    public void assertThatPassportAndAndAreDisplayedInConfirmationPage(String number, String expiry, String country) throws Throwable {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passengerDetailsSection));
        String passportNumber = driver.findElement(By.xpath("//div[contains(text(),'Passport Number')]/following-sibling::div")).getText();
        Assert.assertEquals(passportNumber,number);
        String passportExpiry = driver.findElement(By.xpath("//div[contains(text(),'Passport Expiry Date')]/following-sibling::div")).getText();
        Assert.assertEquals(passportExpiry, expiry);
        String passportCountry = driver.findElement(By.xpath("//div[contains(text(),'Passport Country')]/following-sibling::div")).getText();
        Assert.assertEquals(passportCountry, country);


        throw new PendingException();
    }

    @And("^Assert that frequent flyer \"([^\"]*)\" is displayed in confirmation page$")
    public void assertThatFrequentFlyerIsDisplayedInConfrimationPage(String flyernumber) throws Throwable {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passengerDetailsSection));
        String flyerNumber = driver.findElement(By.xpath("//div[contains(text(),'Frequent flyer airline number')]/following-sibling::div")).getText();
        Assert.assertEquals(flyerNumber, flyernumber);

        throw new PendingException();
    }
  
    @Then("^The total hold price is the same before and after the booking$")
    public void theTotalHoldPriceIsTheSameBeforeAndAfterTheBooking() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalPriceAfterConfirmationVAL));
        String totalPriceAfterConfirmation = driver.findElement(totalPriceAfterConfirmationVAL).getText().trim();
        Assert.assertEquals(totalPriceAfterConfirmation,SearchResultTest.holdPrice.replace("HOLD FOR ",""));
    }
}
