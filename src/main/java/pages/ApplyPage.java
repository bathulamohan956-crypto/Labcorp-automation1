package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApplyPage extends BasePage {
    private By jobTitle = By.cssSelector("h1.job-title");
    private By jobId = By.cssSelector(".job-id");
    private By jobLocation = By.cssSelector(".job-location");

    public ApplyPage(WebDriver driver) {
        super(driver);
    }

    public String getJobTitle() { return getText(jobTitle); }
    public String getJobId() { return getText(jobId); }
    public String getJobLocation() { return getText(jobLocation); }
}
