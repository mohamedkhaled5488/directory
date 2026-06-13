@functional @filter
Feature: Filter Functionality Tests

  @functional @TC057
  Scenario: Europe filter shows correct countries
    Given the user is on the countries page
    When the user selects the "Europe" region filter
    Then only "Europe" countries should be displayed
    And the filtered results should not be empty

  @functional @TC058
  Scenario: Asia filter shows correct countries
    Given the user is on the countries page
    When the user selects the "Asia" region filter
    Then only "Asia" countries should be displayed

  @functional @TC059
  Scenario: Middle East filter works
    Given the user is on the countries page
    When the user selects the "Middle East" region filter
    Then the filtered results should not be empty

  @functional @TC060
  Scenario: Africa & Oceania filter works
    Given the user is on the countries page
    When the user selects the "Africa & Oceania" region filter
    Then the filtered results should not be empty

  @functional @TC061
  Scenario: All tab resets filter
    Given the user is on the countries page
    When the user selects the "Europe" region filter
    And the user selects the "All" filter tab
    Then all 50 countries should be displayed
