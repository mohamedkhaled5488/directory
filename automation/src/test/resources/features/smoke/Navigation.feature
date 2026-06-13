@smoke @navigation
Feature: Navigation Smoke Tests
  As a user
  I want to verify site navigation works correctly
  So that I can browse all sections of the site

  @smoke @TC009
  Scenario: Europe region page loads
    When the user navigates to the "europe" region page
    Then the region page should load successfully
    And the region should display countries
    And the URL should contain "/regions/europe"

  @smoke @TC010
  Scenario: Asia region page loads
    When the user navigates to the "asia" region page
    Then the region page should load successfully
    And the region should display countries

  @smoke @TC011
  Scenario: Americas region page loads
    When the user navigates to the "americas" region page
    Then the region page should load successfully
    And the region should display countries

  @smoke @TC012
  Scenario: Middle East region page loads
    When the user navigates to the "middle-east" region page
    Then the region page should load successfully
    And the region should display countries

  @smoke @TC013
  Scenario: Africa & Oceania region page loads
    When the user navigates to the "africa-oceania" region page
    Then the region page should load successfully
    And the region should display countries

  @smoke @TC014
  Scenario: Japan country page loads
    Given the user navigates to the "japan" country page
    Then the country name should be visible
    And the stats bar should be visible

  @smoke @TC015
  Scenario: Japan tipping page loads
    Given the user navigates to the "tipping" topic on the "japan" page
    Then the page title should contain "Tipping"
    And the tourist tips should be displayed

  @smoke @TC016
  Scenario: Country comparison page loads
    When the user navigates to compare "japan" and "thailand"
    Then the comparison title should mention both countries

  @smoke @TC017
  Scenario: Countries list page loads
    Given the user is on the countries page
    Then all 50 countries should be displayed

  @smoke @TC018
  Scenario: UAE alcohol rules page loads with warning
    Given the user navigates to the "alcohol-rules" topic on the "uae" page
    Then the warning box should be visible
    And the quick answer should contain "licensed"

  @smoke @TC019
  Scenario: Topics page loads with all topics
    When the user navigates to "/topics"
    Then the page title should contain "Topics"

  @smoke @TC020
  Scenario: Germany electricity page has plug type info
    Given the user navigates to the "electricity-and-plugs" topic on the "germany" page
    Then the quick answer should contain "Type F"
