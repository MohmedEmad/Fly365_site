Feature: Passenger page

  Background:
    Given Navigate to "NZ" Fly365 "stage" site

  Scenario: Verify that the mandatory fields are required at one way
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Choose a trip
    And Click on Next Step
    Then error message appear for each field at fill passenger details

  Scenario: Verify that the first "Read more" link opens on the correct link
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Choose a trip
    And Press on first 'Read more'
    Then 'If you have one name' pop up will be opened

  Scenario: Verify that the second "Read more" link opens on the correct link
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Choose a trip
    And Press on second 'Read more'
    Then 'Correct name format' pop up will be opened

  Scenario: Verify that the "Flights Details" link opens on the correct link
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Choose a trip
    And Press on 'Flights Details'
    Then 'Flights Details' is displayed


  Scenario: Verify that user can Add passport details in passenger details while booking
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Get the price of the trip
    And Choose a trip
    And Add the following data in the passenger Details
      | Title | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr    | John       | William     | Smith     | 15  | February | 1985 |
    And click on Passport details section
    And Add passport number
    And Add passport expiry date
    And Select passport country
    And Add the following data in the Contact Details
      | Title | First Name | Last Name | Email                        | Phone Number|
      | Mr    | John      | Smith      | john.smith.fly365@gmail.com  |01010101010|
    And Click on Next Step
    And Add a valid data for the credit card
      | Card Holder Number | Card Number         | Card Expire Date | Card CVV |
      | John Smith         | 5399 9999 9999 9999 | 0225             | 123      |
    And Select the passenger name as passport acknowledgment
    And Select the Fare Rules and Terms and Conditions
    And Press on Pay button
    And Assert that passport "A1234567890" and "05 Sep 2026" and "Egypt" are displayed in confirmation page


  Scenario: Verify that user can Add frequent flyer in passenger details while booking
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Get the price of the trip
    And Choose a trip
    And Add the following data in the passenger Details
      | Title | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr    | John       | William     | Smith     | 15  | February | 1985 |
    And click on frequent flyer section
    And Add frequent flyer number
    And Add the following data in the Contact Details
      | Title | First Name | Last Name | Email                        | Phone Number|
      | Mr    | John      | Smith      | john.smith.fly365@gmail.com  |01010101010|
    And Click on Next Step
    And Add a valid data for the credit card
      | Card Holder Number | Card Number         | Card Expire Date | Card CVV |
      | John Smith         | 5399 9999 9999 9999 | 0225             | 123      |
    And Select the passenger name as passport acknowledgment
    And Select the Fare Rules and Terms and Conditions
    And Press on Pay button
    And Assert that frequent flyer "123456" is displayed in confirmation page



  Scenario: Verify that user can select preferences in passenger details  while booking
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Get the price of the trip
    And Choose a trip
    And Add the following data in the passenger Details
      | Title | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr    | John       | William     | Smith     | 15  | February | 1985 |
    And Click on Service Request section
    And User select preferences
    And Add the following data in the Contact Details
      | Title | First Name | Last Name | Email                        | Phone Number|
      | Mr    | John      | Smith      | john.smith.fly365@gmail.com  |01010101010|
    And Click on Next Step
    And Add a valid data for the credit card
      | Card Holder Number | Card Number         | Card Expire Date | Card CVV |
      | John Smith         | 5399 9999 9999 9999 | 0225             | 123      |
    And Select the passenger name as passport acknowledgment
    And Select the Fare Rules and Terms and Conditions
    And Press on Pay button
    And Get Fly Reference
    And Open hub login page
    And login into hub with super admin
    And Open menu
    And Open  "BackOffice"
    And Search for booking returned in "retrieved booking pnr" Quick Search
    And Click on Edit Traveler Icon
    And Assert that seat is "Aisle Seat Request" and meal "SEA FOOD MEAL" and assistance is "WHEELCHAIR - CANNOT CLIMB STAIRS"


  Scenario: Verify that user can add special request in passenger details  while booking
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Get the price of the trip
    And Choose a trip
    And Add the following data in the passenger Details
      | Title | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr    | John       | William     | Smith     | 15  | February | 1985 |
    And Click on Write request
    And Write your request "Request"
    And Add the following data in the Contact Details
      | Title | First Name | Last Name | Email                        | Phone Number|
      | Mr    | John      | Smith      | john.smith.fly365@gmail.com  |01010101010|
    And Click on Next Step
    And Add a valid data for the credit card
      | Card Holder Number | Card Number         | Card Expire Date | Card CVV |
      | John Smith         | 5399 9999 9999 9999 | 0225             | 123      |
    And Select the passenger name as passport acknowledgment
    And Select the Fare Rules and Terms and Conditions
    And Press on Pay button
    And Open hub login page
    And login into hub with super admin
    And Open menu
    And Open  "BackOffice"
    And Search for booking returned in "retrieved booking pnr" Quick Search
    And Assert that special request is having "Testing Request"

