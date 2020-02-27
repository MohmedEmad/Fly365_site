Feature: Subscription Email

  Background:
    Given Navigate to "NZ" Fly365 "stage" site


  Scenario: Verify that the user can subscribe his/her email
    And  Add the email address "john.smith.fly365@gmail.com" to Subscription Email field
    When Press on SUBSCRIBE
    Then Successfully validation message is displayed

  Scenario: Verify that the user can unsubscribe his/her email
    And Delete all messages in the Inbox
    And  Add the email address "john.smith.fly365@gmail.com" to Subscription Email field
    And Press on SUBSCRIBE
    And Go to the email account
    And Open the new message
    And Press on 'Click Here' link
    Given Navigate to "NZ" Fly365 "stage" site
    And  Add the email address "john.smith.fly365@gmail.com" to Subscription Email field
    When Press on SUBSCRIBE
    Then Successfully validation message is displayed

  Scenario: Verify that the Subscription Email is sent successfully
    And Delete all messages in the Inbox
    And  Add the email address "john.smith.fly365@gmail.com" to Subscription Email field
    And Press on SUBSCRIBE
    Then The Subscription Email is sent successfully

  Scenario: Verify that the user can not subscribe with subscribed email
    And  Add previously subscribed email address "john.smith.fly365@gmail.com" to Subscription Email field
    And Press on SUBSCRIBE
    Then Error validation message is displayed

  Scenario: Verify that mandatory field at subscribe email is required
    When Press on SUBSCRIBE
    Then empty subscribe error message appear
