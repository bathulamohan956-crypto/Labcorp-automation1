package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testUtils.TestContext;

public class Hooks {
    private static WebDriver driver;
    private static TestContext context;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        context = new TestContext();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
            context = null;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static TestContext getContext() {
        return context;
    }
}