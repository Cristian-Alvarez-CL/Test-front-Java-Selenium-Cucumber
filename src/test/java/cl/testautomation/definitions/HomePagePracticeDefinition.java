package cl.testautomation.definitions;

import cl.testautomation.MainSuiteTest;
import cl.testautomation.services.SuitesService;
import cl.testautomation.utils.PausasUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertTrue;

public class HomePagePracticeDefinition {

	@Given("^Que ingreso a la web de practica$")
	public void que_ingreso_a_la_web_de_practiva() throws Throwable{
		MainSuiteTest.driver.navigate().to(SuitesService.URL);
		PausasUtil.waitForLoad(MainSuiteTest.driver, 50);
	}

	@When("^Muestre la pagina inicial$")
	public void muestre_la_pagina_inicial() {
		assertTrue("Error al cargar pagina", MainSuiteTest.perfilHomePagePractice.existeElemento("Footer"));
	}

	@Then("^Verifico que muestre (.*)$")
	public void verifico_que_muestre_menu( String arg1) {
		assertTrue("Error - Menú " + arg1, MainSuiteTest.perfilHomePagePractice.existeElemento(arg1));
	}

	@And("^Verifico que al realizar un click me derive a la pagina (.*)$")
	public void verifico_que_al_realizar_un_click_me_derive_a_la_pagina ( String arg1) {
		MainSuiteTest.perfilHomePagePractice.clicElemento(arg1);
		assertTrue("Error - lbl" + arg1, MainSuiteTest.perfilHomePagePractice.existeElemento(arg1+"1"));
	}
}
