package step_definition;

import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;


public class TermsConditionsTest extends TestBase {

    private boolean result;
    private By termsConditionsHDRLINK = By.xpath("//a[@class='link text-sm font-normal mr-5 router-link-exact-active router-link-active']");

    @Then("^'Terms and Conditions' page is opened$")
    public void termsAndConditionsPageIsOpened() {
        String headerText = driver.findElement(termsConditionsHDRLINK).getText();
        Assert.assertEquals(headerText, "Terms and Conditions");
    }


    @Then("^'Terms and conditions' page will be opened$")
    public void termsAndConditionsPageWillBeOpened() throws InterruptedException {
        Thread.sleep(5000);
        for (String windowID : driver.getWindowHandles()) {
            String title = driver.switchTo().window(windowID).getTitle();
            if (title.equals("Fly365")) {
                String headerText = driver.findElement(termsConditionsHDRLINK).getText();
                result = headerText.equals("Terms and Conditions");
                break;
            }
        }
        Assert.assertTrue(result);
    }
}
