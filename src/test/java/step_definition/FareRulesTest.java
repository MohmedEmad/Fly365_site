package step_definition;

import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;

public class FareRulesTest extends TestBase {

    private boolean result;
    private By fareRulesHDR = By.xpath("//h1");

    @Then("^'Fare Rules' page will be opened$")
    public void fareRulesPageWillBeOpened() throws InterruptedException {
        Thread.sleep(5000);

        for (String windowID : driver.getWindowHandles()) {
            String title = driver.switchTo().window(windowID).getTitle();
            if (title.equals("Fly365 - Fare rules")) {
                String headerText = driver.findElement(fareRulesHDR).getText();
                result = (headerText.equals("Fare Rules"));
                break;
            }
        }
        Assert.assertTrue(result);
    }
}
