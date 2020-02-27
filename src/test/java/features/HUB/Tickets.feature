Feature: tickets

  Scenario: Verify that super admin can create ticket successfully
    Given Open hub login page
    And login into hub with super admin
    And open Back office
    And open tickets
    And press on create ticket
    And fill message data
    And press send
    Then ticket created


  Scenario: Verify that customer can send direct mail to open a ticket
    Given send direct mail to support mail
    And Open hub login page
    And login into hub with super admin
    And open Back office
    And open tickets
    And press on create ticket
    And fill message data
    And press send
    Then ticket created


  Scenario: Verify ticket is created through cancelling non refundable booking
    Given Navigate to "NZ" Fly365 "stage" site
    Given Delete All Rules
    And Book a "round trip" trip from API for "stage" and get "order"
    And Get data for this booking "john.smith.fly365@gmail.com"
    And Get StoreID
    And Create "Active" "Non Refundable" Rule from API for "stage"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "orderNumber"
    And Press Find Booking
    And Click on Manage My Booking
    And Get Fly Reference
    And Click Cancel Booking
    And Enter Cancel Comment
    And Open hub login page
    And login into hub with super admin
    And open Back office
    And open tickets
    Then Cancel Request is created in Tickets
    And Delete Created Rule From Database
