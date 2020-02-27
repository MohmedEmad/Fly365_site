Feature: Create Cancel Rule

  Scenario: Verify that agent can create rule & successful message is displayed
    Given Open hub login page
    And login into hub with super admin
    And Open Rules
    And Click Create Rule
    And Fill Rule Data
    And Submit Rule
    Then Rule Success Message Is Displayed

  Scenario: Verify that agent can create rule & rule is added to the list
    Given Open hub login page
    And login into hub with super admin
    And Open Rules
    And Click Create Rule
    And Fill Rule Data
    And Submit Rule
    Then Rule Is Added To The List

  Scenario: Verify that agent can update rule and successful message is displayed
    Given Open hub login page
    And login into hub with super admin
    And Open Rules
    And Click Edit Rule
    And Edit Rule Name
    And Submit Rule
    Then Rule Updated Message Is Displayed

  Scenario: Verify that agent can update rule and rule is updated in the list
    Given Open hub login page
    And login into hub with super admin
    And Open Rules
    And Click Edit Rule
    And Edit Rule Name
    And Submit Rule
    Then Rule Is Updated In The List

  Scenario: Verify That Agent Can Disable Rule And Successful Message Is Displayed
    Given Open hub login page
    And login into hub with super admin
    And Open Rules
    And Click Edit Rule
    And Change Status To Disabled
    And Submit Rule
    Then Rule Updated Message Is Displayed

  Scenario: Verify That Agent Can Disable Rule Status Is Disabled In The List
    Given Open hub login page
    And login into hub with super admin
    And Open Rules
    And Click Edit Rule
    And Change Status To Disabled
    And Submit Rule
    Then Rule status Is Updated In The List

   Scenario: Verify That Agent Can't Create Rule With the Same Name
     Given Open hub login page
     And login into hub with super admin
     And Open Rules
     And Submit Rule With Same Name
     Then Name Error Message Is Displayed


