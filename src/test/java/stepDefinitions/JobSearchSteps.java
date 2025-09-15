package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.*;
import testUtils.TestContext;

import static org.junit.jupiter.api.Assertions.*;

public class JobSearchSteps {
    WebDriver driver;
    TestContext context;
    HomePage homePage;
    CareersPage careersPage;
    JobListingPage jobListingPage;
    ApplyPage applyPage;



    String expectedTitle, expectedId, expectedLocation;

    @Given("I open the LabCorp website")
    public void openLabCorpWebsite() {
        driver = Hooks.getDriver();
        context = Hooks.getContext();
        driver.get("https://www.labcorp.com");
        homePage = new HomePage(driver);
    }

    @When("I navigate to the Careers page")
    public void navigateToCareers() {
        homePage.goToCareers();
        careersPage = new CareersPage(driver);
    }

    @When("I search for {string}")
    public void searchForJob(String jobTitle) {
        context.set("jobTitle", jobTitle);
        careersPage.searchJob(jobTitle);
        jobListingPage = new JobListingPage(driver);
    }

    @When("I select the job from search results")
    public void selectJob() {
        String jobTitle = (String)context.get("jobTitle");
        jobListingPage.selectJob(jobTitle);
    }

    @Then("I should see the job title, location, and job ID")
    public void verifyJobDetails() {
        expectedTitle = jobListingPage.getJobTitle();
        expectedId = jobListingPage.getJobId();
        expectedLocation = jobListingPage.getJobLocation();

        assertEquals((String)context.get("jobTitle"),expectedTitle);
        assertNotNull(expectedTitle);
        assertNotNull(expectedId);
        assertNotNull(expectedLocation);
    }

    @Then("I should verify job description details")
    public void verifyJobDescriptionDetails() {
        // Here you can add XPath/CSS locators to validate specific text
        // Example assertion (replace locator accordingly):
        // String intro = jobListingPage.getText(By.xpath("(//p)[3]"));
        // assertTrue(intro.contains("test automation technology"));
    }

    @When("I click Apply Now")
    public void clickApplyNow() {
        jobListingPage.clickApplyNow();
        applyPage = new ApplyPage(driver);
    }

    @Then("I should confirm job details match in the application page")
    public void confirmJobDetailsOnApplyPage() {
        assertEquals(expectedTitle, applyPage.getJobTitle());
        assertEquals(expectedId, applyPage.getJobId());
        assertEquals(expectedLocation, applyPage.getJobLocation());
    }
}
