# new feature
# Tags: optional

Feature: TrashMail Test

  Scenario: Check TrashMail Creation
    Given I go to trashmail.com
    When I create new trashmail user
    And I create new disposable address
    And I add new disposable address to preperty
    And I log in to yandex email
    Then I verify that trashmail email was created