@functional @comparison
Feature: Country Comparison Tests
  As a user
  I want to compare two countries side by side
  So that I can make informed travel decisions

  @functional @TC051
  Scenario: Compare Japan and Thailand
    Given the user navigates to compare "japan" and "thailand"
    Then the comparison title should mention both countries
    And the comparison should show 25 topics
    And each topic should have answers for both countries
    And the more comparisons section should be visible

  @functional @TC052
  Scenario: Compare UAE and Qatar
    Given the user navigates to compare "uae" and "qatar"
    Then the comparison title should mention both countries
    And warning badges should be shown where applicable

  @functional @TC053
  Scenario: Compare France and Italy
    Given the user navigates to compare "france" and "italy"
    Then the comparison title should mention both countries
    And the comparison should show 25 topics

  @functional @TC054
  Scenario: Compare Spain and Portugal
    Given the user navigates to compare "spain" and "portugal"
    Then the comparison title should mention both countries

  @functional @TC055
  Scenario: Compare US and UK
    Given the user navigates to compare "us" and "uk"
    Then the comparison title should mention both countries
    And the comparison should show 25 topics

  @functional @TC056
  Scenario: Compare Japan and Germany
    Given the user navigates to compare "japan" and "germany"
    Then the comparison title should mention both countries
    And each topic should have answers for both countries
