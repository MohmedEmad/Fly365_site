Feature: Payment page

  Background:
    Given Navigate to "NZ" Fly365 "stage" site

  Scenario: Verify that the anonymous user can book a One-Way trip
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Choose a trip
    And Add the following data in the passenger Details
      | Title | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr    | John       | William     | Smith     | 15  | February | 1985 |
    And Add the following data in the Contact Details
      | Title | First Name | Last Name | Email                        | Phone Number|
      | Mr    | John      | Smith      | john.smith.fly365@gmail.com  |01010101010|
    And Click on Next Step
    And Press on Pay button
    And Press on Pay button
    Then error message appear for each field at fill passenger details on payment page

  Scenario: Verify that the "Edit Passenger details" link opens on the correct link
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Choose a trip
    And Add the following data in the passenger Details
      | Title | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr    | John       | William     | Smith     | 15  | February | 1985 |
    And Add the following data in the Contact Details
      | Title | First Name | Last Name | Email                | Phone Number|
      | Mr    | Remon      | Refaat    | remon@mailinator.com  |01010101010|
    And Click on Next Step
    And Press on 'Edit Passenger details'
    Then 'Passenger' page will be opened

  Scenario: Verify that the "Back" link opens on the correct link
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Choose a trip
    And Add the following data in the passenger Details
      | Title | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr    | John       | William     | Smith     | 15  | February | 1985 |
    And Add the following data in the Contact Details
      | Title | First Name | Last Name | Email                | Phone Number|
      | Mr    | Remon      | Refaat    | remon@mailinator.com  |01010101010|
    And Click on Next Step
    And Press on 'Back'
    Then 'Passenger' page will be opened

  @New_Tab
  Scenario: Verify that the "Fare Rules" link opens on the correct link
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Choose a trip
    And Add the following data in the passenger Details
      | Title | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr    | John       | William     | Smith     | 15  | February | 1985 |
    And Add the following data in the Contact Details
      | Title | First Name | Last Name | Email                | Phone Number|
      | Mr    | Remon      | Refaat    | remon@mailinator.com  |01010101010|
    And Click on Next Step
    And Press on 'Fare Rules'
    Then 'Fare Rules' page will be opened

  @New_Tab
  Scenario: Verify that the "Terms and conditions" link opens on the correct link
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Choose a trip
    And Add the following data in the passenger Details
      | Title | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr    | John       | William     | Smith     | 15  | February | 1985 |
    And Add the following data in the Contact Details
      | Title | First Name | Last Name | Email                | Phone Number|
      | Mr    | Remon      | Refaat    | remon@mailinator.com  |01010101010|
    And Click on Next Step
    And Press on 'Terms and conditions'
    Then 'Terms and conditions' page will be opened