Feature: API Tests feature

  @smoke
  Scenario: Validate GET response for sample request
    Given I send a GET request to "https://echo.free.beeceptor.com/sample-request?author=beeceptor"
    Then the response should contain the fields "path", "ip", and all "headers"


  @smoke
  Scenario: Validate POST response with order payload
    Given I send a POST request to "http://echo.free.beeceptor.com/sample-request?author=beeceptor" with valid order payload
    Then the response should contain correct customer, payment, and product details