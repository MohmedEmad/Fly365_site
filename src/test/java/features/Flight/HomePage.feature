Feature: Home page

  Background:
    Given Navigate to "NZ" Fly365 "stage" site

  Scenario: Verify that the Logo link in the header open on the correct link
    And Press on 'About us'
    And Press on Logo in header
    Then Home page is opened

  Scenario: Verify that the Flights link in the header open on the correct link
    And Press on 'About us'
    And Press on flights in header
    Then Home page is opened

  Scenario: Verify that the Offers link in the header open on the correct link
    And Press on offers in header
    Then Offers page is opened

  Scenario: Verify that the Sign In button in the header open on the correct link
    And Press on Sign In button
    Then 'Sign In' page will be opened

  Scenario: Verify that the Passenger Rules link in the Passenger/Cabin pop over open on the correct link
    And Press on Passenger/Cabin pop over
    And Press on Passenger Rules link
    Then 'Passenger Rules' pop up will be opened

  Scenario: Validation messages will be displayed for empty origin, destination, date
    And Press on Search Now
    Then error message appear for each field

  Scenario: Verify that the Mobile app button in Header open the correct
    And Press on Mobile app
    Then Mobile app Page will be opened link on "NZ" and "stage"
  @New_Tab
  Scenario: Verify that App Store button in Mobile app screen opens the correct App Store page
    And Press on Mobile app
    And Press on Available on App store button
    Then App Store page opened correctly
  @New_Tab
  Scenario: Verify that Play Store button in Mobile app screen opens the correct Play Store page
    And Press on Mobile app
    And Press On Get it on Google Play
    Then Google Play page opened correctly
  @New_Tab
  Scenario: Verify that App Store button in Home page opens the correct App Store page
    And Press on Available on App store button on Home Page
    Then App Store page opened correctly
  @New_Tab
  Scenario:  Verify that Play Store button in Home page opens the correct App Store page
    And Press on Get it on Google Play button on Home Page
    Then Google Play page opened correctly

  Scenario: Verify URL after changing the store from header to be Australia
    And Select Australia from store list
    Then Url shall be changed to the correct Australia store on "stage"

  Scenario: Verify URL after changing the store from header to be Malaysia
    And Select Malaysia from store list
    Then Url shall be changed to the correct Malaysia store URL on "stage"

  Scenario: Verify URL after changing the store from header to be Worldwide
    And Select Worldwide from store
    Then Url shall be changed to the correct Worldwide store URL on "stage"