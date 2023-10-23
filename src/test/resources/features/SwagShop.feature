@Test
Feature: Swag Mobile shop

  Scenario: Failing a login
    Given the app is loaded
    When i login with:
      | username | standard_user |
      | password | secret_sauces |
    Then an error is expected