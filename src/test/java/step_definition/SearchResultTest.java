package step_definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SearchResultTest extends TestBase {
    WebDriverWait wait = new WebDriverWait(driver, 60);

    private By bookThisTripBTN = By.xpath("//div[@class='search-container']/div[2]//button[contains(text(),'BOOK FOR')]");
    private By stopsFilterBTN = By.xpath("//button[contains(text(),'STOPS')]");
    private By onlyOneStopsLINK = By.xpath("//label[span//div[contains(text(), '1')]]/following-sibling::button");
    private By tripPriceVAL = By.xpath("//div[contains(@class,'result-group')][2]//div[contains(@id,'-price')]");
    private By frstHoldBTN = By.xpath("(//div[contains(@class,'flex md:flex-row')]//button[contains(text(),'HOLD')])[1]");

    public static String tripPrice;
    public static String holdPrice;


    @And("^Choose a trip$")
    public void chooseATrip() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookThisTripBTN));
        tripPrice = driver.findElement(tripPriceVAL).getText();
        driver.findElement(bookThisTripBTN).click();
    }

    @And("^Press on 'Stops' Filter$")
    public void pressOnStopsFilter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(stopsFilterBTN));
        driver.findElement(stopsFilterBTN).click();

    }

    @And("^Select 'One Stop' trips$")
    public void selectOneStopTrips() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(onlyOneStopsLINK)).moveToElement(driver.findElement(onlyOneStopsLINK)).click().build().perform();
    }


    @And("^Get the price of the trip$")
    public void getThePriceOfTheTrip() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tripPriceVAL));
        tripPrice = driver.findElement(tripPriceVAL).getText().trim();
    }

    @And("^Click on hold button$")
    public void clickOnHoldButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(frstHoldBTN));
        driver.findElement(frstHoldBTN).click();
    }

    @Then("^Hold With Value \"([^\"]*)\" is displayed in hold button$")
    public void holdWithValueIsDisplayedInHoldButton(String holdValue) throws Throwable {
        WebElement frstHoldElmnt = driver.findElement(frstHoldBTN);
        holdPrice = driver.findElement(frstHoldBTN).getText().trim();
        Assert.assertTrue(frstHoldElmnt.getText().contains(holdValue));
    }
    @Then("^Hold button is not displayed$")
    public void holdButtonIsNotDisplayed() {
        //WebElement frstHoldElmnt = driver.findElement(frstHoldBTN);
        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(frstHoldBTN)));
    }

}
