Feature: Apply Hold on Bookings

  Scenario: Verify Hold button is not displayed if departure is less than minimum hours before departure and ticketing
    Given  Navigate to Fly "stage" site
    When Set Data on stage for hold Rule API through fly365_nz and exclude "sv"
      | Min hours before departure |  | Min hours before ticketing |  | Hold hours |  | Hold status |  | hold value |
      | 100                   |       | 120                        |  | 90      |     | true  |     | 40         |
    And Navigate to "NZ" Fly365 "stage" site
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "2" day from today
    And Press on Search Now
    Then Hold button is not displayed

  Scenario: Verify Hold button is not displayed when hold is matched but disabled
    Given  Navigate to Fly "stage" site
    When Set Data on stage for hold Rule API through fly365_nz and exclude "sv","ms"
      | Min hours before departure |  | Min hours before ticketing |  | Hold hours |  | Hold status |  | hold value |
      | 72                         |  | 72                         |  | 90      |  | false   |  | 40         |
    And Navigate to "NZ" Fly365 "stage" site
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    Then Hold button is not displayed

  Scenario: Verify Hold button is not displayed when hold value is 0
    Given  Navigate to Fly "stage" site
    When Set Data on stage for hold Rule API through fly365_nz and exclude "sv"
      | Min hours before departure |  | Min hours before ticketing |  | Hold hours |  | Hold status |  | hold value |
      | 72                         |  | 72                         |  | 90      |  | true    |  | 40         |
    And Navigate to "NZ" Fly365 "stage" site
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    Then Hold button is not displayed

  Scenario: Verify Hold value is displayed correctly in hold button
    Given  Navigate to Fly "stage" site
    When Set Data on stage for hold Rule API through fly365_nz and exclude "sv"
      | Min hours before departure |  | Min hours before ticketing |  | Hold hours |  | Hold status |  | hold value |
      | 72                         |  | 72                         |  | 90      |  | true |  | 300        |
    And Navigate to "NZ" Fly365 "stage" site
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dublin International (DUB), Ireland"
    And Select the date of the departure, after "10" day from today
    And Press on Search Now
    Then Hold With Value "300" is displayed in hold button

  Scenario: Verify Hold Hours is displayed successfully
    Given  Navigate to Fly "stage" site
    When Set Data on stage for hold Rule API through fly365_nz and exclude "sv"
      | Min hours before departure |  | Min hours before ticketing |  | Hold hours |  | Hold status |  | hold value |
      | 72                         |  | 72                         |  | 72     |  | true |  | 40         |
    And Navigate to "NZ" Fly365 "stage" site
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dubai (DXB), United Arab Emirates"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Click on hold button
    Then Hold hours is displayed with this value "72" correctly in passenger details


  Scenario: Verify Excluded airline will not have hold button
    Given  Navigate to Fly "stage" site
    When Set Data on stage for hold Rule API through fly365_nz and exclude "ms"
      | Min hours before departure |  | Min hours before ticketing |  | Hold hours |  | Hold status |  | hold value |
      | 72                         |  | 72                         |  | 90      |  | true  |  | 40         |
    And Navigate to "NZ" Fly365 "stage" site
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dubai (DXB), United Arab Emirates"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    And Click on Airline Filter
    And Filter with the following Airline "Egyptair"
    Then Hold button is not displayed


  Scenario: Traveler can hold a trip
    Given  Navigate to Fly "stage" site
    When Set Data on stage for hold Rule API through fly365_nz and exclude "sv"
      | Min hours before departure |  | Min hours before ticketing |  | Hold hours |  | Hold status |  | hold value |
      | 72                         |  | 72                         |  | 90      |  | true|  | 300       |
    And Navigate to "NZ" Fly365 "stage" site
    And Select One Way trip
    And Add airport to the Origin "Cairo International Airport (CAI), Egypt"
    And Add airport to the Destination "Dubai (DXB), United Arab Emirates"
    And Select the date of the departure, after "5" day from today
    And Press on Search Now
    Then Hold With Value "300" is displayed in hold button
    And Click on hold button
    And Add the following data in the passenger Details
      | Title | First Name | Middle Name | Last Name | Day | Month    | Year |
      | Mr    | John       | William     | Smith     | 15  | February | 1985 |
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
    Then The total hold price is the same before and after the booking

    Scenario: Verify hold is applied on itineraries when they are matched
    Given  Navigate to Fly "stage" site
      When Set Data on stage for hold Rule API through fly365_nz and exclude "sv"
        | Min hours before departure |  | Min hours before ticketing |  | Hold hours |  | Hold status |  | hold value |
        | 72                         |  | 72                         |  | 90      |  | true   |  | 40         |
      And Make Search from API
     Then Assert itineraries has hold


  Scenario: Verify hold isn't applied on itineraries when hold value is 0
    Given  Navigate to Fly "stage" site
    When Set Data on stage for hold Rule API through fly365_nz and exclude "sv"
      | Min hours before departure |  | Min hours before ticketing |  | Hold hours |  | Hold status |  | hold value |
      | 72                         |  | 72                         |  | 90      |  | true |  | 0         |
    And Make Search from API
    Then Itineraries does not have hold

  Scenario: Verify Hold is not applied when hold is matched but disabled
    Given  Navigate to Fly "stage" site
    When Set Data on stage for hold Rule API through fly365_nz and exclude "sv","ms"
      | Min hours before departure |  | Min hours before ticketing |  | Hold hours |  | Hold status |  | hold value |
      | 72                         |  | 72                         |  | 90      |  | false |  | 40         |
    And Make Search from API
    Then Itineraries does not have hold
