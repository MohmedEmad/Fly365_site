package step_definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.GeneralMethods;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class ContactUsTest extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver,10);

    private GeneralMethods gmObject = new GeneralMethods();

    private By contactUsHDR = By.xpath("//div[2]/h1");
    private By emailSupportTXT = By.xpath("//a[@class='text-sm font-medium text-primary-fourth no-underline']");
    private By fullNameTXT = By.xpath("//input[@placeholder='Full name']");
    private By emailTXT = By.xpath("//input[@placeholder='example@email.com']");
    private By categoryDDL = By.xpath("//input[@placeholder='Select Category']");
    private By messageTXT = By.xpath("//textarea[@placeholder='Write your message here …']");
    private By sendBTN = By.xpath("//button[contains(text(),'SEND')]");
    private By successMSG = By.xpath("//h3[contains(text(),'Thank you for contacting us')]");


    @Then("^'Contact Us' page is opened$")
    public void contactUsPageIsOpened() {
        String headerText = driver.findElement(contactUsHDR).getText();
        Assert.assertEquals(headerText, "Contact Us");
    }


    @Then("^'Contact Us' page is opened matching New Zealand site$")
    public void contactUsPageIsOpenedMatchingNewZealandSite() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailSupportTXT));
        Assert.assertEquals(driver.findElement(emailSupportTXT).getText(),"support@fly365.com");
    }

    @And("^Enter Full Name$")
    public void enterFullName() {
        driver.findElement(fullNameTXT).sendKeys("John Smith");
    }

    @And("^Enter Email$")
    public void enterEmail() {
        driver.findElement(emailTXT).sendKeys("john.smith.fly365@gmail.com");
    }

    @And("^Choose Category$")
    public void chooseCategory() throws InterruptedException {
        gmObject.selectFromDDL(categoryDDL,"General Question");
    }

    @And("^Write the message$")
    public void writeTheMessage() {
        driver.findElement(messageTXT).sendKeys("I want to ask about my airline reference");
    }

    @And("^Click Send$")
    public void clickSend() {
        driver.findElement(sendBTN).click();
    }

    @Then("^Success message displayed$")
    public void successMessageDisplayed() {
        Assert.assertTrue(driver.findElement(successMSG).isDisplayed());
    }
}
