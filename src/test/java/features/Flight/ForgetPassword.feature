Feature: Forget Password

  Background:
    Given Navigate to "NZ" Fly365 "stage" site
    And open login page


  Scenario: Customer forget password with exist email
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And press on forget password link
    And enter email at forget password text "john.smith.fly365@gmail.com"
    When click on send email button
    Then page shall be redirect to login page
    And 'Sign In' page is opened
    And delete new user at database "john.smith.fly365@gmail.com"

  @Go_Tab_Again
  @New_Tab
  @Email_Logout
  @Sign_Out
  Scenario: User can change his/her password Successfully
    And Delete all messages in the Inbox
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And press on forget password link
    And enter email at forget password text "john.smith.fly365@gmail.com"
    When click on send email button
    And Go to the email account
    And Open the new message
    And Press on Reset Password Button in the email
    And Go to the Reset Password page and add new password "12345678"
    And Press on Reset Password Button
    Then the user shall be redirect to my booking page

  @Go_Tab_Again
  @New_Tab
  @Email_Logout
  @Sign_Out
  Scenario: User can change his/her password Successfully
    And Delete all messages in the Inbox
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And press on forget password link
    And enter email at forget password text "john.smith.fly365@gmail.com"
    When click on send email button
    And Go to the email account
    And Open the new message
    And Press on Reset Password Button in the email
    And Go to the Reset Password page and add new password "12345678"
    And Press on Reset Password Button
    And user logout
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "12345678"
    When the user click on login button
    Then the user shall be redirect to my booking page

  Scenario: Verify that the Password reset requested email is sent successfully
    And Delete all messages in the Inbox
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And press on forget password link
    And enter email at forget password text "john.smith.fly365@gmail.com"
    When click on send email button
    Then The Password reset requested email is sent successfully

  @Go_Tab_Again
  @Sign_Out
  @New_Tab
  @Email_Logout
  Scenario: Verify that the Password reset successfully email is sent successfully
    And Delete all messages in the Inbox
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And press on forget password link
    And enter email at forget password text "john.smith.fly365@gmail.com"
    When click on send email button
    And Go to the email account
    And Open the new message
    And Press on Reset Password Button in the email
    And Go to the Reset Password page and add new password "12345678"
    And Press on Reset Password Button
    Then The Password reset successfully email is sent successfully

  Scenario: Customer forget password with empty email
    And press on forget password link
    When click on send email button
    Then user shall see empty email error message at forget password page

  Scenario: Customer forget password with invalid email
    And press on forget password link
    And enter email at forget password text "john.smith.fly365gmail.com"
    When click on send email button
    Then user shall see email error message at forget password page

  Scenario: Customer forget password with Unregistered email
    And press on forget password link
    And enter unregistered email at forget password page
    When click on send email button
    Then user shall see successfully message

