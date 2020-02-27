Feature:  Radar Scenarios

  Scenario: Verify that the Radar is Opened and redirected successfully
    Given  Navigate to Fly "stage" site
    When login into hub with super admin
    And  Open menu
    And  Open  "Radar"
    Then Assert that "radar" URL "stage" is opened successfully
