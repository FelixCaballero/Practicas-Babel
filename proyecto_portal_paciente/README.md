# Practicas-Babel

Repositorio con una aplicacion completa formada por:

- `portal-backoffice`: frontend en Angular para gestionar menus y paginas.
- `portal_paciente`: backend en Spring Boot que expone la API REST y se conecta a MySQL.

## Funcionalidad del proyecto

El proyecto implementa un pequeño portal de administracion con autenticacion basica y operaciones CRUD sobre la estructura de navegacion y el contenido.

### Funcionalidades principales

- Inicio de sesion contra el backend mediante usuario y password.
- Alta, consulta, modificacion y eliminacion de menus.
- Alta, consulta, modificacion y eliminacion de paginas.
- Busqueda de menus por identificador.
- Busqueda de paginas por identificador o descripcion.
- Navegacion guiada de menus por niveles e hijo/padre.
- Persistencia de datos en MySQL.

## Tecnologias utilizadas

### Frontend

- Angular 21
- TypeScript
- Bootstrap 5
- RxJS

### Backend

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- Spring Security
- MySQL
- Maven

## Estructura del repositorio

```text
Practicas-Babel/
|-- README.md
|-- portal-backoffice/
|   |-- src/
|   |   |-- app/
|   |   |   |-- components/
|   |   |   |   |-- auth/login/
|   |   |   |   |-- consulta-menu/
|   |   |   |   |-- consulta-pagina/
|   |   |   |   |-- guiadas/
|   |   |   |   |-- layout/
|   |   |   |   |-- menu-alta/
|   |   |   |   |-- menu-form/
|   |   |   |   |-- pagina-alta/
|   |   |   |   `-- pagina-form/
|   |   |   |-- services/
|   |   |   |-- app.routes.ts
|   |   |   `-- app.config.ts
|   |   |-- index.html
|   |   `-- styles.css
|   |-- package.json
|   `-- angular.json
`-- portal_paciente/
    |-- src/
    |   |-- main/
    |   |   |-- java/com/example/portal_paciente/
    |   |   |   |-- Configuration/
    |   |   |   |-- controller/
    |   |   |   |-- DTO/
    |   |   |   |-- model/
    |   |   |   |-- repository/
    |   |   |   `-- service/
    |   |   `-- resources/
    |   |       `-- application.properties
    |   `-- test/
    `-- pom.xml
```

## Arquitectura general

### `portal-backoffice`

Aplicacion Angular que consume la API en `http://localhost:8080/api/portal`. Incluye:

- Pantalla de login.
- Layout principal con rutas hijas.
- Formularios para crear y editar menus.
- Formularios para crear y editar paginas.
- Pantallas de consulta para buscar y eliminar registros.
- Vista guiada para recorrer menus por niveles.

### `portal_paciente`

Aplicacion Spring Boot que expone endpoints REST bajo:

```text
http://localhost:8080/api/portal
```

Controladores principales:

- `/usuario`: gestion de usuarios y login.
- `/menu`: CRUD y consultas de menus.
- `/pagina`: CRUD y consultas de paginas.

## Requisitos previos

Antes de ejecutar el proyecto necesitas:

- Node.js y npm instalados.
- Angular CLI disponible con `npx` o instalado globalmente.
- Java 21.
- Maven 3.9+ o usar el wrapper incluido (`mvnw` / `mvnw.cmd`).
- MySQL en ejecucion.

## Configuracion de base de datos

El backend esta configurado para conectarse a esta base de datos:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/portal_paciente
spring.datasource.username=root
spring.datasource.password=practicas
spring.jpa.hibernate.ddl-auto=validate
```

Esto implica que:

- Debe existir una base de datos llamada `portal_paciente`.
- Las tablas deben existir previamente, porque `ddl-auto=validate` solo valida el esquema y no lo crea.
- Debes ajustar usuario y password en `portal_paciente/src/main/resources/application.properties` si tu entorno es diferente.

## Instalacion

### 1. Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd Practicas-Babel
```

### 2. Instalar dependencias del frontend

```bash
cd portal-backoffice
npm install
```

### 3. Verificar la configuracion del backend

Revisa el archivo:

```text
portal_paciente/src/main/resources/application.properties
```

y confirma que las credenciales y la base de datos de MySQL coinciden con tu entorno.

### 4. Compilar dependencias del backend

Desde la carpeta `portal_paciente` puedes usar Maven instalado o el wrapper:

```bash
cd portal_paciente
./mvnw clean install
```

En Windows:

```bash
mvnw.cmd clean install
```

## Ejecucion del proyecto

Para que el sistema funcione correctamente, primero levanta el backend y despues el frontend.

### Levantar el backend

Desde `portal_paciente`:

```bash
./mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

El backend quedara disponible en:

```text
http://localhost:8080
```

### Levantar el frontend

Desde `portal-backoffice`:

```bash
npm start
```

o bien:

```bash
ng serve
```

El frontend quedara disponible en:

```text
http://localhost:4200
```

## Flujo de uso

1. Inicia el backend Spring Boot.
2. Inicia el frontend Angular.
3. Accede a `http://localhost:4200`.
4. Inicia sesion con un usuario existente en la tabla `portal_usuario`.
5. Gestiona menus, paginas y navegacion guiada desde el backoffice.

## Endpoints principales

### Usuarios

- `POST /api/portal/usuario/create`
- `GET /api/portal/usuario`
- `GET /api/portal/usuario/{id}`
- `PUT /api/portal/usuario/update`
- `DELETE /api/portal/usuario/delete/{id}`
- `POST /api/portal/usuario/login`

### Menus

- `POST /api/portal/menu/create`
- `GET /api/portal/menu`
- `GET /api/portal/menu/{id}`
- `PUT /api/portal/menu/update/{id}`
- `DELETE /api/portal/menu/delete/{id}`
- `GET /api/portal/menu/search?filtro={valor}`
- `GET /api/portal/menu/nivel/{nivel}`
- `GET /api/portal/menu/padre/{padre}`

### Paginas

- `POST /api/portal/pagina/create`
- `GET /api/portal/pagina`
- `GET /api/portal/pagina/{id}`
- `PUT /api/portal/pagina/update/{id}`
- `DELETE /api/portal/pagina/delete/{id}`
- `GET /api/portal/pagina/search/{filtro}`

## Notas importantes

- El frontend espera que el backend este disponible en `localhost:8080`.
- El backend permite peticiones CORS desde `http://localhost:4200`.
- La autenticacion actual valida usuario y password, pero la API esta abierta en configuracion de seguridad y no usa JWT ni sesion avanzada.
- En el repositorio hay carpetas generadas como `node_modules`, `target` y `.angular/cache`; no forman parte de la logica del proyecto.

## Posibles mejoras

- Añadir scripts SQL de creacion e insercion de datos iniciales.
- Proteger la API con autenticacion real y control de autorizacion.
- Incorporar tests funcionales para frontend y backend.
- Externalizar la configuracion con variables de entorno.
