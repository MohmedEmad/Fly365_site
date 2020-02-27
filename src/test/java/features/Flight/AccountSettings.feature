@Sign_Out
Feature: Open Account Settings page

  Background: Open Fly365 site
    Given Navigate to "NZ" Fly365 "stage" site
    And open login page

  Scenario: Verify that the logged in user can open account settings page
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    And Click on Account Settings tab
    Then Account Settings page opened
    And delete new user at database "john.smith.fly365@gmail.com"


  Scenario: Verify that the user can update his information
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    And Click on Account Settings tab
    Then Update First Name with David
    And Success message for update is display
    Then Check updated First Name
    And delete new user at database "john.smith.fly365@gmail.com"


  Scenario: Verify that the user can add his billing address
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    And Click on Account Settings tab
    And Click on Billing Address tab
    And Add Billing Address
      | Address Line 1 | Address Line 2 | City      | ZIP Code |
      | 509 Z Nozha    | Cairo, Egypt   | Nasr City | 11255    |
    And Success message for update is display
    And delete new user at database "john.smith.fly365@gmail.com"


  Scenario: Verify that user can change his password
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    And Click on Account Settings tab
    And Click on Change Password tab
    Then Enter old password "@Test123" and Enter New password "12345678"
    And Click on save button
    And Success message for update is display
    And delete new user at database "john.smith.fly365@gmail.com"


  Scenario: Verify that user can update his email settings
    And insert new user at database "john.smith.fly365@gmail.com" "$2a$10$CBQKVE4xsSFH6mYiUSFiAODo0omGgQQbXV0Vmtngvs84gI0hXZoIC"
    And user enter email "john.smith.fly365@gmail.com"
    And user enter password "@Test123"
    When the user click on login button
    And Click on Account Settings tab
    And Click on Email Settings tab
    And Success message for update is display
    And delete new user at database "john.smith.fly365@gmail.com"

