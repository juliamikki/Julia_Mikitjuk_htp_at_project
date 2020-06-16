# new feature
# Tags: optional

Feature: Cinema

  @qa
  Scenario: Search movie
    Given I open an app
    When I search for "ножи" word
    Then I see the list of movie items
    And each item name or description contains "ножи"

  @qa
  Scenario: Login app
    Given I open an app
    When I login with "julia.mikitjuk@gmail.com" and "zaqwerty"
    Then I can see Red Carpet Club Level "Новичок" in upper right corner

  @qa
  Scenario Outline: Login app blank field
    Given I open an app
    When I left blank <field> field
    Then I see <message> message

    Examples:
    | field	     | message                              |
    | 'login'	 | 'Необходимо заполнить поле "E-mail"' |
    | 'password' | 'Необходимо заполнить поле "Пароль"' |

  @qa
  Scenario: Login app no such user
    Given I open an app
    When I login with "vasya.pupkin@gmail.com" and "zaqwerty"
    Then I see no such user message

