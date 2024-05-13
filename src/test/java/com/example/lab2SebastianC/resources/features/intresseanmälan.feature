Feature: Test Intresseanmälan

  Scenario: Test form on page Intresseanmälan | IT-Högskolan
    Given the user is on the page "https://www.iths.se/kontakt/intresseanmalan/"
    When the user selects Agil testautomatiserare - YH-kurs under Intresse för utbildning
    And the user enters "Fulanito" under Namn-field
    And the user clicks on Send
    Then error message "Ett problem uppstod när ditt formulär skulle skickas. Granska nedanstående fält." should appear and the Namn-field is colored red