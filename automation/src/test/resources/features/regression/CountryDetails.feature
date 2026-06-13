@regression @country
Feature: Country Details Tests
  As a user
  I want to view detailed country information
  So that I can prepare for my travels

  @regression @TC031
  Scenario: Japan country page displays correct information
    Given the user navigates to the "japan" country page
    Then the country page should display "Japan"
    And the currency information should be displayed
    And the language information should be displayed
    And the stats bar should be visible

  @regression @TC032
  Scenario: Japan tipping - do not tip culture verified
    Given the user navigates to the "tipping" topic on the "japan" page
    Then the quick answer should contain "not"
    And the tourist tips should be displayed

  @regression @TC033
  Scenario: UAE alcohol rules shows warning
    Given the user navigates to the "alcohol-rules" topic on the "uae" page
    Then the warning box should be visible
    And the quick answer should contain "licensed"

  @regression @TC034
  Scenario: Germany electricity shows Type F plug
    Given the user navigates to the "electricity-and-plugs" topic on the "germany" page
    Then the quick answer should contain "Type F"

  @regression @TC035
  Scenario: UK driving rules mentions left side
    Given the user navigates to the "driving-rules" topic on the "uk" page
    Then the quick answer should contain "left"

  @regression @TC036
  Scenario: Thailand water safety shows warning
    Given the user navigates to the "water-safety" topic on the "thailand" page
    Then the warning box should be visible
    And the quick answer should contain "tap"

  @regression @TC037
  Scenario: Country page shows correct topic count
    Given the user navigates to the "france" country page
    Then the country page should display "France"
    And the stats bar should be visible

  @regression @TC038
  Scenario: Topic page shows comparison section
    Given the user navigates to the "tipping" topic on the "germany" page
    Then the page should show comparison with other countries

  @regression @TC039
  Scenario: Tourist tips are displayed on topic page
    Given the user navigates to the "public-transport" topic on the "italy" page
    Then the tourist tips should be displayed

  @regression @TC040
  Scenario: Breadcrumbs are visible on topic detail page
    Given the user navigates to the "local-laws" topic on the "singapore" page
    Then the breadcrumbs should be visible
