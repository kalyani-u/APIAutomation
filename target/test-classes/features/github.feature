Feature: Verify Github APIs

@Test
  Scenario: Verify GitHub Public Gists API 
    Given I access Github API endpoint with valid date "2021-01-01"
    When I execute the GET HTTP method
    Then I should see the statuscode as '200'