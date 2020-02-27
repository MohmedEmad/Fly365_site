Feature: Apply Cancel on Bookings

  Scenario: Verify that customer can cancel booking when rule matches and successful message is displayed to customer
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "round trip" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    Then Request Is sent Successfully
    And Delete Created Rule From Database

  Scenario: Verify that status of canceled booking is to be refunded to the customer
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "round trip" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    Then Booking Status Will Be To Be Refunded
    And Delete Created Rule From Database

  Scenario: Verify that customer can't cancel refundable flight canceled before
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
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    Then Request Is sent Successfully
    And Click on Manage My Booking
    Then Cancel My Booking is not Clickable
    And Delete Created Rule From Database

  Scenario: Verify that canceled booking status in hub is to be refunded
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "round trip" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    And Open hub login page
    And login into hub with super admin
    And open Back office
    And Open Orders
    And Search for Order Number from Advanced Search
    Then Order Will Have To be Refunded status
    And Delete Created Rule From Database

  Scenario: Verify that canceled booking status for order details in hub is to be refunded
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "round trip" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Refundable" Rule from API for "stage"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Get Fly Reference
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    And Open hub login page
    And login into hub with super admin
    And open Back office
    And Open Orders
    And Search for booking returned in "booking pnr response" Quick Search
    Then Order Details Will Have To be Refunded status
    And Delete Created Rule From Database

  Scenario: Verify that customer can cancel one way trip when rule matches
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
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    Then Booking Status Will Be To Be Refunded
    And Delete Created Rule From Database

  Scenario: Verify that customer can cancel round trip when rule matches
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
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    Then Booking Status Will Be To Be Refunded
    And Delete Created Rule From Database

  Scenario: Verify that customer can cancel multi city trip when rule matches
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
    And Click Cancel Booking
    And Mark Terms and Conditions
    And Click Cancel My Booking
    Then Booking Status Will Be To Be Refunded
    And Delete Created Rule From Database


  Scenario: Verify that rule is not applied if it isn't active
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "round trip" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Inactive" "Refundable" Rule from API for "stage"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Click Cancel Booking
    And Enter Cancel Comment
    Then Booking Status Will still confirmed
    And Delete Created Rule From Database
