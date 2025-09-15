package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        System.out.println("Waiting for element to be clickable: " + locator.toString());
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

            // Scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            // Optional short delay
            Thread.sleep(300);

            // Try regular click
            element.click();
            System.out.println("Element clicked successfully.");

        } catch (ElementClickInterceptedException e) {
            System.out.println("Element click intercepted. Using JavaScript click instead.");

            WebElement element = driver.findElement(locator);

            // Scroll into view again just in case
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            // JavaScript click as fallback
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            System.out.println("Failed to click on element: " + locator.toString());
            ex.printStackTrace();
        }
    }


    protected void type(By locator, String text) {
        find(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected void handleCookieBanner() {
        try {
            By cookieAcceptBtn = By.xpath("//*[text()='Accept All Cookies']");
            wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptBtn));
            click(cookieAcceptBtn);
            System.out.println("Cookie banner accepted.");
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Cookie banner not present, continuing...");
        }
    }
}
