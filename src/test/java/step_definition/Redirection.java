package step_definition;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.GeneralMethods;
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



    @Given("^Navigate to Fly \"([^\"]*)\" site$")
    public void navigateToFlySite(String site) {
        driver.navigate().to("https://hub.fly365" + site + ".com/en");


    }

    @When("^login to hub with super admin$")
    public void loginHubWithSuperAdmin() {
        driver.findElement(HubLoginEmailTXT).sendKeys("john.smith.fly365@gmail.com");
        driver.findElement(HubLoginPasswordTXT).sendKeys("@Fly10200");
        driver.findElement(HubLoginBTN).click();
        String WelcomeMSG = driver.findElement(HubDashWelcomeMSG).getText();
        Assert.assertEquals(WelcomeMSG, "Welcome to Fly365 Hub");
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


}