package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GeneralMethods extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 10);

    // This general method for drop down list in website //
    public void selectFromDDL(By field, String value) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(field));
        driver.findElement(field).click();
        Thread.sleep(2000);
        List<WebElement> allDDL = driver.findElements(By.xpath("//div[@class='el-select-dropdown el-popper']"));
        WebElement lastDDL = allDDL.get(allDDL.size() - 1);
        lastDDL.findElement(By.xpath("./div[1]/div[1]/ul[1]/li[span[contains(text(), '" + value + "')]]")).click();
    }

    // This general method for drop down list in Hub //
    public void selectFromDDLHub(String field, String value) throws InterruptedException {
        driver.findElement(By.xpath("//div[@name='"+field+"']//i[@class='open-indicator']")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//span[contains(normalize-space(text()),'"+value+"')]")).click();

    }

    public void selectFromAutoCompleteDDL(String value) throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> allDDL = driver.findElements(By.xpath("//div[@class='el-autocomplete-suggestion el-popper search-autocomplete']"));
        WebElement lastDDL = allDDL.get(allDDL.size() - 1);
        lastDDL.findElement(By.xpath("./div[1]/div[1]/ul[1]/li[div[contains(text(), '" + value + "')]]")).click();
    }


    public String addDateWithCertainPeriodAndFormat(int period, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, period);
        SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern);
        String date = simpleFormat.format(cal.getTime());
        return date;
    }

    // Methods for dynamic pdf
    public String changeFaretoDecimalFormat(By element) {
        String numberAsStringValue = driver.findElement(element).getText().trim().replaceAll("[a-zA-Z\\s\\,]", "");
        Float numberAsFloatValue = Float.parseFloat(numberAsStringValue);
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        String numberAsStringWithTwoDecimals = df.format(numberAsFloatValue);
        return numberAsStringWithTwoDecimals;
    }

    public String getFarePerPassenger(String fare, String tax) {
        Double totalFareperPassenger = Double.valueOf(fare) + Double.valueOf(tax);
        Float numberAsFloatValue = Float.parseFloat(totalFareperPassenger.toString());
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        String numberAsStringWithTwoDecimals = df.format(numberAsFloatValue);
        return numberAsStringWithTwoDecimals;
    }

    public String getTotalBaseTaxFare(String value1, String value2, String value3, int passengerNumber) {
        Double totalFareperPassenger = Double.valueOf(value1) * passengerNumber + Double.valueOf(value2) * passengerNumber + Double.valueOf(value3) * passengerNumber;
        Float numberAsFloatValue = Float.parseFloat(totalFareperPassenger.toString());
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        String numberAsStringWithTwoDecimals = df.format(numberAsFloatValue);
        return numberAsStringWithTwoDecimals;
    }

    public String getTotalFare(String value1, String value2, String value3) {
        Double totalFareperPassenger = Double.valueOf(value1) + Double.valueOf(value2) + Double.valueOf(value3);
        Float numberAsFloatValue = Float.parseFloat(totalFareperPassenger.toString());
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        String numberAsStringWithTwoDecimals = df.format(numberAsFloatValue);
        return numberAsStringWithTwoDecimals;
    }

    public String getTheCorrectFormatForTheDate(By dateElement) throws ParseException {
        String date = driver.findElement(dateElement).getText().trim();
        Date date1 = new SimpleDateFormat("dd MMM yyyy").parse(date);
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd MMM yyyy");
        String correctDate = simpleFormat.format(date1);
        return correctDate;
    }

    public String changeFaretoDecimalFormatAPI(String element) {
        Float numberAsFloatValue = Float.parseFloat(element);
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        String numberAsStringWithTwoDecimals = df.format(numberAsFloatValue);
        return numberAsStringWithTwoDecimals;
    }

    public long changeTimeFormatInMinutes(String time) {
        String[] split = time.split("  ");
        long minutes = 0;
        if (split.length == 2) {
            minutes = TimeUnit.HOURS.toMinutes(Integer.parseInt(split[0])) +
                    Integer.parseInt(split[1]);
        }
        return minutes;
    }

    public String changeDateFormat(String deliverydate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

        Date date = sdf.parse(deliverydate);

        sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public void makeBrowserBack() throws InterruptedException {
        Thread.sleep(1000);
        driver.navigate().back();
    }

}
