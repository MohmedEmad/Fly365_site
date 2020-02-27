Feature: login to account

  Background:
    Given Navigate to "NZ" Fly365 "stage" site
    And open login page

  Scenario: verify that uer can't login with unregistered data
    And user enter unregistered email
    And user enter password "@test123"
    When the user click on login button
    Then user shall see InValid Login Error Message

  Scenario: login into account with wrong password
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "1122334455"
    When the user click on login button
    Then user shall see InValid Login Error Message
    And delete new user at database "john.smith.fly365@gmail.com"

  Scenario: login into account with password less than 8 chars
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "111"
    When the user click on login button
    Then user shall see password too short error message

  Scenario: login into account with password more than 60 chars
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "1111111111111111111111111111111111111111111111111111111111111"
    When the user click on login button
    Then user shall see password too long error message

  Scenario: login into account with empty password
    And user enter email "john.smith.fly365@gmail.com"
    And user enter empty password
    When the user click on login button
    Then user shall see empty password error message

  Scenario: Login into account with empty email
    And user enter an empty email
    And user enter password "@Test123"
    When the user click on login button
    Then user shall see email empty error message

  Scenario: Login into account with invalid email formation
    And user enter email "john.smith.fly365gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    Then user shall see email error message

  @Sign_Out
  Scenario: Login into account with correct details
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    Then the user shall be redirect to my booking page
    And user logout
    And delete new user at database "john.smith.fly365@gmail.com"

  @Sign_Out
  Scenario: Login into account with correct Upper case email
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "JOHN.SMITH.FLY365@GMAIL.COM"
    And user enter password "@Test123"
    When the user click on login button
    Then the user shall be redirect to my booking page
    And user logout
    And delete new user at database "john.smith.fly365@gmail.com"

