package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By careersLink = By.linkText("Careers");
    private By acceptAllCookies = By.xpath("//*[text()='Accept All Cookies']");

    public HomePage(WebDriver driver) {
        super(driver);
        handleCookieBanner();
    }

    public void goToCareers() {
        click(careersLink);
    }
}
