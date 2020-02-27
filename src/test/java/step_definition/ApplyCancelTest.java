package step_definition;

import com.github.javafaker.Faker;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helper.APIUtility;
import helper.DataBase;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

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
    private By closeMsgBTN = By.xpath("//button[@aria-label='Close']");
    private By cancelCommentTXT = By.xpath("//textarea[@placeholder = 'Write your comment here …']");
    private By ceancelRequestBTN = By.xpath("//button[@type='submit' and text()='SEND REQUEST']");

    private By selectFrstOrderStatusBTN = By.cssSelector("div.mb-2 div.el-select:nth-child(2)");
    private By frstOrderStatus = By.xpath("(//li[contains (@class,'el-select-dropdown__item selected')])[2]");
    private By orderDetailsStatus = By.xpath("(//li[contains (@class,'el-select-dropdown__item selected')])[4]");
    private By selectStatusOrderDetailsBTN = By.cssSelector("ul.mb-3 div.el-select");
    private static String returnedMyBookJson = null;
    //static String myBookArrData[] = null;
    APIUtility apiObj = new APIUtility();

    private Faker fakerNameGenerator = new Faker();
    private String fakerRuleName = fakerNameGenerator.name().name();
    private String updatedRuleName = fakerNameGenerator.name().name();

    private String hostName = "k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432";
    private String dbsName = "flight_rules";

    @Given("^Create \"([^\"]*)\" \"([^\"]*)\" Rule from API for \"(.*)\"$")
    public void createAruleFromApiFor(String status , String cancelOption , String domain) throws IOException {
        APIUtility.sendPostRequestCreateTicket("https://internal.fly365" + domain + ".com/rules/rule", apiObj.createRuleAPI(fakerRuleName ,
                                    APIUtility.storeId , APIUtility.carrierCode, APIUtility.bookingCode,
                "airport", APIUtility.depCity, "airport" , APIUtility.arrCity , cancelOption ,status));

        //apiObj.createRuleAPI(domain);
    }


    @And("^Get data for this booking \"([^\"]*)\"$")
    public void getDataForThisBooking(String email) throws Throwable {

        returnedMyBookJson = APIUtility.getTripResponse(email , HomeTest.orderNumber);

    }

    @And("^Get StoreID$")
    public void getStoreID() {
       APIUtility.getstoreId(returnedMyBookJson);
    }

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
        driver.findElement(closeMsgBTN).click();
    }

    @Then("^Booking Status Will Be To Be Refunded$")
    public void bookingStatusWillBeToBeRefunded() throws InterruptedException {
        driver.findElement(closeMsgBTN).click();
        //wait.until(ExpectedConditions.textToBe().visibilityOfElementLocated(bookStatusMSG));
        WebElement bookStatusElmnt = driver.findElement(bookStatusMSG);
        wait.until(ExpectedConditions.textToBe(bookStatusMSG,"Refund submitted"));
        Thread.sleep(1500);
        Assert.assertEquals(bookStatusElmnt.getText() , "Refund submitted" );
    }

    @And("^Enter Cancel Comment$")
    public void enterCancelComment() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelCommentTXT));
        driver.findElement(cancelCommentTXT).sendKeys("Cancel request");
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
        Assert.assertEquals(orderStatusElmnt.getText(), "To be refunded");
    }

    @Then("^Booking Status Will still confirmed$")
    public void bookingStatusWillStillConfirmed() throws InterruptedException {
        driver.findElement(closeMsgBTN).click();
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

}
