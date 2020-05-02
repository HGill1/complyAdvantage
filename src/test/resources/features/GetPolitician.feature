@getpolitician
Feature: Get Politician details
  As a user I should be able to fetch any politician details

  Scenario Outline: Detail of a politician is fetched
    Given I have a valid endpoint for fetching a politician
    When I add a politician using "<name>", "<country>", "<position>", <risk>, <yob>
    And I send a get request to access the added politician
    Then I see added politician detail
    Examples:
      | name | country | position   | risk | yob  |
#      The below test failing due to the defect in API. API returns yob 0 instead of expecting value 1985 or 1990
      | Sam  | UK      | politician | 1    | 1985 |
      | John | UK      | MP         | 5    | 1990 |


  Scenario: Error displayed for invalid id
    Given I have a valid endpoint for fetching a politician
    And I send a get request to access a politician detail using invalid id
    Then I receive 404 status code in response
