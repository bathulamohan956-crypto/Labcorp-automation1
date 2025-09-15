package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersPage extends BasePage {
    private By searchBox = By.id("typehead");
    private By searchBtn = By.xpath("//button[@aria-label='Search']");

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public void searchJob(String jobTitle) {
        type(searchBox, jobTitle);
        click(searchBtn);
    }
}
