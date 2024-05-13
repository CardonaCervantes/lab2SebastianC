Feature: Test quiz

  Scenario: The user navigates to vilken utbildning passar mig
    Given the user lands on the page "https://www.iths.se/vilken-utbildning-passar-mig/"
    And   the user accepts all cookies
    Then  the user should end up in page "Vilken utbildning passar mig? | IT-Högskolan"

  Scenario: The user completes the quiz
    Given the user is still on the page "https://www.iths.se/vilken-utbildning-passar-mig/"
    When  the user selects option '1' on step '1'
    And   the user selects option '3' on step '2'
    And   the user selects option '2' on step '3'
    And   the user selects option '2' on step '4'
    And   the user selects option '1' on step '5'
    And   the user selects option '3' on step '6'
    And   the user selects option '4' on step '7'
    Then  the user should end up with box "Ange din mailadress för att se resultatet:"
    And   the user enters "enFejkMail" in the email field
    Then  the user page should return dialog box with error message