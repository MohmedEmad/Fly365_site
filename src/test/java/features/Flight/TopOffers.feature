Feature: Open Top Offers page

  Background: Open Fly365 site
    Given Navigate to "NZ" Fly365 "stage" site


  Scenario: Verify that 404 page is displayed when user is navigating between stores and offer doesn't exist
    Given Click on offer link
    Then Click on view details button
    And Change store
    Then Assert that 404 error page is displayed


  Scenario: Verify that the offers URL is matching the offers page
    Given Click on offer link
    Then Check current URL matches the offer page




