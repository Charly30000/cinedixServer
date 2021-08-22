# Proyecto fin de grado DAM

## Descripcion del proyecto
Proyecto de fin de grado de Desarrollo de aplicaciones multiplataforma.
El proyecto consiste en dos aplicaciones:
- La primera parte ([CinedixServer](https://github.com/Charly30000/cinedixServer)) consiste en una API REST, la cual será la parte principal de la aplicacion. Se realizará aqui la conexion con la base de datos y la generacion automatica de todas las tablas necesarias (datos de prueba si tambien se desean). Funciona con JWT. Esta mayormente orientada a la interaccion con los clientes para la aplicacion en Android (no es necesario recibir la informacion desde un dispositivo Android, se pueden personalizar las peticiones). Puedes verla [pinchando aqui](https://github.com/Charly30000/cinedixServer).

- La segunda parte ([CinedixServerAdmin](https://github.com/Charly30000/cinedixServerAdmin)) consiste en una aplicacion web orientada a la gestion de la aplicacion del cine (pensada para los administradores del sistema). Esta aplicación se maneja utilizando sesiones. Puedes verla [pinchando aqui](https://github.com/Charly30000/cinedixServerAdmin).

- La tercera parte ([Cinedix](https://github.com/Charly30000/Cinedix)) consiste en una aplicación hecha en Android para la comunicación con el cliente, poder realizar compras desde esta y navegar entre diferentes menus y realizar distintas interacciones. Puedes verla [pinchando aqui](https://github.com/Charly30000/Cinedix).

- La cuarta parte ([pasarela-simulacion-master](https://github.com/Charly30000/pasarela-simulacion-master)) consiste en una pasarela de pago. Es completamente una simulación realizada en PHP, la cual consiste en la simulación de un banco, para reconocer si se ha realizado algún tipo de compra.

### CinedixServer (microservicios)
#### Post
**/api/login** → recibir token de autenticación 
```json
{
   "username": "string",
   "password": "string"
}
```
**/api/clientes/entradas/crear** →Crear entrada en el servidor
```json
{
   "sesionPelicula": integer,
   "sitiosOcupados": integer[]
}
```
**/api/clientes/usuario/crear** → Crear un nuevo usuario
```json
{
   "username": "string",
   "password": "string",
   "email": "string"
}
```
#### Put
**/api/clientes/usuario/modificar** → Modificar un usuario existente
```json
{
   "username": "string",
   "newPassword": "string",
   "oldPassword": "string",
   "email": "string"
}
```

#### Get
- **/api/clientes/usuario** → obtener información sobre el usuario logueado
- **/api/clientes/entradas** → obtener las entradas del usuario logueado
- **/api/clientes/peliculas/venta** → obtener las películas en venta (películas en cartelera)
- **/api/clientes/peliculas/estrenos** → obtener las películas por estrenar (películas en estrenos)
- **/api/clientes/peliculas/{id}** → obtener una película
  - **{id}** → id de la película
- **/api/clientes/entradas/sitios-ocupados** → obtiene todos los sitios ocupados
- **/api/sesionpelicula/cines/{id}** → obtiene las sesiones de una película según la película
  - **{id}** → id de la película
- **/api/sesionpelicula/{cineid}/{peliculaid}** → obtiene las sesiones de una película según el cine y la película
  - **{cineid}** →id del cine
  - **{peliculaid}** →id de la película