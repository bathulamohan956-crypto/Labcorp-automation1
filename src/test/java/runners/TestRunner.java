package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = "stepDefinitions", // Package with step definitions
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true,
        tags = "@smoke" // Optional: run scenarios with this tag
)
public class TestRunner {
}




