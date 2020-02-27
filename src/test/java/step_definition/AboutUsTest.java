package step_definition;

import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;


public class AboutUsTest extends TestBase {


    private By aboutUsHDR = By.xpath("//header/span[1]");

    @Then("^'About Us' page is opened$")
    public void aboutUsPageIsOpened() {
        String headerText = driver.findElement(aboutUsHDR).getText();
        Assert.assertEquals(headerText, "About Fly365");
    }


}
