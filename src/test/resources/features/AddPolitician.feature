@addPolitician
Feature: Add Politician details
  As a user I want to be able to add politician details

  @positive
  Scenario Outline: Politician should be added to the list of politician
    Given I have a valid endpoint for adding politician
    When I send "<name>", "<country>", "<position>", <risk>, <yob> as post request
    Then I see a successful message of politician added
    Examples:
      | name               | country        | position         | risk | yob  |
      | Rosina Aldred      | UK             | Audit Director   | 1    | 1985 |
      | Tayyibah Dominguez | United Kinddom | MLA              | 2    | 1985 |
      | Sadia Sweeney      | United Kinddom | MP posiiton      | 3    | 1985 |
      | Mahdi Mccaffre     | United Kinddom | MLA              | 4    | 1985 |
      | @£$^&              | United Kinddom | MP               | 5    | 1985 |
#      Not specified if 2 digits allowed or not in yob
      | Happy @tester      | United Kinddom | Testing position | 5    | 85   |
#      | 123           | United Kinddom | Testing position | 5    | 1985 |  This test should fail but passing as there is no check at API level that risk should not be greater than 5
#      | Max Reye      | United Kinddom | Testing position | 5    | 111111 | Should fail but passing

#  More test case can be added if:
#  Requirement says anything about what are allowed characters and not allowed characters
#  Says anything about what would be the minimum and max characters allowed for each field

  @negative
  Scenario Outline: Politician should not be added to the list of politician if invalid details are passed
    Given I have a valid endpoint for adding politician
    When I send "<name>" "<country>", "<position>", <risk>, <yob> as post request with missing name field
    Then I see a invalid request error message
    Examples:
      | name           | country | position       | risk | yob   |
      |                | UK      | Home secretary | 3    | 1985  |
      | Tester         | UK      |                | 3    | 1985  |

# These are failed test should pass ideally according to test document
      | Martin Foster  | UK      | Education      | 6    | 1985  |
      | Willie Lindsey | UK      | MLA            | 0    | 1985  |
      | Frankie Diaz   | UK      | MP             | 1    | 0     |
      | Audrey Brown   | UK      | MP             | 2    | 10000 |
      | Myron Gregory  |         | MLA            | 2    | 1985  |


