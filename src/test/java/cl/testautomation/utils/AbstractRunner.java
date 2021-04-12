package cl.testautomation.utils;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin={"junit:target/reportes/junit/junit.xml",},
		monochrome = true,
		features="src/test/resources/features",
		glue= "cl/testautomation/definitions")
public abstract class AbstractRunner {

}