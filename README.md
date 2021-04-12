# Test-front-Java-Selenium-Cucumber
Prueba Automatizada para Web, usando Java, Selenium, CucumberNodeJs

## Instalacion    
-----------------
Clonar proyecto desde el repositorio [https://github.com/Cristian-Alvarez-CL/Test-front-Java-Selenium-Cucumber](https://github.com/Cristian-Alvarez-CL/Test-front-Java-Selenium-Cucumber)


## Prerrequisito    
-----------------
- Maven 3.3.9+ installed     
- JDK 1.8+ installed   

## Ejecucion    
-------------
Se debe configurar previamente el ambiente de ejecucion previmente que se encuentra en la carpeta "Ambiente" y Sub-carpeta <ANB1 o n+1>, en su contenido de estas sub-carpetas
se encuentra el archivo "config.properties" con el siguiente contenido:

1.- env.url (Url de la web a probar)
2.- env.suite (Suit a ejecutar, en este ejemplo LoginPagePractice o HomePagePractice)
3.- env.test.browser (nevegador a utilizar chrome, ie, firefox)

Luego de tener definido estas variables se puede ejecutar la siguiente instruccion

`mvn -f pom.xml -P run-tests -Dbuild.profile.id=<Ambiente> clean test`     

	* Si es la primera vez, debe realizar previamente la instruccion a continuacion que permite bajar los webdrivers de los navegadores

## Descarga de controladres (Solo Local)
----------------------------------------

`mvn -f pom.xml -P install-drivers -Dbuild.profile.id=<Ambiente> clean verify`

	* Este generara automaticamente las carpetas para almacenar los controladores

## Actualizacion de Controladores
---------------------------------

Para actualizar los controladores ejecute la siguiente instruccion

`mvn -f pom.xml -P install-drivers -Doverwrite.binaries=true -Dbuild.profile.id=<Ambiente> clean verify`

	* Ademas se puede eliminar el contenido de las carpetas WebDrivers/bin y WebDrivers/zip si se requiere