@regression @search
Feature: Search Functionality Tests
  As a user
  I want to search for countries and topics
  So that I can quickly find travel information

  Background:
    Given the user is on the KnowBefore homepage

  @regression @TC021
  Scenario: Search by exact country name - Japan
    When the user searches for "Japan"
    Then search results should be displayed
    And the search results should contain "Japan"

  @regression @TC022
  Scenario: Search by exact country name - Germany
    When the user searches for "Germany"
    Then search results should be displayed
    And the search results should contain "Germany"

  @regression @TC023
  Scenario: Search by partial country name
    When the user searches for "Jap"
    Then search results should be displayed
    And the search results should contain "Japan"

  @regression @TC024
  Scenario: Search by topic name - tipping
    When the user searches for "tipping"
    Then search results should be displayed
    And the search results should contain "Tipping"

  @regression @TC025
  Scenario: Search by topic name - water safety
    When the user searches for "water"
    Then search results should be displayed

  @regression @TC026
  Scenario: Search with invalid country name
    When the user searches for "Xyzxyzxyz123"
    Then no search results should be displayed

  @regression @TC027
  Scenario: Search with special characters
    When the user searches for "@#$%"
    Then no search results should be displayed

  @regression @TC028
  Scenario: Search with numbers only
    When the user searches for "12345"
    Then no search results should be displayed

  @regression @TC029
  Scenario: Search results are limited to max 8 results
    When the user searches for "a"
    Then the search results count should be at most 8

  @regression @TC030
  Scenario: Search and navigate to result
    When the user searches for "Japan"
    And the user clicks the first search result
    Then the user should be navigated to a country or topic page
