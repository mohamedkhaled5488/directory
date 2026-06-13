@smoke
Feature: Homepage Smoke Tests
  As a user
  I want to verify the KnowBefore homepage loads correctly
  So that I can access country travel information

  Background:
    Given the user is on the KnowBefore homepage

  @smoke @TC001
  Scenario: Homepage loads successfully
    Then the homepage should load successfully

  @smoke @TC002
  Scenario: Page title is correct
    Then the page title should contain "KnowBefore"

  @smoke @TC003
  Scenario: Navigation bar is visible
    Then the navigation bar should be visible

  @smoke @TC004
  Scenario: Search bar is visible on homepage
    Then the search bar should be visible on the homepage

  @smoke @TC005
  Scenario: Country grid displays countries
    Then the country grid should display countries

  @smoke @TC006
  Scenario: Navigate to Countries page from nav
    When the user clicks on the Countries navigation link
    Then the user should be on the countries page
    And the URL should contain "/countries"

  @smoke @TC007
  Scenario: Navigate to Topics page from nav
    When the user clicks on the Topics navigation link
    Then the user should be on the topics page
    And the URL should contain "/topics"

  @smoke @TC008
  Scenario: Logo navigates back to homepage
    When the user clicks on the Countries navigation link
    And the user clicks the KnowBefore logo
    Then the user should be redirected to the homepage
