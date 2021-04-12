package cl.testautomation.services;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import cl.testautomation.exception.qaTestException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.firefox.FirefoxDriver.PROFILE;
import static org.openqa.selenium.remote.CapabilityType.PROXY;

public enum WebDriverManager {
	
	INIT;
	
	private boolean isMobile = false;
	
	/** 
     * <b>Nombre:</b> getDriver</br></br>
     * <b>Description:</b> Retorna la conexion de selenium
     * @return WebDriver Rerotna un Objeto de tipo WebDriver
     **/
	public WebDriver getDriver() {
		WebDriver driver;
	    switch(SuitesService.ENTORNO) {
	    case "local":
	    	driver = initLocalDriver();
	    	break;
	    case "grid":
	        driver = initGridDriver();
	        break;
	    default:
	        throw new qaTestException("Entorno no soportado! [" + SuitesService.ENTORNO + "]");
	    }
	    return driver;
	}
	
	/** 
     * <b>Nombre:</b> getDriver</br></br>
     * <b>Description:</b> Retorna la conexion de selenium
     * @param {@link Boolean} True, para establecer driver con emulador mobile
     * @return WebDriver Retorna un Objeto de tipo WebDriver
     **/
    public WebDriver getDriver(Boolean isMobile) {
        this.isMobile = isMobile;
        return this.getDriver();
    }
	
	/** 
     * <b>Nombre:</b> initLocalDriver</br></br>
     * <b>Description:</b> Inicializa el controlador de selenium grid
     * @return WebDriver Rerotna un Objeto de tipo WebDriver basado en la configuracion local
     **/
    private WebDriver initLocalDriver(){
        DesiredCapabilities capability;
        switch(SuitesService.BROWSER) {		
        case "chrome":
        	System.setProperty("webdriver.chrome.driver", SuitesService.CHROMEDRIVER);
        	Map <String, String> mobileEmulation = new HashMap <String, String>();
            Map <String, Object> chromeOptions = new HashMap <String, Object>();
        	capability = DesiredCapabilities.chrome();
        	chromeOptions.put("args",  Arrays.asList("test-type"));
        	if(isMobile) {
        		switch (SuitesService.MOBILE_EMUL) {
        		case "GalaxyS5" : mobileEmulation.put("deviceName", "Galaxy S5"); break;
        		case "iPhone6" : mobileEmulation.put("deviceName", "iPhone 6"); break;
        		case "Nexus7" : mobileEmulation.put("deviceName", "Nexus 7"); break;
        		}
        		chromeOptions.put("mobileEmulation", mobileEmulation);
        	}
        	capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            if (SuitesService.PROXY_ENABLE == true){
            	capability.setCapability(PROXY, getProxySettings());
            }
            return new ChromeDriver(capability);
        case "ie":
            System.setProperty("webdriver.ie.driver", SuitesService.IEDRIVER);
    	    capability = DesiredCapabilities.internetExplorer();
        	capability.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        	capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        	capability.setCapability("ignoreZoomSetting", true);
        	return new InternetExplorerDriver(capability);
        case "firefox":	    	
    	    System.setProperty("webdriver.gecko.driver", SuitesService.GECKODRIVER);
    	    capability = DesiredCapabilities.firefox();
    	    FirefoxProfile ffProfile = new FirefoxProfile();
        	if (SuitesService.PROXY_ENABLE == true){
        		ffProfile.setPreference("network.proxy.type", 1); // Configuracion Manual
                ffProfile.setPreference("network.proxy.http", SuitesService.PROXY_HOST);
                ffProfile.setPreference("network.proxy.http_port", Integer.valueOf(SuitesService.PROXY_PORT));
                ffProfile.setPreference("network.proxy.ssl", SuitesService.PROXY_HOST);
                ffProfile.setPreference("network.proxy.ssl_port", Integer.valueOf(SuitesService.PROXY_PORT));
                ffProfile.setPreference("network.proxy.no_proxies_on", SuitesService.NO_PROXY_HOST);
            }
        	capability.setCapability(PROFILE, ffProfile);
    	    return new FirefoxDriver(capability);
		case "safari":
			return new SafariDriver();
        default:
            throw new qaTestException("Navegador [" + SuitesService.BROWSER + "] no soportado!");
        }
    }
	
	/** 
     * <b>Nombre:</b> initGridDriver</br></br>
     * <b>Description:</b> Inicializa el controlador de selenium grid
     * @return WebDriver Rerotna un Objeto de tipo WebDriver basado en los parametros de config.properties o entorno de Jenkins
     **/
    private WebDriver initGridDriver(){
        DesiredCapabilities capability;
        switch(SuitesService.BROWSER) {
        case "chrome":	
        	Map <String, String> mobileEmulation = new HashMap <String, String>();
            Map <String, Object> chromeOptions = new HashMap <String, Object>();
        	capability = DesiredCapabilities.chrome();
        	capability.setPlatform(Platform.VISTA);
        	chromeOptions.put("args",  Arrays.asList("test-type"));
        	if(isMobile) {
        		switch (SuitesService.MOBILE_EMUL) {
        		case "GalaxyS5" : mobileEmulation.put("deviceName", "Galaxy S5"); break;
        		case "iPhone6" : mobileEmulation.put("deviceName", "iPhone 6"); break;
        		case "Nexus7" : mobileEmulation.put("deviceName", "Nexus 7"); break;
        		}
        		chromeOptions.put("mobileEmulation", mobileEmulation);
        	}
        	capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            if (SuitesService.PROXY_ENABLE == true){
            	capability.setCapability(PROXY, getProxySettings());
            }   
        break;    
        case "ie":
        	capability = DesiredCapabilities.internetExplorer();
        	capability.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        	capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        	capability.setCapability("ignoreZoomSetting", true);
        	capability.setPlatform(Platform.VISTA);
            break;
        case "firefox":
        	capability = DesiredCapabilities.firefox();
        	FirefoxProfile ffProfile = new FirefoxProfile();
        	if (SuitesService.PROXY_ENABLE == true){
        		ffProfile.setPreference("network.proxy.type", 1); // Configuracion Manual
                ffProfile.setPreference("network.proxy.http", SuitesService.PROXY_HOST);
                ffProfile.setPreference("network.proxy.http_port", Integer.valueOf(SuitesService.PROXY_PORT));
                ffProfile.setPreference("network.proxy.ssl", SuitesService.PROXY_HOST);
                ffProfile.setPreference("network.proxy.ssl_port", Integer.valueOf(SuitesService.PROXY_PORT));
                ffProfile.setPreference("network.proxy.no_proxies_on", SuitesService.NO_PROXY_HOST);
            }
        	capability.setBrowserName("firefox");
        	capability.setPlatform(Platform.VISTA);
        	capability.setCapability(PROFILE, ffProfile);
            break;
		case "safari":
			capability = DesiredCapabilities.safari();
			capability.setPlatform(Platform.MAC);
			break;
        default:
            throw new qaTestException("Navegador [" + SuitesService.BROWSER + "] no soportado!");
        }
        try {
            return new RemoteWebDriver(new URL(SuitesService.URL_HUB), capability);
        } catch (MalformedURLException e) {
            throw new qaTestException("Error inicializando Servicio Selenium Grid en: " + SuitesService.URL_HUB, e);
        }
    }
	
	private Proxy getProxySettings() {
	    return new Proxy()
	        .setProxyType(ProxyType.MANUAL)
	        .setHttpProxy(SuitesService.PROXY_HOST + ":" + SuitesService.PROXY_PORT)
	        .setSslProxy(SuitesService.PROXY_HOST + ":" + SuitesService.PROXY_PORT)
	        .setNoProxy(SuitesService.NO_PROXY_HOST);
	  }

}
