Feature: Apply Modify on Bookings


  Scenario: Modify order through API
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "one way" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Modify the booking with this data through api
      | store |  | environment |  | SearchDateAfter| | departure | | arrival |
      | nz    |  | stage          |  | 15             | | JED       | | CAI     |
    And Book the new order through api on store "nz" and the environment "stage" and get "order"
    And Delete Created Rule From Database

    Scenario: Verify that user can modify a booking when there is a rule matched
      Given Navigate to "NZ" Fly365 "stage" site
      Given Delete All Rules
      And Book a "one way" trip from API for "stage" and get "order"
      And Get data for this booking "john.smith.fly365@gmail.com"
      And Get StoreID
      And Create "Active" "Refundable" Rule from API for "stage"
      And Click on Find My Booking
      And Add a valid email address "john.smith.fly365@gmail.com"
      And Add a valid "orderNumber"
      And Press Find Booking
      And Click on Manage My Booking
      And Click Change Booking
      And Press on Search Now
      And Select a flight to be a new booking
      And Add a valid data for the credit card
        | Card Holder Number | Card Number         | Card Expire Date | Card CVV |
        | John Smith         | 5399 9999 9999 9999 | 0225             | 123      |
      And Select the passenger name as passport acknowledgment
      And Select the Fare Rules and Terms and Conditions
      And Press on Pay button
      Then 'Your booking has ben changed successful' message is displayed
      And Delete Created Rule From Database

  Scenario: Verify user can't modify modified order & can send case only
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "one way" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Modify the booking with this data through api
      | store |  | environment |  | SearchDateAfter| | departure | | arrival |
      | nz    |  | stage          |  | 15             | | JED       | | CAI     |
    And Book the new order through api on store "nz" and the environment "stage" and get "order"
    And Get data for this booking with this email "john.smith.fly365@gmail.com" and "modifiedOrderNumber"
    And Delete Created Rule From Database
    And Create "Active" "Refundable" Rule from API for "stage"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "modifiedOrderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Change Booking
    And Enter request comment
    Then Modification Request Is sent Successfully
    And Delete Created Rule From Database

  Scenario: Verify user can't cancel modified order & can send case only
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "one way" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Modify the booking with this data through api
      | store |  | environment |  | SearchDateAfter| | departure | | arrival |
      | nz    |  | stage          |  | 15             | | JED       | | CAI     |
    And Book the new order through api on store "nz" and the environment "stage" and get "order"
    And Get data for this booking with this email "john.smith.fly365@gmail.com" and "modifiedOrderNumber"
    And Delete Created Rule From Database
    And Create "Active" "Refundable" Rule from API for "stage"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "modifiedOrderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Enter request comment
    Then Request Is sent Successfully
    And Delete Created Rule From Database

  Scenario: Verify old order will have status cancel
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "one way" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Modify the booking with this data through api
      | store |  | environment |  | SearchDateAfter| | departure | | arrival |
      | nz    |  | stage          |  | 15             | | JED       | | CAI     |
    And Book the new order through api on store "nz" and the environment "stage" and get "order"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    Then Old order Status Will be cancelled
    And Delete Created Rule From Database

  Scenario: Verify old order can't be cancel
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "one way" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Modify the booking with this data through api
      | store |  | environment |  | SearchDateAfter| | departure | | arrival |
      | nz    |  | stage          |  | 15             | | JED       | | CAI     |
    And Book the new order through api on store "nz" and the environment "stage" and get "order"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    Then Cancel My Booking is not Clickable
    And Delete Created Rule From Database

  Scenario: Verify old order can't be modified again
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "one way" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Modify the booking with this data through api
      | store |  | environment |  | SearchDateAfter| | departure | | arrival |
      | nz    |  | stage          |  | 15             | | JED       | | CAI     |
    And Book the new order through api on store "nz" and the environment "stage" and get "order"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    Then Change My Booking is not Clickable

  Scenario: Verify user can't change origin country
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "one way" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Modify the booking with this data through api
      | store |  | environment |  | SearchDateAfter| | departure | | arrival |
      | nz    |  | stage          |  | 15             | | DXB       | | CAI     |
    Then Assert 'Can not modify origin country.' message is returned in response
    And Delete Created Rule From Database

  Scenario: Verify only itineraries with same carrier code of old booking returned in response while modifying order
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "one way" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Modify the booking with this data through api
      | store |  | environment |  | SearchDateAfter| | departure | | arrival |
      | nz    |  | stage          |  | 15             | | JED       | | CAI     |
    Then Assert all returned itineraries have the same carrier code
    And Delete Created Rule From Database
