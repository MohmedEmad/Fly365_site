Feature: Open Rules

  Scenario: Verify that the Rules is Opened and redirected successfully
    Given  Navigate to Fly "stage" site
    When login into hub with super admin
    And  Open menu
    And  Open  "Rules"
    Then Assert that "rules" URL "stage" is opened successfully