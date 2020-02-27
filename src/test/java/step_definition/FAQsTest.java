package step_definition;

import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;


public class FAQsTest extends TestBase {


    private By faqsHDRLINK = By.xpath("//a[@class='link text-sm font-normal mr-5 router-link-exact-active router-link-active']");

    @Then("^'FAQs' page is opened$")
    public void faqsPageIsOpened() {
        String headerText = driver.findElement(faqsHDRLINK).getText();
        Assert.assertEquals(headerText, "FAQs");
    }


}
