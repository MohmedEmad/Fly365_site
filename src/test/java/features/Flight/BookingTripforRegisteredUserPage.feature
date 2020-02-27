Feature: Booking a Trip for Registered User

  Background:
    Given Navigate to "NZ" Fly365 "stage" site
    And open login page

  @Sign_Out
  Scenario: Verify that the registered user can book a One-Way trip
    And Delete all messages in the Inbox
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    And Wait until My Booking Page is opened
    And Navigate to "NZ" Fly365 "stage" site
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Choose a trip
    And Add the following data in the passenger Details
      | Title | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr    | John       | William     | Smith     | 15  | February | 1985 |
    And Click on Next Step
    And Add a valid data for the credit card
      | Card Holder Number | Card Number         | Card Expire Date | Card CVV |
      | John Smith         | 5399 9999 9999 9999 | 0225             | 123      |
    And Select the passenger name as passport acknowledgment
    And Select the Fare Rules and Terms and Conditions
    And Press on Pay button
    And Get the 'Fly365 Ref' code
    Then 'Thank you for booking with Fly365' message is displayed
    Then Booking Confirmation email is displayed
    And delete new user at database "john.smith.fly365@gmail.com"

  @delete_pdf
  @Sign_Out
  Scenario: Verify that the the Tax Invoice pdf contains the correct data (Round Trip)
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    And Wait until My Booking Page is opened
    And Navigate to "NZ" Fly365 "stage" site
    And Select Round Trip trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure for round trip, after "5" day from today
    And Select the date of the return for round trip, after "15" day from today
    And Select Passengers: "2" adult, "2" child, "2" infant
    And Select "Economy" Class
    And Press on Search Now
    ##And Press on 'Stops' Filter
    ##And Select 'One Stop' trips
    And Choose a trip
    And Add the following data in the passenger Details
      | Title  | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr     | John       | William     | Smith     | 15  | February | 1985 |
      | Mr     | Michael    | William     | Smith     | 15  | February | 1985 |
      | Master | Frank      | William     | Smith     | 15  | February | 2012 |
      | Master | Peter      | William     | Smith     | 15  | February | 2012 |
      | Miss   | Suzy       | Peter       | Frank     | 15  | January  | 2019 |
      | Miss   | Lila       | Peter       | Frank     | 15  | January  | 2019 |
    And Click on Next Step
    And Add a valid data for the credit card
      | Card Holder Number | Card Number         | Card Expire Date | Card CVV |
      | John Smith         | 5399 9999 9999 9999 | 0225             | 123      |
    And Select the passenger name as passport acknowledgment
    And Select the Fare Rules and Terms and Conditions
    And Press on Pay button
    And Get the 'Fly365 Ref' code
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid Reference 'Fly365 Ref'
    And Press Find Booking
    And Get the different values of the Flight
    And Get the different values of the Flight for the Round Trip
    And Download the tax invoice pdf
    Then The tax invoice pdf contains the correct data for Round Trip
    And delete new user at database "john.smith.fly365@gmail.com"

  @delete_pdf
  @Sign_Out
  Scenario: Verify that the the Booking Confirmation pdf contains the correct data (Round Trip)
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    And Wait until My Booking Page is opened
    And Navigate to "NZ" Fly365 "stage" site
    And Select Round Trip trip
    And Add airport to the Origin "Auckland International (AKL), New Zealand"
    And Add airport to the Destination "Canberra (CBR), Australia"
    And Select the date of the departure for round trip, after "5" day from today
    And Select the date of the return for round trip, after "15" day from today
    And Select Passengers: "1" adult, "1" child, "1" infant
    And Press on Search Now
    ##And Press on 'Stops' Filter
    ##And Select 'One Stop' trips
    And Choose a trip
    And Add the following data in the passenger Details
      | Title  | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr     | John       | William     | Smith     | 15  | February | 1985 |
      | Master | Frank      | William     | Smith     | 15  | February | 2012 |
      | Master | Paul       | Peter       | Frank     | 15  | January  | 2019 |
    And Click on Next Step
    And Add a valid data for the credit card
      | Card Holder Number | Card Number         | Card Expire Date | Card CVV |
      | John Smith         | 5399 9999 9999 9999 | 0225             | 123      |
    And Select the passenger name as passport acknowledgment
    And Select the Fare Rules and Terms and Conditions
    And Press on Pay button
    And Get the 'Fly365 Ref' code
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid Reference 'Fly365 Ref'
    And Press Find Booking
    And Get the different values of the Flight
    And Get the different values of the Flight for the Round Trip
    And Download the booking confirmation pdf
    Then The booking confirmation pdf contains the correct data for Round Trip
    And delete new user at database "john.smith.fly365@gmail.com"

  @Sign_Out
  Scenario: Verify that the Booking Confirmation email contains 'Tax Invoice' and 'Booking Confirmation' pdf (Multi City Trip)
    And Delete all messages in the Inbox
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    And Wait until My Booking Page is opened
    And Navigate to "NZ" Fly365 "stage" site
    And Select Multi City trip
    And Choose the number of flights "3"
    And Add the following origin, destinations and date periods
      | Origin                                   | Destination                               | Date Period |
      | Cairo International Airport (CAI), Egypt | Dublin International (DUB), Ireland       | 10          |
      | Dublin International (DUB), Ireland      | Cairo International Airport (CAI), Egypt  | 20          |
      | Cairo International Airport (CAI), Egypt | Auckland International (AKL), New Zealand | 30          |
    And Press on Search Now
    And Choose a trip
    And Add the following data in the passenger Details
      | Title | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr    | John       | William     | Smith     | 15  | February | 1985 |
    And Click on Next Step
    And Add a valid data for the credit card
      | Card Holder Number | Card Number         | Card Expire Date | Card CVV |
      | John Smith         | 5399 9999 9999 9999 | 0225             | 123      |
    And Select the passenger name as passport acknowledgment
    And Select the Fare Rules and Terms and Conditions
    And Press on Pay button
    And Get the 'Fly365 Ref' code
    Then Booking Confirmation email contains 'Tax Invoice' and 'Booking Confirmation' pdf
    And delete new user at database "john.smith.fly365@gmail.com"