Feature: Test iths

  Background:
    Given the user visits the website
    And   the user allows all cookies

    # Scenario 1
  Scenario: Verify the correct title of iths.se
    Then  the title should be "IT-Högskolan – Här startar din IT-karriär!"

    # Scenario 2
  Scenario: Verify availability and path of iths.se
    And   the user clicks on the link Studentportal
    Then  the Url should be "https://www.ithsdistans.se/login/index.php"

    # Scenario 3
  Scenario: Compare course filtrations and verify the difference in count
    Given the user visits the page "https://www.iths.se/utbildningar/"
    When  the default filtration is active
    Then  the count of courses in the default filtration should be 28
    When  the user applies the Göteborg filtration
    Then  the count of courses in the Göteborg filtration should be 9
    And   the difference in course count between the default and Göteborg filtrations should be 19

    # Scenario 4
  Scenario: Verify link to YouTube is correct
    Given the user goes to page "https://www.iths.se/om-oss/"
    When  the user clicks on the play button
    And   the user clicks on showOnYouTubeButton in embedded video
    Then  the link to the content on YouTube should be "Välkommen till IT-Högskolan!"

    # Scenario 5
  Scenario: Verify visibility of objects in accordion menu
    # kan återanvändas i en backgroundklass eller ny background för ny feature
    Given the user goes to the page "https://www.iths.se/om-oss/"
    When  the user expands the accordion menu
    Then  the object x is visible

    # Scenario 6
  Scenario: Verify that the object in previous scenario is not visible
    Given the user goes to the page "https://www.iths.se/om-oss/"
    When  the user expands the accordion menu
    And   the user collapses the accordion menu
    Then  the object x is not visible

    # Scenario 7
  Scenario: Check header menu
    Given user is on main page
    When  hover over item
    And   selects nested item
    Then  new page should be "Preparandkurs i programmering och programmering grundkurs – vad är skillnaden? | IT-Högskolan"