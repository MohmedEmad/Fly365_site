package step_definition;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Map;

public class PaymentTest extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 10);


    private By cardHolderNameTXT = By.id("card-holder-name");
    private By cardNumberTXT = By.id("card-number");
    private By cardExpireDateTXT = By.id("card-expiry-date");
    private By cardCVVTXT = By.id("card-cvv");
    private By addressLine1TXT = By.xpath("//input[@placeholder='Address Line 1']");
    private By addressLine2TXT = By.xpath("//input[@placeholder='Address Line 2']");
    private By stateTXT = By.xpath("//input[@placeholder='State']");
    private By zipTXT = By.xpath("//input[@placeholder='Zip Code']");
    private By acknowledgeBOX = By.xpath("//form/div[1]//span[@class='el-checkbox__inner']");
    private By fareRulesBOX = By.xpath("//form/div[2]//span[@class='el-checkbox__inner']");
    private By payNowBTN = By.xpath("//button[contains(text(),'PAY')]");
    private By editPassengerLINK = By.xpath("//div[4]/div/div/div[1]/div");
    private By fareRulesLINK = By.xpath("//p/a[1]");
    private By termsAndConditionsLINK = By.xpath("//p/a[2]");
    private By backLINK = By.xpath("//div[6]/div");

    private By HolderEmptyErrorMSg = By.xpath("//*[@id='pane-cc']//form/div[2]/div[1]/div[2]//div[2]/span");
    private By CardEmptyErrorMSG = By.xpath("//*[@id='pane-cc']//form/div[2]/div[2]/div[2]//div[2]/span");
    private By ExpiryEmptyErrorMSG = By.xpath("//*[@id='pane-cc']//form/div[2]/div[3]/div[2]//div[2]/span");
    private By CVVEmptyErrorMSG = By.xpath("//*[@id='pane-cc']//form/div[2]/div[3]/div[3]//div[2]/span");
    private By Yes1EmptyErrorMSG = By.xpath("//div[2]//div[1]//form/div[1]/div/div/span");
    private By Yes2EmptyErrorMSG = By.xpath("//div[2]//div[1]//form/div[2]/div/div/span");

    @And("^Add a valid data for the credit card$")
    public void addAValidDataForTheCreditCard(DataTable paymentCardData) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(cardHolderNameTXT));

        for (Map<String, String> paymentCardDetails : paymentCardData.asMaps(String.class, String.class)) {
            driver.findElement(cardHolderNameTXT).sendKeys(paymentCardDetails.get("Card Holder Number"));
            driver.findElement(cardNumberTXT).sendKeys(paymentCardDetails.get("Card Number"));
            driver.findElement(cardExpireDateTXT).sendKeys(paymentCardDetails.get("Card Expire Date"));
            driver.findElement(cardCVVTXT).sendKeys(paymentCardDetails.get("Card CVV"));
        }


    }

    @And("^Add a valid data for the Billing Address$")
    public void addAValidDataForTheBillingAddress(DataTable billingAddressData) {

        for (Map<String, String> billingAddressDetails : billingAddressData.asMaps(String.class, String.class)) {
            driver.findElement(addressLine1TXT).sendKeys(billingAddressDetails.get("Addres Line 1"));
            driver.findElement(addressLine2TXT).sendKeys(billingAddressDetails.get("Addres Line 2"));
            driver.findElement(stateTXT).sendKeys(billingAddressDetails.get("State"));
            driver.findElement(zipTXT).sendKeys(billingAddressDetails.get("Zip Code"));
        }
    }

    @And("^Select the Fare Rules and Terms and Conditions$")
    public void selectTheFareRulesAndTermsAndConditions() {
        driver.findElement(fareRulesBOX).click();

    }

    @And("^Press on Pay button$")
    public void pressOnPayButton() throws InterruptedException {
        driver.findElement(payNowBTN).click();
        Thread.sleep(8000);
    }

    @And("^Select the passenger name as passport acknowledgment$")
    public void selectThePassengerNameAsPassportAcknowledgment() {
        driver.findElement(acknowledgeBOX).click();
    }

    @And("^Press on 'Edit Passenger details'$")
    public void pressOnEditPassengerDetails() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(editPassengerLINK));
        driver.findElement(editPassengerLINK).click();
    }

    @And("^Press on 'Back'$")
    public void pressOnBack() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(backLINK));
        driver.findElement(backLINK).click();
    }

    @And("^Press on 'Fare Rules'$")
    public void pressOnFareRules() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(fareRulesLINK).click();
    }

    @And("^Press on 'Terms and conditions'$")
    public void pressOnTermsAndConditions() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(termsAndConditionsLINK).click();
    }

    @Then("^error message appear for each field at fill passenger details on payment page$")
    public void errorMessageAppearForEachFieldAtFillPassengerDetailsOnPaymentPage() {
        String Holderempty = driver.findElement(HolderEmptyErrorMSg).getText();
        Assert.assertEquals(Holderempty, "!Please enter name");

        String CardEmpty = driver.findElement(CardEmptyErrorMSG).getText();
        Assert.assertEquals(CardEmpty, "!Please input a card number");

        String Expirtempty = driver.findElement(ExpiryEmptyErrorMSG).getText();
        Assert.assertEquals(Expirtempty, "!Please enter an expiry date");

        String CVVEmpty = driver.findElement(CVVEmptyErrorMSG).getText();
        Assert.assertEquals(CVVEmpty, "!Please enter CVV");

        String yes1error = driver.findElement(Yes1EmptyErrorMSG).getText();
        Assert.assertEquals(yes1error, "!Please acknowledge risk to continue");

        String yes2empty = driver.findElement(Yes2EmptyErrorMSG).getText();
        Assert.assertEquals(yes2empty, "!Please accept terms and conditions to continue");
    }
}
