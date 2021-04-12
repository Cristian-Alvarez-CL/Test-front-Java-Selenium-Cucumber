package cl.testautomation.runner;

import org.junit.runner.RunWith;

import cl.testautomation.utils.AbstractRunner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		tags={"@tag_feature"})

public class HomePagePracticeRunner extends AbstractRunner {
	
}
