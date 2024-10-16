package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber", glue = "brightHorizons.stepDefinitions", monochrome = true, plugin = {
		"html:target/cucumber.html", "json:target/cucumber-reports/cucumber.json" })

public class TestRunner extends AbstractTestNGCucumberTests {

}
