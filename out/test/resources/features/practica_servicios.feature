#language: es

Característica: Yo como automatizador
  Quiero probar los servicios rest
  Para garantizar su correcto funcionamiento

  Escenario: Escenario: Automatizar servicio Post del registro de usuarios
    Cuando Carlos hace el registro de un usuario con las características:
      | Email    | eve.holt@reqres.in |
      | Password | pistol             |
    Entonces el registro se realiza de manera correcta con un id '4' y con token 'QpwL5tke4Pnpja7X4'



