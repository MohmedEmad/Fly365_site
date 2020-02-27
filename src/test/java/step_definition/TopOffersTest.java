package step_definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TopOffersTest extends TestBase {
    WebDriverWait wait = new WebDriverWait(driver, 10);

    private By bestOffersHDR = By.xpath("//h3[contains(text(),'Best Offers')]");
    private By firstOfferLNK = By.xpath("//h4[contains(text(),'upload offer')]");
    private By firstOfferHDR = By.xpath("//span[contains(text(),'upload offer')]");
    private By OffersLNK = By.xpath("(//span[contains(text(),'Offers')])[1]");
    private By OfferViewBTN2 = By.xpath("(//div[@class='btn-details absolute']//a[contains(text(),'View details')])[2]");
    private By OfferViewBTN1 = By.xpath("(//div[@class='btn-details absolute']//a[contains(text(),'View details')])[1]");
    private By StoresDDL = By.xpath("(//span[@role='button'])[1]");
    private By StoreWWW = By.xpath("//ul[@class='el-dropdown-menu el-popper']//li[contains(text(),'Worldwide')]");
    private By ErrorPageHDR = By.xpath("//div[@class='text-2xl font-bold pt-24 text-white text-center']");




    @Given("^Click on one offer from top offers$")
    public void clickOnOneOfferFromTopOffers() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bestOffersHDR));
        driver.findElement(firstOfferLNK).click();
    }

    @Then("^Check the selected offer page open$")
    public void checkTheSelectedOfferPageOpen() {
        Assert.assertEquals(driver.findElement(firstOfferHDR).getText(), "NZ Offers 60");
    }

    @Given("^Click on offer link$")
    public void clickOnOfferLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(OffersLNK));
        driver.findElement(OffersLNK).click();
    }

        @Then("^Click on view details button$")
        public void clickOnViewDetailsButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(OfferViewBTN2));
        driver.findElement(OfferViewBTN2).click();
    }

        @And("^Change store$")
        public void changeStore() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(StoresDDL));
        driver.findElement(StoresDDL).click();
        Thread.sleep(1000);
        driver.findElement (StoreWWW).click();

    }
    @Then("^Assert that (\\d+) error page is displayed$")
    public void assertThatErrorPageIsDisplayed(int arg0) {
        String headerText = driver.findElement(ErrorPageHDR).getText();
        Assert.assertEquals(headerText, "Oops! Why Are You Here ?");
    }



    @Then("^Check current URL matches the offer page$")
    public void checkCurrentURLMatchesTheOfferPage() {
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://nz.fly365stage.com/en/offers" );
    }


}
