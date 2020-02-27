Feature: Open Data

  Scenario: Verify that the Data is Opened and redirected successfully
    When Open hub login page
    And  login into hub with super admin
    And  Open menu
    And  Open  "Data"
    Then Assert that "data" URL "stage" is opened successfully