package step_definition;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Redirection extends TestBase {
    WebDriverWait wait = new WebDriverWait(driver, 30);

    private By HubLoginEmailTXT = By.xpath("//input[@id='inputEmail']");
    private By HubLoginPasswordTXT = By.xpath("//input[@id='inputPassword']");
    private By HubLoginBTN = By.xpath("//button[@class='btn btn-lg btn-primary btn-block']");
    private By HubDashWelcomeMSG = By.xpath("//h2[@class='d-flex justify-content-center align-items-center text-primary dashboard__title']");
    private By Menu = By.xpath("//div[@class='el-submenu__title']//img");
    private By settingsBTN = By.xpath("//i[@class='icon-settings']");
    private By holdSettingsBTN = By.xpath("//li[text()='Hold Settings']");



    @Given("^Navigate to Fly \"([^\"]*)\" site$")
    public void navigateToFlySite(String site) {
        driver.navigate().to("https://hub.fly365" + site + ".com/en");


    }

    @And("^Open menu$")
    public void openMenu() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Menu));
        driver.findElement(Menu).click();
    }

    @And("^Open  \"([^\"]*)\"$")
    public void open(String Systems)  {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),"+ Systems+")]")));
        driver.findElement(By.xpath("//span[contains(text(),'"+ Systems+"')]")).click();

    }


    @Then("^Assert that \"([^\"]*)\" URL \"([^\"]*)\" is opened successfully$")
    public void assertThatURLIsOpenedSuccessfully(String Systems, String site) throws Throwable {
        Thread.sleep(2000);
        String URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("https://"+Systems+".fly365" + site + ".com/"));
        throw new PendingException();
    }

    @And("^Open Hold Settings$")
    public void openHoldSettings() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(settingsBTN));
        driver.findElement(settingsBTN).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(holdSettingsBTN));
        driver.findElement(holdSettingsBTN).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div//h6[@class='title'])[1]")));
    }

}