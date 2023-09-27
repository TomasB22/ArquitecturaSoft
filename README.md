Gestión de Clientes Morosos
¡Bienvenido a la aplicación de Gestión de Clientes Morosos! Esta es una aplicación web que te permite gestionar clientes y sus deudas. Puedes ver la lista de clientes, agregar nuevos clientes, editar información existente y eliminar clientes.

1.Contenido
2.Instalación
3.Ejecución del Backend
4.Ejecución del Frontend
5.Uso de la Aplicación
6.Instalación
Antes de poder ejecutar la aplicación, debes asegurarte de tener Node.js y Angular CLI instalados en tu sistema. También, necesitarás Java y Spring Boot para el backend.

Clona este repositorio:

bash
Copy code
git clone https://github.com/tuusuario/gestion-clientes-morosos.git
Navega a la carpeta del proyecto:

bash
Copy code
cd gestion-clientes-morosos
Ejecución del Backend
El backend de la aplicación está construido con Spring Boot y Java. Sigue estos pasos para ejecutarlo:

Abre una terminal y navega a la carpeta backend:

bash
Copy code
cd backend
Ejecuta la aplicación Spring Boot:

bash
Copy code
./mvnw spring-boot:run
El backend estará disponible en http://localhost:8080.

Ejecución del Frontend
El frontend de la aplicación está construido con Angular. Sigue estos pasos para ejecutarlo:

Abre otra terminal y navega a la carpeta frontend:

bash
Copy code
cd frontend
Instala las dependencias:

bash
Copy code
npm install
Inicia la aplicación Angular:

bash
Copy code
ng serve
El frontend estará disponible en http://localhost:4200.

Uso de la Aplicación
Una vez que ambos el backend y el frontend estén en funcionamiento, puedes acceder a la aplicación en tu navegador web:

Backend: http://localhost:8080
Frontend: http://localhost:4200
La aplicación te permitirá ver, agregar, editar y eliminar clientes y sus deudas.
