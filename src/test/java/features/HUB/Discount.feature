Feature: Apply discount on flight trips

  Scenario: Verify that the Rules is Opened and redirected successfully
    Given  Navigate to Fly "stage" site
    When login into hub with super admin
    And  Open menu
    And  Open  "Discount"
    Then Assert that "discount" URL "stage" is opened successfully

  Scenario: Verify that the super admin can access discount campaign
    When Open hub login page
    And  login into hub with super admin
    And  Open Discount
    Then Discount page open

  Scenario: Verify that the system success when super admin apply discount campaign
    When Open hub login page
    And  login into hub with super admin
    And  Open Discount
    And  Click on Create Campaign
    And  Fill required data for discount rule
    And  Click on Submit
    Then Check Discount rule created successfully
    And  Delete new discount from database

  Scenario: Verify that the super admin can apply discount campaign
    When Open hub login page
    And  login into hub with super admin
    And  Open Discount
    And  Click on Create Campaign
    And  Fill required data for discount rule
    And  Click on Submit
    And  Check discount from database to be applied
    And  Make Search from API
    Then Check Discount rule applied
    And  Delete new discount from database

  Scenario: Verify that the super admin can update discount name campaign
    When Open hub login page
    And  login into hub with super admin
    And  Open Discount
    And  Click on Create Campaign
    And  Fill required data for discount rule
    And  Click on Submit
    And  Make Browser back
    And  Click on Update Campaign
    And  Update Name of discount rule
    And  Click on Submit
    Then Check Discount rule updated successfully
    And  Delete new discount from database

  Scenario: Verify that the system success when super admin disable discount rule
    And  Apply discount rule from API
    When Open hub login page
    And  login into hub with super admin
    And  Open Discount
    And  Click on Update Campaign
    And  Disable the discount rule
    And  Click on Submit
    Then Check Discount rule updated successfully
    And  Delete new discount from database

  Scenario: Verify that the super admin can disable all discount rules
    And Apply discount rule from API
    When Open hub login page
    And  login into hub with super admin
    And  Open Discount
    And  Open Discount Settings
    And  Disable the discount rule
    And  Click on Submit
    And  Make Search from API
    Then Check Discount rule disabled
    And  Delete new discount from database

