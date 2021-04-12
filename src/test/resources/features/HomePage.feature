@tag_feature
Feature: Validar modificacion de menu en pagina web
  Con el fin de poder validar los cambios en el menu de la pagina web
  Yo como cliente quiero poder revisar los menus desplegados
  Para poder seleccionarlos y verificar su contenido

  @Inicia_tag
  Scenario Outline: 01 Verificar que muestre los menus
    Given Que ingreso a la web de practica
    When Muestre la pagina inicial
    Then Verifico que muestre <menu>
    And Verifico que al realizar un click me derive a la pagina <menu>

    Examples:
      |menu|
      |Women     |
      |Dress  |
      |Tshirts   |