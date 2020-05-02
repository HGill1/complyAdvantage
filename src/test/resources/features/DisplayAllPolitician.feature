@displayPoliticians
Feature: List all Politicians details
  As a user I should be able to see all politician details

  Scenario: Details of all the politician is displayed
    Given I have a valid endpoint for displaying politician details
    When I send a get request to access politicians
    Then I verify politician details are displayed
    And I verify that list displayed the latest 10 politician added
    And I verify politician are displayed in order of date added