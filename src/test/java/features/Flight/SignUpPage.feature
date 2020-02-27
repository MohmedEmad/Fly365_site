@Subscription_Email
@Sign_Up
Feature: Sign Up

  Background: Open Fly365 Site
    Given Navigate to "NZ" Fly365 "stage" site
    And   Open Sign up page
    And Delete the user "john.smith.fly365@gmail.com" if he exists in the database

  Scenario: Verify that the user can open sign up page
    And   Sign Up page is opened

  @Go_Tab_Again
  @New_Tab
  @Email_Logout
  @Sign_Out
  Scenario: Verify that the Account Verification Success email is sent successfully
    And Delete all messages in the Inbox
    And   Fill the following required data
      | First Name | Last Name | Email Address               | Password |
      | John       | Smith     | john.smith.fly365@gmail.com | 12345678 |
    And   Click on Create Account
    And Go to the email account
    And Open the new message
    And Press on Verify Button in the email
    Then The Account Verification Success email is sent successfully

  @Sign_Out
  Scenario: Verify that Verify your email is sent successfully
    And Delete all messages in the Inbox
    And   Fill the following required data
      | First Name | Last Name | Email Address               | Password |
      | John       | Smith     | john.smith.fly365@gmail.com | 12345678 |
    And   Click on Create Account
    Then Verify your email is sent successfully

  @New_Tab
  @Email_Logout
  @Sign_Out
  Scenario: Verify that the account is verified successfully
    And Delete all messages in the Inbox
    And   Fill the following required data
      | First Name | Last Name | Email Address               | Password |
      | John       | Smith     | john.smith.fly365@gmail.com | 12345678 |
    And   Click on Create Account
    And Go to the email account
    And Open the new message
    And Press on Verify Button in the email
    Then The account is verified successfully

  Scenario: Verify that the user can't sign up without the mandatory fields
    And   Click on Create Account
    Then  The system display validation messages for all mandatory fields


  Scenario Outline: Verify that the user can't sign up with invalid first name
    And  Fill required data "<First Name>", "<Last Name>", "<Email Address>", "<Password>"
    And  Click on Create Account
    Then The system should display validation message for invalid name
    Examples:
      | First Name | Last Name | Email Address               | Password |
      | 1234       | Smith     | john.smith.fly365@gmail.com | 12345678 |
      | !@#$%^&    | Smith     | john.smith.fly365@gmail.com | 12345678 |
      | حرف عربي   | Smith     | john.smith.fly365@gmail.com | 12345678 |


  Scenario Outline: Verify that the user can't sign up with invalid family name
    And  Fill required data "<First Name>", "<Last Name>", "<Email Address>", "<Password>"
    And  Click on Create Account
    Then The system should display validation message for invalid name
    Examples:
      | First Name | Last Name | Email Address               | Password |
      | John       | 1234      | john.smith.fly365@gmail.com | 12345678 |
      | John       | !@#$%^&   | john.smith.fly365@gmail.com | 12345678 |
      | John       | حرف عربي  | john.smith.fly365@gmail.com | 12345678 |

  @Sign_Out
  Scenario: Verify that the user can sign up
    And   Fill the following required data
      | First Name | Last Name | Email Address               | Password |
      | John       | Smith     | john.smith.fly365@gmail.com | 12345678 |
    And   Click on Create Account
    Then  The user created successfully
    And   The new record set on database


  Scenario Outline: Verify that the user can't sign up with invalid email
    And  Fill required data "<First Name>", "<Last Name>", "<Email Address>", "<Password>"
    And  Click on Create Account
    Then The system should display validation message for invalid email
    Examples:
      | First Name | Last Name | Email Address        | Password |
      | John       | Smith     | plainttext           | 12345678 |
      | John       | Smith     | omda @mailinator.com | 12345678 |
      | John       | Smith     | omda@mail nantor.com | 12345678 |
      | John       | Smith     | omda@mailinator      | 12345678 |
      | John       | Smith     | om@mailinator@com    | 12345678 |
      | John       | Smith     | omda@mailnantor..com | 12345678 |


  Scenario Outline: Verify that the user can't sign up with password less than 8 or more than 50 characters
    And  Fill required data "<First Name>", "<Last Name>", "<Email Address>", "<Password>"
    Then The system should display validation message for invalid password
    Examples:
      | First Name | Last Name | Email Address               | Password |
      | John       | Smith     | john.smith.fly365@gmail.com | 123      |
      | John       | Smith     | john.smith.fly365@gmail.com | !@##     |
      | John       | Smith     | john.smith.fly365@gmail.com | Hell@oo  |


  Scenario: Verify that the user can't sign up with the same email twice
    Given insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And   Fill the following required data
      | First Name | Last Name | Email Address               | Password |
      | John       | Smith     | john.smith.fly365@gmail.com | 12345678 |
    And Click on Create Account
    Then The system display validation message for email already exist


  Scenario: Verify that the user can show and hide the entered password
    And   Fill the following required data
      | First Name | Last Name | Email Address               | Password |
      | John       | Smith     | john.smith.fly365@gmail.com | 12345678 |
    And Click on Show beside password
    Then The password should display


  Scenario: Verify that the user can hide the entered password after show it
    And   Fill the following required data
      | First Name | Last Name | Email Address               | Password |
      | John       | Smith     | john.smith.fly365@gmail.com | 12345678 |
    And Click on Show beside password
    And Click on Hide beside password
    Then The password should hide

