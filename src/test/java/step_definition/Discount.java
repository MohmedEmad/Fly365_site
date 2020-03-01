package step_definition;

import com.github.javafaker.Faker;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.APIUtility;
import helper.DataBase;
import helper.GeneralMethods;
import helper.TestBase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import step_definition.FlightAndHubAPIs.BookingCycleAPI;
import step_definition.FlightAndHubAPIs.HubRulesAPIs;

import java.io.IOException;
import java.util.List;

public class Discount extends TestBase {

    GeneralMethods gm = new GeneralMethods();
    APIUtility api = new APIUtility();
    BookingCycleAPI bookingApiObj = new BookingCycleAPI();
    HubRulesAPIs hubRulesApiObj = new HubRulesAPIs();
    FluentWait<WebDriver> wait = new WebDriverWait(driver, 30, 1000).withMessage("Element not found yet");

    private By systemBarMenuBTN = By.xpath("//div[@class='el-submenu__title']//img");
    private By discountBTN = By.xpath("/html/body/div[2]/ul/li[2]/a/span[2]");
    private By pageTitleText = By.xpath("//h4[@class='pull-left header-title']");
    private By createCampaignBTN = By.xpath("//a[@class='btn button-link']");
    private By discountNameTXT = By.xpath("//input[@id='Name']");
    private By discountStoreDDL = By.xpath("//div[@name='storeId']//input[@class='form-control']");
    private By discountStoreTXT = By.xpath("//span[@class='selected-tag']");
    private By discountPercentTXT = By.xpath("//input[@id='Discount Percent']");
    private By excludeCountryTXT = By.xpath("//div[@name='excludeCountry']//input[@class='form-control']");
    private By discountStatusTXT = By.xpath("//div[@name='isActive']//input[@class='form-control']");
    private By submitBTN = By.xpath("//input[@name='submit']");
    private By createdSuccMSG = By.xpath("//p[contains(text(),'Created Successfully')]");
    private By updatedSuccMSG = By.xpath("//p[contains(text(),'Updated Successfully')]");
    private By storeDiscountTAB = By.xpath("//span[contains(text(),'HK')]");
    private By editDiscountBTN = By.xpath("//i[@class='icon-edit']");
    private By clearSelectionBTN = By.xpath("//div[@name='isActive']//span[contains(text(),'×')]");
    private By settingsBTN = By.xpath("//span[contains(text(),'Settings')]");

    private Faker Fake = new Faker();
    String discountEnteredName = Fake.name().name();
    String discountName = null;
    String allTrips = null;
    //String discountStatus = null;

    private String hostName = "k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432";
    private String dbsName = "discount_api";

    @And("^Open Discount$")
        public void openDiscount() {
            driver.findElement(systemBarMenuBTN).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(discountBTN));
            driver.findElement(discountBTN).click();
    }

    @And("^Click on Create Campaign$")
    public void clickOnCreateCampaign() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(createCampaignBTN));
            driver.findElement(createCampaignBTN).click();
    }

    @And("^Fill required data for discount rule$")
    public void fillRequiredDataForDiscountRule() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(discountNameTXT));
        driver.findElement(discountNameTXT).sendKeys(discountEnteredName);
        //gm.selectFromDDLHub("storeId", "fly365_au");
        driver.findElement(discountStoreDDL).sendKeys("fly365_nz");
        driver.findElement(discountStoreDDL).sendKeys(Keys.ENTER);
        driver.findElement(discountPercentTXT).sendKeys("5");
        driver.findElement(excludeCountryTXT).sendKeys("No");
        driver.findElement(excludeCountryTXT).sendKeys(Keys.ENTER);
        driver.findElement(discountStatusTXT).sendKeys("Active");
        driver.findElement(discountStatusTXT).sendKeys(Keys.ENTER);
    }

    @And("^Click on Submit$")
    public void clickOnSubmit() throws InterruptedException {
            wait.until(ExpectedConditions.visibilityOfElementLocated(submitBTN));
            Thread.sleep(3000);
            driver.findElement(submitBTN).click();
    }

    @When("^Apply discount rule from API$")
    public void applyDiscountRuleFromAPI() throws IOException {
            api.sendRequestHub("https://internal.fly365stage.com/discount/campaign", hubRulesApiObj.createDiscount(discountEnteredName),"post");
    }

    @And("^Make Search from API$")
    public void makeSearchFromAPI() throws InterruptedException, IOException {
        Thread.sleep(5000);
        allTrips = api.sendRequestFlight("https://nz.fly365stage.com/api/flight-search/search", bookingApiObj.oneWayAPI(),"post");
    }

    @And("^Open certain store$")
    public void openCertainStore() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(storeDiscountTAB));
            driver.findElement(storeDiscountTAB).click();
    }

    @And("^Click on Update Campaign$")
    public void clickOnUpdateCampaign() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(editDiscountBTN));
        Thread.sleep(4000);
        driver.findElement(editDiscountBTN).click();
    }

    @And("^Update Name of discount rule$")
    public void updateNameOfDiscountRule() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(discountNameTXT));
        Thread.sleep(1000);
        int nameLength = driver.findElement(discountNameTXT).getAttribute("value").length();
        for (int i = 0 ; i < nameLength ; i++){
            driver.findElement(discountNameTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
        }
        driver.findElement(discountNameTXT).sendKeys(discountEnteredName);

    }

    @And("^Disable the discount rule$")
    public void disableTheDiscountRule() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(clearSelectionBTN));
        Thread.sleep(3000);
        driver.findElement(clearSelectionBTN).click();
        Thread.sleep(3000);
        driver.findElement(discountStatusTXT).sendKeys("disabled");
        driver.findElement(discountStatusTXT).sendKeys(Keys.ENTER);
    }

    @And("^Open Discount Settings$")
    public void openDiscountSettings() {
            wait.until(ExpectedConditions.elementToBeClickable(settingsBTN));
            driver.findElement(settingsBTN).click();
    }

    @Then("^Discount page open$")
    public void discountPageOpen() {
        Assert.assertEquals(driver.findElement(pageTitleText).getText(),"Campaigns");
    }


    @Then("^Check Discount rule created successfully$")
    public void checkDiscountRuleCreatedSuccessfully() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(createdSuccMSG));
        Assert.assertTrue(true, String.valueOf(driver.findElement(createdSuccMSG).isDisplayed()));
    }


    @Then("^Check Discount rule applied$")
    public void checkDiscountRuleApplied() throws InterruptedException {
        Thread.sleep(3000);
        List<String> discountname = api.jsonPathEvaluator.getList("itineraries.discounts.name");
        for (String discount : discountname) {
            Assert.assertEquals(discount ,discountEnteredName);
        }
    }


    @Then("^Check Discount rule updated successfully$")
    public void checkDiscountRuleUpdatedSuccessfully() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(updatedSuccMSG));
        Assert.assertTrue(true,String.valueOf(driver.findElement(updatedSuccMSG).isDisplayed()));
    }


    @Then("^Check Discount rule disabled$")
    public void checkDiscountRuleDisabled() throws InterruptedException {
        Thread.sleep(20000);
        List<String> discountStatus = api.jsonPathEvaluator.getList("itineraries.discounts");
        for (String discount : discountStatus) {
            Assert.assertEquals(discount ,null);
        }
    }

    @And("^Delete new discount from database$")
    public void deleteNewDiscountFromDatabase() {
        DataBase.execute_query_dbs(hostName, dbsName, "delete from campaigns where \"name\" = '" + discountEnteredName + "'");
        }

    @And("^Check discount from database to be applied$")
    public void CheckDiscountFromDatabaseToBeApplied() {
            DataBase.execute_query_dbs(hostName, dbsName, "Update settings \n" +
                    "SET \"isActive\" = 'TRUE'\n" +
                    "WHERE id='715dbed1-93bb-4430-915e-f862596ceaff'");
    }

    @And("^Make Browser back$")
    public void makeBrowserBack() throws InterruptedException {
        gm.makeBrowserBack();
    }

    @And("^Book a trip from API on \"(.*)\" and get \"(.*)\"$")
    public String bookATripFromAPIOnAndGet(String domain, String responseField) throws Throwable {
        String requestUrl = "https://api.fly365" + domain + ".com/flight-search/search";
        String allAvailableTrips = api.sendRequestFlight(requestUrl, bookingApiObj.oneWayAPI(),"post");
        String itinaryID = bookingApiObj.getItineraryId(allAvailableTrips, 2);
        String cardID = bookingApiObj.createCart(itinaryID, domain);
        bookingApiObj.addPassenger(cardID, domain);
        String finalResponse = bookingApiObj.getresult(bookingApiObj.orderIdCheckoutResponse, bookingApiObj.orderNumberCheckoutResponse);
        JSONObject jObject = new JSONObject(finalResponse);
        JSONArray arr = jObject.getJSONArray("products");
        String discountNAM = null;
        for (int i = 0; i < arr.length(); i++) {
            JSONArray array_1 = arr.getJSONObject(i).getJSONArray("options");
            JSONObject OBJ1 = array_1.getJSONObject(0);
            discountNAM = OBJ1.getJSONObject("discounts").get("name").toString();
        }
        return responseField;
    }
}
