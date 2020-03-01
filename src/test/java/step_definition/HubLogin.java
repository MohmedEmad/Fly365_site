package step_definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helper.GeneralMethods;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import step_definition.FlightAndHubAPIs.BookingCycleAPI;
import step_definition.RetrieveYourFlightBookingTest.*;

public class HubLogin extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 30);

    private GeneralMethods gmObject = new GeneralMethods();


    private By HubLoginEmailTXT = By.xpath("//input[@id='inputEmail']");
    private By HubLoginPasswordTXT = By.xpath("//input[@id='inputPassword']");
    private By HubLoginBTN = By.xpath("//button[@class='btn btn-lg btn-primary btn-block']");
    private By HubDashWelcomeMSG = By.xpath("//h2[@class='d-flex justify-content-center align-items-center text-primary dashboard__title']");
    //private By HubHamMenu = By.xpath("//li[@class='header__top__left__el-submenu el-submenu']//div[@class='el-submenu__title']");
    //private By BackOffice = By.xpath("//input[@id='inputEmail']");
    private By Tickets = By.xpath("//span[contains(text(),'Cases')]");
    private By orders = By.xpath("//span[contains(text(),'Orders')]");
    private By advSearchBTN = By.xpath("//a[contains(text(),'Advanced search')]");
    private By Firstinboxtitle = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/h3[1]/a[1]");
    private By Createticket = By.xpath("//button[span[contains(text(),'Create New Case')]]");
    private By ticketEmail = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/input[1]");
    private By ticketsubject = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/input[1]");
    private By ticketbody = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/div[2]/div[2]/div[1]");
    private By ticketstore = By.xpath("//input[@placeholder='Select Store']");
    private By nzticketstore = By.xpath("//span[contains(text(),'New Zealand')]");
    private By sendBTN = By.xpath("(//button//span[text()='Send'])");
    private By ticketLST = By.xpath("//div[@class='tickets-container mb-5']");
    private By orderNumberTXT = By.id("Order number");
    private By submitSearchBTN = By.xpath("//input[@name='submit']");
    private By quickSearchTXT = By.xpath("//input[@placeholder='Enter PNR or Order No. or Order Rf. or customer email']");
    private By frstQuickSearchItem = By.xpath("//li[contains(@id ,'el-autocomplete')]");
    private By frstOrder = By.xpath("//span[@xpath='1']");


    @And("^Open hub login page$")
    public void openHubLoginPage() {
        driver.navigate().to("https://hub.fly365stage.com/login?redirect=/");
    }


    @And("^login into hub with super admin$")
    public void loginIntoHubWithSuperAdmin() {
        driver.findElement(HubLoginEmailTXT).sendKeys("john.smith.fly365@gmail.com");
        driver.findElement(HubLoginPasswordTXT).sendKeys("@John12345");
        driver.findElement(HubLoginBTN).click();
    }

    @And("^open Back office$")
    public void openBackoffice() {
        driver.navigate().to("https://backoffice.fly365stage.com/");
    }

    @And("^open tickets$")
    public void opentickets() throws InterruptedException {
        driver.findElement(Tickets).click();
        Thread.sleep(5000);
    }


    @Then("^contact us message appear as ticket$")
    public void contactusmessageappearasticket() {
        String Messagetitle = driver.findElement(Firstinboxtitle).getText();
        Assert.assertTrue(Messagetitle.contains("John Smith-General Question"));

    }

    @And("^press on create ticket$")
    public void pressOnCreateTicket() {
        driver.findElement(Createticket).click();

    }

    @And("^fill message data$")
    public void fillMessageData() throws InterruptedException {
        driver.findElement(ticketEmail).sendKeys("john.smith.fly365@gmail.com");
        driver.findElement(ticketsubject).sendKeys("test001");
        driver.findElement(ticketbody).sendKeys("test send create ticket throw create ");
        driver.findElement(ticketstore).click();
        Thread.sleep(1000);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(nzticketstore));
        driver.findElement(nzticketstore).click();
    }

    @And("^press send$")
    public void pressSend() {
        //driver.findElement(sendBTN).click();
        try {
            Thread.sleep(2000);
            driver.findElement(sendBTN).click();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }


    }

    @Then("^ticket created$")
    public void ticketCreated() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Firstinboxtitle));
        String Messagetitle = driver.findElement(Firstinboxtitle).getText();
        Assert.assertTrue(Messagetitle.contains("test001"));
    }

    @Given("^send direct mail to support mail$")
    public void sendDirectMailToSupportMail() throws InterruptedException {

        String userName = "john.smith.fly365@gmail.com";
        String password = "@Fly365@Fly365";
        String toAddress = "test@fly365.com";
        String subject = "test001";
        driver.get("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/&hl=en");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(userName);
        driver.findElement(By.xpath("//span[@class='CwaK9']")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-KE L3']")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.name("to")).sendKeys(toAddress);
        driver.findElement(By.name("subjectbox")).sendKeys(subject);
        driver.findElement(By.xpath("//div[@role='button' and text()='Send']")).click();
        Thread.sleep(3000);
    }

    @Then("^Cancel Request is created in Tickets$")
    public void cancelRequestIsCreatedInTickets() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ticketLST));
        WebElement ticketLSTEmnt = driver.findElement(ticketLST);
        Assert.assertTrue(ticketLSTEmnt.getText().contains("Cancellation Request" + " " + RetrieveYourFlightBookingTest.retrievedBookingPnr));
    }

    @And("^Open Orders$")
    public void openOrders() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orders));
        driver.findElement(orders).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(advSearchBTN));
    }

    @And("^Search for Order Number from Advanced Search$")
    public void searchForOrderNumberFromAdvancedSearch() throws InterruptedException {
        driver.findElement(advSearchBTN).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberTXT));
        driver.findElement(orderNumberTXT).sendKeys(BookingCycleAPI.orderNumberCheckoutResponse);
        Thread.sleep(1500);
        driver.findElement(submitSearchBTN).click();
        driver.findElement(advSearchBTN).click();

    }

    @And("^Search for Order Number from Quick Search$")
    public void searchForOrderNumberFromQuickSearch() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quickSearchTXT));
        driver.findElement(quickSearchTXT).sendKeys(BookingCycleAPI.orderNumberCheckoutResponse);
        wait.until(ExpectedConditions.visibilityOfElementLocated(frstQuickSearchItem));
        driver.findElement(quickSearchTXT).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(quickSearchTXT).sendKeys(Keys.ENTER);
    }

    @And("^Search for Order Number from Quick Search Through UI$")
    public void searchForOrderNumberFromQuickSearchThroughUI() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quickSearchTXT));
        driver.findElement(quickSearchTXT).sendKeys(RetrieveYourFlightBookingTest.retrievedBookingPnr);
        wait.until(ExpectedConditions.visibilityOfElementLocated(frstQuickSearchItem));
        driver.findElement(quickSearchTXT).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(quickSearchTXT).sendKeys(Keys.ENTER);
    }
    @And("^Search for booking returned in \"([^\"]*)\" Quick Search$")
    public void searchForBookingReturnedInQuickSearch(String order){
        String orderNumber = null;
        if(order.equalsIgnoreCase("booking pnr response")){
            orderNumber = BookingCycleAPI.pnrNumberCheckoutResponse;
        }
        else if(order.equalsIgnoreCase("retrieved booking pnr")){
            orderNumber = RetrieveYourFlightBookingTest.retrievedBookingPnr;
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(quickSearchTXT));
        driver.findElement(quickSearchTXT).sendKeys(orderNumber);
        wait.until(ExpectedConditions.visibilityOfElementLocated(frstQuickSearchItem));
        driver.findElement(quickSearchTXT).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(quickSearchTXT).sendKeys(Keys.ENTER);
    }
}
