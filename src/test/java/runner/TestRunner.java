package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * @author swetabhadani
 */
@CucumberOptions(features = {"src/test/resources"}, plugin = {"pretty"}, glue = "stepDefinition")
public class TestRunner extends AbstractTestNGCucumberTests {
}
