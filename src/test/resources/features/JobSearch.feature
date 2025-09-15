Feature: LabCorp Job Search and Application

#  @smoke
  Scenario: Verify Job Details and Apply Process
    Given I open the LabCorp website
    When I navigate to the Careers page
    And I search for "QA Auditor - BioAnalytical"
    And I select the job from search results
    Then I should see the job title, location, and job ID
    And I should verify job description details
    When I click Apply Now
    Then I should confirm job details match in the application page
