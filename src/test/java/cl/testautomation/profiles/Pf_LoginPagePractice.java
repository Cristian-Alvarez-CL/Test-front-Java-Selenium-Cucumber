package cl.testautomation.profiles;

import cl.testautomation.exception.qaTestException;
import cl.testautomation.utils.WebElementUtil;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;


public class Pf_LoginPagePractice {
	
	@FindBy(how = How.XPATH, using = "/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a")
	private WebElement menuSignin;

	@FindBy(how = How.ID, using = "email_create")
	private WebElement tbxEmailCreate;

	@FindBy(how = How.ID, using = "SubmitCreate")
	private WebElement btnCrearCuenta;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div/div/div[1]/form/div/div[1]/ol/li/text()")
	//@FindBy(how = How.ID, using = "create_account_error")
	private WebElement lblErrorEmail;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div/div/div[1]/form/div/div[1]")
	private WebElement lblInformacionPersonal;

	@FindBy(how = How.ID, using = "id_gender1")
	private WebElement rbMr;

	@FindBy(how = How.ID_OR_NAME, using = "customer_firstname")
	private WebElement tbxFirstNameCustomer;

	@FindBy(how = How.ID_OR_NAME, using = "customer_lastname")
	private WebElement tbxLastNameCustomer;

	@FindBy(how = How.ID, using = "email")
	private WebElement tbxEmail;

	@FindBy(how = How.NAME, using = "passwd")
	private WebElement tbxPass;
//---
	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div/div/form/div[1]/div[6]/div/div[1]/div/select")
	private WebElement cbxDia;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div/div/form/div[1]/div[6]/div/div[1]/div/select/option[2]")
	private WebElement setCbxDiaN;

	@FindBy(how = How.ID_OR_NAME, using = "months")
	private WebElement cbxMes;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div/div/form/div[1]/div[6]/div/div[2]/div/select/option[3]")
	private WebElement setCbxMesN;

	@FindBy(how = How.ID_OR_NAME, using = "years")
	private WebElement cbxAnio;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div/div/form/div[1]/div[6]/div/div[3]/div/select/option[39]")
	private WebElement setCbxAnioN;

	@FindBy(how = How.ID, using = "id_state")
	private WebElement cbxState;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div/div/form/div[2]/p[7]/div/select/option[4]")
	private WebElement setCbxStateN;

	@FindBy(how = How.ID, using = "id_country")
	private WebElement cbxCountry;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div/div/form/div[2]/p[9]/div/select/option[2]")
	private WebElement setCbxCountryN;
//----
	@FindBy(how = How.ID_OR_NAME, using = "firstname")
	private WebElement tbxFirstNameAddress;

	@FindBy(how = How.ID_OR_NAME, using = "lastname")
	private WebElement tbxLastNameAddress;

	@FindBy(how = How.ID_OR_NAME, using = "submitAccount")
	private WebElement btnSubmitAccount;

	@FindBy(how = How.ID_OR_NAME, using = "company")
	private WebElement tbxCompany;

	@FindBy(how = How.ID_OR_NAME, using = "address1")
	private WebElement tbxAddress;

	@FindBy(how = How.ID_OR_NAME, using = "city")
	private WebElement tbxCity;

	@FindBy(how = How.ID_OR_NAME, using = "postcode")
	private WebElement tbxPostcode;

	@FindBy(how = How.ID_OR_NAME, using = "phone_mobile")
	private WebElement tbxPhone;

	@FindBy(how = How.ID_OR_NAME, using = "alias")
	private WebElement tbxAlias;

	/**
	 * <b>Nombre:</b> existeElemto</br></br>
	 * <b>Description:</b> Retorna true si el elemento existe
	 * @return {@link Boolean} Retorna <b>True</b> si el elemento es encontrado, de lo contrario retorna <b>False</b>
	 **/
	public boolean existeElemento(String elemento){
		switch (elemento) {
			case "Error": return WebElementUtil.isElementPresent(lblErrorEmail);
			case "ok": return WebElementUtil.isElementPresent(lblInformacionPersonal);
			default: return false;
		}
	}

	 /**
     * <b>Nombre:</b> obtenerText</br></br>
     * <b>Description:</b> Retorna String al obtener un texto de un elemento
     * @return {@link String} Retorna el valor de la caja de texto en <b>String</b>
     **/
    public String obtenerText(String elemento){
    	switch (elemento) {
    		case "First name": return tbxFirstNameAddress.getAttribute("value");
			case "Last name": return tbxLastNameAddress.getAttribute("value");
			default: throw new qaTestException("No existe el elemento");
		}
    }

	/**
	 * <b>Nombre:</b> clicElemento</br></br>
	 * <b>Description:</b> Clic a Elemento
	 * @return {@link boolean} Retorna <b>True</b> si el elemento es cliqueado, de lo contrario retorna <b>False</b>
	 **/
	public boolean clicElemento(String elemento){
		switch (elemento) {
			case "Sign in": menuSignin.click(); return true;
			case "Boton crear cuenta": btnCrearCuenta.click(); return true;
			case "Mr": rbMr.click(); return true;
			case "FechaDia": cbxDia.click(); return true;
			case "FechaDianum": setCbxDiaN.click();;return true;
			case "FechaMes": cbxDia.click(); return true;
			case "FechaMesnum": setCbxMesN.click();;return true;
			case "FechaAnio": cbxDia.click(); return true;
			case "FechaAnionum": setCbxAnioN.click();;return true;

			case "State": cbxState.click(); return true;
			case "Statenum": setCbxStateN.click();;return true;

			case "Country": cbxCountry.click(); return true;
			case "Countrynum": setCbxCountryN.click();;return true;

			case "SubmitAccount": btnSubmitAccount.click(); return true;
			case "Error Email":lblErrorEmail.click(); return true;

			default: throw new qaTestException("No existe el elemento " + elemento + " en la lista de click");
		}
	}

	/**
	 * <b>Nombre:</b> elementoActivo</br></br>
	 * <b>Description:</b> Enviar  texto
	 * @return {@link boolean}
	 **/
	public boolean addTexto(String elemento, String valor){
		switch (elemento) {
			case "Email": tbxEmailCreate.sendKeys(valor); break;
			case "FirstNameCustomer": tbxFirstNameCustomer.sendKeys(valor); break;
			case "LastNameCustomer": tbxLastNameCustomer.sendKeys(valor); break;
			case "PassCustomer": tbxPass.sendKeys(valor); break;
			case "Company": tbxCompany.sendKeys(valor); break;
			case "Address": tbxAddress.sendKeys(valor); break;
			case "City": tbxCity.sendKeys(valor); break;
			case "Postal Code": tbxPostcode.sendKeys(valor); break;
			case "Phone": tbxPhone.sendKeys(valor); break;
			case "Alias Address": tbxAlias.sendKeys(valor); break;

			default: throw new qaTestException("No existe el elemento " + elemento + " en la lista de click");
		}
		return false;
	}

	/**
	 * <b>Nombre:</b> elementoActivo</br></br>
	 * <b>Description:</b> Enviar  texto
	 * @return {@link boolean}
	 **/
	public boolean datosFormulario(){
		Faker faker = new Faker();

		this.clicElemento("Mr");
		this.addTexto("FirstNameCustomer", faker.name().firstName());
		this.addTexto("LastNameCustomer", faker.name().lastName());
		this.addTexto("PassCustomer", faker.internet().password());

		this.clicElemento("FechaDia");
		this.clicElemento("FechaDianum");

		this.clicElemento("FechaMes");
		this.clicElemento("FechaMesnum");

		this.clicElemento("FechaAnio");
		this.clicElemento("FechaAnionum");

		this.addTexto("Company", faker.company().name());
		this.addTexto("Address", faker.address().fullAddress());
		this.addTexto("City", faker.address().city());

		this.clicElemento("State");
		this.clicElemento("Statenum");

		this.addTexto("Postal Code", faker.address().zipCode());

		this.clicElemento("Country");
		this.clicElemento("Countrynum");

		this.addTexto("Phone", faker.phoneNumber().cellPhone());
		this.addTexto("Alias Address", "My-alias");

		//this.clicElemento("Registrar");
		return true;

	}

}
