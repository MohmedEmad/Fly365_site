Feature: Go to MyBooking page

  Background: Open Fly365 site
    Given Navigate to "NZ" Fly365 "stage" site
    And open login page

  @Sign_Out
  Scenario: Verify that the registered user can go to My Booking page
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    And Click on My Booking
    Then Check My Booking page opened
    And delete new user at database "john.smith.fly365@gmail.com"

  @Sign_Out
   Scenario: Verify that registered user can find his booking in My Bookings page
     And Book a "oneway" trip from API for "stage" and get "flyref"
     And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
     And user enter email "john.smith.fly365@gmail.com"
     And user enter password "@Test123"
     When the user click on login button
     And Click on My Booking
     Then Check My Booking page opened
     And delete new user at database "john.smith.fly365@gmail.com"

  @Sign_Out
     Scenario: Verify that user can view booking details through my booking page
       And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
       And user enter email "john.smith.fly365@gmail.com"
       And user enter password "@Test123"
       When the user click on login button
       And Click on My Booking
       Then Check My Booking page opened
       Then Click on Booking details button
       Then Assert that the booking details is displayed successfully
       And delete new user at database "john.smith.fly365@gmail.com"