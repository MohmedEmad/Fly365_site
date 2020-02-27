package step_definition;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.DataBase;
import helper.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Map;

public class SignUpTest extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 50);

    private By signinBTN = By.xpath("//a[contains(text(),'SIGN IN')]");
    private By signUpHDR = By.xpath("//div[@class='text-xs mb-8 text-primary-fourth']");
    private By signUpBTN = By.xpath("//a[@class='text-primary-second link-sign-up font-semibold no-underline']");
    private By firstNameTXT = By.xpath("//input[@placeholder='First Name']");
    private By lastNameTXT = By.xpath("//input[@placeholder='Family Name']");
    private By emailTXT = By.xpath("//input[@placeholder='john@example.com']");
    private By passwordTXT = By.xpath("//input[@placeholder='******************']");
    private By creatAccountBTN = By.xpath("//button[contains(text(),'CREATE ACCOUNT')]");
    private By validationNameErrorMSG = By.xpath("//span[text()='Name must be letters only']");
    private By validationEmailErrorMSG = By.xpath("//span[text()='Please enter a valid email']");
    private By validationPasswordErrorMSG = By.xpath("//span[text()='Password length must be between 8 to 50 characters']");
    private By firstNameRequiredErrorMSG = By.xpath("//span[text()='Please enter first name']");
    private By lastNameRequiredErrorMSG = By.xpath("//span[text()='Please enter family name']");
    private By emailRequiredErrorMSG = By.xpath("//span[contains(text(),'Please enter a valid email')]");
    private By passwordRequiredErrorMSG = By.xpath("//span[text()='Please enter password']");
    private By emailExitErrorMSG = By.xpath("//span[text()='email already existed']");
    private By showPasswordBTN = By.xpath("//span[text()='Show']");
    private By hidePasswordBTN = By.xpath("//span[text()='Hide']");
    private By passwordDisplayedLBL = By.xpath("//div[@class='password-input el-input']/input[@type='text']");
    private By passwordNotDisplayedLBL = By.xpath("//div[@class='password-input el-input']/input[@type='password']");




    @And("Open Sign up page")
    public void openSignUpPage() {
        driver.findElement(signinBTN).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpBTN));
        driver.findElement(signUpBTN).click();
    }


    @And("^Fill required data \"(.*)\", \"(.*)\", \"(.*)\", \"(.*)\"$")
    public void fillRequiredData(String fName, String lName, String eMAIL, String password) {
        driver.findElement(firstNameTXT).sendKeys(fName);
        driver.findElement(lastNameTXT).sendKeys(lName);
        driver.findElement(emailTXT).sendKeys(eMAIL);
        driver.findElement(passwordTXT).sendKeys(password);
    }


    @And("^Fill the following required data$")
    public void fillTheFollowingRequiredData(DataTable usersData) {
        for (Map<String, String> userData : usersData.asMaps(String.class, String.class)) {
            driver.findElement(firstNameTXT).sendKeys(userData.get("First Name"));
            driver.findElement(lastNameTXT).sendKeys(userData.get("Last Name"));
            driver.findElement(emailTXT).sendKeys(userData.get("Email Address"));
            driver.findElement(passwordTXT).sendKeys(userData.get("Password"));
        }
    }

    @And("Click on Create Account")
    public void clickOnCreateAccount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(creatAccountBTN));
        driver.findElement(creatAccountBTN).click();
    }


    @And("The new record set on database")
    public void theNewRecordSetOnDatabase() {
        DataBase.execute_query_dbs("k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432", "user_api", "select * from users where email='john.smith.fly365@gmail.com'");
        Assert.assertEquals(DataBase.data, "john.smith.fly365@gmail.com");
        DataBase.execute_query_dbs("k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432", "user_api", "delete from users where email='john.smith.fly365@gmail.com'");
    }

    @And("^Click on Show beside password$")
    public void clickOnShowBesidePassword() {
        driver.findElement(showPasswordBTN).click();
    }


    @And("^Click on Hide beside password$")
    public void clickOnHideBesidePassword() {
        driver.findElement(hidePasswordBTN).click();
    }


    @Then("The user created successfully")
    public void theUserCreatedSuccessfully() {
        driver.getTitle().contains("Fly365");
    }

    @Then("^'Sign Up' page will be opened$")
    public void signUpPageWillBeOpened() {
        String headerText = driver.findElement(signUpHDR).getText();
        Assert.assertEquals(headerText, "Sign up");
    }

    @Then("^Sign Up page is opened$")
    public void signUpPageIsOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpHDR));
        String headerText = driver.findElement(signUpHDR).getText();
        Assert.assertEquals(headerText, "Sign up");
    }

    @Then("The system display validation messages for all mandatory fields")
    public void theSystemDisplayValidationMessagesForAllMandatoryFields() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameRequiredErrorMSG));
        String fErrorMSG = driver.findElement(firstNameRequiredErrorMSG).getText();
        Assert.assertEquals(fErrorMSG, "Please Enter First Name");

        String lErrorMSG = driver.findElement(lastNameRequiredErrorMSG).getText();
        Assert.assertEquals(lErrorMSG, "Please Enter Family Name");

        String eErrorMSG = driver.findElement(emailRequiredErrorMSG).getText();
        Assert.assertEquals(eErrorMSG, "Please enter email");

        String pErrorMSG = driver.findElement(passwordRequiredErrorMSG).getText();
        Assert.assertEquals(pErrorMSG, "Please enter password");
    }

    @Then("^The system should display validation message for invalid name$")
    public void theSystemShouldDisplayValidationMessageForInvalidName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(validationNameErrorMSG));
        String error_message = driver.findElement(validationNameErrorMSG).getText();
        Assert.assertEquals(error_message, "Name Must Be Letters Only");
    }

    @Then("^The system should display validation message for invalid email$")
    public void theSystemShouldDisplayValidationMessageForInvalidEmail() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(validationEmailErrorMSG));
        String error_message = driver.findElement(validationEmailErrorMSG).getText();
        Assert.assertEquals(error_message, "Please enter a valid email");
    }

    @Then("^The system should display validation message for invalid password$")
    public void theSystemShouldDisplayValidationMessageForInvalidPassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(validationPasswordErrorMSG));
        String pass_error = driver.findElement(validationPasswordErrorMSG).getText();
        Assert.assertEquals(pass_error, "password is too short (minimum is 8 characters)");
    }

    @Then("^The system display validation message for email already exist$")
    public void theSystemDisplayValidationMessageForEmailAlreadyExist() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailExitErrorMSG));
        String emailError = driver.findElement(emailExitErrorMSG).getText();
        System.out.println(emailExitErrorMSG);
        Assert.assertEquals(emailError, "email already existed");
    }


    @Then("^The password should display$")
    public void thePasswordShouldDisplay() {
        Assert.assertTrue(true, String.valueOf(driver.findElement(passwordDisplayedLBL).isDisplayed()));
    }


    @Then("^The password should hide$")
    public void thePasswordShouldHide() {
        Assert.assertTrue(true, String.valueOf(driver.findElement(passwordNotDisplayedLBL).isDisplayed()));
    }

    @And("^Delete the user \"(.*)\" if he exists in the database$")
    public void deleteTheUserIfHeExistsInTheDatabase(String userEmail) {
        DataBase.execute_query_dbs("k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432","user_api", "select * from users where email = '" + userEmail + "'");
        if (DataBase.data != null) {
            DataBase.execute_query_dbs("k8stage1.cl9iojf4kdop.eu-west-1.rds.amazonaws.com:5432", "user_api", "delete from users where email='" + userEmail + "'");
        }
    }


}
