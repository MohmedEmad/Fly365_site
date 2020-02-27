package step_definition;

import cucumber.api.java.en.And;
import helper.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;


public class AFTATest extends TestBase {

    boolean result;
    private By aftaHDR = By.xpath("//div[@id='page_content']/h3");


    @And("^'afta' page is opened$")
    public void AftaPageIsOpened() throws InterruptedException {
        Thread.sleep(5000);
        for (String windowID : driver.getWindowHandles()) {
            String title = driver.switchTo().window(windowID).getTitle();
            if (title.equals("Australian Federation of Travel Agents")) {
                String headerText = driver.findElement(aftaHDR).getText().trim();
                result = (headerText.equals("WELCOME TO AFTA"));
                break;
            }
        }

        Assert.assertEquals(result, true);
    }

}
