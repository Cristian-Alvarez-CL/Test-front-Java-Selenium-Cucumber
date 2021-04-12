@tag_feature1
Feature: Validar ingreso al login
  Con el fin de poder validar el ingreso al login
  Yo como cliente quiero pooder registrarme
  Para poder realizar login

  @Inicia_tag1
  Scenario Outline: 02 Ingresar al menú login
    Given Que ingreso a la web de practica
    When Ingreso al menu Sign in
    Then valido el campo <email> para obtener una <respuesta>

    Examples:
      |email|respuesta|
      |calvarez@clmconsultores.com     | exitosa|
      | " " |fallida  |

  Scenario Outline: 03 Realizar registro de login
    Given Que ingreso a la web de practica
    When Ingreso al menu Sign in
    Then ingreso el <email> para registrarme
    And completo el formulario de registro

    Examples:
      |email|
      |calvarez@clmconsultores.com     |