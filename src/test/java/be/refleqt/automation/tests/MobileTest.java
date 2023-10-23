package be.refleqt.automation.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "be.refleqt.automation",
        tags = "@Test and not @Ignore",
        monochrome = true,
        plugin = { "pretty", "json:target/cucumber/json/full.json", "html:target/cucumber/json/full.html"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class MobileTest {
    
}
