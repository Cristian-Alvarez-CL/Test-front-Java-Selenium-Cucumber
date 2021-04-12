package cl.testautomation.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cl.testautomation.utils.IoDriver;

public enum SuitesService {

	INIT { public void init() {
		
		String propFileName = "config/config.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
		try {
			parametros.load(inputStream);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SUITE = setConstante("jSuite", "suite", "todos"); 
		
	} },
	CONFIG {
		
		@Override
		public void init() {
			
			if (parametros.isEmpty()) {
                SuitesService.INIT.init();
            }
			
			URL_HUB = setConstante("jUrlHub", "hub", "http://10.235.27.193:4444/wd/hub");
			ENTORNO = setConstante("jTestExecutionEnv", "test.execution.env", "grid");
			BROWSER = setConstante("jTestBrowser", "test.browser", "chrome");
			SCREENSHOT = setConstante("jScreenShot", "reportehtml.screenshot", "fails"); 
			TITULO = setConstante("jTitulo", "reportehtml.titulo", "Reporte De Automatizacion"); 
			ICONOGRAFIA = setConstante("jIconografia", "reportehtml.iconografia", "true"); 
			
			PROXY_ENABLE = setConstanteBoolean("jProxyEnable", "proxy.enable", false);
			
			if (PROXY_ENABLE == true){
				PROXY_HOST = setConstante("jProxyHost", "proxy.host", "transparentproxy.bns"); 
				PROXY_PORT = setConstante("jProxyPort", "proxy.port", "8080");  
				NO_PROXY_HOST = setConstante("jNoProxy", "no.proxy.host", "*.bns,localhost");  
			}
			
			IEDRIVER = set_ConfigVar("webdriver.ie.driver", "ie"); 
			CHROMEDRIVER = set_ConfigVar("webdriver.chrome.driver", "chrome"); 
			GECKODRIVER = set_ConfigVar("webdriver.gecko.driver", "firefox"); 
			OPERADRIVER = set_ConfigVar("webdriver.opera.driver", "opera"); 
			EDGEDRIVER = set_ConfigVar("webdriver.edge.driver", "edge"); 
			PHANTOMJSDRIVER = set_ConfigVar("phantomjs.binary.path", "phantom"); 
			
			MOBILE_EMUL = setConstante("jTestMobile","test.mobile", "Nexus7");
			
			// Incluir la inicializacion de las variables del proyecto aca
			
			URL = setConstante("jAppUrl", "url");
			URLWAS = setConstante("jAppUrlWas", "was");

			DB2 = setConstante("jAppDb2", "db2");
			DB2_LiB = setConstante("jAppDb2Lib", "db2.libreria");
			DB2_USER = setConstante("jAppDb2Usr", "db2.usuario");
			DB2_PASS = setConstante("jAppDb2Pass", "db2.pass");
			DB2_DB = setConstante("jAppDb2Db", "db2.db");
			
			// FIN inicializacion de las variables del proyecto	
		}
	} ;
	
	public abstract void init();
	
	private static Properties parametros = new Properties();
	
	public static String SUITE;
	public static String URL_HUB;
	public static String ENTORNO;
	public static String BROWSER;
	public static String SCREENSHOT;
	public static String TITULO;
	public static String ICONOGRAFIA;
	public static Boolean PROXY_ENABLE;
	public static String PROXY_HOST;
	public static String PROXY_PORT;
	public static String NO_PROXY_HOST;
	
	public static String IEDRIVER;
	public static String CHROMEDRIVER;
	public static String GECKODRIVER;
	public static String OPERADRIVER;
	public static String EDGEDRIVER;
	public static String PHANTOMJSDRIVER;
	
	public static String MOBILE_EMUL;
	
	// Variables propietarias al proyecto
	
	public static String URL;
	public static String URLWAS;

	public static String DB2;
	public static String DB2_LiB;
	public static String DB2_USER;
	public static String DB2_PASS;
	public static String DB2_DB;
	
	// FiN Variables propietarias al proyecto

	/**
	 * Check if there is an environment variable for given property. This is to allow configuration
	 * to be overwritten if it is provided from Jenkins
	 *
	 * @param property property key
	 * @return true if variable is specified on Jenkins, false otherwise
	 */
	private static boolean isEnvVarSet(String property) {
		return System.getenv(property) != null && !System.getenv(property).isEmpty();
	}
	
	/**
	 * Comprueba la existencia del valor
	 *
	 * @param property property key
	 * @return true if variable is specified on Jenkins, false otherwise
	 */
	private static boolean isConfigVarSet(String property) {
		return property != null && !property.isEmpty() && !property.endsWith("null") && !property.equals("null");
	}
	
	/**
	 * Pasa Parametros para establecer el valor de una constante
	 *
	 * @param {@link String} Variable de entorno Jenkins
	 * @param {@link String} Nombre de la propiedad del archivo de configuracion
	 * @param {@link String} Valor por defecto
	 * @return {@link String} El valor de una constante
	 */
	private static String setConstante(String entornoJ, String propiedad, String valorDefecto) {
		return isEnvVarSet(entornoJ) ? System.getenv(entornoJ) : (isConfigVarSet(parametros.getProperty(propiedad))) ? parametros.getProperty(propiedad) : valorDefecto;
	}
	
	/**
	 * Pasa Parametros para establecer el valor de una constante
	 *
	 * @param {@link String} Variable de entorno Jenkins
	 * @param {@link String} Nombre de la propiedad del archivo de configuracion
	 * @return {@link String} El valor de una constante
	 */
	private static String setConstante(String entornoJ, String propiedad) {
		return isEnvVarSet(entornoJ) ? System.getenv(entornoJ) : parametros.getProperty(propiedad);
	}
	
	/**
	 * Pasa Parametros para establecer el valor de una constante
	 *
	 * @param {@link String} Variable de entorno Jenkins
	 * @param {@link String} Nombre de la propiedad del archivo de configuracion
	 * @param {@link String} Valor por defecto
	 * @return {@link String} El valor de una constante
	 */
	private static Boolean setConstanteBoolean(String entornoJ, String propiedad, boolean valorDefecto) {
		return isEnvVarSet(entornoJ) ? Boolean.valueOf(System.getenv(entornoJ)) : (isConfigVarSet(parametros.getProperty(propiedad))) ? Boolean.valueOf(parametros.getProperty(propiedad)) : valorDefecto;
	}
	
	/**
	 * Pasa Parametros para establecer el valor de una constante
	 *
	 * @param {@link String} Nombre de la propiedad del archivo de configuracion
	 * @param {@link String} Valor por defecto
	 * @return {@link String} El valor de una constante
	 */
	private static String set_ConfigVar(String propiedad, String valorDefecto) {
		return (isConfigVarSet(parametros.getProperty(propiedad).trim())) ? parametros.getProperty(propiedad) : IoDriver.getRutaValidada(valorDefecto);
	}
	

}
