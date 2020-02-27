package step_definition;

import cucumber.api.java.en.And;
import helper.APIUtility;
import helper.TestBase;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplyModifyTest extends TestBase {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    APIUtility apiObj = new APIUtility();


    @And("^Modify the booking in store \"([^\"]*)\" environment \"([^\"]*)\"$")
    public void modifyTheBookingInStoreEnvironment(String store, String environment) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String modifyApiUrl = "https://"+store+".fly365"+environment+".com/api/flight-search/modify";
        apiObj.modifyBookingAPI(modifyApiUrl);
        //throw new PendingException();
    }


}
