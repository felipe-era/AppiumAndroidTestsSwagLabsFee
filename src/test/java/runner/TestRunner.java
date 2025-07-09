package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions (
        features = "src/test/java/features",
        glue = {"utility", "stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-report.html", "json:cucumber.json"}
)


public class TestRunner {
}

//package runner;
//
//import org.junit.platform.suite.api.ConfigurationParameter;
//import org.junit.platform.suite.api.SelectClasspathResource;
//import org.junit.platform.suite.api.Suite;
//
//import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
//
//@Suite
//@SelectClasspathResource("features")
//@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepdefinitions")
//public class TestRunner {
//}