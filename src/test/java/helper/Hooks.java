package helper;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import step_definition.HomeTest;

import java.io.File;
import java.io.IOException;

public class Hooks extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 20);


    @After
    public void takeScreenShot(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File scrFile = ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/Screenshots/" + scenario.getName() + ".jpg"));
        }
    }

    @After("@Go_Tab_Again")
    public void goToTheCurrentTab() {
        for (String windowID : driver.getWindowHandles()) {
            String title = driver.switchTo().window(windowID).getTitle();
            if (title.equals("Fly365")) {
                break;
            }
        }
    }

    @After("@Sign_Out")
    public void SignOut() {
        if (driver.findElement(By.xpath("//div/div[2]/span")).getText().trim().equals("John")) {
            driver.findElement(By.xpath("//span[contains(@class, 'el-dropdown-link capitalize text-xs text-white el-dropdown-selfdefine')]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Sign Out')]")));
            driver.findElement(By.xpath("//li[contains(text(),'Sign Out')]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='SIGN IN']")));
        }

    }

    @After("@New_Tab")
    public void closeTheNewTab() {
        if (driver.getWindowHandles().size() > 1) {
            driver.close();
        }
        driver.switchTo().window(HomeTest.currentWindow);
    }


    @After("@Email_Logout")
    public void emailLogout() throws InterruptedException {
        driver.findElement(By.xpath("//div[2]/div[3]//a/span")).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Sign out']")));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='Sign out']")).click();
    }


    @After
    public void clearTheCash() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.sessionStorage.clear();");
        js.executeScript("window.localStorage.clear();");
        driver.manage().deleteAllCookies();
    }

    @After("@delete_pdf")
    public void removeTheDownloadedPDF() {
        File dir = new File(System.getProperty("user.dir") + "/Downloads/");
        File[] myFiles = dir.listFiles();
        for (File file : myFiles) {
            file.delete();
        }
    }
}
