package step_definition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.GeneralMethods;
import helper.TestBase;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

public class FindMyBooking extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, 10);
    GeneralMethods gmObject = new GeneralMethods();

    boolean pdfNameResult;

    By taxInvoiceBTN = By.xpath("//a[contains(text(),'Invoice PDF')]");
    By bookingConfirmationBTN = By.xpath("//a[contains(text(),'Ticket PDF')]");
    By fly365RefVAL = By.xpath("(//strong[@class='txt-primary-second font-semibold'])[1]");
    By airlineRefVAL = By.xpath("(//strong[@class='txt-primary-second font-semibold'])[2]");
    By dateIssuedVAL = By.xpath("(//strong[@class='txt-primary-second font-semibold'])[3]");
    By firstTripDepartureDateVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-0-origin']//div[2]/span[2]");
    By secondTripDepartureDateVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-0-origin']//div[2]/span[2]");
    By firstTripArrivalDateVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-1-destination']/div[2]/span[2]");
    By secondTripArrivalDateVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-1-destination']/div[2]/span[2]");
    By adultFareVAL = By.xpath("//div[2]/div/div[1]/div[2]//div[2]/p[1]");
    By adultTaxVAL = By.xpath("//div[2]/div/div[1]/div[2]//div[2]/p[2]");
    By childFareVAL = By.xpath("//div[2]/div/div[2]/div[2]//div[2]/p[1]");
    By childTaxVAL = By.xpath("//div[2]/div/div[2]/div[2]//div[2]/p[2]");
    By infantFareVAL = By.xpath("(//p[@class='mb-2'])[11]");
    By infantTaxVAL = By.xpath("(//p[@class='mb-2'])[12]");
    By creditCardVAL = By.xpath("//div[4]/div[2]//p[2]");
    By firstTripCarrierCodeVAL = By.xpath("//*[@id='itin-gp-undefined-optionset-0-option-0']/div/div[1]/div[1]//label");
    By secondTripCarrierCodeVAL = By.xpath("//*[@id='itin-gp-undefined-optionset-1-option-0']/div/div[1]/div[1]//label");
    By firstTripBaggageNumberVAL = By.xpath("//*[@id='itin-gp-undefined-optionset-0-option-0']//div[1]/div[3]//label[2]");
    By secondTripBaggageNumberVAL = By.xpath("//*[@id='itin-gp-undefined-optionset-1-option-0']//div[2]//div[1]/div[1]/div[3]//label[2]");
    By firstTripDepartureTimeVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-0-origin']//div[2]/span[1]");
    By secondTripDepartureTimeVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-0-origin']//div[2]/span[1]");
    By firstTriparrivalTimeVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-1-destination']/div[2]/span[1]");
    By secondTriparrivalTimeVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-1-destination']/div[2]/span[1]");
    By firstTripDepartureTerminalVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-0-origin']//div[1]/span[3]");
    By secondTripDepartureTerminalVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-0-origin']//div[1]/span[3]");
    By firstTripArrivalTerminalVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-1-destination']/div[1]/span[3]");
    By secondTripArrivalTerminalVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-1-destination']/div[1]/span[3]");
    By firstTripTotalTravelTimeVAL = By.xpath("(//span[@class='text-xs inline-block text-primary-fourth font-medium mb-1 flight-duration'])[1]");
    By secondTripTotalTravelTimeVAL = By.xpath("//div[@id='itin-gp-0-optionset-1-option-0-segment-1-duration']//div[@class='text-xs inline-block font-semibold segment-duration'][contains(text(),'h -')]");
    By currencyVAL = By.xpath("//p[@class='text-lg font-bold uppercase']/span");
    By firstTripFirstDepartureAirlineVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-0-carrier']//label[1]");
    By firstTripSecondDepartureAirlineVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-1-carrier']//label[1]");
    By firstTripFirstAirlineNumberVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-0-carrier']//label[2]");
    By firstTripSecondAirlineNumberVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-1-carrier']//label[2]");
    By firstTripFirstSegmentDurationVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-0-duration']/div");
    By firstTripSecondSegmentDurationVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-1-duration']/div");
    By firstTripStopDurationVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-1-conflight']");
    By firstStopArrivalStopCityCountryVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-0-destination']/span[1]");
    By firstStopArrivalAirportVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-0-destination']/div[1]/span[1]");
    By firstStopArrivalTerminalVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-0-destination']/div[1]/span[3]");
    By firstStopArrivalTimeVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-0-destination']/div[2]/span[1]");
    By firstStopArrivalDateVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-0-destination']/div[2]/span[2]");
    By firstStopDepartureStopCityCountryVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-1-origin']/div/span[1]");
    By firstStopDepartureAirportVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-1-origin']/div/div[1]/span[1]");
    By firstStopDepartureTerminalVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-1-origin']//div[1]/span[3]");
    By firstStopDepartureTimeVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-1-origin']//div[2]/span[1]");
    By firstStopDepartureDateVAL = By.xpath("//*[@id='itin-gp-0-optionset-0-option-0-segment-1-origin']//div[2]/span[2]");
    By thirdTripDepartureDateVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-0-origin']//div[2]/span[2]");
    By thirdTripArrivalDateVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-1-destination']/div[2]/span[2]");
    By thirdTripCarrierCodeVAL = By.xpath("//*[@id='itin-gp-undefined-optionset-2-option-0']/div/div[1]/div[1]//label");
    By thirdTripBaggageNumberVAL = By.xpath("//*[@id='itin-gp-undefined-optionset-2-option-0']//div[2]//div[1]/div[1]/div[3]//label[2]");
    By thirdTripDepartureTimeVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-0-origin']//div[2]/span[1]");
    By thirdTriparrivalTimeVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-1-destination']/div[2]/span[1]");
    By thirdTripTotalTravelTimeVAL = By.xpath("//*[@id='itin-gp-undefined-optionset-2-option-0']/div/div[2]/div/div/div/div[3]//div[2]//div/div[1]/div/div[2]");
    By thirdDepartureTerminalVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-0-origin']//div[1]/span[3]");
    By thirdArrivalTerminalVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-1-destination']/div[1]/span[3]");

    By secondTripSecondDepartureAirlineVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-1-carrier']//label[1]");;
    By secondTripSecondAirlineNumberVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-1-carrier']//label[2]");
    By secondStopArrivalTimeVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-0-destination']/div[2]/span[1]");
    By secondStopArrivalDateVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-0-destination']/div[2]/span[2]");
    By secondStopArrivalStopCityCountryVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-0-destination']/span[1]");
    By secondStopArrivalAirportVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-0-destination']/div[1]/span[1]");
    By secondStopArrivalTerminalVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-0-destination']/div[1]/span[3]");
    By secondTripFirstSegmentDurationVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-0-duration']/div");
    By secondTripSecondSegmentDurationVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-1-duration']/div");
    By secondStopDepartureTimeVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-1-origin']//div[2]/span[1]");
    By secondStopDepartureDateVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-1-origin']//div[2]/span[2]");
    By secondStopDepartureStopCityCountryVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-1-origin']/div/span[1]");
    By secondStopDepartureAirportVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-1-origin']/div/div[1]/span[1]");
    By secondStopDepartureTerminalVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-1-origin']//div[1]/span[3]");
    By secondTripFirstDepartureAirlineVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-0-carrier']//label[1]");
    By secondTripFirstAirlineNumberVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-0-carrier']//label[2]");
    By secondTripStopDurationVAL = By.xpath("//*[@id='itin-gp-0-optionset-1-option-0-segment-1-conflight']");

    By thirdTripSecondDepartureAirlineVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-1-carrier']//label[1]");;
    By thirdTripSecondAirlineNumberVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-1-carrier']//label[2]");
    By thirdStopArrivalTimeVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-0-destination']/div[2]/span[1]");
    By thirdStopArrivalDateVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-0-destination']/div[2]/span[2]");
    By thirdStopArrivalStopCityCountryVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-0-destination']/span[1]");
    By thirdStopArrivalAirportVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-0-destination']/div[1]/span[1]");
    By thirdStopArrivalTerminalVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-0-destination']/div[1]/span[3]");
    By thirdTripFirstSegmentDurationVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-0-duration']/div");
    By thirdTripSecondSegmentDurationVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-1-duration']/div");
    By thirdStopDepartureTimeVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-1-origin']//div[2]/span[1]");
    By thirdStopDepartureDateVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-1-origin']//div[2]/span[2]");
    By thirdStopDepartureStopCityCountryVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-1-origin']/div/span[1]");
    By thirdStopDepartureAirportVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-1-origin']/div/div[1]/span[1]");
    By thirdStopDepartureTerminalVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-1-origin']//div[1]/span[3]");
    By thirdTripFirstDepartureAirlineVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-0-carrier']//label[1]");
    By thirdTripFirstAirlineNumberVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-0-carrier']//label[2]");
    By thirdTripStopDurationVAL = By.xpath("//*[@id='itin-gp-0-optionset-2-option-0-segment-1-conflight']");




    String fly365Ref;
    String airlineRef;
    String dateIssued;
    String firstTripCarrierCode;
    String secondTripCarrierCode;
    String firstTripbaggageNumber;
    String secondTripbaggageNumber;
    String firstTripbaggageNumberUC;
    String firstTripdepartureTime;
    String secondTripdepartureTime;
    String firstTriparrivalTime;
    String secondTriparrivalTime;
    String firstTripdepartureTerminal;
    String secondTripdepartureTerminal;
    String firstTriparrivalTerminal;
    String secondTriparrivalTerminal;
    String firstTripTotalTravelTime;
    String secondTripTotalTravelTime;
    String firstTripDepartureDate;
    String secondTripDepartureDate;
    String firstTriparrivalDate;
    String secondTriparrivalDate;
    String adultFare;
    String adultTax;
    String childFare;
    String childTax;
    String infantFare;
    String infantTax;
    String totalFareperAdult;
    String totalFareperInfant;
    String totalFareperChild;
    String totalBaseFare;
    String totalTaxesAndFees;
    String creditCardFees;
    String totalCharges;
    String currency;
    String firstTripFirstDepartureAirline;
    String firstTripSecondDepartureAirline;
    String firstTripFirstAirlineNumber;
    String firstTripSecondAirlineNumber;
    String firstTripFirstSegmentDuration;
    String firstTripSecondSegmentDuration;
    String firstTripStopDuration;
    String firstStopArrivalStopCityCountry;
    String firstStopArrivalAirport;
    String firstStopArrivalTerminal;
    String firstStopArrivalTime;
    String firstStopArrivalDate;
    String firstStopDepartureStopCityCountry;
    String firstStopDepartureAirport;
    String firstStopDepartureTerminal;
    String firstStopDepartureTime;
    String firstStopDepartureDate;
    String thirdTripDepartureDate;
    String thirdTripCarrierCode;
    String thirdTripbaggageNumber;
    String thirdTripdepartureTime;
    String thirdTriparrivalTime;
    String thirdTripdepartureTerminal;
    String thirdTriparrivalTerminal;
    String thirdTriparrivalDate;
    String thirdTripTotalTravelTime;

    String secondTripbaggageNumberUC;
    String secondTripFirstDepartureAirline;
    String secondTripFirstAirlineNumber;
    String secondTripStopDuration;
    String secondTripSecondDepartureAirline;
    String secondTripSecondAirlineNumber;
    String secondStopArrivalTime;
    String secondStopArrivalDate;
    String secondStopArrivalStopCityCountry;
    String secondStopArrivalAirport;
    String secondStopArrivalTerminal;
    String secondTripFirstSegmentDuration;
    String secondTripSecondSegmentDuration;
    String secondStopDepartureTime;
    String secondStopDepartureDate;
    String secondStopDepartureStopCityCountry;
    String secondStopDepartureAirport;
    String secondStopDepartureTerminal;

    String thirdTripbaggageNumberUC;
    String thirdTripFirstDepartureAirline;
    String thirdTripFirstAirlineNumber;
    String thirdTripStopDuration;
    String thirdTripSecondDepartureAirline;
    String thirdTripSecondAirlineNumber;
    String thirdStopArrivalTime;
    String thirdStopArrivalDate;
    String thirdStopArrivalStopCityCountry;
    String thirdStopArrivalAirport;
    String thirdStopArrivalTerminal;
    String thirdTripFirstSegmentDuration;
    String thirdTripSecondSegmentDuration;
    String thirdStopDepartureTime;
    String thirdStopDepartureDate;
    String thirdStopDepartureStopCityCountry;
    String thirdStopDepartureAirport;
    String thirdStopDepartureTerminal;




    @And("^Download the tax invoice pdf$")
    public void downloadTheTaxInvoicePdf() throws InterruptedException {
        Thread.sleep(20000);
        driver.findElement(taxInvoiceBTN).click();
        Thread.sleep(7000);
    }


    @And("^Get the different values of the Flight$")
    public void getTheDifferentValuesOfTheFlight() throws ParseException {

        fly365Ref = driver.findElement(fly365RefVAL).getText().trim();
        airlineRef = driver.findElement(airlineRefVAL).getText().trim();
        dateIssued = gmObject.getTheCorrectFormatForTheDate(dateIssuedVAL);
        firstTripDepartureDate = gmObject.getTheCorrectFormatForTheDate(firstTripDepartureDateVAL);
        firstTriparrivalDate = gmObject.getTheCorrectFormatForTheDate(firstTripArrivalDateVAL);
        firstTripCarrierCode = driver.findElement(firstTripCarrierCodeVAL).getText().trim();
        firstTripbaggageNumber = driver.findElement(firstTripBaggageNumberVAL).getText().trim();
        firstTripbaggageNumberUC = firstTripbaggageNumber.toUpperCase();
        firstTripdepartureTime = driver.findElement(firstTripDepartureTimeVAL).getText().trim().replaceAll("[\\-\\s]","");;
        firstTriparrivalTime = driver.findElement(firstTriparrivalTimeVAL).getText().trim().replaceAll("[\\-\\s]","");
        firstTripdepartureTerminal = driver.findElement(firstTripDepartureTerminalVAL).getText().trim();
        firstTriparrivalTerminal = driver.findElement(firstTripArrivalTerminalVAL).getText().trim();
        firstTripTotalTravelTime = driver.findElement(firstTripTotalTravelTimeVAL).getText().trim();
        adultFare = gmObject.changeFaretoDecimalFormat(adultFareVAL);
        adultTax = gmObject.changeFaretoDecimalFormat(adultTaxVAL);
        childFare = gmObject.changeFaretoDecimalFormat(childFareVAL);
        childTax = gmObject.changeFaretoDecimalFormat(childTaxVAL);
        infantFare = gmObject.changeFaretoDecimalFormat(infantFareVAL);
        infantTax = gmObject.changeFaretoDecimalFormat(infantTaxVAL);
        totalFareperAdult = gmObject.getFarePerPassenger(adultFare,adultTax);
        totalFareperChild = gmObject.getFarePerPassenger(childFare,childTax);
        totalFareperInfant = gmObject.getFarePerPassenger(infantFare,infantTax);
        totalBaseFare = gmObject.getTotalBaseTaxFare(adultFare, childFare, infantFare,2);
        totalTaxesAndFees = gmObject.getTotalBaseTaxFare(adultTax, childTax, infantTax, 2);
        creditCardFees = gmObject.changeFaretoDecimalFormat(creditCardVAL);
        totalCharges = gmObject.getTotalFare(totalBaseFare, totalTaxesAndFees, creditCardFees);
        currency = driver.findElement(currencyVAL).getText().trim();
        firstTripFirstDepartureAirline = driver.findElement(firstTripFirstDepartureAirlineVAL).getText().trim();
        firstTripSecondDepartureAirline= driver.findElement(firstTripSecondDepartureAirlineVAL).getText().trim();
        firstTripFirstAirlineNumber = driver.findElement(firstTripFirstAirlineNumberVAL).getText().trim();
        firstTripSecondAirlineNumber = driver.findElement(firstTripSecondAirlineNumberVAL).getText().trim();
        firstTripFirstSegmentDuration = driver.findElement(firstTripFirstSegmentDurationVAL).getText().trim();
        firstTripSecondSegmentDuration = driver.findElement(firstTripSecondSegmentDurationVAL).getText().trim();
        firstTripStopDuration = driver.findElement(firstTripStopDurationVAL).getText().trim();
        firstStopArrivalStopCityCountry = driver.findElement(firstStopArrivalStopCityCountryVAL).getText().trim();
        firstStopArrivalAirport = driver.findElement(firstStopArrivalAirportVAL).getText().trim();
        firstStopArrivalTerminal = driver.findElement(firstStopArrivalTerminalVAL).getText().trim();
        firstStopArrivalTime = driver.findElement(firstStopArrivalTimeVAL).getText().trim().replaceAll("[\\-\\s]","");
        firstStopArrivalDate = gmObject.getTheCorrectFormatForTheDate(firstStopArrivalDateVAL);
        firstStopDepartureStopCityCountry = driver.findElement(firstStopDepartureStopCityCountryVAL).getText().trim();
        firstStopDepartureAirport = driver.findElement(firstStopDepartureAirportVAL).getText().trim();
        firstStopDepartureTerminal = driver.findElement(firstStopDepartureTerminalVAL).getText().trim();
        firstStopDepartureTime = driver.findElement(firstStopDepartureTimeVAL).getText().trim().replaceAll("[\\-\\s]","");
        firstStopDepartureDate = gmObject.getTheCorrectFormatForTheDate(firstStopDepartureDateVAL);
//firstAirlineNumber, secondAirlineNumber, firstSegmentDuration, secondSegmentDuration, stopDuration

    }

    @And("^Get the different values of the Flight for the Round Trip$")
    public void getTheDifferentValuesOfTheFlightForTheRoundTrip() throws ParseException {
        secondTripDepartureDate = gmObject.getTheCorrectFormatForTheDate(secondTripDepartureDateVAL);
        secondTripCarrierCode = driver.findElement(secondTripCarrierCodeVAL).getText().trim();
        secondTripbaggageNumber = driver.findElement(secondTripBaggageNumberVAL).getText().trim();
        secondTripdepartureTime = driver.findElement(secondTripDepartureTimeVAL).getText().trim().replaceAll("[\\-\\s]","");;
        secondTriparrivalTime = driver.findElement(secondTriparrivalTimeVAL).getText().trim().replaceAll("[\\-\\s]","");
        secondTripdepartureTerminal = driver.findElement(secondTripDepartureTerminalVAL).getText().trim();
        secondTriparrivalTerminal = driver.findElement(secondTripArrivalTerminalVAL).getText().trim();
        secondTriparrivalDate = gmObject.getTheCorrectFormatForTheDate(secondTripArrivalDateVAL);
        secondTripTotalTravelTime = driver.findElement(secondTripTotalTravelTimeVAL).getText().trim();



        secondTripSecondDepartureAirline= driver.findElement(secondTripSecondDepartureAirlineVAL).getText().trim();
        secondTripSecondAirlineNumber = driver.findElement(secondTripSecondAirlineNumberVAL).getText().trim();
        secondStopArrivalTime = driver.findElement(secondStopArrivalTimeVAL).getText().trim().replaceAll("[\\-\\s]","");
        secondStopArrivalDate = gmObject.getTheCorrectFormatForTheDate(secondStopArrivalDateVAL);
        secondStopArrivalStopCityCountry = driver.findElement(secondStopArrivalStopCityCountryVAL).getText().trim();
        secondStopArrivalAirport = driver.findElement(secondStopArrivalAirportVAL).getText().trim();
        secondStopArrivalTerminal = driver.findElement(secondStopArrivalTerminalVAL).getText().trim();
        secondStopDepartureTerminal = driver.findElement(secondStopDepartureTerminalVAL).getText().trim();
        secondStopDepartureAirport = driver.findElement(secondStopDepartureAirportVAL).getText().trim();
        secondStopDepartureStopCityCountry = driver.findElement(secondStopDepartureStopCityCountryVAL).getText().trim();
        secondStopDepartureDate = gmObject.getTheCorrectFormatForTheDate(secondStopDepartureDateVAL);
        secondStopDepartureTime = driver.findElement(secondStopDepartureTimeVAL).getText().trim().replaceAll("[\\-\\s]","");
        secondTripFirstSegmentDuration = driver.findElement(secondTripFirstSegmentDurationVAL).getText().trim();
        secondTripSecondSegmentDuration = driver.findElement(secondTripSecondSegmentDurationVAL).getText().trim();
        secondTripbaggageNumberUC = secondTripbaggageNumber.toUpperCase();
         secondTripFirstDepartureAirline = driver.findElement(secondTripFirstDepartureAirlineVAL).getText().trim();;
         secondTripFirstAirlineNumber = driver.findElement(secondTripFirstAirlineNumberVAL).getText().trim();;
         secondTripStopDuration = driver.findElement(secondTripStopDurationVAL).getText().trim();;

    }

    @And("^Get the different values of the Flight for the Multi City$")
    public void getTheDifferentValuesOfTheFlightForTheMultiCity() throws ParseException {
        thirdTripDepartureDate = gmObject.getTheCorrectFormatForTheDate(thirdTripDepartureDateVAL);
        thirdTripCarrierCode = driver.findElement(thirdTripCarrierCodeVAL).getText().trim();
        thirdTripbaggageNumber = driver.findElement(thirdTripBaggageNumberVAL).getText().trim();
        thirdTripdepartureTime = driver.findElement(thirdTripDepartureTimeVAL).getText().trim().replaceAll("[\\-\\s]","");;
        thirdTriparrivalTime = driver.findElement(thirdTriparrivalTimeVAL).getText().trim().replaceAll("[\\-\\s]","");
        thirdTripdepartureTerminal = driver.findElement(thirdDepartureTerminalVAL).getText().trim();
        thirdTriparrivalTerminal = driver.findElement(thirdArrivalTerminalVAL).getText().trim();
        thirdTriparrivalDate = gmObject.getTheCorrectFormatForTheDate(thirdTripArrivalDateVAL);
        thirdTripTotalTravelTime = driver.findElement(thirdTripTotalTravelTimeVAL).getText().trim();

        thirdTripSecondDepartureAirline= driver.findElement(thirdTripSecondDepartureAirlineVAL).getText().trim();
        thirdTripSecondAirlineNumber = driver.findElement(thirdTripSecondAirlineNumberVAL).getText().trim();
        thirdStopArrivalTime = driver.findElement(thirdStopArrivalTimeVAL).getText().trim().replaceAll("[\\-\\s]","");
        thirdStopArrivalDate = gmObject.getTheCorrectFormatForTheDate(thirdStopArrivalDateVAL);
        thirdStopArrivalStopCityCountry = driver.findElement(thirdStopArrivalStopCityCountryVAL).getText().trim();
        thirdStopArrivalAirport = driver.findElement(thirdStopArrivalAirportVAL).getText().trim();
        thirdStopArrivalTerminal = driver.findElement(thirdStopArrivalTerminalVAL).getText().trim();
        thirdStopDepartureTerminal = driver.findElement(thirdStopDepartureTerminalVAL).getText().trim();
        thirdStopDepartureAirport = driver.findElement(thirdStopDepartureAirportVAL).getText().trim();
        thirdStopDepartureStopCityCountry = driver.findElement(thirdStopDepartureStopCityCountryVAL).getText().trim();
        thirdStopDepartureDate = gmObject.getTheCorrectFormatForTheDate(thirdStopDepartureDateVAL);
        thirdStopDepartureTime = driver.findElement(thirdStopDepartureTimeVAL).getText().trim().replaceAll("[\\-\\s]","");
        thirdTripFirstSegmentDuration = driver.findElement(thirdTripFirstSegmentDurationVAL).getText().trim();
        thirdTripSecondSegmentDuration = driver.findElement(thirdTripSecondSegmentDurationVAL).getText().trim();
        thirdTripbaggageNumberUC = thirdTripbaggageNumber.toUpperCase();
        thirdTripFirstDepartureAirline = driver.findElement(thirdTripFirstDepartureAirlineVAL).getText().trim();;
        thirdTripFirstAirlineNumber = driver.findElement(thirdTripFirstAirlineNumberVAL).getText().trim();;
        thirdTripStopDuration = driver.findElement(thirdTripStopDurationVAL).getText().trim();;
    }

    @Then("^The tax invoice pdf is downloaded$")
    public void theTaxInvoicePdfIsDownloaded() throws IOException {
        File dir = new File(System.getProperty("user.dir") + "/Downloads/");
        File[] myFiles = dir.listFiles();

        for (File file : myFiles) {
            pdfNameResult = file.getName().equals("Tax Invoice.pdf");
        }
        Assert.assertTrue(pdfNameResult);
    }

    @Then("^The tax invoice pdf contains the correct data for One Way$")
    public void theTaxInvoicePdfContainsTheCorrectDataForOneWay() throws IOException {
        URL TestURL = new URL("file://" + System.getProperty("user.dir") + "/Downloads/Tax Invoice.pdf");
        BufferedInputStream TestFile = new BufferedInputStream(TestURL.openStream());
        PDFParser TestPDF = new PDFParser(TestFile);
        TestPDF.parse();
        String TestText = new PDFTextStripper().getText(TestPDF.getPDDocument());
        System.out.println("****************");
        System.out.println(TestText);
        System.out.println("****************");
        Assert.assertEquals(TestText,"Fly365 Pty Ltd\n" +
                "ABN : 606 601 521\n" +
                "TAX INVOICE\n" +
                "Fly365 Reference Airlines Reference Date Issued\n" +
                fly365Ref+" "+airlineRef+" "+dateIssued+"\n" +
                "Flights Details\n" +
                "From Cairo (CAI) to Dublin (DUB) on the "+firstTripDepartureDate+"\n" +
                "Trip Details\n" +
                "Fare Details\n" +
                "Family Name/First Middle Names Title Type Frequent Flyer Base Fare Taxes and Fees Total\n" +
                "Smith/John William Mr Adult N/A "+adultFare+" "+currency+" "+adultTax+" "+currency+" "+totalFareperAdult+" "+currency+"\n" +
                "Smith/Michael William Mr Adult N/A "+adultFare+" "+currency+" "+adultTax+" "+currency+" "+totalFareperAdult+" "+currency+"\n" +
                "Smith/Frank William Mr Child N/A "+childFare+" "+currency+" "+childTax+" "+currency+" "+totalFareperChild+" "+currency+"\n" +
                "Smith/Peter William Mr Child N/A "+childFare+" "+currency+" "+childTax+" "+currency+" "+totalFareperChild+" "+currency+"\n" +
                "Frank/Paul Peter Mr Infant N/A "+infantFare+" "+currency+" "+infantTax+" "+currency+" "+totalFareperInfant+" "+currency+"\n" +
                "Frank/John Peter Mr Infant N/A "+infantFare+" "+currency+" "+infantTax+" "+currency+" "+totalFareperInfant+" "+currency+"\n" +
                "Payment Summary Total\n" +
                "Total Base Fare "+totalBaseFare+" "+currency+"\n" +
                "Total Taxes and Fees "+totalTaxesAndFees+" "+currency+"\n" +
                "GST 0.00 "+currency+"\n" +
                "Credit Card Fee "+creditCardFees+" "+currency+"\n" +
                "This is an electronically generated invoice and does not require a physical signature. Total amount charged "+totalCharges+" "+currency+"\n" +
                firstTripCarrierCode+"\n" +
                firstTripbaggageNumber+" Baggage\n" +
                firstTripdepartureTime+" - "+firstTripDepartureDate+"\n" +
                "Cairo (CAI) - Egypt\n" +
                "Cairo International Airport -\n" +
                firstTripdepartureTerminal+"\n" +
                firstTriparrivalTime+" - "+firstTriparrivalDate+"\n" +
                "Dublin (DUB) - Ireland\n" +
                "Dublin International - "+firstTriparrivalTerminal+"\n" +
                firstTripTotalTravelTime+"\n" +
                "1 Stop\n");
    }

    @And("^Download the booking confirmation pdf$")
    public void downloadTheBookingConfirmationPdf() throws InterruptedException {
        Thread.sleep(16000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookingConfirmationBTN));
        driver.findElement(bookingConfirmationBTN).click();
        Thread.sleep(5000);
    }

    @Then("^The booking confirmation pdf contains the correct data for One Way$")
    public void theBookingConfirmationPdfContainsTheCorrectDataForOneWay() throws IOException {
        URL TestURL = new URL("file://" + System.getProperty("user.dir") + "/Downloads/Booking Confirmation.pdf");
        BufferedInputStream TestFile = new BufferedInputStream(TestURL.openStream());
        PDFParser TestPDF = new PDFParser(TestFile);
        TestPDF.parse();
        String TestText = new PDFTextStripper().getText(TestPDF.getPDDocument());
        System.out.println("****************");
        System.out.println(TestText);
        System.out.println("****************");
        Assert.assertEquals(TestText,"Email : nz.support@fly365.com - monitored 24 hours a day\n" +
                "BOOKING CONFIRMATION\n" +
                "You are ready to fly\n" +
                "Thank you for booking with Fly365! Please print this confirmation and present at check-in . Ensure you have checked that all information is correct\n" +
                "Check in online, or\n" +
                "Online check via the airlines website is\n" +
                "available from 24-48hrs prior to departure.\n" +
                "If checking in at the airport, arrive up to 3\n" +
                "hours prior to departure.\n" +
                "120 Minutes\n" +
                "If you’re checking in bags, go to the airline\n" +
                "check in counters at least 120 minutes\n" +
                "before your flight.\n" +
                "60 Minutes\n" +
                "Once you have checked in, go through\n" +
                "security. You should do this at least 60\n" +
                "minutes before your flight.\n" +
                "45 Minutes\n" +
                "Arrive at the boarding gate 45 minutes\n" +
                "before departure. The gates close 20\n" +
                "minutes before the flight leaves.\n" +
                "Booking Information\n" +
                "Fly365 Reference Airlines Reference Date Issued Booking Status\n" +
                fly365Ref+" "+airlineRef+" "+dateIssued+" Confirmed\n" +
                "Contact Details\n" +
                "Name Email Phone\n" +
                "John Smith john.smith.fly365@gmail.com +20 136253637474\n" +
                "Passengers Details\n" +
                "Family Name/First Middle Names Title Birth Date Type Frequent Flyer\n" +
                "Smith/John William Mr 15 Feb 1985 Adult N/A\n" +
                "Smith/Frank William Mr 15 Feb 2012 Child N/A\n" +
                "Frank/Paul Peter Mr 15 Jan 2019 Infant N/A\n" +
                "Flights Details\n" +
                "From Auckland (AKL) to Canberra (CBR) on the "+firstTripDepartureDate+"\n" +
                firstTripCarrierCode+"\n" +
                firstTripbaggageNumber+" Baggage\n" +
                firstTripdepartureTime+" - "+firstTripDepartureDate+"\n" +
                "Auckland (AKL) - New\n" +
                "Zealand\n" +
                "Auckland International -\n" +
                firstTripdepartureTerminal+"\n" +
                firstTriparrivalTime+" - "+firstTriparrivalDate+"\n" +
                "Canberra (CBR) - Australia\n" +
                "Canberra - "+firstTriparrivalTerminal+"\n" +
                firstTripTotalTravelTime+"\n" +
                "1 Stop\n" +
                firstTripFirstDepartureAirline+"\n" +
                firstTripFirstAirlineNumber+"\n" +
                firstTripbaggageNumber+" Baggage\n" +
                "Economy\n" +
                firstTripdepartureTime+" - "+firstTripDepartureDate+"\n" +
                "Auckland (AKL) - New\n" +
                "Zealand\n" +
                "Auckland International -\n" +
                firstTripdepartureTerminal+"\n" +
                firstStopArrivalTime+" - "+firstStopArrivalDate+"\n" +
                firstStopArrivalStopCityCountry+"\n" +
                firstStopArrivalAirport+" - "+firstStopArrivalTerminal+"\n" +
                firstTripFirstSegmentDuration+" "+firstTripbaggageNumberUC+"\n" +
                "Baggage\n" +
                firstTripSecondDepartureAirline+"\n" +
                firstTripSecondAirlineNumber+"\n" +
                firstTripbaggageNumber+" Baggage\n" +
                "Economy\n" +
                firstStopDepartureTime+" - "+firstStopDepartureDate+"\n" +
                firstStopDepartureStopCityCountry+"\n" +
                firstStopDepartureAirport+" - "+firstStopDepartureTerminal+"\n" +
                firstTriparrivalTime+" - "+firstTriparrivalDate+"\n" +
                "Canberra (CBR) - Australia\n" +
                "Canberra - "+firstTriparrivalTerminal+"\n" +
                firstTripSecondSegmentDuration+" "+firstTripbaggageNumberUC+"\n" +
                "Baggage\n" +
                "CONNECTING FLIGHT :  Stop Time : "+firstTripStopDuration+"\n" +
                "Notes\n" +
                "Online Itinerary Title\n" +
                "Please note that certain airlines consolidate passenger names and titles,\n" +
                "and some may show your name format similar to the examples below.\n" +
                "E.g. Mr John Smith will be shown as:\n" +
                "Smithmr John; or\n" +
                "Johnmr Smith\n" +
                "Passport / Visa\n" +
                "Please ensure your passport is valid for six months beyond your intended\n" +
                "return date. Some countries require you to obtain a visa prior to departure\n" +
                "or on arrival into certain countries. Please check your visa requirements\n" +
                "prior to travel.\n" +
                "Do I need to Confirm My Flights?\n" +
                "Yes - Important: Re-Con\uF001rming Flights Read carefully.\n" +
                "Fly365 strongly recommends recon\uF001rming all \uF002ights a minimum 48-24\n" +
                "hours prior to departure.\n" +
                "Failure to reconfirm flights may result in you missing your departure\n" +
                "times due to a sudden Airline Schedule Change or in the event your\n" +
                "flight has been cancelled.\n" +
                "It is important to note, that your booking is your responsibility and\n" +
                "you are required to be aware of any potential changes that may\n" +
                "occur in your itinerary.\n" +
                "You can also track your \uF002ight progress by going to google and typing\n" +
                "your \uF002ight number on day of departure.\n" +
                "Seating\n" +
                "Fly365 will always try and pre-seat you prior to travel. If you have been\n" +
                "pre-seated, you will notice this on your Itinerary/Confirmation above.\n" +
                "Some airlines will charge for us to pre-seat prior to departure and this can\n" +
                "start from 25.00 NZD per seat per \uF002ight sector.\n" +
                "If you are unhappy with the seat selection or would like to choose your\n" +
                "own seat prior to departure, please visit your corresponding airlines\n" +
                "website to change your seat selection. We always suggest you check in\n" +
                "online at least 48-24 hours prior to departure depending on your airline\n" +
                "conditions. If you are unable to select your seats prior to departure you\n" +
                "will be able to select your seats during online check in process.\n" +
                "Payment\n" +
                "We take your credit card security seriously\n" +
                "We may ask you to verify your payment after you have received your\n" +
                "receipt. Fly365 will never ask you to send credit card information over\n" +
                "email. Our snapshot verification process is the safest and quickest way to\n" +
                "instantly verify the payment to us. You can simply click the payment\n" +
                "charge to Fly365 from your device and send us an email or using the\n" +
                "snippet tool on your desktop or laptop. We will never request anything\n" +
                "further than this and we will never request bank details or credit card\n" +
                "numbers. Please ensure you look out for any follow-up emails or text\n" +
                "messages.\n" +
                "Baggage\n" +
                "We do understand baggage and check in baggage can be confusing and\n" +
                "not clear on most websites. Your baggage limit on your Confirmation\n" +
                "above is on a per person basis. Infants are allowed 10kgs of luggage.\n" +
                "Below is an example only and we do suggest you check with your airline\n" +
                "for further information.\n" +
                "Carry On:\n" +
                "As a standard across all airlines your carry-on luggage must not weigh\n" +
                "more than 7kgs (1 piece of cabin baggage weighing 7kg per person).\n" +
                "Checked Luggage:\n" +
                "There are two systems that the airlines use. One is the kilo system and\n" +
                "the other is the Piece System e.g.\n" +
                "1 piece = 1 checked baggage weighing a maximum 23kg per person.\n" +
                "2 pieces = 2 checked baggage each weighing a maximum 23kg per\n" +
                "person.\n" +
                "Kilo System e.g.\n" +
                "30kgs = 30 kilos of baggage can be checked from 1 or more pieces\n" +
                "totalling 30kgs per person.\n" +
                "Mixed Baggage Solutions:\n" +
                "More and more airlines are now offering a mixed baggage solution. Some\n" +
                "airlines will offer a cheaper fare for less baggage. This can happen on one\n" +
                "itinerary as your fare class maybe cheap on the leg to your destination\n" +
                "and more expensive on the way back. Don’t assume your baggage is the\n" +
                "same across all flights. Please ensure you check your baggage options on\n" +
                "your itinerary in great detail.\n" +
                "Fare Rules\n" +
                "Important Airline Terms and Conditions Fare\n" +
                "Rules\n" +
                "Your Ticket is non-refundable.\n" +
                "Date and time changes are permitted 72 hours prior to\n" +
                "departure.\n" +
                "Your airline change fee starts from 225 NZD per person\n" +
                "plus any fare of tax difference.\n" +
                "Fly365 will also charge a rebooking fee of 135 NZD per\n" +
                "person in addition to the airline charges.\n" +
                "If your ticket is deemed refundable by the airline,\n" +
                "Fly365 will charge Refund and Cancellation fee of 300\n" +
                "NZD per person in addition to any airline refund funds.\n" +
                "Please ensure you recon1firm your flights 48 hours\n" +
                "prior to departure.\n" +
                "Name changes are not permitted. Errors in names will\n" +
                "require purchase of another ticket.\n" +
                "No Shows are not permitted. Your ticket will be marked\n" +
                "as invalid and you will be unable to travel.\n" +
                "Some airlines will not permit changes after departure.\n" +
                "For more information kindly check our Support Centre\n");
    }

    @Then("^The user can retrieve his booking from Find my Booking$")
    public void theUserCanRetrieveHisBookingFromFindMyBooking() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fly365RefVAL));
        Assert.assertEquals(driver.findElement(fly365RefVAL).getText(),ConfirmationTest.fly356Refernce);
    }


    @Then("^The tax invoice pdf contains the correct data for Round Trip$")
    public void theTaxInvoicePdfContainsTheCorrectDataForRoundTrip() throws IOException {
        URL TestURL = new URL("file://" + System.getProperty("user.dir") + "/Downloads/Tax Invoice.pdf");
        BufferedInputStream TestFile = new BufferedInputStream(TestURL.openStream());
        PDFParser TestPDF = new PDFParser(TestFile);
        TestPDF.parse();
        String TestText = new PDFTextStripper().getText(TestPDF.getPDDocument());
        System.out.println("****************");
        System.out.println(TestText);
        System.out.println("****************");
        Assert.assertEquals(TestText,"Fly365 Pty Ltd\n" +
                "ABN : 606 601 521\n" +
                "TAX INVOICE\n" +
                "Fly365 Reference Airlines Reference Date Issued\n" +
                fly365Ref+" "+airlineRef+" "+dateIssued+"\n" +
                "Flights Details\n" +
                "From Cairo (CAI) to Dublin (DUB) on the "+firstTripDepartureDate+"\n" +
                "Trip Details\n" +
                "From Dublin (DUB) to Cairo (CAI) on the "+secondTripDepartureDate+"\n" +
                "Trip Details\n" +
                "Fare Details\n" +
                "Family Name/First Middle Names Title Type Frequent Flyer Base Fare Taxes and Fees Total\n" +
                "Smith/John William Mr Adult N/A "+adultFare+" "+currency+" "+adultTax+" "+currency+" "+totalFareperAdult+" "+currency+"\n" +
                "Smith/Michael William Mr Adult N/A "+adultFare+" "+currency+" "+adultTax+" "+currency+" "+totalFareperAdult+" "+currency+"\n" +
                "Smith/Frank William Mr Child N/A "+childFare+" "+currency+" "+childTax+" "+currency+" "+totalFareperChild+" "+currency+"\n" +
                "Smith/Peter William Mr Child N/A "+childFare+" "+currency+" "+childTax+" "+currency+" "+totalFareperChild+" "+currency+"\n" +
                "Frank/Paul Peter Mr Infant N/A "+infantFare+" "+currency+" "+infantTax+" "+currency+" "+totalFareperInfant+" "+currency+"\n" +
                "Frank/John Peter Mr Infant N/A "+infantFare+" "+currency+" "+infantTax+" "+currency+" "+totalFareperInfant+" "+currency+"\n" +
                "Payment Summary Total\n" +
                "Total Base Fare "+totalBaseFare+" "+currency+"\n" +
                "Total Taxes and Fees "+totalTaxesAndFees+" "+currency+"\n" +
                "GST 0.00 "+currency+"\n" +
                "Credit Card Fee "+creditCardFees+" "+currency+"\n" +
                "This is an electronically generated invoice and does not require a physical signature. Total amount charged "+totalCharges+" "+currency+"\n" +
                firstTripCarrierCode+"\n" +
                firstTripbaggageNumber+" Baggage\n" +
                firstTripdepartureTime+" - "+firstTripDepartureDate+"\n" +
                "Cairo (CAI) - Egypt\n" +
                "Cairo International Airport -\n" +
                firstTripdepartureTerminal+"\n" +
                firstTriparrivalTime+" - "+firstTriparrivalDate+"\n" +
                "Dublin (DUB) - Ireland\n" +
                "Dublin International - "+firstTriparrivalTerminal+"\n" +
                firstTripTotalTravelTime+"\n" +
                "1 Stop\n" +
                secondTripCarrierCode+"\n" +
                secondTripbaggageNumber+" Baggage\n" +
                secondTripdepartureTime+" - "+secondTripDepartureDate+"\n" +
                "Dublin (DUB) - Ireland\n" +
                "Dublin International - "+secondTripdepartureTerminal+"\n" +
                secondTriparrivalTime+" - "+secondTriparrivalDate+"\n" +
                "Cairo (CAI) - Egypt\n" +
                "Cairo International Airport -\n" +
                secondTriparrivalTerminal+"\n" +
                secondTripTotalTravelTime+"\n" +
                "1 Stop\n");
    }

    @Then("^The tax invoice pdf contains the correct data for Multi City$")
    public void theTaxInvoicePdfContainsTheCorrectDataForMultiCity() throws IOException {
        URL TestURL = new URL("file://" + System.getProperty("user.dir") + "/Downloads/Tax Invoice.pdf");
        BufferedInputStream TestFile = new BufferedInputStream(TestURL.openStream());
        PDFParser TestPDF = new PDFParser(TestFile);
        TestPDF.parse();
        String TestText = new PDFTextStripper().getText(TestPDF.getPDDocument());
        System.out.println("****************");
        System.out.println(TestText);
        System.out.println("****************");
        Assert.assertEquals(TestText,"Fly365 Pty Ltd\n" +
                "ABN : 606 601 521\n" +
                "TAX INVOICE\n" +
                "Fly365 Reference Airlines Reference Date Issued\n" +
                fly365Ref+" "+airlineRef+" "+dateIssued+"\n" +
                "Flights Details\n" +
                "From Cairo (CAI) to Dublin (DUB) on the "+firstTripDepartureDate+"\n" +
                "Trip Details\n" +
                "From Dublin (DUB) to Cairo (CAI) on the "+secondTripDepartureDate+"\n" +
                "Trip Details\n" +
                "From Cairo (CAI) to Auckland (AKL) on the "+thirdTripDepartureDate+"\n" +
                "Trip Details\n" +
                "Fare Details\n" +
                "Family Name/First Middle Names Title Type Frequent Flyer Base Fare Taxes and Fees Total\n" +
                "Smith/John William Mr Adult N/A "+adultFare+" "+currency+" "+adultTax+" "+currency+" "+totalFareperAdult+" "+currency+"\n" +
                "Smith/Michael William Mr Adult N/A "+adultFare+" "+currency+" "+adultTax+" "+currency+" "+totalFareperAdult+" "+currency+"\n" +
                "Smith/Frank William Mr Child N/A "+childFare+" "+currency+" "+childTax+" "+currency+" "+totalFareperChild+" "+currency+"\n" +
                "Smith/Peter William Mr Child N/A "+childFare+" "+currency+" "+childTax+" "+currency+" "+totalFareperChild+" "+currency+"\n" +
                "Frank/Paul Peter Mr Infant N/A "+infantFare+" "+currency+" "+infantTax+" "+currency+" "+totalFareperInfant+" "+currency+"\n" +
                "Frank/John Peter Mr Infant N/A "+infantFare+" "+currency+" "+infantTax+" "+currency+" "+totalFareperInfant+" "+currency+"\n" +
                "Payment Summary Total\n" +
                "Total Base Fare "+totalBaseFare+" "+currency+"\n" +
                firstTripCarrierCode+"\n" +
                firstTripbaggageNumber+" Baggage\n" +
                firstTripdepartureTime+" - "+firstTripDepartureDate+"\n" +
                "Cairo (CAI) - Egypt\n" +
                "Cairo International Airport -\n" +
                firstTripdepartureTerminal+"\n" +
                firstTriparrivalTime+" - "+firstTriparrivalDate+"\n" +
                "Dublin (DUB) - Ireland\n" +
                "Dublin International - "+firstTriparrivalTerminal+"\n" +
                firstTripTotalTravelTime+"\n" +
                "1 Stop\n" +
                secondTripCarrierCode+"\n" +
                secondTripbaggageNumber+" Baggage\n" +
                secondTripdepartureTime+" - "+secondTripDepartureDate+"\n" +
                "Dublin (DUB) - Ireland\n" +
                "Dublin International - "+secondTripdepartureTerminal+"\n" +
                secondTriparrivalTime+" - "+secondTriparrivalDate+"\n" +
                "Cairo (CAI) - Egypt\n" +
                "Cairo International Airport -\n" +
                secondTriparrivalTerminal+"\n" +
                secondTripTotalTravelTime+"\n" +
                "1 Stop\n" +
                thirdTripCarrierCode+"\n" +
                thirdTripbaggageNumber+" Baggage\n" +
                thirdTripdepartureTime+" - "+thirdTripDepartureDate+"\n" +
                "Cairo (CAI) - Egypt\n" +
                "Cairo International Airport -\n"+
                thirdTripdepartureTerminal+"\n" +
                thirdTriparrivalTime+" - "+thirdTriparrivalDate+"\n" +
                "Auckland (AKL) - New Zealand\n" +
                "Auckland International - "+thirdTriparrivalTerminal+"\n" +
                thirdTripTotalTravelTime+"\n" +
                "1 Stop\n" +
                "Total Taxes and Fees "+totalTaxesAndFees+" "+currency+"\n" +
                "GST 0.00 "+currency+"\n" +
                "Credit Card Fee "+creditCardFees+" "+currency+"\n" +
                "This is an electronically generated invoice and does not require a physical signature. Total amount charged "+totalCharges+" "+currency+"\n");
    }

    @Then("^The booking confirmation pdf contains the correct data for Round Trip$")
    public void theBookingConfirmationPdfContainsTheCorrectDataForRoundTrip() throws IOException {
        URL TestURL = new URL("file://" + System.getProperty("user.dir") + "/Downloads/Booking Confirmation.pdf");
        BufferedInputStream TestFile = new BufferedInputStream(TestURL.openStream());
        PDFParser TestPDF = new PDFParser(TestFile);
        TestPDF.parse();
        String TestText = new PDFTextStripper().getText(TestPDF.getPDDocument());
        System.out.println("****************");
        System.out.println(TestText);
        System.out.println("****************");

        Assert.assertEquals(TestText,"Email : nz.support@fly365.com - monitored 24 hours a day\n" +
                "BOOKING CONFIRMATION\n" +
                "You are ready to fly\n" +
                "Thank you for booking with Fly365! Please print this confirmation and present at check-in . Ensure you have checked that all information is correct\n" +
                "Check in online, or\n" +
                "Online check via the airlines website is\n" +
                "available from 24-48hrs prior to departure.\n" +
                "If checking in at the airport, arrive up to 3\n" +
                "hours prior to departure.\n" +
                "120 Minutes\n" +
                "If you’re checking in bags, go to the airline\n" +
                "check in counters at least 120 minutes\n" +
                "before your flight.\n" +
                "60 Minutes\n" +
                "Once you have checked in, go through\n" +
                "security. You should do this at least 60\n" +
                "minutes before your flight.\n" +
                "45 Minutes\n" +
                "Arrive at the boarding gate 45 minutes\n" +
                "before departure. The gates close 20\n" +
                "minutes before the flight leaves.\n" +
                "Booking Information\n" +
                "Fly365 Reference Airlines Reference Date Issued Booking Status\n" +
                fly365Ref+" "+airlineRef+" "+dateIssued+" Confirmed\n" +
                "Contact Details\n" +
                "Name Email Phone\n" +
                "John Smith john.smith.fly365@gmail.com +20 136253637474\n" +
                "Passengers Details\n" +
                "Family Name/First Middle Names Title Birth Date Type Frequent Flyer\n" +
                "Smith/John William Mr 15 Feb 1985 Adult N/A\n" +
                "Smith/Frank William Mr 15 Feb 2012 Child N/A\n" +
                "Frank/Paul Peter Mr 15 Jan 2019 Infant N/A\n" +
                "Flights Details\n" +
                "From Auckland (AKL) to Canberra (CBR) on the "+firstTripDepartureDate+"\n" +
                "From Canberra (CBR) to Auckland (AKL) on the "+secondTripDepartureDate+"\n" +
                firstTripCarrierCode+"\n" +
                firstTripbaggageNumber+" Baggage\n" +
                firstTripdepartureTime+" - "+firstTripDepartureDate+"\n" +
                "Auckland (AKL) - New\n" +
                "Zealand\n" +
                "Auckland International -\n" +
                firstTripdepartureTerminal+"\n" +
                firstTriparrivalTime+" - "+firstTriparrivalDate+"\n" +
                "Canberra (CBR) - Australia\n" +
                "Canberra - "+firstTriparrivalTerminal+"\n" +
                firstTripTotalTravelTime+"\n" +
                "1 Stop\n" +
                firstTripFirstDepartureAirline+"\n" +
                firstTripFirstAirlineNumber+"\n" +
                firstTripbaggageNumber+" Baggage\n" +
                "Economy\n" +
                firstTripdepartureTime+" - "+firstTripDepartureDate+"\n" +
                "Auckland (AKL) - New\n" +
                "Zealand\n" +
                "Auckland International -\n" +
                firstTripdepartureTerminal+"\n" +
                firstStopArrivalTime+" - "+firstStopArrivalDate+"\n" +
                firstStopArrivalStopCityCountry+"\n" +
                firstStopArrivalAirport+" - "+firstStopArrivalTerminal+"\n" +
                firstTripFirstSegmentDuration+" "+firstTripbaggageNumberUC+"\n" +
                "Baggage\n" +
                firstTripSecondDepartureAirline+"\n" +
                firstTripSecondAirlineNumber+"\n" +
                firstTripbaggageNumber+" Baggage\n" +
                "Economy\n" +
                firstStopDepartureTime+" - "+firstStopDepartureDate+"\n" +
                firstStopDepartureStopCityCountry+"\n" +
                firstStopDepartureAirport+" - "+firstStopDepartureTerminal+"\n" +
                firstTriparrivalTime+" - "+firstTriparrivalDate+"\n" +
                "Canberra (CBR) - Australia\n" +
                "Canberra - "+firstTriparrivalTerminal+"\n" +
                firstTripSecondSegmentDuration+" "+firstTripbaggageNumberUC+"\n" +
                "Baggage\n" +
                secondTripCarrierCode+"\n" +
                secondTripbaggageNumber+" Baggage\n" +
                secondTripdepartureTime+" - "+secondTripDepartureDate+"\n" +
                "Canberra (CBR) - Australia\n" +
                "Canberra - "+secondTripdepartureTerminal+"\n" +
                secondTriparrivalTime+" - "+secondTriparrivalDate+"\n" +
                "Auckland (AKL) - New\n" +
                "Zealand\n" +
                "Auckland International -\n" +
                secondTriparrivalTerminal+"\n" +
                secondTripTotalTravelTime+"\n" +
                "1 Stop\n" +
                secondTripFirstDepartureAirline+"\n" +
                secondTripFirstAirlineNumber+"\n" +
                secondTripbaggageNumber+" Baggage\n" +
                "Economy\n" +
                secondTripdepartureTime+" - "+secondTripDepartureDate+"\n" +
                "Canberra (CBR) - Australia\n" +
                "Canberra - "+secondTripdepartureTerminal+"\n" +
                secondStopArrivalTime+" - "+secondStopArrivalDate+"\n" +
                secondStopArrivalStopCityCountry+"\n" +
                secondStopArrivalAirport+" - "+secondStopArrivalTerminal+"\n" +
                secondTripFirstSegmentDuration+" "+secondTripbaggageNumberUC+"\n" +
                "Baggage\n" +
                secondTripSecondDepartureAirline+"\n" +
                secondTripSecondAirlineNumber+"\n" +
                secondTripbaggageNumber+" Baggage\n" +
                "Economy\n" +
                secondStopDepartureTime+" - "+secondStopDepartureDate+"\n" +
                secondStopDepartureStopCityCountry+"\n" +
                secondStopDepartureAirport+" - "+secondStopDepartureTerminal+"\n" +
                secondTriparrivalTime+" - "+secondTriparrivalDate+"\n" +
                "Auckland (AKL) - New\n" +
                "Zealand\n" +
                "Auckland International -\n" +
                secondTriparrivalTerminal+"\n" +
                secondTripSecondSegmentDuration+" "+secondTripbaggageNumberUC+"\n" +
                "Baggage\n" +
                "CONNECTING FLIGHT :  Stop Time : "+firstTripStopDuration+"\n" +
                "CONNECTING FLIGHT :  Stop Time : "+secondTripStopDuration+"\n" +
                "Notes\n" +
                "Online Itinerary Title\n" +
                "Please note that certain airlines consolidate passenger names and titles,\n" +
                "and some may show your name format similar to the examples below.\n" +
                "E.g. Mr John Smith will be shown as:\n" +
                "Smithmr John; or\n" +
                "Johnmr Smith\n" +
                "Passport / Visa\n" +
                "Please ensure your passport is valid for six months beyond your intended\n" +
                "return date. Some countries require you to obtain a visa prior to departure\n" +
                "or on arrival into certain countries. Please check your visa requirements\n" +
                "prior to travel.\n" +
                "Do I need to Confirm My Flights?\n" +
                "Yes - Important: Re-Con\uF001rming Flights Read carefully.\n" +
                "Fly365 strongly recommends recon\uF001rming all \uF002ights a minimum 48-24\n" +
                "hours prior to departure.\n" +
                "Failure to reconfirm flights may result in you missing your departure\n" +
                "times due to a sudden Airline Schedule Change or in the event your\n" +
                "flight has been cancelled.\n" +
                "It is important to note, that your booking is your responsibility and\n" +
                "you are required to be aware of any potential changes that may\n" +
                "occur in your itinerary.\n" +
                "You can also track your \uF002ight progress by going to google and typing\n" +
                "your \uF002ight number on day of departure.\n" +
                "Seating\n" +
                "Fly365 will always try and pre-seat you prior to travel. If you have been\n" +
                "pre-seated, you will notice this on your Itinerary/Confirmation above.\n" +
                "Some airlines will charge for us to pre-seat prior to departure and this can\n" +
                "start from 25.00 NZD per seat per \uF002ight sector.\n" +
                "If you are unhappy with the seat selection or would like to choose your\n" +
                "own seat prior to departure, please visit your corresponding airlines\n" +
                "website to change your seat selection. We always suggest you check in\n" +
                "online at least 48-24 hours prior to departure depending on your airline\n" +
                "conditions. If you are unable to select your seats prior to departure you\n" +
                "will be able to select your seats during online check in process.\n" +
                "Payment\n" +
                "We take your credit card security seriously\n" +
                "We may ask you to verify your payment after you have received your\n" +
                "receipt. Fly365 will never ask you to send credit card information over\n" +
                "email. Our snapshot verification process is the safest and quickest way to\n" +
                "instantly verify the payment to us. You can simply click the payment\n" +
                "charge to Fly365 from your device and send us an email or using the\n" +
                "snippet tool on your desktop or laptop. We will never request anything\n" +
                "further than this and we will never request bank details or credit card\n" +
                "numbers. Please ensure you look out for any follow-up emails or text\n" +
                "messages.\n" +
                "Baggage\n" +
                "We do understand baggage and check in baggage can be confusing and\n" +
                "not clear on most websites. Your baggage limit on your Confirmation\n" +
                "above is on a per person basis. Infants are allowed 10kgs of luggage.\n" +
                "Below is an example only and we do suggest you check with your airline\n" +
                "for further information.\n" +
                "Carry On:\n" +
                "As a standard across all airlines your carry-on luggage must not weigh\n" +
                "more than 7kgs (1 piece of cabin baggage weighing 7kg per person).\n" +
                "Checked Luggage:\n" +
                "There are two systems that the airlines use. One is the kilo system and\n" +
                "the other is the Piece System e.g.\n" +
                "1 piece = 1 checked baggage weighing a maximum 23kg per person.\n" +
                "2 pieces = 2 checked baggage each weighing a maximum 23kg per\n" +
                "person.\n" +
                "Kilo System e.g.\n" +
                "30kgs = 30 kilos of baggage can be checked from 1 or more pieces\n" +
                "totalling 30kgs per person.\n" +
                "Mixed Baggage Solutions:\n" +
                "More and more airlines are now offering a mixed baggage solution. Some\n" +
                "airlines will offer a cheaper fare for less baggage. This can happen on one\n" +
                "itinerary as your fare class maybe cheap on the leg to your destination\n" +
                "and more expensive on the way back. Don’t assume your baggage is the\n" +
                "same across all flights. Please ensure you check your baggage options on\n" +
                "your itinerary in great detail.\n" +
                "Fare Rules\n" +
                "Important Airline Terms and Conditions Fare\n" +
                "Rules\n" +
                "Your Ticket is non-refundable.\n" +
                "Date and time changes are permitted 72 hours prior to\n" +
                "departure.\n" +
                "Your airline change fee starts from 225 NZD per person\n" +
                "plus any fare of tax difference.\n" +
                "Fly365 will also charge a rebooking fee of 135 NZD per\n" +
                "person in addition to the airline charges.\n" +
                "If your ticket is deemed refundable by the airline,\n" +
                "Fly365 will charge Refund and Cancellation fee of 300\n" +
                "NZD per person in addition to any airline refund funds.\n" +
                "Please ensure you recon1firm your flights 48 hours\n" +
                "prior to departure.\n" +
                "Name changes are not permitted. Errors in names will\n" +
                "require purchase of another ticket.\n" +
                "No Shows are not permitted. Your ticket will be marked\n" +
                "as invalid and you will be unable to travel.\n" +
                "Some airlines will not permit changes after departure.\n" +
                "For more information kindly check our Support Centre\n");
    }

    @Then("^The booking confirmation pdf contains the correct data for Multi City$")
    public void theBookingConfirmationPdfContainsTheCorrectDataForMultiCity() throws IOException {
        URL TestURL = new URL("file://" + System.getProperty("user.dir") + "/Downloads/Booking Confirmation.pdf");
        BufferedInputStream TestFile = new BufferedInputStream(TestURL.openStream());
        PDFParser TestPDF = new PDFParser(TestFile);
        TestPDF.parse();
        String TestText = new PDFTextStripper().getText(TestPDF.getPDDocument());
        System.out.println("****************");
        System.out.println(TestText);
        System.out.println("****************");

        Assert.assertEquals(TestText,"Email : nz.support@fly365.com - monitored 24 hours a day\n" +
                "BOOKING CONFIRMATION\n" +
                "You are ready to fly\n" +
                "Thank you for booking with Fly365! Please print this confirmation and present at check-in . Ensure you have checked that all information is correct\n" +
                "Check in online, or\n" +
                "Online check via the airlines website is\n" +
                "available from 24-48hrs prior to departure.\n" +
                "If checking in at the airport, arrive up to 3\n" +
                "hours prior to departure.\n" +
                "120 Minutes\n" +
                "If you’re checking in bags, go to the airline\n" +
                "check in counters at least 120 minutes\n" +
                "before your flight.\n" +
                "60 Minutes\n" +
                "Once you have checked in, go through\n" +
                "security. You should do this at least 60\n" +
                "minutes before your flight.\n" +
                "45 Minutes\n" +
                "Arrive at the boarding gate 45 minutes\n" +
                "before departure. The gates close 20\n" +
                "minutes before the flight leaves.\n" +
                "Booking Information\n" +
                "Fly365 Reference Airlines Reference Date Issued Booking Status\n" +
                fly365Ref+" "+airlineRef+" "+dateIssued+" Confirmed\n" +
                "Contact Details\n" +
                "Name Email Phone\n" +
                "John Smith john.smith.fly365@gmail.com +20 136253637474\n" +
                "Passengers Details\n" +
                "Family Name/First Middle Names Title Birth Date Type Frequent Flyer\n" +
                "Smith/John William Mr 15 Feb 1985 Adult N/A\n" +
                "Smith/Frank William Mr 15 Feb 2012 Child N/A\n" +
                "Frank/Paul Peter Mr 15 Jan 2019 Infant N/A\n" +
                "Flights Details\n" +
                "From Auckland (AKL) to Canberra (CBR) on the "+firstTripDepartureDate+"\n" +
                "From Canberra (CBR) to Auckland (AKL) on the "+secondTripDepartureDate+"\n" +
                firstTripCarrierCode+"\n" +
                firstTripbaggageNumber+" Baggage\n" +
                firstTripdepartureTime+" - "+firstTripDepartureDate+"\n" +
                "Auckland (AKL) - New\n" +
                "Zealand\n" +
                "Auckland International -\n" +
                firstTripdepartureTerminal+"\n" +
                firstTriparrivalTime+" - "+firstTriparrivalDate+"\n" +
                "Canberra (CBR) - Australia\n" +
                "Canberra - "+firstTriparrivalTerminal+"\n" +
                firstTripTotalTravelTime+"\n" +
                "1 Stop\n" +
                firstTripFirstDepartureAirline+"\n" +
                firstTripFirstAirlineNumber+"\n" +
                firstTripbaggageNumber+" Baggage\n" +
                "Economy\n" +
                firstTripdepartureTime+" - "+firstTripDepartureDate+"\n" +
                "Auckland (AKL) - New\n" +
                "Zealand\n" +
                "Auckland International -\n" +
                firstTripdepartureTerminal+"\n" +
                firstStopArrivalTime+" - "+firstStopArrivalDate+"\n" +
                firstStopArrivalStopCityCountry+"\n" +
                firstStopArrivalAirport+" - "+firstStopArrivalTerminal+"\n" +
                firstTripFirstSegmentDuration+" "+firstTripbaggageNumberUC+"\n" +
                "Baggage\n" +
                firstTripSecondDepartureAirline+"\n" +
                firstTripSecondAirlineNumber+"\n" +
                firstTripbaggageNumber+" Baggage\n" +
                "Economy\n" +
                firstStopDepartureTime+" - "+firstStopDepartureDate+"\n" +
                firstStopDepartureStopCityCountry+"\n" +
                firstStopDepartureAirport+" - "+firstStopDepartureTerminal+"\n" +
                firstTriparrivalTime+" - "+firstTriparrivalDate+"\n" +
                "Canberra (CBR) - Australia\n" +
                "Canberra - "+firstTriparrivalTerminal+"\n" +
                firstTripSecondSegmentDuration+" "+firstTripbaggageNumberUC+"\n" +
                "Baggage\n" +
                secondTripCarrierCode+"\n" +
                secondTripbaggageNumber+" Baggage\n" +
                secondTripdepartureTime+" - "+secondTripDepartureDate+"\n" +
                "Canberra (CBR) - Australia\n" +
                "Canberra - "+secondTripdepartureTerminal+"\n" +
                secondTriparrivalTime+" - "+secondTriparrivalDate+"\n" +
                "Auckland (AKL) - New\n" +
                "Zealand\n" +
                "Auckland International -\n" +
                secondTriparrivalTerminal+"\n" +
                secondTripTotalTravelTime+"\n" +
                "1 Stop\n" +
                secondTripFirstDepartureAirline+"\n" +
                secondTripFirstAirlineNumber+"\n" +
                secondTripbaggageNumber+" Baggage\n" +
                "Economy\n" +
                secondTripdepartureTime+" - "+secondTripDepartureDate+"\n" +
                "Canberra (CBR) - Australia\n" +
                "Canberra - "+secondTripdepartureTerminal+"\n" +
                secondStopArrivalTime+" - "+secondStopArrivalDate+"\n" +
                secondStopArrivalStopCityCountry+"\n" +
                secondStopArrivalAirport+" - "+secondStopArrivalTerminal+"\n" +
                secondTripFirstSegmentDuration+" "+secondTripbaggageNumberUC+"\n" +
                "Baggage\n" +
                secondTripSecondDepartureAirline+"\n" +
                secondTripSecondAirlineNumber+"\n" +
                secondTripbaggageNumber+" Baggage\n" +
                "Economy\n" +
                secondStopDepartureTime+" - "+secondStopDepartureDate+"\n" +
                secondStopDepartureStopCityCountry+"\n" +
                secondStopDepartureAirport+" - "+secondStopDepartureTerminal+"\n" +
                secondTriparrivalTime+" - "+secondTriparrivalDate+"\n" +
                "Auckland (AKL) - New\n" +
                "Zealand\n" +
                "Auckland International -\n" +
                secondTriparrivalTerminal+"\n" +
                secondTripSecondSegmentDuration+" "+secondTripbaggageNumberUC+"\n" +
                "Baggage\n" +
                "CONNECTING FLIGHT :  Stop Time : "+firstTripStopDuration+"\n" +
                "CONNECTING FLIGHT :  Stop Time : "+secondTripStopDuration+"\n" +
                "From Auckland (AKL) to Canberra (CBR) on the "+thirdTripDepartureDate+"\n" +
                thirdTripCarrierCode+"\n" +
                thirdTripbaggageNumber+" Baggage\n" +
                thirdTripdepartureTime+" - "+thirdTripDepartureDate+"\n" +
                "Auckland (AKL) - New\n" +
                "Zealand\n" +
                "Auckland International -\n" +
                thirdTripdepartureTerminal+"\n" +
                thirdTriparrivalTime+" - "+thirdTriparrivalDate+"\n" +
                "Canberra (CBR) - Australia\n" +
                "Canberra - "+thirdTriparrivalTerminal+"\n" +
                thirdTripTotalTravelTime+"\n" +
                "1 Stop\n" +
                thirdTripFirstDepartureAirline+"\n" +
                thirdTripFirstAirlineNumber+"\n" +
                thirdTripbaggageNumber+" Baggage\n" +
                "Economy\n" +
                thirdTripdepartureTime+" - "+thirdTripDepartureDate+"\n" +
                "Auckland (AKL) - New\n" +
                "Zealand\n" +
                "Auckland International -\n" +
                thirdTripdepartureTerminal+"\n" +
                thirdStopArrivalTime+" - "+thirdStopArrivalDate+"\n" +
                thirdStopArrivalStopCityCountry+"\n" +
                thirdStopArrivalAirport+" -\n" +
                thirdStopArrivalTerminal+"\n" +
                thirdTripFirstSegmentDuration+" "+thirdTripbaggageNumberUC+"\n" +
                "Baggage\n" +
                thirdTripSecondDepartureAirline+"\n" +
                thirdTripSecondAirlineNumber+"\n" +
                thirdTripbaggageNumber+" Baggage\n" +
                "Economy\n" +
                thirdStopDepartureTime+" - "+thirdStopDepartureDate+"\n" +
                thirdStopDepartureStopCityCountry+"\n" +
                thirdStopDepartureAirport+" -\n" +
                thirdStopDepartureTerminal+"\n" +
                thirdTriparrivalTime+" - "+thirdTriparrivalDate+"\n" +
                "Canberra (CBR) - Australia\n" +
                "Canberra - "+thirdTriparrivalTerminal+"\n" +
                thirdTripSecondSegmentDuration+" "+thirdTripbaggageNumberUC+"\n" +
                "Baggage\n" +
                "CONNECTING FLIGHT :  Stop Time : "+thirdTripStopDuration+"\n" +
                "Notes\n" +
                "Online Itinerary Title\n" +
                "Please note that certain airlines consolidate passenger names and titles,\n" +
                "and some may show your name format similar to the examples below.\n" +
                "E.g. Mr John Smith will be shown as:\n" +
                "Smithmr John; or\n" +
                "Johnmr Smith\n" +
                "Passport / Visa\n" +
                "Please ensure your passport is valid for six months beyond your intended\n" +
                "return date. Some countries require you to obtain a visa prior to departure\n" +
                "or on arrival into certain countries. Please check your visa requirements\n" +
                "prior to travel.\n" +
                "Do I need to Confirm My Flights?\n" +
                "Yes - Important: Re-Con\uF001rming Flights Read carefully.\n" +
                "Fly365 strongly recommends recon\uF001rming all \uF002ights a minimum 48-24\n" +
                "hours prior to departure.\n" +
                "Failure to reconfirm flights may result in you missing your departure\n" +
                "times due to a sudden Airline Schedule Change or in the event your\n" +
                "flight has been cancelled.\n" +
                "It is important to note, that your booking is your responsibility and\n" +
                "you are required to be aware of any potential changes that may\n" +
                "occur in your itinerary.\n" +
                "You can also track your \uF002ight progress by going to google and typing\n" +
                "your \uF002ight number on day of departure.\n" +
                "Seating\n" +
                "Fly365 will always try and pre-seat you prior to travel. If you have been\n" +
                "pre-seated, you will notice this on your Itinerary/Confirmation above.\n" +
                "Some airlines will charge for us to pre-seat prior to departure and this can\n" +
                "start from 25.00 NZD per seat per \uF002ight sector.\n" +
                "If you are unhappy with the seat selection or would like to choose your\n" +
                "own seat prior to departure, please visit your corresponding airlines\n" +
                "website to change your seat selection. We always suggest you check in\n" +
                "online at least 48-24 hours prior to departure depending on your airline\n" +
                "conditions. If you are unable to select your seats prior to departure you\n" +
                "will be able to select your seats during online check in process.\n" +
                "Payment\n" +
                "We take your credit card security seriously\n" +
                "We may ask you to verify your payment after you have received your\n" +
                "receipt. Fly365 will never ask you to send credit card information over\n" +
                "email. Our snapshot verification process is the safest and quickest way to\n" +
                "instantly verify the payment to us. You can simply click the payment\n" +
                "charge to Fly365 from your device and send us an email or using the\n" +
                "snippet tool on your desktop or laptop. We will never request anything\n" +
                "further than this and we will never request bank details or credit card\n" +
                "numbers. Please ensure you look out for any follow-up emails or text\n" +
                "messages.\n" +
                "Baggage\n" +
                "We do understand baggage and check in baggage can be confusing and\n" +
                "not clear on most websites. Your baggage limit on your Confirmation\n" +
                "above is on a per person basis. Infants are allowed 10kgs of luggage.\n" +
                "Below is an example only and we do suggest you check with your airline\n" +
                "for further information.\n" +
                "Carry On:\n" +
                "As a standard across all airlines your carry-on luggage must not weigh\n" +
                "more than 7kgs (1 piece of cabin baggage weighing 7kg per person).\n" +
                "Checked Luggage:\n" +
                "There are two systems that the airlines use. One is the kilo system and\n" +
                "the other is the Piece System e.g.\n" +
                "1 piece = 1 checked baggage weighing a maximum 23kg per person.\n" +
                "2 pieces = 2 checked baggage each weighing a maximum 23kg per\n" +
                "person.\n" +
                "Kilo System e.g.\n" +
                "30kgs = 30 kilos of baggage can be checked from 1 or more pieces\n" +
                "totalling 30kgs per person.\n" +
                "Mixed Baggage Solutions:\n" +
                "More and more airlines are now offering a mixed baggage solution. Some\n" +
                "airlines will offer a cheaper fare for less baggage. This can happen on one\n" +
                "itinerary as your fare class maybe cheap on the leg to your destination\n" +
                "and more expensive on the way back. Don’t assume your baggage is the\n" +
                "same across all flights. Please ensure you check your baggage options on\n" +
                "your itinerary in great detail.\n" +
                "Fare Rules\n" +
                "Important Airline Terms and Conditions Fare\n" +
                "Rules\n" +
                "Your Ticket is non-refundable.\n" +
                "Date and time changes are permitted 72 hours prior to\n" +
                "departure.\n" +
                "Your airline change fee starts from 225 NZD per person\n" +
                "plus any fare of tax difference.\n" +
                "Fly365 will also charge a rebooking fee of 135 NZD per\n" +
                "person in addition to the airline charges.\n" +
                "If your ticket is deemed refundable by the airline,\n" +
                "Fly365 will charge Refund and Cancellation fee of 300\n" +
                "NZD per person in addition to any airline refund funds.\n" +
                "Please ensure you recon1firm your flights 48 hours\n" +
                "prior to departure.\n" +
                "Name changes are not permitted. Errors in names will\n" +
                "require purchase of another ticket.\n" +
                "No Shows are not permitted. Your ticket will be marked\n" +
                "as invalid and you will be unable to travel.\n" +
                "Some airlines will not permit changes after departure.\n" +
                "For more information kindly check our Support Centre\n");
    }


}
