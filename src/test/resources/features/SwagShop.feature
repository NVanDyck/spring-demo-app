@Test
Feature: Swag Mobile shop

  Scenario: Failing a login
    Given the app is loaded
    When i login with:
      | username | standard_user |
      | password | secret_sauces |
    Then an error is expected

  Scenario: Feature 2
    Given the app is loaded
    When i login with:
      | username | standard_user |
      | password | secret_sauces |
    Then a new error is expected

  Scenario: Failing a login feature 1
    Given the app is loaded
    When i login with:
      | username | standard_user |
      | password | secret_sauces |
    Then an other new error is expected