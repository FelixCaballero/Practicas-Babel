# Portal del Paciente - Full-Stack App

Este proyecto es una aplicación web Full-Stack diseñada para gestionar el "Portal del Paciente". Está compuesto por un backend robusto construido con Java y un framework Spring Boot, y un frontend moderno y reactivo desarrollado en TypeScript usando Angular.

## 🚀 Tecnologías Utilizadas

### Backend (Carpeta `spring/`)
* **Java**
* **Spring Boot**: Framework principal para el desarrollo de la API REST.
* **Spring Data JPA**: Para la persistencia de datos y comunicación con la base de datos.
* **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.

### Frontend (Carpeta `frontend-babel/`)
* **TypeScript**
* **Angular 17+**: Framework para la construcción de interfaces de usuario.
* **RxJS**: Para el manejo de datos asíncronos mediante Observables.
* **HTML/CSS**: Para la estructura y estilización visual de los componentes.

## 📁 Estructura del Proyecto

La solución se divide en dos módulos independientes y claramente separados:

### 1. Backend (`/angular/src/main/java/com/babel/crudfullstack/angular/`)
Provee una API RESTful para realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar) sobre las entidades del sistema y validar la seguridad mediante login.
* **Modelos (`model/`)**: Define la estructura y mapeo de las entidades de la base de datos (`Usuario`, `Menu`, `Pagina`).
* **Repositorios (`repository/`)**: Interfaces de Spring Data para automatizar las consultas y persistencia.
* **Controladores (`controller/`)**: Exponen los endpoints HTTP para que sea consumido por el frontend (ej. `/api/menus`, `/api/paginas`).

### 2. Frontend (`/frontend-babel/src/app/`)
Es la capa de presentación que interactúa directamente del lado del navegador del usuario.
* **Modelos (`models/`)**: Tipado estático (interfaces) de la información (`menu.ts`, `pagina.ts`, `usuario.ts`) en espejo a las clases de Java.
* **Servicios (`services/`)**: 
  * `portal.service.ts`: Interactúa con el backend para recuperar, crear, editar o borrar información de menús y páginas del portal.
  * `auth.service.ts`: Lógica de autenticación enviando las credenciales al servidor para el acceso.
* **Componentes Visuales**: 
  * `Login` (`/login`): Pantalla de acceso que valida la información contra base de datos.
  * `Dashboard` (`/dashboard`): Panel de control y administración para que los gestores operen sobre páginas y menús.

## ⚙️ Configuración y Ejecución

### Requisitos Previos
* Java JDK instalado.
* Maven instalado (opcional, ya que cuenta con `mvnw` wrapper).
* Node.js y npm para ejecutar el motor del frontend.
* Base de Datos (MySQL, PostgreSQL, u otra requerida) levantada u operativa según la definición del backend.

### Levantar el Backend (Spring Boot)
1. Abre un terminal y dirígete al directorio de la API:
   ```bash
   cd angular
   ```
2. Inicia el servidor usando el wrapper de Maven:
   ```bash
   # En Windows:
   mvnw.cmd spring-boot:run
   ```
   *La API quedará expuesta en `http://localhost:8080` de manera predeterminada.*

### Levantar el Frontend (Angular)
1. Abre un nuevo terminal y navega hasta:
   ```bash
   cd frontend-babel
   ```
2. Instala las dependencias declaradas si es la primera vez que se descarga el proyecto:
   ```bash
   npm install
   ```
3. Ejecuta el entorno de desarrollo dinámico de Angular:
   ```bash
   npm run start
   ```
   *La página web será accesible en tu navegador abriendo la URL `http://localhost:4200`.*
