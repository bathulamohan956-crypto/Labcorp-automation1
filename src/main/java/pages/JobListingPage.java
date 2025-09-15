package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JobListingPage extends BasePage {
    private By firstJobLink = By.xpath("(//a[contains(@href,'jobs')])");
    private By jobTitle = By.cssSelector("h1.job-title");
    private By jobId = By.xpath("//*[@class='au-target jobId']");
    private By jobLocation = By.cssSelector(".job-location");
    private By applyNowBtn = By.xpath("//a[@ph-tevent='apply_click']");
    private String jobLink = "//*[@class='job-title']//*[contains(text(),'%s')]";

    CareersPage careersPage;
    public JobListingPage(WebDriver driver) {
        super(driver);
        careersPage = new CareersPage(driver);
    }

    public void selectJob(String jobTitle) {
        click(By.xpath(String.format(jobLink,jobTitle)));
    }

    public String getJobTitle() { return getText(jobTitle); }
    public String getJobId() { return getText(jobId); }
    public String getJobLocation() { return getText(jobLocation); }

    public void clickApplyNow() {
        click(applyNowBtn);
    }
}
