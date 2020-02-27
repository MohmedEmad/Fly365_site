@Sign_Out
Feature: Add Traveller to Login user

  Background:
    Given Navigate to "NZ" Fly365 "stage" site


  Scenario: Check that Login user can add traveller
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And open login page
    And 'Sign In' page is opened
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    Then the user shall be redirect to my booking page
    And User press on traveller tab
    And user press on add traveller button
    And user add traveller
    Then Success message is display
    And Delete new traveller from database
    And delete new user at database "john.smith.fly365@gmail.com"

  Scenario: Check that Login user can delete traveller
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And open login page
    And 'Sign In' page is opened
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    Then the user shall be redirect to my booking page
    And User press on traveller tab
    And user press on add traveller button
    And user add traveller
    And User delete the traveler
    Then Deleted user is removed from the list
    Then Success message is display
    And Delete new traveller from database
    And delete new user at database "john.smith.fly365@gmail.com"

  Scenario: Check that Login user can edit traveller
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And open login page
    And 'Sign In' page is opened
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    Then the user shall be redirect to my booking page
    And User press on traveller tab
    And user press on add traveller button
    And user add traveller
    And User edit the saved traveler
    Then The traveler displayed updated
    Then Success message is display
    And Delete new traveller from database
    And delete new user at database "john.smith.fly365@gmail.com"

