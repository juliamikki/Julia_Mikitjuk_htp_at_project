Feature: Users API Test

Scenario: Check User by full name
    Given I start execution
    When I search user by "John" name
    Then I verify that I got "John"

Scenario: Check User by partial name
     Given I start execution
     When I search user by "Ber" name
     Then I verify that I got "Berta"