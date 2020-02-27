package step_definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.DataBase;
import helper.GeneralMethods;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddTraveller extends TestBase {
    WebDriverWait wait = new WebDriverWait(driver, 60);

    private GeneralMethods gmObject = new GeneralMethods();

    private By TravellerTAB = By.xpath("//a[@class='account-links__link text-sm flex items-center link link-with-icon mr-5']");
    private By AddTravellerBTN = By.xpath("//button[@class='btn px-3 py-2 btn-primary-second m-auto mt-0']");
    private By TravellerHeaderTXT = By.xpath("//h3[@class='text-xl font-semibold']");
    private By SaveTravellerBTN = By.xpath("//button[@class='btn btn-primary-second w-full btn-add-traveller']");
    private By TravellerTitleDrop = By.xpath("//input[@placeholder='Title']");
    private By MRTitle = By.xpath("//div[1]/div[1]/ul[1]/li[span[text()='Mr']]");
    private By TravellerFirstNameTXT = By.xpath("//input[@placeholder='First name']");
    private By TravellerFamileyNameTXT = By.xpath("//input[@placeholder='Family name']");
    private By TravellerBirthDateDayDrop = By.xpath("//body//div//div//div//div//div//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//div[1]//input[1]");
    private By Day1 = By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/ul[1]/li[2]");
    private By TravellerBirthDateMonth = By.xpath("//body//div//div//div//div//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//div[2]//div[1]//input[1]");
    private By Month1 = By.xpath("/html[1]/body[1]/div[6]/div[1]/div[1]/ul[1]/li[4]");
    private By TravellerBirthDateYear = By.xpath("//body//div//div//div//div//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//div[3]//div[1]//input[1]");
    private By Year1 = By.xpath("/html[1]/body[1]/div[7]/div[1]/div[1]/ul[1]/li[2]");
    private By SaveTravellerInnerBTN = By.xpath("//button[@class='btn btn-primary-second w-full btn-add-traveller']");
    private By AddedTraveller = By.xpath("//label[contains(text(),'ID Type')]");
    private By deleteTravelerBTN = By.xpath("//span[text()='Delete']");
    private By confirmDeleteBTN = By.xpath("//button[text()='Confirm']");
    private By noTravelersFoundMSG = By.xpath("//h3[text()='Sorry you have not added a traveller']");
    private By successMessageTXT = By.xpath("//div[@class='el-notification__group is-with-icon']");
    private By editTravelerBTN = By.xpath("//span[contains(text(),'Edit')]");
    private By editSaveBTN = By.xpath("//button[contains(text(),'Edit')]");
    private By cancelBTN = By.xpath("//button[contains(text(),'CANCEL')]");

    private String hostName = "k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432";
    private String dbsName = "user_api";


    @And("^User press on traveller tab$")
    public void userPressOnTravellerTab() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TravellerTAB));
        driver.findElement(TravellerTAB).click();
        String headerText = driver.findElement(TravellerHeaderTXT).getText();
        Assert.assertEquals(headerText, "Traveller Details");
    }


    @And("^user press on add traveller button$")
    public void userPressOnAddTravellerButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(AddTravellerBTN));
        driver.findElement(AddTravellerBTN).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SaveTravellerBTN));
        String SaveBTNText = driver.findElement(SaveTravellerBTN).getText();
        Assert.assertEquals(SaveBTNText, "Save");


    }

    @And("^user add traveller$")
    public void userEnterTravellerTitle() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TravellerFirstNameTXT));
        gmObject.selectFromDDL(TravellerTitleDrop, "Mr");
        driver.findElement(TravellerFirstNameTXT).sendKeys("john");
        driver.findElement(TravellerFamileyNameTXT).sendKeys("smith");
        gmObject.selectFromDDL(TravellerBirthDateDayDrop, "7");
        gmObject.selectFromDDL(TravellerBirthDateMonth, "June");
        gmObject.selectFromDDL(TravellerBirthDateYear, "1988");
        driver.findElement(SaveTravellerInnerBTN).click();
        driver.findElement(cancelBTN).click();
        Assert.assertTrue(driver.findElement(AddedTraveller).isDisplayed());
    }

    @And("^Delete new traveller from database$")
    public void deleteNewTravellerFromDatabase() {
        DataBase.execute_query_dbs(hostName, dbsName, "delete from travellers where 'firstName'='johnnnn'");

    }

    @And("^User delete the traveler$")
    public void userPressOnDeleteButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteTravelerBTN));
        driver.findElement(deleteTravelerBTN).click();
        driver.findElement(confirmDeleteBTN).click();
    }

    @Then("^Success message is display$")
    public void successMessageIsDisplayed() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessageTXT));
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(successMessageTXT).isDisplayed());
    }

    @Then("^Deleted user is removed from the list$")
    public void deletedUserIsRemovedFromTheList() {
        Assert.assertEquals(driver.findElement(noTravelersFoundMSG).getText(), "Sorry you have not added a traveller");
    }

    @And("^User edit the saved traveler$")
    public void userEditTheSavedTraveler() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(editTravelerBTN));
        driver.findElement(editTravelerBTN).click();
        driver.findElement(TravellerFirstNameTXT).sendKeys(Keys.chord(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE));
        driver.findElement(TravellerFirstNameTXT).sendKeys("david");
        driver.findElement(editSaveBTN).click();
    }

    @Then("^The traveler displayed updated$")
    public void theTravelerDisplayedUpdated() {
        Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'david')]")).isDisplayed());
    }
}
