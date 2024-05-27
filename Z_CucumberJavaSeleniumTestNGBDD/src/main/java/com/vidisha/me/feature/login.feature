@Regression
Feature: Testing Login feature

Background: 
Given User launches URL

@Valid
Scenario: Valid credentials
When User gives valid credentials
Then User lands on home page

@Invalid
Scenario: Invalid Credentials
When User gives invalid credentials
Then User sees error message

@Valid
Scenario: Verify forgot password
When User clicks on forgot password
Then User lands on Reset Password page