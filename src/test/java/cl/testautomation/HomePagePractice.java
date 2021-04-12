package cl.testautomation;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.support.PageFactory;

import cl.testautomation.profiles.Pf_HomePagePractice;
import cl.testautomation.runner.HomePagePracticeRunner;
import cl.testautomation.services.SuitesService;
import cl.testautomation.services.WebDriverManager;


@RunWith(Suite.class)
@SuiteClasses({
	HomePagePracticeRunner.class,
	})
public class HomePagePractice {

	 @BeforeClass
	    public static void SetUp(){
	    	
	    	System.out.println("******************************************************************");
			System.out.println("**    Inicia ciclo de pruebas automatizadas  - HomePagePractice **");
	    	System.out.println("******************************************************************");
	    	System.out.println();
	    	  	
	    	SuitesService.CONFIG.init();
	    	
	    	MainSuiteTest.driver = WebDriverManager.INIT.getDriver();
	    	
	   	
			MainSuiteTest.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (SuitesService.BROWSER.equals("chrome") || SuitesService.BROWSER.equals("firefox")) MainSuiteTest.driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			
			MainSuiteTest.driver.manage().window().maximize();
			
			MainSuiteTest.perfilHomePagePractice = PageFactory.initElements(MainSuiteTest.driver, Pf_HomePagePractice.class);
			
	    }
	    

	    @AfterClass
	    public static void finish(){
	    	System.out.println("*******************************************************************");
			System.out.println("**   Finaliza ciclo de pruebas automatizadas  - HomePagePractice **");
	    	System.out.println("*******************************************************************");
	    	System.out.println();
	    	MainSuiteTest.driver.quit();
	    }
	}
