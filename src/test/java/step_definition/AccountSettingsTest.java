package step_definition;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.*;

public class AccountSettingsTest extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 10);

    private By hiHDR = By.xpath("//span[contains(text(),'Hi')]");
    private By accountSettingsTAB = By.xpath("//a[contains(text(),'ACCOUNT SETTINGS')]");
    private By billingAddressTAB = By.xpath("//span[contains(text(),'Billing Address')]");
    private By accountSettingsHDR = By.xpath("//p[contains(text(),'Account information details, Add or edit')]");
    private By firstNameTXT = By.xpath("//input[@placeholder='First Name']");
    private By save1BTN = By.xpath("//div[@class='account-form-container']//button[@type='submit'][contains(text(),'Save')]");
    private By save2BTN = By.xpath("//div[@class='col-6 offset-18 mt-2']//button[@class='w-full btn btn-primary-second'][contains(text(),'Save')]");
    private By save3BTN = By.xpath("//div[@id='pane-change-password-settings']//button[@type='submit'][contains(text(),'Save')]");
    private By save4BTN = By.xpath("//div[@id='pane-email-settings']//button[@type='submit'][contains(text(),'Save')]");
    private By updatedNameHDR = By.xpath("//span[@class='el-dropdown-link capitalize text-xs text-white el-dropdown-selfdefine']");
    private By addressLine1TXT = By.xpath("//input[@placeholder='Address Line 1']");
    private By addressLine2TXT = By.xpath("//input[@placeholder='Address Line 2']");
    private By cityTXT = By.xpath("//input[@placeholder='City']");
    private By zipTXT = By.xpath("//input[@placeholder='90210']");
    private By updateSuccMSG = By.xpath("//p[contains(text(),'Updated successfully!')]");
    private By changePasswordTAB = By.xpath("//span[contains(text(),'Change Password')]");
    private By oldPasswordTXT = By.xpath("(//input[@type='password'])[1]");
    private By newPasswordTXT = By.xpath("(//input[@type='password'])[2]");
    private By changePasswordSaveBTN = By.xpath("(//button[@class='w-full btn btn-primary-second'])[3]");
    private By emailSettingsTAB = By.xpath("//span[contains(text(),'Email Settings')]");
    private By emailSettingsCheckBox = By.xpath("(//span[@class='el-checkbox__inner'][1])");

    @And("^Click on Account Settings tab$")
    public void clickOnAccountSettingsTab() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(hiHDR));
        driver.findElement(accountSettingsTAB).click();
        Thread.sleep(2000);
    }

    @Then("^Account Settings page opened$")
    public void accountSettingsPageOpened() {
        Assert.assertEquals(driver.findElement(accountSettingsHDR).getText(), "Account information details, Add or edit");
    }

    @Then("^Update First Name with (.*)$")
    public void updateFirstName(String name) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameTXT));
        driver.findElement(firstNameTXT).sendKeys(Keys.chord(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE));
        driver.findElement(firstNameTXT).sendKeys(name);
        driver.findElement(save1BTN).click();
    }

    @Then("^Check updated First Name$")
    public void checkUpdatedFirstName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(updateSuccMSG));
        Assert.assertEquals(driver.findElement(updatedNameHDR).getText(), "David");
    }

    @And("^Click on Billing Address tab$")
    public void clickOnBillingAddressTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(billingAddressTAB));
        driver.findElement(billingAddressTAB).click();
    }

    @And("^Add Billing Address$")
    public void addBillingAddress(DataTable billingAddressData) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressLine1TXT));

        for (Map<String, String> billingData : billingAddressData.asMaps(String.class, String.class)) {

            driver.findElement(addressLine1TXT).sendKeys(billingData.get("Address Line 1"));
            driver.findElement(addressLine2TXT).sendKeys(billingData.get("Address Line 1"));
            driver.findElement(cityTXT).sendKeys(billingData.get("City"));
            driver.findElement(zipTXT).sendKeys(billingData.get("ZIP Code"));
            driver.findElement(save2BTN).click();
        }

    }

    @Then("^Success message for update is display$")
    public void successMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(updateSuccMSG));
        Assert.assertTrue(driver.findElement(updateSuccMSG).isDisplayed());
    }

    @And("^Click on Change Password tab$")
    public void clickOnChangePasswordTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(changePasswordTAB));
        driver.findElement(changePasswordTAB).click();

    }


    @Then("^Enter old password \"([^\"]*)\" and Enter New password \"([^\"]*)\"$")
    public void enterOldPasswordAndEnterNewPassword(String oldPassword, String newpassword) throws Throwable {
        wait.until(ExpectedConditions.visibilityOfElementLocated(oldPasswordTXT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(newPasswordTXT));
        driver.findElement(oldPasswordTXT).sendKeys(oldPassword);
        Thread.sleep(3000);
        driver.findElement(newPasswordTXT).sendKeys(newpassword);
    }

    @And("^Click on save button$")
    public void clickOnSaveButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(changePasswordSaveBTN));
        driver.findElement(changePasswordSaveBTN).click();
    }

    @And("^Click on Email Settings tab$")
    public void clickOnEmailSettingsTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailSettingsTAB));
        driver.findElement(emailSettingsTAB).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailSettingsCheckBox));
        driver.findElement(emailSettingsCheckBox).click();
        driver.findElement(save4BTN).click();
    }
}

