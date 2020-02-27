package step_definition;

import com.sun.org.apache.xerces.internal.xs.StringList;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.APIUtility;
import helper.DataBase;
import helper.TestBase;
import org.json.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.*;

public class ApplyHoldTest extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 30);
    private By minHoursBefDepTXT = By.xpath("//input[@name='hours before first departure']");
    private By minHoursBefTicTXT = By.name("hours before last ticketing time");
    private By holdHoursTXT = By.name("hold hours");
    private By statusFLD = By.xpath("//select[@name='hold status']");
    private By saveFrstConfigBTN = By.xpath("(//button)[1]");
    private By excAirlineTXT = By.xpath("//textarea[@name='excludedAirlines']");
    private By excAirlineSaveBTN = By.xpath("(//button)[2]");
    private By worlwideHoldValueTXT = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class='col-md-3']/following-sibling::div//input)[1]");
    private By australiaHoldValueTXT = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class='col-md-3']/following-sibling::div//input)[2]");
    private By uaeHoldValueTXT = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class='col-md-3']/following-sibling::div//input)[3]");
    private By nzHoldValueTXT = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class='col-md-3']/following-sibling::div//input)[4]");
    private By myHoldValueTXT = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class='col-md-3']/following-sibling::div//input)[5]");
    private By hkHoldValueTXT = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class='col-md-3']/following-sibling::div//input)[6]");
    private By chinaHoldValueTXT = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class='col-md-3']/following-sibling::div//input)[7]");
    private By worlwideSaveValueBTN = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class=\"col-md-3\"]/following-sibling::div//button)[1]");
    private By australiaSaveValueBTN = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class=\"col-md-3\"]/following-sibling::div//button)[2]");
    private By uaeSaveValueBTN = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class=\"col-md-3\"]/following-sibling::div//button)[3]");
    private By nzSaveValueBTN = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class=\"col-md-3\"]/following-sibling::div//button)[4]");
    private By mySaveValueBTN = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class=\"col-md-3\"]/following-sibling::div//button)[5]");
    private By hkSaveValueBTN = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class=\"col-md-3\"]/following-sibling::div//button)[6]");
    private By chinaSaveValueBTN = By.xpath("(//input[@name = 'storeId']/ancestor::div[@class=\"col-md-3\"]/following-sibling::div//button)[7]");

    private String hostName = "k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432";
    private String dbsHoldCon = "flight_rules";
    private String dbsHoldExc = "hold_excluded_airlines";
    private String dbsHoldFees = "hold_fees";

    private By frstHoldBTN = By.xpath("(//div[contains(@class,'flex md:flex-row')]//button[contains(text(),'HOLD')])[1]");


    @And("^Set Hold Status \"([^\"]*)\"$")
    public void setHoldStatus(String status) throws Throwable {
        Thread.sleep(2000);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("hold status")));
        Select statusDDL = new Select(driver.findElement(statusFLD));
        if (status.equalsIgnoreCase("enabled")) {
            statusDDL.selectByIndex(0);
        } else if (status.equalsIgnoreCase("disabled")) {
            statusDDL.selectByIndex(1);
        }

    }


    @And("^Set Hold for Store \"([^\"]*)\" with Value \"([^\"]*)\"$")
    public void setHoldForStoreWithValue(String storeName, String value) throws Throwable {
        if ((storeName.equalsIgnoreCase("world wide")) || (storeName.equalsIgnoreCase("ww"))) {
            int valueLength = driver.findElement(worlwideHoldValueTXT).getAttribute("value").length();
            for (int i = 0; i < valueLength; i++) {
                driver.findElement(worlwideHoldValueTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
            }
            driver.findElement(worlwideHoldValueTXT).sendKeys(value);
            driver.findElement(worlwideSaveValueBTN).click();
        } else if ((storeName.equalsIgnoreCase("australia")) || (storeName.equalsIgnoreCase("au"))) {
            int valueLength = driver.findElement(australiaHoldValueTXT).getAttribute("value").length();
            for (int i = 0; i < valueLength; i++) {
                driver.findElement(australiaHoldValueTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
            }
            driver.findElement(australiaHoldValueTXT).sendKeys(value);
            driver.findElement(australiaSaveValueBTN).click();
        } else if ((storeName.equalsIgnoreCase("united arab emirates")) || (storeName.equalsIgnoreCase("uae"))) {
            int valueLength = driver.findElement(uaeHoldValueTXT).getAttribute("value").length();
            for (int i = 0; i < valueLength; i++) {
                driver.findElement(uaeHoldValueTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
            }
            driver.findElement(uaeHoldValueTXT).sendKeys(value);
            driver.findElement(uaeSaveValueBTN).click();
        } else if ((storeName.equalsIgnoreCase("new zeeland")) || (storeName.equalsIgnoreCase("nz"))) {
            int valueLength = driver.findElement(nzHoldValueTXT).getAttribute("value").length();
            for (int i = 0; i < valueLength; i++) {
                driver.findElement(nzHoldValueTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
            }
            driver.findElement(nzHoldValueTXT).sendKeys(value);
            driver.findElement(nzSaveValueBTN).click();
        } else if ((storeName.equalsIgnoreCase("malaysia")) || (storeName.equalsIgnoreCase("my"))) {
            int valueLength = driver.findElement(myHoldValueTXT).getAttribute("value").length();
            for (int i = 0; i < valueLength; i++) {
                driver.findElement(myHoldValueTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
            }
            driver.findElement(myHoldValueTXT).sendKeys(value);
            driver.findElement(mySaveValueBTN).click();
        } else if ((storeName.equalsIgnoreCase("hong kong")) || (storeName.equalsIgnoreCase("hk"))) {
            int valueLength = driver.findElement(hkHoldValueTXT).getAttribute("value").length();
            for (int i = 0; i < valueLength; i++) {
                driver.findElement(hkHoldValueTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
            }
            driver.findElement(hkHoldValueTXT).sendKeys(value);
            driver.findElement(hkSaveValueBTN).click();
        } else if ((storeName.equalsIgnoreCase("china")) || (storeName.equalsIgnoreCase("ch"))) {
            int valueLength = driver.findElement(chinaHoldValueTXT).getAttribute("value").length();
            for (int i = 0; i < valueLength; i++) {
                driver.findElement(chinaHoldValueTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
            }
            driver.findElement(chinaHoldValueTXT).sendKeys(value);
            driver.findElement(chinaSaveValueBTN).click();
        }


    }

    @And("^Set Minimum hours before deaparture with value \"([^\"]*)\"$")
    public void setMinimumHoursBeforeDeapartureWithValue(String minHoursDep) throws Throwable {
        Thread.sleep(1500);
        int valueLength = driver.findElement(minHoursBefDepTXT).getAttribute("value").length();
        for (int i = 0; i < valueLength; i++) {
            driver.findElement(minHoursBefDepTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
        }
        driver.findElement(minHoursBefDepTXT).sendKeys(minHoursDep);

    }

    @And("^Set Minimum hours before ticketing with value \"([^\"]*)\"$")
    public void setMinimumHoursBeforeTicketingWithValue(String minHorsTic) throws Throwable {
        Thread.sleep(1500);
        int valueLength = driver.findElement(minHoursBefTicTXT).getAttribute("value").length();
        System.out.println(valueLength);
        for (int i = 0; i < valueLength; i++) {
            driver.findElement(minHoursBefTicTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
        }
        driver.findElement(minHoursBefTicTXT).sendKeys(minHorsTic);
    }

    @And("^Set Excluded Airlines with \"([^\"]*)\"$")
    public void setExcludedAirlinesWith(String excAirline) throws Throwable {
        Thread.sleep(1500);
        int valueLength = driver.findElement(excAirlineTXT).getAttribute("value").length();
        for (int i = 0; i < valueLength; i++) {
            driver.findElement(excAirlineTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
        }
        driver.findElement(excAirlineTXT).sendKeys(excAirline);
        driver.findElement(excAirlineSaveBTN).click();
    }

    @And("^Set Hold Hours with Value \"([^\"]*)\"$")
    public void setHoldHours(String holdHours) throws InterruptedException {
        Thread.sleep(1500);
        int valueLength = driver.findElement(holdHoursTXT).getAttribute("value").length();
        for (int i = 0; i < valueLength; i++) {
            driver.findElement(holdHoursTXT).sendKeys(Keys.chord(Keys.BACK_SPACE));
        }
        driver.findElement(holdHoursTXT).sendKeys(holdHours);
    }

    @And("^Set data for hold rule$")
    public void setDataForHoldRule(DataTable holdData) throws Throwable {

        wait.until(ExpectedConditions.visibilityOfElementLocated(saveFrstConfigBTN));

        for (Map<String, String> holdRuleData : holdData.asMaps(String.class, String.class)) {
            //driver.findElement(minHoursBefDepTXT).sendKeys(paymentCardDetails.get("Min hours before departure"));
            //driver.findElement(minHoursBefTicTXT).sendKeys(paymentCardDetails.get("Min hours before ticketing"));
            setMinimumHoursBeforeDeapartureWithValue(holdRuleData.get("Min hours before departure"));
            setMinimumHoursBeforeTicketingWithValue(holdRuleData.get("Min hours before ticketing"));
            setHoldHours(holdRuleData.get("Hold hours"));
            setHoldStatus(holdRuleData.get("Hold status"));
            clickSave();
            setExcludedAirlinesWith(holdRuleData.get("Exc airlines"));
            driver.findElement(excAirlineSaveBTN).click();
        }

    }

    @And("^Click save$")
    public void clickSave() {
        driver.findElement(saveFrstConfigBTN).click();
    }


    @Given("^Set hold data for store \"([^\"]*)\" in database$")
    public void setHoldDataInDatabase(String store, DataTable holdData) {
        boolean reqHoldStatus;
        String appliedHoldStore = null;
        System.out.println(store);
        if (store.equalsIgnoreCase("nz")) {
            appliedHoldStore = "fly365_nz";
        } else if (store.equalsIgnoreCase("au")) {
            appliedHoldStore = "fly365_au";
        } else if (store.equalsIgnoreCase("my")) {
            appliedHoldStore = "fly365_my";
        } else if (store.equalsIgnoreCase("ww")) {
            appliedHoldStore = "fly365_com";
        } else if (store.equalsIgnoreCase("cn")) {
            appliedHoldStore = "fly365_cn";
        } else if (store.equalsIgnoreCase("hk")) {
            appliedHoldStore = "fly365_hk";
        } else if (store.equalsIgnoreCase("ae")) {
            appliedHoldStore = "fly365_ae";
        }
        for (Map<String, String> holdRuleData : holdData.asMaps(String.class, String.class)) {
            if (holdRuleData.get("Hold status").equalsIgnoreCase("enabled")) {
                reqHoldStatus = true;
            } else {
                reqHoldStatus = false;
            }
            DataBase.execute_update(hostName, dbsHoldCon, "UPDATE hold_configs SET \"hoursBeforeFirstDeparture\"=" + holdRuleData.get("Min hours before departure") + " ," +
                    " \"hoursBeforeLastTicketingTime\" = " + holdRuleData.get("Min hours before ticketing") + "," +
                    " \"isHoldEnabled\" = " + reqHoldStatus + "," +
                    " \"holdHours\" = " + holdRuleData.get("Hold hours") + " " +
                    "WHERE \"id\"=\'de896fb8-358a-4615-bd7c-ef9c5c1f9761\'");
            System.out.println(holdRuleData.get("Exc airlines"));
            DataBase.execute_update(hostName, dbsHoldCon, "UPDATE hold_excluded_airlines SET \"code\" ='" + holdRuleData.get("Exc airlines") + "' WHERE \"id\" = \'49fab3e8-add9-45a1-aa10-ee3c8e344f14\'");
            DataBase.execute_update(hostName, dbsHoldCon, "UPDATE hold_fees SET \"amount\" ='" + holdRuleData.get("hold value") + "' WHERE \"storeId\" = '" + appliedHoldStore + "'");

        }


    }

    @Then("^Assert itineraries has hold$")
    public void assertItinerariesHasHold() {
        List<JSONArray> itineraries = APIUtility.jsonPathEvaluator.getList("itineraries");
        List<Boolean> itinerariesAvailability = APIUtility.jsonPathEvaluator.getList("itineraries.hold.isAvailable");
        System.out.println(itinerariesAvailability);
        String id = "";
        for (int c = 0; c < itineraries.size(); c++) {
            Boolean itenaryAvail = APIUtility.jsonPathEvaluator.getBoolean("itineraries[" + c + "].hold.isAvailable");
            if (itenaryAvail) {
                id = APIUtility.jsonPathEvaluator.get("itineraries[" + c + "].itineraryId").toString();
                break;
            }
        }
        Assert.assertTrue(itinerariesAvailability.contains(true));
    }

    @Then("^Itineraries does not have hold$")
    public void itinerariesDoesNotHaveHold() {
        List<Boolean> itinerariesAvailability = APIUtility.jsonPathEvaluator.getList("itineraries.hold.isAvailable");
        System.out.println(itinerariesAvailability);
        Assert.assertFalse(itinerariesAvailability.contains(true));
    }


    @Given("^Set Data on (.*) for hold Rule API through (.*) and exclude (.*)$")
    public void setDataForHoldRule(String domain, String StoreId, String excludedAirlines,  DataTable Data) {
        for (Map<String, String> holdRuleData : Data.asMaps(String.class, String.class)) {
            String departureHours = holdRuleData.get("Min hours before departure");
            String ticketingHours = holdRuleData.get("Min hours before ticketing");
            String holdHours = holdRuleData.get("Hold hours");
            String status =  holdRuleData.get("Hold status");
            String holdAmount = holdRuleData.get("hold value");
            List<String> exairlines =  new ArrayList<>(Arrays.asList(excludedAirlines.split(",")));
            APIUtility.updateHoldRule(domain, Integer.valueOf(holdHours), Integer.valueOf(ticketingHours), Integer.valueOf(departureHours), Boolean.valueOf(status), Integer.valueOf(holdAmount), StoreId, exairlines);
        }
    }





    }


