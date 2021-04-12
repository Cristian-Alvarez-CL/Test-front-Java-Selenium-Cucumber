package cl.testautomation;

import cl.testautomation.profiles.Pf_LoginPagePractice;
import cl.testautomation.utils.LoginPagePractice;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import cl.testautomation.profiles.Pf_HomePagePractice;
import cl.testautomation.services.SuitesService;
import cl.testautomation.utils.DynamicSuite;

@RunWith(DynamicSuite.class)
public class MainSuiteTest {

	public static WebDriver driver;
	public static Pf_HomePagePractice perfilHomePagePractice;
	public static Pf_LoginPagePractice perfilLoginPagePractice;

	public static Class[] suite() {

		Class[] homePagePractice = { HomePagePractice.class };
		Class[] loginPagePractice = { LoginPagePractice.class };
		Class[] todos = joinArray(homePagePractice, loginPagePractice);

		SuitesService.INIT.init();
		switch (SuitesService.SUITE) {
		case "HomePagePractice":
			return homePagePractice;
		case "LoginPagePractice":
			return loginPagePractice;
		case "Todos":
			return todos;
		default:
			Class[] vacio = {};
			return vacio;
		}
	}

	static Class[] joinArray(Class[]... arrays) {

		int length = 0;
		for (Class[] array : arrays) {
			length += array.length;
		}
		final Class[] result = new Class[length];
		int offset = 0;
		for (Class[] array : arrays) {

			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	@BeforeClass
	public static void SetUp() {
		System.out.println("************************************************");
		System.out.println("**    Inicia Ciclo de Pruebas Automatizadas   **");
		System.out.println("************************************************");
		System.out.println();
	}

	@AfterClass
	public static void finish() {
		System.out.println("************************************************");
		System.out.println("**   Finaliza Ciclo de Pruebas Automatizadas  **");
		System.out.println("************************************************");
		System.out.println();
	}
}