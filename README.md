# Gestión de Clientes Morosos

¡Bienvenido a la aplicación de Gestión de Clientes Morosos! Esta es una aplicación web que te permite gestionar clientes y sus deudas. Puedes ver la lista de clientes, agregar nuevos clientes, editar información existente y eliminar clientes.

## Contenido

1. [Instalación](#instalación)
2. [Ejecución del Backend](#ejecución-del-backend)
3. [Ejecución del Frontend](#ejecución-del-frontend)
4. [Uso de la Aplicación](#uso-de-la-aplicación)

## Instalación

Antes de poder ejecutar la aplicación, debes asegurarte de tener Node.js y Angular CLI instalados en tu sistema. También, necesitarás Java y Spring Boot para el backend.

1. **Clona este repositorio:**

  ```bash
  git clone https://github.com/tuusuario/gestion-clientes-morosos.git
  ```

   
2. **Navega a la carpeta del proyecto:**

  ```bash
  cd gestion-clientes-morosos
  ```
## ejecucion del backend
- El backend de la aplicación está construido con Spring Boot y Java. Sigue estos pasos para ejecutarlo:

Abre una terminal y navega a la carpeta backend:

  ```bash
  cd backend
  ```
1. Ejecuta la aplicación Spring Boot:

```bash
./mvnw spring-boot:run
```
El backend estará disponible en http://localhost:8080.

Puedes acceder al h2 de la aplicación en la url http://localhost:8080/h2-console con las credenciales especificadas en application.properties del backend.

## Ejecución del Frontend
El frontend de la aplicación está construido con Angular. Sigue estos pasos para ejecutarlo:

Abre otra terminal y navega a la carpeta frontend:

```bash
cd frontend
```
Instala las dependencias:

```bash
npm install
```
Inicia la aplicación Angular:

```bash
ng serve
```
El frontend estará disponible en http://localhost:4200.

Uso de la Aplicación
Una vez que ambos el backend y el frontend estén en funcionamiento, puedes acceder a la aplicación en tu navegador web:

Backend: http://localhost:8080
Frontend: http://localhost:4200

## Uso de la Aplicación
-  La aplicación te permitirá ver, agregar, editar y eliminar clientes y sus deudas.
-  Hay un Preload en el backend para poder cargar datos previo al uso.
-  El agregar un cliente podrás realizarlo vía Postman ya que no existe botón ni ventana de agregar. 
