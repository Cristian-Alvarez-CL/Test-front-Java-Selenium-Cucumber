package cl.testautomation.definitions;

import cl.testautomation.MainSuiteTest;
import cl.testautomation.services.SuitesService;
import cl.testautomation.utils.PausasUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class LoginPagePracticeDefinition {

	@When("^Ingreso al menu (.*)$")
	public void Ingreso_al_menu_(String arg) {
		assertTrue("Error al cargar pagina", MainSuiteTest.perfilLoginPagePractice.clicElemento(arg));
	}

	@Then("^ingreso el (.*) para registrarme$")
	public void ingreso_el_para_registrarme( String arg1) {
		MainSuiteTest.perfilLoginPagePractice.addTexto("Email",arg1);
		MainSuiteTest.perfilLoginPagePractice.clicElemento("Boton crear cuenta");
		assertTrue("Error - No arroja error en Email", MainSuiteTest.perfilLoginPagePractice.existeElemento("ok"));
	}

	@And("^completo el formulario de registro$")
	public void completo_el_formulario_de_registro () {
		assertTrue("Error - SubmitAccount", MainSuiteTest.perfilLoginPagePractice.datosFormulario());
	}


	@Then("^valido el campo (.*) para obtener una (.*)$")
	public void valido_el_campo_para_obtener_una( String arg1, String arg2) {
		if (arg2 == "fallido"){
			MainSuiteTest.perfilLoginPagePractice.addTexto("Email",arg1);
			MainSuiteTest.perfilLoginPagePractice.clicElemento("Boton crear cuenta");
			MainSuiteTest.perfilLoginPagePractice.clicElemento("Error Email");
			assertTrue("Error - No arroja el error en Email", MainSuiteTest.perfilLoginPagePractice.existeElemento("Error"));
		}else{
			MainSuiteTest.perfilLoginPagePractice.addTexto("Email",arg1);
			MainSuiteTest.perfilLoginPagePractice.clicElemento("Boton crear cuenta");
			assertTrue("Error - No arroja error en Email", MainSuiteTest.perfilLoginPagePractice.existeElemento("ok"));
		}

	}
}
