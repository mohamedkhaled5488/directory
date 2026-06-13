@functional @negative
Feature: Negative Test Cases

  Background:
    Given the user is on the KnowBefore homepage

  @functional @negative @TC062
  Scenario: Search with empty string shows no results
    When the user searches for ""
    Then no search results should be displayed

  @functional @negative @TC063
  Scenario: Search with special characters shows no results
    When the user searches for "@#$%^&*"
    Then no search results should be displayed

  @functional @negative @TC064
  Scenario: Search with numbers shows no results
    When the user searches for "99999"
    Then no search results should be displayed

  @functional @negative @TC065
  Scenario: Search with invalid country name
    When the user searches for "Atlantis"
    Then no search results should be displayed

  @functional @negative @TC066
  Scenario: Non-existent country page returns 404 indicator
    When the user navigates to "/nonexistent-country-xyz"
    Then the page title should contain "not found"

  @functional @negative @TC067
  Scenario: Search with very long string
    When the user searches for "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    Then no search results should be displayed
