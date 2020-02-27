Feature: Find my Booking

  Background:
    Given Navigate to "NZ" Fly365 "stage" site

  Scenario: Verify that the anonymous user can retrieve his/her booking via Fly365 Reference
    And Book a "round" trip from API for "stage" and get "Fly365 Reference"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "Fly365 Reference"
    And Press Find Booking
    Then The system will retrieve the details of the Booking for this "Fly365 Reference"

  Scenario: Verify that the anonymous user can retrieve his/her booking via Airline Reference
    #And Book a trip from API for "stage" and get "Airline Reference"
    And Click on Find My Booking
    And Add a valid email address "john.smith.fly365@gmail.com"
    And Add a valid "Airline Reference"
    And Press Find Booking
    Then The system will retrieve the details of the Booking for this "Airline Reference"

  Scenario: Verify that error message appear over the two fields
    And Click on Find My Booking
    And Press Find Booking
    Then error message appear appear over the two fields



