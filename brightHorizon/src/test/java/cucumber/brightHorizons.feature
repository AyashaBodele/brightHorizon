Feature: To verify search and comments on Home page

  Scenario: To verify search functionality
    Given user has landed on BrightHorizon Home Page
    When user click on search (top,right corner)
    Then search field is visible on the page
    When user type "Employee Education in 2018: Strategies to Watch" into search field and click search icon
    Then verify if the first search result is exact match to what user typed into search "Employee Education in 2018: Strategies to Watch"
  
  
  Scenario: To verify comments
    Given user has landed on BrightHorizon Home Page
    When user click on Find a Center option (Top header)
    Then verify newly open page contains "/child-care-locator" as part of URL
    When user type "New York" into search field and press Enter
    Then verify if a number of found centers is the same as a number of centers displayed
    When user click on the first center on the list
    Then Verify if center name and address are the same (on the list and on the popup)
