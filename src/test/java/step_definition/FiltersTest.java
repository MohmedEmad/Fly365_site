package step_definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FiltersTest extends TestBase {
    WebDriverWait wait = new WebDriverWait(driver, 30);

    private By airlineFilterBTN = By.xpath("//button[contains(text(),'AIRLINES')]");
    private By closeAirlineFilterBTN = By.xpath("((//div[@role='tooltip'])//*[contains(@class,'cursor-pointer pr-4 svg-icon svg-fill')])[6]");
    private By oneStopTXT = By.xpath("(//span[contains(text(),'One Stop')])[1]");
    private By resetAllFilterBTN = By.xpath("//i[@class='el-icon-close mr-1 bg-primary-fourth rounded-full text-white']");
    private By priceFilterBTN = By.xpath("//button[contains(text(),'PRICE')]");
    private By leftSlider = By.xpath("(//div[@class='el-slider__button-wrapper']//div[@class='el-slider__button el-tooltip'])[6]");
    private By firstPriceRange = By.xpath("(//div[@class='price-label border-secondary-fourth border px-4 py-2 text-black font-medium'])[1]");
    private By secondPriceRange = By.xpath ("(//div[@class='price-label border-secondary-fourth border px-4 py-2 text-black font-medium'])[2]");
    private By tripPrice = By.xpath("(//div[@class='flex justify-between items-center'])[1]");
    private By airportFilterBTN = By.xpath("//button[contains(text(),'AIRPORTS')]");
    private By arrAirportTXT = By.xpath("//span[contains(text(),'Dubai Bus Station')]");
    private By durationFilterBTN = By.xpath("//button[contains(text(),'DURATION')]");
    private By rightSlider = By.xpath("(//div[@class='el-slider__button-wrapper']//div[@class='el-slider__button el-tooltip'])[7]");
    private By durationTime = By.xpath("//span[contains(text(),'Time')]//span");
    private By tripDuration = By.xpath("(//span[@class ='text-xs inline-block text-primary-fourth font-medium mb-1 flight-duration'])[1]");


    @And("^Click on Airline Filter$")
    public void clickOnAirlineFilter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(airlineFilterBTN));
        driver.findElement(airlineFilterBTN).click();
    }

    @And("^Filter with the following Airline \"([^\"]*)\"$")
    public void filterWithTheFollowingAirline(String onlyAirline) throws Throwable {
        Thread.sleep(1000);
        WebElement airlineOnlyElmnt =driver.findElement(By.xpath("//span[contains(text(),'"+onlyAirline+"')]/ancestor::label/following-sibling::button"));
        airlineOnlyElmnt.click();
        Thread.sleep(1000);
        driver.findElement(closeAirlineFilterBTN).click();
    }

    @And("^Assert that search results are only 'One Stop'$")
    public void assertThatSearchResultsAreOnlyOneStop() {
        String headerText = driver.findElement(oneStopTXT).getText();
        Assert.assertEquals(headerText, "One Stop");
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetAllFilterBTN));
        driver.findElement(resetAllFilterBTN).click();

    }

    @And("^Press on 'Price' Filter$")
    public void pressOnPriceFilter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceFilterBTN));
        driver.findElement(priceFilterBTN).click();
    }

    @Then("^Slide the slider to the right$")
    public void slideTheSliderToTheRight() throws InterruptedException {
        WebElement sliderA = driver.findElement(leftSlider);
        Actions move = new Actions(driver);
        move.dragAndDropBy(sliderA,70, 0).click();
        move.build().perform();
    }

    @And("^Assert that search result matches the filtered price$")
    public void assertThatSearchResultMatchesTheFilteredPrice() {
        String firstPriceValue = driver.findElement(firstPriceRange).getText().split(" ")[0].replaceAll(",", "");
        String secondPriceValue = driver.findElement(secondPriceRange).getText().split(" ")[0].replaceAll(",", "");
        float amount1 = Float.parseFloat(firstPriceValue);
        float amount2 = Float.parseFloat(secondPriceValue);
        String totalTripPrice = driver.findElement(tripPrice).getText().split(" ")[0].replaceAll(",", "");
        float totalAmount = Float.parseFloat(totalTripPrice);
        Assert.assertTrue(amount1 <= totalAmount && totalAmount <= amount2);

    }

// methods are commented because airports implementation is not working right

    /*@And("^Click on 'Airport' Filter$")
    public void clickOnAirportFilter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(airportFilterBTN));
        driver.findElement(airportFilterBTN).click();
    }

    @Then("^Filter with the following Airport \"([^\"]*)\"$")
    public void filterWithTheFollowingAirport(String onlyAirport) throws InterruptedException {
        Thread.sleep(1000);
        WebElement airportOnlyElmnt =driver.findElement(By.xpath("//span[contains(text(),'"+onlyAirport+"')]/ancestor::label/following-sibling::button"));
        airportOnlyElmnt.click();
        Thread.sleep(1000);
        driver.findElement(closeAirlineFilterBTN).click();
    }



    @And("^Assert that all search results matched \"([^\"]*)\"$")
    public void assertThatAllSearchResultsMatched(String arrivalAirport) throws Throwable {
        Thread.sleep(1000);
        WebElement selectedAirport =driver.findElement(By.xpath("//span[contains(text(),'"+arrivalAirport+"')]"));
        selectedAirport .click();
        Thread.sleep(1000);
        List<WebElement> arrAirport = driver.findElements(By.xpath("//span[contains(text(),'"+arrivalAirport+"')]"));
        Assert.assertTrue(arrAirport.getText().contains(arrivalAirport));
        for (WebElement airport : arrAirport) {

        }

    }*/


    @And("^Click on 'Duration' Filter$")
    public void clickOnDurationFilter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(durationFilterBTN));
        driver.findElement(durationFilterBTN).click();
    }

    @Then("^Slide the slider to the left$")
    public void slideTheSliderToTheLeft() throws InterruptedException {
        WebElement durationSlider = driver.findElement(rightSlider);
        Actions move = new Actions(driver);
        move.dragAndDropBy(durationSlider,0, 50).click();
        Thread.sleep(2000);
        move.build().perform();
    }

    @And("^Assert that search result duration matches the filtered duration$")
    public void assertThatSearchResultDurationMatchesTheFilteredDuration() {
        String integerOnlyValue1 = driver.findElement(durationTime).getText().trim().replaceAll("[^[^\\d]*(\\d+)]", "");
        float timeDuration = Float.parseFloat(integerOnlyValue1);
        String integerOnlyValue2 = driver.findElement(tripDuration).getText().trim().replaceAll("[^[^\\d]*(\\d+)]", "");
        float flightDuration = Float.parseFloat(integerOnlyValue2);
        Assert.assertTrue(flightDuration <= timeDuration);

    }

}
