package step_definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class MyBookingTest extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 20);

    boolean assertion;

    private By hiHDR = By.xpath("//span[contains(text(),'Hi')]");
    private By recentBookingHDR = By.xpath("//p[text()='Your most recent bookings will show up here.']");
    private By noBookingHistoryMSG = By.xpath("//h3[text()='Sorry there is no booking history']");
    private By backToHomeBTN = By.linkText("BACK TO HOME");
    private By homePageHDR = By.xpath("//span[text()='Low Fares']");
    private By myBookingLINK = By.xpath("//div[2]/div[1]/div/div[1]/a[1]");
    private By lastFly365RefVAL = By.xpath("//div[2]/div[3]//div[1]//div[1]/div/strong");
    private By bookingDetailsBTN = By.xpath("(//a[@class='btn btn-primary-second font-bold text-xs btn-more-derails h-8 w-32 p-1 no-underline'])[1]");
    private By bookingHeader = By.xpath("//div[@class='text-black font-medium text-sm mr-5']");

    @And("^Click on My Booking$")
    public void clickOnMyBooking() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(hiHDR));
//        driver.findElement(By.xpath("//*[contains(@class,'el-icon--right svg-icon svg-fill')]")).click();
//        driver.findElement(By.linkText("My Account")).click();
    }

    @Then("^Check My Booking page opened$")
    public void checkMyBookingPageOpened() {
        Assert.assertEquals(driver.findElement(recentBookingHDR).getText(), "Your most recent bookings will show up here.");

    }

    @Then("^No Booking History displayed$")
    public void noBookingHistoryDisplayed() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(noBookingHistoryMSG).getText(), "Sorry there is no booking history");
    }

    @Then("^Back to Home Page when click on Back button$")
    public void backToHomePageWhenClickOnBackButton() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(backToHomeBTN).click();
        Assert.assertEquals(driver.findElement(homePageHDR).getText(), "Low Fares");
    }

    @Then("^The account is verified successfully$")
    public void theAccountIsVerifiedSuccessfully() throws InterruptedException {
        Thread.sleep(8000);
        for (String windowID : driver.getWindowHandles()) {
            String title = driver.switchTo().window(windowID).getTitle();
            if (title.equals("Fly365")) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(myBookingLINK));
                List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Verify your account by your mail')]"));
                assertion = (list.size() == 0);
                break;
            }
        }
        Assert.assertEquals(assertion, true);

    }

    @Then("^The user can see his booking in my Booking$")
    public void theUserCanSeeHisBookingInMyBooking() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastFly365RefVAL));
        Assert.assertEquals(driver.findElement(lastFly365RefVAL).getText(), ConfirmationTest.fly356Refernce);
    }

    @And("^Go to My Booking$")
    public void goToMyBooking() {
        driver.findElement(By.xpath("//*[contains(@class,'el-icon--right svg-icon svg-fill')]")).click();
        driver.findElement(By.linkText("My Account")).click();
    }


    @Then("^Click on Booking details button$")
    public void clickOnBookingDetailsButton() {
        driver.findElement(bookingDetailsBTN).click();

    }

    @Then("^Assert that the booking details is displayed successfully$")
    public void assertThatTheBookingDetailsIsDisplayedSuccessfully() {
       String headerMsg= driver.findElement(bookingHeader).getText();
        Assert.assertEquals(headerMsg, "Available files to download:");
    }
}
