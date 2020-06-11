# new feature
# Tags: optional

Feature: Booking Hotels Test

  Scenario: Check Paris
    Given I start test execution with 0 test data
    When I enter destination "Paris" with arrival in 3 days and 7 nights accommodation
    And I enter accommodation details: 4 adults, 0 children, 2 rooms - on main page
    And I do search on main page
    And I apply maximum price hotel filter
    And I apply ascending price sorting
    And I identify and print cheapest hotel per night price and price per night from highest budget hotels
    Then I verify cheapest hotel per night price >= minimum price per night from highest budget hotels

  Scenario: Check Moscow
    Given I start test execution with 2 test data
    When I enter destination "Moscow" with arrival in 10 days and 5 nights accommodation
    And I do search on main page
    And I enter accommodation details: 4 adults, 0 children, 2 rooms - using actions
    And I do search on search page
    And I apply minimum price hotel filter
    And I identify and print first hotel per night price and price per night from lowest budget hotels
    Then I verify top hotel per night price <= maximum price per night from lowest budget hotels

  Scenario: Check Oslo
    Given I start test execution with 1 test data
    When I enter destination "Oslo" with arrival in 1 days and 1 nights accommodation
    And I enter accommodation details: 2 adults, 1 children, 1 rooms - on main page
    And I do search on main page
    And I apply star rating filter
    And I apply styles to tenth hotel
    Then I verify styles are applied