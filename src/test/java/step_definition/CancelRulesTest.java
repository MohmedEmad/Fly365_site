package step_definition;

import com.github.javafaker.Faker;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helper.APIUtility;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;


public class CancelRulesTest extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 30);


    private By createRuleBTN = By.xpath("//a[@href='/rule/create']");
    private By ruleNameTXT = By.id("Name");
    private By carrierCodeTXT = By.id("Plating Carrier Code");
    private By storeTXT = By.xpath("//div[@name='storeId']//input");
    private By bookingCodeTXT = By.id("Booking Code");
    private By departTypeTXT = By.xpath("//div[@name='departureType']//input");
    private By departCodeTXT = By.id("Departure Code");
    private By destTypeTXT = By.xpath("//div[@name='destinationType']//input");
    private By destCodeTXT = By.id("Destination Code");
    private By airlineChangeFeesTXT = By.id("Airline Fees Per Passenger");
    private By flyChangeFeesTXT = By.id("Fly365 Fees Per Passenger");
    private By baseFeesTXT = By.id("Default Base Fare fees per passenger");
    private By taxFeesTXT = By.id("Default Tax Fare fees per passenger");
    private By cancelOptionTXT = By.xpath("//div[@name='cancellationOption']//input");
    private By airlineCancelFeesTXT = By.name("airlineCancelFees");
    private By flyCancelFeesTXT = By.name("fly365CancelFees");
    private By ruleStatusTXT = By.xpath("//div[@name='isActive']//input");
    private By clearStatusBTN = By.xpath("//div[@name='isActive']//button[@title='Clear selection']");
    private By submitBTN = By.name("submit");
    private By editBTN = By.xpath("//tr[1]//td[9]//div[1]//a[1]");
    private By successMSG = By.xpath("//div[@class='el-notification__group']//h2");
    private By createUpdateMSG = By.xpath("//div[@class='el-notification__content']//p");
    private By ruleInTable = By.xpath("(//tbody[@class='content-table']//td)[2]//div");
    private By ruleStatusInTable = By.xpath("(//tbody[@class='content-table']//td)[6]//div");
    private By menuBTN = By.xpath("//div[@class='el-submenu__title']//img") ;
    private By rulesMenuBTN = By.xpath("//span[text()='Rules']");
    private By rulesListBTN = By.xpath("//span[text()='Rule']");
    private By nameError = By.xpath("(//div[@id='field_name'])//span[@class='help is-danger']");

    public JavascriptExecutor jse = (JavascriptExecutor)driver;

    APIUtility apiObj = new APIUtility();

    private Faker fakerNameGenerator = new Faker();
    private String fakerRuleName = fakerNameGenerator.name().name();
    private String updatedRuleName = fakerNameGenerator.name().name();

    static String returnedMyBookJson = null;
    static String myBookArrData[] = null;

    @And("^Open Rules$")
    public void openRules() throws InterruptedException {
        driver.findElement(menuBTN).click();
        Thread.sleep(4000);
        driver.findElement(rulesMenuBTN).click();
        Thread.sleep(4000);
    }

    public void backToRuleList(){
        WebElement rulesMenu = driver.findElement(rulesListBTN);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMSG));
            rulesMenu.click();
        }catch(Exception e){
            jse.executeScript("arguments[0].scrollIntoView()", rulesMenu);
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMSG));
            wait.until(ExpectedConditions.elementToBeClickable(rulesListBTN));
            rulesMenu.click();
        }

    }

    @And("^Click Create Rule$")
    public void clickCreateRule() {

        driver.findElement(createRuleBTN).click();
    }



    @And("^Submit Rule$")
    public void submitRule() throws InterruptedException {
        WebElement submitElmnt = driver.findElement(submitBTN);
        try{
            submitElmnt.click();
        }catch(Exception e){
            jse.executeScript("arguments[0].scrollIntoView()", submitElmnt);
            submitElmnt.click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(submitBTN));

    }

    @Then("^Rule Success Message Is Displayed$")
    public void ruleSuccessMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMSG));
        Assert.assertTrue(driver.findElement(successMSG).getText().contains("Success!") &&
                driver.findElement(createUpdateMSG).getText().contains("Created Successfully"));
    }

    @Then("^Rule Is Added To The List$")
    public void ruleIsAddedToTheList() throws InterruptedException {
        backToRuleList();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ruleInTable));
        System.out.println(driver.findElement(ruleInTable).getText());
        Assert.assertTrue(driver.findElement(ruleInTable).getText().equalsIgnoreCase(fakerRuleName));
    }

    @And("^Click Edit Rule$")
    public void clickEditRule() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(editBTN));
        Thread.sleep(4000);
        driver.findElement(editBTN).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ruleNameTXT));
    }

    @And("^Edit Rule Name$")
    public void editRuleName() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(ruleNameTXT));
        Thread.sleep(1000);
        int nameLength = driver.findElement(ruleNameTXT).getAttribute("value").length();
        for (int i = 0 ; i<nameLength ; i++){
            driver.findElement(ruleNameTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
        }
        driver.findElement(ruleNameTXT).sendKeys(updatedRuleName);
    }

    @Then("^Rule Updated Message Is Displayed$")
    public void ruleUpdatedMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMSG));
        Assert.assertTrue(driver.findElement(successMSG).getText().contains("Success!") &&
                driver.findElement(createUpdateMSG).getText().contains("Updated Successfully"));
    }

    @Then("^Rule Is Updated In The List$")
    public void ruleIsUpdatedInTheList() throws InterruptedException {
        backToRuleList();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ruleInTable));
        Assert.assertEquals(driver.findElement(ruleInTable).getText().toUpperCase(), updatedRuleName.toUpperCase());
    }

    @And("^Change Status To Disabled$")
    public void changeStatusToDisabled() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(clearStatusBTN).click();
        driver.findElement(ruleStatusTXT).sendKeys("Disabled");
        driver.findElement(ruleStatusTXT).sendKeys(Keys.ENTER);
        Thread.sleep(1500);
    }


    @Then("^Rule status Is Updated In The List$")
    public void ruleStatusIsUpdatedInTheList() throws InterruptedException {
        backToRuleList();
        Assert.assertEquals(driver.findElement(ruleStatusInTable).getText(), "Disabled");
    }
    @And("^Submit Rule With Same Name$")
    public void createRuleSameName() throws InterruptedException {
        fakerRuleName = driver.findElement(ruleInTable).getText();
        System.out.println(fakerRuleName);
        clickCreateRule();
        fillRuleData();
        submitRule();
    }
    @And("^Fill Rule Data$")
    public void fillRuleData() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ruleNameTXT));
        driver.findElement(ruleNameTXT).sendKeys(fakerRuleName);
        driver.findElement(carrierCodeTXT).sendKeys("MS");
        driver.findElement(storeTXT).sendKeys("fly365_nz");
        driver.findElement(storeTXT).sendKeys(Keys.ENTER);
        driver.findElement(bookingCodeTXT).sendKeys("L");
        driver.findElement(departTypeTXT).sendKeys("airport");
        driver.findElement(departTypeTXT).sendKeys(Keys.ENTER);
        driver.findElement(destTypeTXT).sendKeys("airport");
        driver.findElement(destTypeTXT).sendKeys(Keys.ENTER);
        driver.findElement(destCodeTXT).sendKeys("CAI");
        driver.findElement(departCodeTXT).sendKeys("DXB");
        driver.findElement(airlineChangeFeesTXT).sendKeys("20");
        driver.findElement(flyChangeFeesTXT).sendKeys("15");
        driver.findElement(baseFeesTXT).sendKeys("10");
        driver.findElement(taxFeesTXT).sendKeys("5");
        driver.findElement(cancelOptionTXT).sendKeys("Refundable");
        driver.findElement(cancelOptionTXT).sendKeys(Keys.ENTER);
        driver.findElement(airlineCancelFeesTXT).sendKeys("20");
        driver.findElement(flyCancelFeesTXT).sendKeys("25");
        driver.findElement(ruleStatusTXT).sendKeys("Active");
        driver.findElement(ruleStatusTXT).sendKeys(Keys.ENTER);
    }

    @Then("^Name Error Message Is Displayed$")
    public void nameErrorMessageIsDisplayed() throws InterruptedException {
        WebElement nameElement = driver.findElement(ruleNameTXT);
        jse.executeScript("arguments[0].scrollIntoView()", nameElement);
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameError));
        String errorTXT = driver.findElement(nameError).getText();
        System.out.println(errorTXT);
        Assert.assertTrue(errorTXT.contains("please select another name"));
    }


}
