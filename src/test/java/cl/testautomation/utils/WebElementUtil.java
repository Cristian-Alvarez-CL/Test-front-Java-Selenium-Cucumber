package cl.testautomation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cl.testautomation.MainSuiteTest;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WebElementUtil {
	
	/** 
     * <b>Nombre:</b> isElementPresent</br></br>
     * <b>Description:</b> Verifica la existencia de un elemento
     * @param {@link WebElement} Objeto de tipo WebElement a buscar                             
     * @return {@link Boolean} Retorna <b>True</b> si el elemento es encontrado, de lo contrario retorna <b>False</b>
      **/
	public static boolean isElementPresent(WebElement webElement) {
		try {
			@SuppressWarnings("unused")
			Boolean resp = webElement.isDisplayed();
		    return true;
		} catch (NoSuchElementException e) {
		    return false;
		}
    }

	/**
	 * <b>Nombre:</b> changeWindowTab</br></br>
	 * <b>Description:</b> Cambia la ventana activa
	 * @param {@link WebDriver} Objeto de tipo WebDriver
	 **/
	public static void changeWindowTab() {
		String newWindow = "";
		Set<String> s1 = MainSuiteTest.driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			newWindow = i1.next();
		}
		MainSuiteTest.driver.switchTo().window(newWindow);
	}
	
	/** 
     * <b>Nombre:</b> getDatosColumnaTabla</br></br>
     * <b>Description:</b> Retorna una lista de String de los datos de una columna
     * @param {@link WebElement} Tabla de datos
     * @param {@link columna} Indice de la columna     
     * @param {@link boolean} True, si mantiene titulos                        
     * @return {@link List} Lista de datos
     **/
	public static List<String> getDatosColumnaTabla(WebElement tabla, int columna, boolean sinTitulos) {
		List<WebElement> filas = tabla.findElements(By.tagName("tr"));
		List<String> datos = new ArrayList<String>();
		if (sinTitulos) {
			filas.remove(0);
		}
		for (WebElement item : filas) {
			String dato = item.findElements(By.tagName("td")).get(columna).getText().trim();
			if(dato.trim().length() == 0) { continue; }
			datos.add(dato);
		}
		return datos;
	}
	
	/** 
     * <b>Nombre:</b> getColumnaTabla</br></br>
     * <b>Description:</b> Retorna una lista de WebElement de los datos de una columna
     * @param {@link WebElement} Tabla de datos
     * @param {@link columna} Indice de la columna     
     * @param {@link boolean} True, si mantiene titulos                        
     * @return {@link List} Lista de datos
     **/
	public static List<WebElement> getColumnaTabla(WebElement tabla, int columna, boolean sinTitulos) {
		List<WebElement> filas = tabla.findElements(By.tagName("tr"));
		List<WebElement> datos = new ArrayList<WebElement>();
		if (sinTitulos) {
			filas.remove(0);
		}
		for (WebElement item : filas) {
			datos.add(item.findElements(By.tagName("td")).get(columna));
		}
		return datos;
	}
	
	/** 
     * <b>Nombre:</b> getFilasTabla</br></br>
     * <b>Description:</b> Retorna las filas de una tabla
     * @param {@link WebElement} Tabla de datos
     * @param {@link boolean} True, si mantiene titulos                        
     * @return {@link List} Lista de datos
     **/
	public static List<WebElement> getFilasTabla(WebElement tabla, boolean sinTitulo){
		List<WebElement> lista = tabla.findElements(By.tagName("tr"));
		if(sinTitulo) {lista.remove(0);}
		return lista;
	}
	
	/** 
     * <b>Nombre:</b> seleccionarItem</br></br>
     * <b>Description:</b> Selecciona una opcion en Select segun valor mostrado
     * @param {@link WebElement} Campo Select
     * @param {@link boolean} Valor a seleccionar                      
     * @return {@link Void}
     **/
	public static void seleccionarItem(WebElement select, String valor) {
		new Select(select).selectByVisibleText(valor);

		//Select nombre = new Select(driver.findElement(elementby);
		//nombre.selectByVisibleText(valor);

		//Select nombre = new Select(select);

		//nombre.selectByVisibleText(valor);

	}
	
	/** 
     * <b>Nombre:</b> getOpcionesSelect</br></br>
     * <b>Description:</b> Retorna lista de String de las opciones de un campo Select
     * @param {@link WebElement} Campo Select                     
     * @return {@link List} Lista de opciones
     **/
	public static List<String> getOpcionesSelect(WebElement selector){
		List<WebElement> opciones = new Select(selector).getOptions();
		List<String> listaOpciones = new ArrayList<String>();
		for(WebElement el : opciones) {
			listaOpciones.add(el.getText().trim());
		}
		return listaOpciones;
	}
	
	/** 
     * <b>Nombre:</b> sortFechas</br></br>
     * <b>Description:</b> Ordena una lista del tipo Date 
     * @param {@link List} Campo Select
     * @param {@link String} String de orden, "ASC" Ascendente, "DESC" Descendente                     
     * @return {@link List} Lista ordenada
     **/
	public static List<Date> sortFechas(List<Date> lista, String orden) {
		switch (orden.toUpperCase()) {
		case "ASC":
			Collections.sort(lista);
			break;
		case "DESC":
			Collections.sort(lista, Collections.reverseOrder());
			break;
		default:
			Collections.sort(lista);
		}
		return lista;
	}
	
	/** 
     * <b>Nombre:</b> sortNumero</br></br>
     * <b>Description:</b> Ordena una lista del tipo Date 
     * @param {@link List} Lista a ordenar
     * @param {@link String} String de orden, "ASC" Ascendente, "DESC" Descendente                     
     * @return {@link List} Lista ordenada
     **/
	public static List<Double> sortNumero(List<Double> lista, String orden) {
		switch (orden.toUpperCase()) {
		case "ASC":
			Collections.sort(lista);
			break;
		case "DESC":
			Collections.sort(lista, Collections.reverseOrder());
			break;
		default:
			Collections.sort(lista);
		}
		return lista;
	}
	
	/**
     * <b>Nombre:</b> formatearMoneda</br></br>
     * <b>Description:</b> Aplica formato de moneda a un valor dado
     * @param {@link String} Valor a formatear
     * @param {@link Char} Caracter separador decimal
     * @param {@link Char} Caracter separador de miles
     * @param {@link Boolean} Verdadero si agregamos decimales (2) usualmente para moneda extranjera
     * @return {@link String} Valor formateado en moneda
     **/
	public static String formatearMoneda(String valor,char sepDecimal, char sepMiles, boolean ext) {
		double monto = Double.valueOf(valor.replaceAll("[^0-9-,]", "").replace(",", "."));
		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator(sepDecimal);
		simbolo.setGroupingSeparator(sepMiles);
		DecimalFormat formateador = new DecimalFormat("###,###.###", simbolo);
		formateador.setMinimumFractionDigits(ext ? 2 : 0);
		formateador.setMaximumFractionDigits(ext ? 2 : 0);
		return formateador.format(monto);
	}
	
	/**
     * <b>Nombre:</b> esCampoPassword</br></br>
     * <b>Description:</b> Comprueba si el campo ingresado como parametro es del tipo password
     * @param {@link String} Campo a verificar
     * @return {@link String} True, si es de tipo Password
     **/
	public static boolean esCampoPassword(WebElement campo) {
		return campo.getAttribute("type").equals("password");
	}

}
