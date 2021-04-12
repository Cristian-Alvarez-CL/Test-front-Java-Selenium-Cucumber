package cl.testautomation.runner;

import cl.testautomation.utils.AbstractRunner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
		tags={"@tag_feature1"})

public class LoginPagePracticeRunner extends AbstractRunner {
	
}
