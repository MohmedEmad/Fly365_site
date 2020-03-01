package step_definition;

import com.github.javafaker.Faker;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helper.APIUtility;
import helper.DataBase;
import helper.TestBase;
import org.json.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import step_definition.FlightAndHubAPIs.BookingCycleAPI;
import step_definition.FlightAndHubAPIs.HubRulesAPIs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplyCancelTest extends TestBase {
    WebDriverWait wait = new WebDriverWait(driver, 30);

    private By manageMyBookBTN = By.xpath("//span[text()='Manage My Booking']");
    private By cancelBookBTN = By.xpath("//div[text()='Cancel Booking']");
    private By dimmedCancelBookBTN = By.xpath("//div[text()='Cancel Booking']/ancestor::li");
    private By acceptTermsCHBOX = By.xpath("//span[@class = 'el-checkbox__inner']");
    private By cancelMyBookBTN = By.xpath("//button[contains(text(),'Cancel My Booking')]");
    private By thanksMSG = By.xpath("//h2[@class = 'mb-5 text-black text-xl']");
    private By requestSentMSG = By.xpath("//span[@class = 'mb-3 block text-black']");
    private By bookStatusMSG = By.xpath("//span[@class='text-success']");
    private By closeCancelMsgBTN = By.xpath("//button[@aria-label='Close']");
    private By requestCommentTXT = By.xpath("//textarea[@placeholder = 'Write your comment here …']");
    private By ceancelRequestBTN = By.xpath("//button[@type='submit' and contains(text(),'SEND REQUEST')]");

    private By selectFrstOrderStatusBTN = By.cssSelector("div.mb-2 div.el-select:nth-child(2)");
    private By frstOrderStatus = By.xpath("(//li[contains (@class,'el-select-dropdown__item selected')])[3]");
    private By orderDetailsStatus = By.xpath("(//li[contains (@class,'el-select-dropdown__item selected')])[5]");
    private By selectStatusOrderDetailsBTN = By.cssSelector("ul.mb-3 div.el-select");
    private static String returnedMyBookJson = null;
    APIUtility apiObj = new APIUtility();
    HubRulesAPIs hubRulesApiObj = new HubRulesAPIs();
    BookingCycleAPI bookingApiObj = new BookingCycleAPI();
    private Faker fakerNameGenerator = new Faker();
    private String fakerRuleName = fakerNameGenerator.name().name();
    private String updatedRuleName = fakerNameGenerator.name().name();

    private String hostName = "k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432";
    private String dbsName = "flight_rules";

    @And("^Click on Manage My Booking$")
    public void clickOnManageMyBooking() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(manageMyBookBTN));
        //driver.findElement(manageMyBookBTN).click();
        Thread.sleep(1200);
        driver.findElement(By.xpath("//span[text()='Manage My Booking']/ancestor::a")).click();
    }

    @And("^Click Cancel Booking$")
    public void clickCanelBooking() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBookBTN));
        driver.findElement(cancelBookBTN).click();
    }

    @And("^Mark Terms and Conditions$")
    public void markTermsAndConditions() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(acceptTermsCHBOX));
        driver.findElement(acceptTermsCHBOX).click();
    }


    @And("^Click Cancel My Booking$")
    public void clickCancelMyBooking() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelMyBookBTN));
        driver.findElement(cancelMyBookBTN).click();
    }

    @Then("^Request Is sent Successfully$")
    public void requestIsSentSuccessfully() throws InterruptedException {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(thanksMSG));
        Thread.sleep(2000);
        WebElement thanksElmnt = driver.findElement(thanksMSG);
        WebElement requestSentElmnt = driver.findElement(requestSentMSG);
        Assert.assertTrue(thanksElmnt.getText().equals("Thank you"));
        Assert.assertTrue(requestSentElmnt.getText().equals("Your request has been sent successfully."));
        driver.findElement(closeCancelMsgBTN).click();
    }

    @Then("^Booking Status Will Be To Be Refunded$")
    public void bookingStatusWillBeToBeRefunded() throws InterruptedException {
        driver.findElement(closeCancelMsgBTN).click();
        //wait.until(ExpectedConditions.textToBe().visibilityOfElementLocated(bookStatusMSG));
        WebElement bookStatusElmnt = driver.findElement(bookStatusMSG);
        wait.until(ExpectedConditions.textToBe(bookStatusMSG,"Refund submitted"));
        Thread.sleep(1500);
        Assert.assertEquals(bookStatusElmnt.getText() , "Refund submitted" );
    }

    @And("^Enter request comment$")
    public void enterRequestComment() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(requestCommentTXT));
        driver.findElement(requestCommentTXT).sendKeys("Read my request");
        driver.findElement(ceancelRequestBTN).click();
    }

    @And("^Delete Created Rule From Database$")
    public void deleteCreatedRuleFromDatabase() {
        String nameWithoutCase = fakerRuleName.toLowerCase();
        DataBase.execute_query_dbs(hostName, dbsName, "select * from rules where \"name\" = '" + nameWithoutCase + "' ");
        if (DataBase.data != null){
            DataBase.execute_query_dbs(hostName, dbsName, "delete from rules where \"name\" = '" + nameWithoutCase +"'");
        }
    }

    @Then("^Order Will Have To be Refunded status$")
    public void orderWillHaveToBeRefundedStatus() throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(selectFrstOrderStatusBTN).click();
        Thread.sleep(1500);
        WebElement orderStatusElmnt = driver.findElement(frstOrderStatus);
        Assert.assertEquals(orderStatusElmnt.getText(), "To be refunded");

    }

    @Then("^Order Details Will Have To be Refunded status$")
    public void orderDetailsWillHaveToBeRefundedStatus() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectStatusOrderDetailsBTN));
        Thread.sleep(1500);
        driver.findElement(selectStatusOrderDetailsBTN).click();
        Thread.sleep(1500);
        WebElement orderStatusElmnt = driver.findElement(orderDetailsStatus);
        Thread.sleep(1500);
        Assert.assertEquals(orderStatusElmnt.getText(), "To be refunded");
    }

    @Then("^Booking Status Will still confirmed$")
    public void bookingStatusWillStillConfirmed() throws InterruptedException {
        driver.findElement(closeCancelMsgBTN).click();
        WebElement bookStatusElmnt = driver.findElement(bookStatusMSG);
        wait.until(ExpectedConditions.textToBe(bookStatusMSG,"Confirmed"));
        Thread.sleep(1500);
        Assert.assertEquals(bookStatusElmnt.getText() , "Confirmed" );
    }

    @Given("^Delete All Rules$")
    public void deleteAllRules() {
        DataBase.execute_query_dbs(hostName, dbsName, "select * from rules");
        if (DataBase.data != null){
            DataBase.execute_query_dbs(hostName, dbsName, "delete from rules");
        }
    }

    @Then("^Cancel My Booking is not Clickable$")
    public void cancelMyBookingIsNotClickable() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBookBTN));
        Assert.assertTrue(driver.findElement(dimmedCancelBookBTN).getAttribute("class").contains("is-disabled"));
    }


    @And("^Create \"([^\"]*)\" \"([^\"]*)\" Rule from API for \"([^\"]*)\" matched with booking$")
    public void createRuleFromAPIForMatchedWithBooking(String status , String cancelOption , String domain) throws Throwable {
        APIUtility.sendRequestHub("https://internal.fly365" + domain + ".com/rules/rule", hubRulesApiObj.createRuleAPI(fakerRuleName ,
                bookingApiObj.storeId , bookingApiObj.carrierCode, bookingApiObj.bookingCode,
                "airport", bookingApiObj.depCity, "airport" , bookingApiObj.arrCity , cancelOption ,status), "post");

    }
}
