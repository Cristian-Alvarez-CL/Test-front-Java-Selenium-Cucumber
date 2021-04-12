package cl.testautomation.profiles;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import cl.testautomation.exception.qaTestException;
import cl.testautomation.utils.WebElementUtil;


public class Pf_HomePagePractice {
	
	@FindBy(how = How.XPATH, using = "/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[1]/a")
	private WebElement menuWomen;
	@FindBy(how = How.XPATH, using = "/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a")
	private WebElement menuDresses;
	@FindBy(how = How.XPATH, using = "/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[3]/a")
	private WebElement menuTshirts;
	@FindBy(how = How.XPATH, using = "//*[@id=\"search_query_top\"]")
	private WebElement textBoxSearch;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div[2]/h1/span[1]")
	private WebElement lblWomen;
	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div[2]/h1/span[1]")
	private WebElement lblDresses;
	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div[3]/div[2]/h1/span[1]")
	private WebElement lblTshirts;

	@FindBy(how = How.XPATH, using = "/html/body/div/div[3]")
	private WebElement Footer;

	 /** 
     * <b>Nombre:</b> existeMenuMisMovimientoss</br></br>
     * <b>Description:</b> Retorna true si el elemento existe                          
     * @return {@link Boolean} Retorna <b>True</b> si el elemento es encontrado, de lo contrario retorna <b>False</b>
     **/
    public boolean existeElemento(String elemento){
    	switch (elemento) {
    		case "Women": return WebElementUtil.isElementPresent(menuWomen);
    		case "Dress": return WebElementUtil.isElementPresent(menuDresses);
    		case "Tshirts" : return WebElementUtil.isElementPresent(menuTshirts);
    		case "TxtBoxSearch" : return WebElementUtil.isElementPresent(textBoxSearch);
			case "Footer" : return WebElementUtil.isElementPresent(Footer);
			case "Women1" : return WebElementUtil.isElementPresent(lblWomen);
			case "Dress1": return WebElementUtil.isElementPresent(lblDresses);
			case "Tshirts1" : return WebElementUtil.isElementPresent(lblTshirts);
			default: return false;
		}
    }


	/**
	 * <b>Nombre:</b> elementoActivo</br></br>
	 * <b>Description:</b> Clic izquierdo
	 * @return {@link Void}
	 **/
	public boolean clicElemento(String elemento){
		switch (elemento) {
			case "Women": menuWomen.click(); break;
			case "Dress": menuDresses.click(); break;
			case "Tshirts": menuTshirts.click(); break;

			default: throw new qaTestException("No existe el elemento " + elemento + " en la lista de click");
		}
		return false;
	}

}
