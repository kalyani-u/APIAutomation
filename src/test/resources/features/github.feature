Feature: Verify Github APIs

@Test
  Scenario: Verify GitHub Public Gists API with valid parameters
    Given I access Github API endpoint with valid date "2021-07-01"
    When I execute the GET HTTP method
    Then I should see the statuscode as 200
    
    
@Test
  Scenario: Verify GitHub Public Gists API with invalid parameters
    Given I access Github API endpoint with invalid date "@@@"
    When I execute the GET HTTP method
    Then I should see the statuscode as 422
    And I should see the expected error message
    

@Test
  Scenario: Verify GitHub Gists API Update with insufficient data
    Given I access Github PATCH API endpoint
    When I execute the PATCH HTTP method
    Then I should see the statuscode as 404
    And I should see the error message as 'Not Found'
    

@Test
  Scenario: Verify GitHub Gists API Delete with insufficient parameters
    Given I access Github DELETE API endpoint
    When I execute the DELETE HTTP method
    Then I should see the statuscode as 404
    And I should see the error message as 'Not Found'