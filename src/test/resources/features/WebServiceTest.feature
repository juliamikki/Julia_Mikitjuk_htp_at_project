# new feature
# Tags: optional

Feature: Web Service Test

  Scenario Outline: Check Web Service
    Given I start execution with <order> test data order
    When I parse response from web service
    And I parse file with expected result and get <order> response order
    Then I verify expected result equals actual result for <testType> test

    Examples:
      | order  | testType                 |
      | 0      | "All Users"              |
      | 1      | "Full Long UserName"     |
      | 2      | "Full Short UserName"    |
      | 3      | "Partial Long UserName"  |
      | 4      | "Partial Short UserName" |