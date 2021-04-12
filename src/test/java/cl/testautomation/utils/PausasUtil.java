package cl.testautomation.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * <b>Description:</b> Clase de utilidades para el control de pausas explicitas e impl&iacute;citas, para implementar esta clase
 * 	se debe contar con la dependencia de:</br>
 * <pre>
 * {@code
 * <dependency>
 *    <groupId>org.seleniumhq.selenium</groupId>
 *    <artifactId>selenium-java</artifactId>
 *    <version>3.4.0</version>
 * </dependency>}</pre>
 * 	La implementacion de esta clase es statica </br>
 *  * Adem&aacute;s, recomendamos agregar las siguientes l&iacute;neas para el control efectivo de otras esperas.</br>
 * <pre>{@code
 *    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 *    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);}</pre>
 * @version 2.1.1
 * @see {@link "https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/support/ui/WebDriverWait.html"}
 */
public class PausasUtil {
	   
    /** 
     * <b>Nombre:</b> generaPausaSegundos</br></br>
     * <b>Description:</b> Genera una pausa implicita
     * @param {@link Integer} segundos Valor en segundos
     * @return {@link Void}
     **/
    public static void generaPausaSegundos(int segundos, Class<?> obj){
    	synchronized(obj){
    		try {
    			obj.wait(segundos * 1000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    /** 
     * <b>Nombre:</b> esperaElementoSegundos</br></br>
     * <b>Description:</b> Genera una pausa explicita hasta que el elemento dado es encontrado.
     * @param {@link WebDriver} driver Controlador WebDrive.
     * @param {@link WebElement} webElement Elemento a esperar.
     * @param {@link Integer} segundos Valor de tiempo en segundos a esperar.
     * @return {@link Boolean} Retorna un valor <b>verdadero</b> si el elemento es encontrado dentro del tiempo estipulado, de lo contrario retorna un valor <b>falso</b>.
     **/
    public static Boolean esperaElementoSegundos( WebDriver driver, WebElement webElement, int segundos){
    	WebDriverWait wait = new WebDriverWait(driver, segundos);
    	try {
    		wait.until(visibilityOf(webElement));
    		return true;
		} catch (TimeoutException e) {
			return false;
		}
    }
    
    /** 
     * <b>Nombre:</b> esperaElementoException</br></br>
     * <b>Description:</b> Genera una pausa explicita hasta que el elemento dado es encontrado.
     * @param {@link WebDriver} driver Controlador WebDrive.
     * @param {@link WebElement} webElement Elemento a esperar.
     * @param {@link Integer} segundos Valor de tiempo en segundos a esperar.
     * @return {@link Void}
     **/
    public static void esperaElementoException( WebDriver driver, WebElement webElement, int segundos){
    	WebDriverWait wait = new WebDriverWait(driver, segundos);
    	wait.until(visibilityOf(webElement));
    }
    
    /** 
     * <b>Nombre:</b> unloadWebElementByClass</br></br>
     * <b>Description:</b> Genera una pausa explicita mientras el elemento dado desaparece del DOM.
     * @param {@link WebDriver} driver Controlador WebDrive.
     * @param {@link WebElement} webElement Elemento a esperar.
     * @param {@link Integer} segundos Valor de tiempo en segundos a esperar.
     * @return {@link Boolean} Retorna un valor <b>verdadero</b> si el elemento aun permanece en el DOM, de lo contrario retorna un valor <b>falso</b>.
     **/
    public static Boolean unloadWebElementByClass(WebDriver driver, WebElement webElement, int segundos, Class<?> obj ){
    	Boolean elementExist = WebElementUtil.isElementPresent(webElement);
    	int conTiempo = 0;
    	String claseTag = "";
    	if (elementExist) claseTag = webElement.getAttribute("class");
    	while (elementExist == true && conTiempo <= segundos ) {
    		elementExist = (Boolean) ((JavascriptExecutor) driver)
    				.executeScript("var elemento = document.getElementsByClassName('" + claseTag + "');return (elemento.length == 0)?false:true;");
			generaPausaSegundos(2, obj);
			conTiempo = conTiempo + 2;
		}    	
    	return elementExist;
    }
    
    /** 
     * <b>Nombre:</b> waitForLoad</br></br>
     * <b>Description:</b> Genera una pausa explicita mientras se carga el sitio web.
     * @param {@link WebDriver} driver Controlador WebDrive.
     * @param {@link Integer} segundos Valor de tiempo en segundos a esperar.
     * @return {@link Void}
     **/
    public static void waitForLoad(WebDriver driver, int segundos) {
    	new WebDriverWait(driver, segundos).until((ExpectedCondition<Boolean>) wd ->
        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }   
    
    /** 
     * <b>Nombre:</b> isPageLoad</br></br>
     * <b>Description:</b> Genera una pausa explicita mientras se carga el sitio web.
     * @param {@link WebDriver} driver Controlador WebDrive.
     * @param {@link Integer} segundos Valor de tiempo en segundos a esperar.
     * @return {@link Boolean} Retorna un valor <b>verdadero</b> si el la pagina carga por completo en el DOM, de lo contrario retorna un valor <b>falso</b>.
     **/
    public static Boolean isPageLoad(WebDriver driver, int segundos) {
    	return new WebDriverWait(driver, segundos).until((ExpectedCondition<Boolean>) wd ->
        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }   
}
