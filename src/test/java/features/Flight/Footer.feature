Feature: Footer Links

  Background:
    Given Navigate to "NZ" Fly365 "stage" site


  Scenario: Verify that "afta" link in the footer open on the correct link
    And Press on 'afta'
    Then 'afta' page is opened

  Scenario: Verify that the Logo link in the footer open on the correct link
    And Press on 'About us'
    And Press on Logo in footer
    Then Home page is opened

  Scenario: Verify that first "Support Center" link in the footer open on the correct link
    And Press on first 'Support Centre'
    Then 'Support Centre' page is opened

  Scenario: Verify that second "Support Center" link in the footer open on the correct link
    And Press on second 'Support Centre'
    Then 'Support Centre' page is opened

  Scenario: Verify that "FAQs" link in the footer open on the correct link
    And Press on 'FAQs'
    Then 'FAQs' page is opened

  Scenario: Verify that "Terms and Conditions" link in the footer open on the correct link
    And Press on 'Terms and Conditions'
    Then 'Terms and Conditions' page is opened

  Scenario: Verify that "Privacy policy" link in the footer open on the correct link
    And Press on 'Privacy policy'
    Then 'Privacy Policy' page is opened

  Scenario: Verify that "About Us" link in the footer open on the correct link
    And Press on 'About us'
    Then 'About Us' page is opened

  Scenario: Verify that first "Contact Us" link in the footer open on the correct link
    And Press on first 'Contact Us'
    Then 'Contact Us' page is opened

  Scenario: Verify that second "Contact Us" link in the footer open on the correct link
    And Press on second 'Contact Us'
    Then 'Contact Us' page is opened

  Scenario: Verify that the user can subscribe his/her email
    And  Add the email address "john.smith.fly365@gmail.com" to Subscription Email field
    When Press on SUBSCRIBE
    Then Successfully validation message is displayed