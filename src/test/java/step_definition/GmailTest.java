package step_definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.EmailUtililty;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import java.util.ArrayList;

public class GmailTest extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 10);
    EmailUtililty emailUtililty = new EmailUtililty("john.smith.fly365@gmail.com", "@Fly365@Fly365", "smtp.gmail.com", EmailUtililty.EmailFolder.INBOX);

   By emailTXT = By.id("identifierId");
   By emailNextBTN = By.id("identifierNext");
    By passwordTXT = By.xpath("//input[@name='password']");
    By passwordNextBTN = By.id("passwordNext");
    By menuLINK = By.xpath("//*[@id='gbwa']/div/a");
    By gmailLink = By.xpath("//*[@id='gb23']");
    By firstMessageLINK = By.xpath("//div[2]/span//span[contains(text(), 'Fly365')]");
    By cllickHereLINK = By.xpath("//p[contains(text(),'Unsubscribe please')]/child::a");
    By verifyBTN = By.xpath("//table//table//table//td/a");
    By resetPasswordBTN = By.xpath("//table//table//table//td/a");

    public GmailTest() throws MessagingException {
    }

    @And("^Go to the email account$")
    public void goToTheEmailAccount() throws InterruptedException {
        Thread.sleep(2000);
//        driver.navigate().to("https://accounts.google.com");
        driver.navigate().to("https://mail.google.com");
        driver.findElement(emailTXT).sendKeys("john.smith.fly365@gmail.com");
        driver.findElement(emailNextBTN).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordTXT));
        driver.findElement(passwordTXT).sendKeys("@Fly365@Fly365");
        driver.findElement(passwordNextBTN).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuLINK));
        driver.findElement(menuLINK).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(gmailLink));
        driver.findElement(gmailLink).click();

    }

    @And("^Open the new message$")
    public void openTheNewMessage() {
       try{
           wait.until(ExpectedConditions.visibilityOfElementLocated(firstMessageLINK));
           driver.findElement(firstMessageLINK).click();

       }catch(Exception e){
           driver.findElement(firstMessageLINK).click();

       }

    }

    @And("^Delete all messages in the Inbox$")
    public void deleteAllMessagesInTheInbox() throws MessagingException {
        emailUtililty.deleteAllMessage();
    }

    @And("^Press on 'Click Here' link$")
    public void pressOnClickHereLink() throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOfElementLocated(cllickHereLINK));
        driver.findElement(cllickHereLINK).click();


        Thread.sleep(5000);
        for (String windowID : driver.getWindowHandles()) {
            String title = driver.switchTo().window(windowID).getTitle();
            if (title.equals("Fly365 - Unsubscribe")) {
                driver.close();
                break;
            }
        }
        driver.switchTo().window(HomeTest.currentWindow);


        driver.findElement(By.xpath("//div[2]/div[3]//a/span")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Sign out']")));
        driver.findElement(By.xpath("//a[text()='Sign out']")).click();
    }

    @And("^Press on Verify Button in the email$")
    public void pressOnVerifyButtonInTheEmail() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(verifyBTN));
        driver.findElement(verifyBTN).click();
    }

    @Then("^The Account Verification Success email is sent successfully$")
    public void theAccountVerificationSuccessEmailIsSentSuccessfully() throws Exception {
        Thread.sleep(10000);
        boolean result = emailUtililty.isMessageInFolder("Account Verification Success", true);
        Assert.assertEquals(result,true);
    }


    @Then("^Verify your email is sent successfully$")
    public void verifyYourEmailIsSentSuccessfully() throws Exception {
        Thread.sleep(8000);
        boolean result = emailUtililty.isMessageInFolder("Verify your email", true);
        Assert.assertEquals(result,true);
    }


    @Then("^Contact us email is sent successfully$")
    public void contactUsEmailIsSentSuccessfully() throws Exception {
        Thread.sleep(8000);
        boolean result = emailUtililty.isMessageInFolder("Contact Us Email", true);
        Assert.assertEquals(result,true);
    }


    @Then("^The Subscription Email is sent successfully$")
    public void theSubscriptionEmailIsSentSuccessfully() throws Exception {
        Thread.sleep(8000);
        boolean result = emailUtililty.isMessageInFolder("Subscription Email", true);
        Assert.assertEquals(result,true);
    }

    @Then("^The Password reset requested email is sent successfully$")
    public void thePasswordResetRequestedEmailIsSentSuccessfully() throws Exception {
        Thread.sleep(11000);
        boolean result = emailUtililty.isMessageInFolder("Password reset requested", true);
        Assert.assertEquals(result,true);
    }

    @And("^Press on Reset Password Button in the email$")
    public void pressOnResetPasswordButtonInTheEmail() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordBTN));
        driver.findElement(resetPasswordBTN).click();
    }

    @Then("^The Password reset successfully email is sent successfully$")
    public void thePasswordResetSuccessfullyEmailIsSentSuccessfully() throws Exception {
        Thread.sleep(11000);
        boolean result = emailUtililty.isMessageInFolder("Password reset successfully", true);
        Assert.assertEquals(result,true);
    }

    @Then("^Booking Confirmation email is displayed$")
    public void bookingConfirmationEmailIsDisplayed() throws Exception {
        Thread.sleep(23000);
        Message message = emailUtililty.getMessagesBySubject("Booking Confirmation", true, 1)[0];
        boolean result = emailUtililty.isTextInMessage(message,ConfirmationTest.fly356Refernce);
        Assert.assertEquals(result, true);
    }

    @Then("^Booking Confirmation email contains 'Tax Invoice' and 'Booking Confirmation' pdf$")
    public void bookingConfirmationEmailContainsTaxInvoiceAndBookingConfirmationPdf() throws Exception {
        Thread.sleep(23000);
        ArrayList<String> actualpdfNames = new ArrayList<String>();
        Message message = emailUtililty.getMessagesBySubject("Booking Confirmation", true, 1)[0];
        Multipart multipart = (Multipart) message.getContent();
        for (int i = 0; i < multipart.getCount(); i++) {
            MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(i);
            if (part.getFileName() != null) {
                actualpdfNames.add(part.getFileName());
            }
        }
        String[] expectedpdfNames = {"Tax Invoice.pdf", "Booking Confirmation.pdf"};
        Assert.assertEquals(actualpdfNames.toArray(), expectedpdfNames);
    }
}
