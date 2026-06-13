@regression @region
Feature: Region Navigation Tests
  As a user
  I want to browse countries by region
  So that I can find destinations in specific areas

  @regression @TC041
  Scenario Outline: All region pages load successfully
    When the user navigates to the "<region>" region page
    Then the region page should load successfully
    And the region should display countries
    And the URL should contain "/regions/<region>"

    Examples:
      | region         |
      | europe         |
      | asia           |
      | americas       |
      | middle-east    |
      | africa-oceania |

  @regression @TC046
  Scenario: Filter countries by Europe on countries page
    Given the user is on the countries page
    When the user selects the "Europe" region filter
    Then only "Europe" countries should be displayed
    And the country count should be less than the total

  @regression @TC047
  Scenario: Filter countries by Asia
    Given the user is on the countries page
    When the user selects the "Asia" region filter
    Then only "Asia" countries should be displayed
    And the filtered results should not be empty

  @regression @TC048
  Scenario: Filter countries by Americas
    Given the user is on the countries page
    When the user selects the "Americas" region filter
    Then the filtered results should not be empty

  @regression @TC049
  Scenario: All filter shows all countries
    Given the user is on the countries page
    When the user selects the "All" filter tab
    Then all 50 countries should be displayed

  @regression @TC050
  Scenario: Region filter tabs are visible
    Given the user is on the countries page
    Then the region filter tabs should be visible
