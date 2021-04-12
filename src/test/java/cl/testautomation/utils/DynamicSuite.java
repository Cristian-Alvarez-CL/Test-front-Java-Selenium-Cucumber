package cl.testautomation.utils;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import cl.testautomation.MainSuiteTest;


public class DynamicSuite extends Suite {
	
	public DynamicSuite(Class<?> setupClass) throws InitializationError {
		super(setupClass, MainSuiteTest.suite());
	}
}
