package dreamdiary;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/integrationTest/resources",
//        stepNotifications = true,
        glue = {"dreamdiary/features"},
        plugin = {"pretty", "org.jetbrains.plugins.cucumber.java.run.CucumberJvm5SMFormatter"}
)
public class IntegrationTestRunner {
}
