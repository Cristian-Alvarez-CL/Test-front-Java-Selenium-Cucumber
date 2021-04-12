package cl.testautomation.utils;

import java.io.File;

import cl.testautomation.exception.qaFrameworkException;
import cl.testautomation.services.SuitesService;

public class IoDriver {
	
	private static String SOper = System.getProperty("os.name").toLowerCase();
	private static String Arch = System.getProperty("os.arch").toLowerCase();
	
	/** 
     * <b>Nombre:</b> getRutaValidada </br></br>
     * <b>Description:</b> retorna la ruta del controlador validando existencia                     
     * @return {@link String} Ruta controlador
     **/
	public static String getRutaValidada(String navegador){
		File file = new File(getRutaControlador(navegador));
		if (file.exists()){
			return getRutaControlador(navegador);
		} else if (navegador.equals("ie") && isUnix()) {
			return "No Aplica";
		} else if (navegador.equals("edge") && isUnix()){
			return "No Aplica";
		} else if (navegador.equals("ie") && isMac()) {
			return "No Aplica";
		} else if (navegador.equals("edge") && isMac()){
			return "No Aplica";
		} else {
			if (SuitesService.ENTORNO.equals("grid")) return "No Aplica";
			else throw new qaFrameworkException("Debe descargar los controladores antes de ejecutar por primera vez!");
		}
	}
	
	/** 
     * <b>Nombre:</b> getRutaControlador </br></br>
     * <b>Description:</b> retorna la ruta del controlador configurado como parametro e config.properties                      
     * @return {@link String} Ruta controlador
     **/
	private static String getRutaControlador(String navegador){
		switch (navegador){
		case "edge": return System.getProperty("user.dir") + File.separator + "WebDrivers" 
			+ File.separator + "bin" + File.separator + getSO() + File.separator + "edge" + File.separator  
			+ getArch() + "bit" + File.separator + ((isWindows())? "MicrosoftWebDriver.exe": "MicrosoftWebDriver");
		case "chrome": return System.getProperty("user.dir") + File.separator + "WebDrivers" 
			+ File.separator + "bin" + File.separator + getSO() + File.separator + "googlechrome" + File.separator 
			+ getArch() + "bit" + File.separator + ((isWindows())? "chromedriver.exe": "chromedriver");
		case "ie": return System.getProperty("user.dir") + File.separator + "WebDrivers" 
			+ File.separator + "bin" + File.separator + getSO() + File.separator + "internetexplorer" + File.separator 
			+ getArch() + "bit" + File.separator + ((isWindows())? "IEDriverServer.exe": "IEDriverServer");
		case "firefox": return System.getProperty("user.dir") + File.separator + "WebDrivers" 
			+ File.separator + "bin" + File.separator + getSO() + File.separator + "marionette" + File.separator 
			+ getArch() + "bit" + File.separator + ((isWindows())? "geckodriver.exe": "geckodriver");
		case "opera": return System.getProperty("user.dir") + File.separator + "WebDrivers" 
			+ File.separator + "bin" + File.separator + getSO() + File.separator + "operachromium" + File.separator 
			+ getArch() + "bit" + File.separator + ((isWindows())? "operadriver.exe": "operadriver");
		case "phantom": return System.getProperty("user.dir") + File.separator + "WebDrivers" 
			+ File.separator + "bin" + File.separator + getSO() + File.separator + "phantomjs" + File.separator 
			+ getArch() + "bit" + File.separator + ((isWindows())? "phantomjs.exe": "phantomjs");
		default: 
			throw new qaFrameworkException("Navegador no soportado! [" + navegador + "]");
		}
	}
	
	/** 
	 * <b>Nombre:</b> getSO </br></br>
	 * <b>Description:</b> Retorna el nombre del SO anfitrion                       
	 * @return {@link String} 
	 **/
	private static String getSO(){
		if (isWindows()) return "windows"; 
		else if (isMac()) return "osx";
		else if (isUnix()) return "linux";
		else 
			throw new qaFrameworkException("S.O. no soportado! [" + SOper + "]");
	}
	
	/** 
	 * <b>Nombre:</b> getArch </br></br>
	 * <b>Description:</b> Retorna los bits de la arquitectura de SO                     
	 * @return {@link String}
	 **/
	private static String getArch() {
		return (Arch.endsWith("64"))? "64": "32";
	}
	
	/** 
	 * <b>Nombre:</b> isWindows </br></br>
	 * <b>Description:</b> Retorna Verdadero si el sistema operatido es Windows                       
	 * @return {@link Boolean}
	 * @author Aldo Saez
	 **/
	private static boolean isWindows() {
		return (SOper.indexOf("win") >= 0);
	}
	 
	/** 
	 * <b>Nombre:</b> rutaInicalSINCarpeta </br></br>
	 * <b>Description:</b> Retorna Verdadero si el sistema operatido es OSX                         
	 * @return {@link Boolean}
	 **/
	private static boolean isMac() {
	    return (SOper.indexOf("mac") >= 0);
	}
	
	/** 
	 * <b>Nombre:</b> rutaInicalSINCarpeta </br></br>
	 * <b>Description:</b> Retorna Verdadero si el sistema operatido es Linux/Unix                         
	 * @return {@link Boolean}
	 * @author Aldo Saez
	 **/
	private static boolean isUnix() {
	    return (SOper.indexOf("nix") >= 0 || SOper.indexOf("nux") >= 0 || SOper.indexOf("aix") > 0 );
	}

}
