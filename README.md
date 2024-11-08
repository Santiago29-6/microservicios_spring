# 🛠️ Proyecto de Microservicios en Spring Boot (v3.3.5-SNAPSHOT) 🛠️

Este proyecto implementa una arquitectura de microservicios con Spring Boot, donde se manejan distintas bases de datos y se gestionan mediante un API Gateway y un servidor Eureka para el descubrimiento de servicios. Los microservicios están configurados en contenedores Docker.

## 📝 Tabla de Contenidos

1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Arquitectura de Microservicios](#arquitectura-de-microservicios)
3. [Requisitos](#requisitos)
4. [Instalación y Configuración](#instalación-y-configuración)
5. [Uso](#uso)
6. [Tecnologías Usadas](#tecnologías-usadas)
7. [Contribución](#contribución)
8. [Licencia](#licencia)

## 📝Descripción del Proyecto

Este sistema se divide en 4 microservicios que gestionan la información de inmuebles y compras, realizan autenticación de usuarios con tokens JWT, y usan el servidor Eureka para el descubrimiento de servicios. La arquitectura se basa en Spring Boot v3.3.5-SNAPSHOT.

## Arquitectura de Microservicios

- **Servicio de Inmuebles**: Gestiona la información de inmuebles. Utiliza una base de datos MySQL v8.4 para almacenamiento.
- **Servicio de Compras**: Almacena temporalmente los detalles de compra de inmuebles. Utiliza H2 como base de datos en memoria.
- **API Gateway**: Intermediario entre los clientes y los servicios, para consultar, almacenar y eliminar información. Gestiona los usuarios en una base de datos PostgreSQL y maneja autenticación con JWT para controlar permisos de acceso (roles `admin` y `user`).
- **Servidor Eureka**: Actúa como servidor de descubrimiento para acceder a los servicios de forma dinámica y facilitar la escalabilidad.

## 📝Requisitos

- **Java** 17 o superior
- **Spring Boot** 3.3.5-SNAPSHOT
- **Docker**
- **MySQL** 8.4 (contenedor Docker)
- **PostgreSQL** 14.4 (contenedor Docker)
- **H2** en memoria para almacenamiento temporal
- **JWT** para autenticación

## ⬇️Instalación y Configuración

1. **Clona el repositorio**:
   ```bash
   git clone -b master https://github.com/tu-usuario/nombre-del-repo.git
2. **Configura las bases de datos**

   - **Configuración de MySQL**: 
     - No es necesario crear la base de datos de MySQL manualmente, ya que el programa la creará junto con la tabla si no existen. Sin embargo, sí es necesario crear el usuario `admin` para el acceso.
     - Accede al contenedor de MySQL con el siguiente comando:
       ```bash
       docker exec -it mysql-x mysql -u root -p
       ```
     - Cuando se te solicite, ingresa la contraseña de `root`. Luego, ejecuta los siguientes comandos en la consola de MySQL para crear el usuario `admin` con la contraseña que prefieras:
       ```sql
       CREATE USER 'admin'@'%' IDENTIFIED BY 'tu_password';
       GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%' WITH GRANT OPTION;
       FLUSH PRIVILEGES;
       ```
       - Reemplaza `'tu_password'` con la contraseña deseada para el usuario `admin`.
       - `'%'` permite que `admin` se conecte desde cualquier host. Si deseas restringir el acceso, puedes cambiar `%` a `localhost`.

   - **Configuración de PostgreSQL**:
     - Accede al contenedor de PostgreSQL:
       ```bash
       docker exec -it postgres-x bash
       ```
     - Luego, abre la consola de PostgreSQL:
       ```bash
       psql -U postgres -d postgres
       ```
     - Crea el usuario `admin` y asígnale privilegios completos:
       ```sql
       CREATE ROLE admin WITH
       LOGIN
       SUPERUSER
       CREATEDB
       CREATEROLE
       INHERIT
       REPLICATION
       CONNECTION LIMIT -1
       PASSWORD 'tu_password';
       ```
       - Reemplaza `'tu_password'` con la contraseña deseada para el usuario `admin`.
     - Finalmente, crea la base de datos `db_inmueble` y asígnala al usuario `admin`:
       ```sql
       CREATE DATABASE db_inmueble
       WITH
       OWNER = admin
       ENCODING = 'UTF8'
       CONNECTION LIMIT = -1;
       ```
     - Sal de la consola de PostgreSQL ejecutando `\q` y luego `exit` para salir del contenedor.
## ⏯️Uso

Este proyecto incluye varios endpoints a los que se puede acceder usando Postman. A continuación se describe cómo realizar cada solicitud.

### Autenticación y Manejo de Usuarios

1. **Registro de Usuario**  
   - **Método**: POST  
   - **Endpoint**: `http://localhost:5555/api/authentication/sign-up`  
   - **Body (JSON)**:
     ```json
     {
       "nombre": "Santiago",
       "username": "santi",
       "password": "Pass123$COL"
     }
     ```
   - **Descripción**: Registra un nuevo usuario en el sistema.

2. **Inicio de Sesión**  
   - **Método**: POST  
   - **Endpoint**: `http://localhost:5555/api/authentication/sign-in`  
   - **Body (JSON)**:
     ```json
     {
       "username": "santi",
       "password": "Pass123$COL"
     }
     ```
   - **Descripción**: Inicia sesión para obtener un token JWT. Este token es necesario para realizar solicitudes que requieren autenticación.

3. **Cambiar Rol de Usuario a ADMIN**  
   - **Método**: PUT  
   - **Endpoint**: `http://localhost:5555/api/user/change/ADMIN`  
   - **Headers**:  
     - `Authorization`: Bearer {token} (Usa el token de la respuesta de sign-in)
   - **Descripción**: Cambia el rol del usuario autenticado a `ADMIN`.

4. **Obtener Información de Usuarios**  
   - **Método**: GET  
   - **Endpoint**: `http://localhost:5555/api/user`  
   - **Headers**:  
     - `Authorization`: Bearer {token}  
   - **Descripción**: Recupera la información de los usuarios registrados en el sistema.

### Gestión de Inmuebles

1. **Listar Inmuebles**  
   - **Método**: GET  
   - **Endpoint**: `http://localhost:5555/gateway/inmueble`  
   - **Headers**:  
     - `Authorization`: Bearer {token}  
   - **Descripción**: Obtiene una lista de todos los inmuebles registrados en el sistema.

2. **Crear Inmueble**  
   - **Método**: POST  
   - **Endpoint**: `http://localhost:5555/gateway/inmueble`  
   - **Headers**:  
     - `Authorization`: Bearer {token}  
   - **Body (JSON)**:
     ```json
     {
       "nombre": "Casa de Marbella",
       "direccion": "Av Las Gardenias",
       "precio": 80000
     }
     ```
   - **Descripción**: Crea un nuevo registro de inmueble con los datos proporcionados.

3. **Eliminar Inmueble**  
   - **Método**: DELETE  
   - **Endpoint**: `http://localhost:5555/gateway/inmueble/{id}`  
   - **Headers**:  
     - `Authorization`: Bearer {token}  
   - **Descripción**: Elimina un inmueble identificado por su `{id}`.

### Gestión de Compras

1. **Listar Compras**  
   - **Método**: GET  
   - **Endpoint**: `http://localhost:5555/gateway/compra`  
   - **Headers**:  
     - `Authorization`: Bearer {token}  
   - **Descripción**: Recupera la lista de todas las compras realizadas.

### Monitoreo de Servicios

1. **Verificar Estado del Servidor Eureka**  
   - **Método**: GET  
   - **Endpoint**: `http://localhost:6666`  
   - **Descripción**: Verifica el estado del servidor Eureka y los microservicios registrados.
> [!IMPORTANT]
> En cada solicitud que requiera autorización, debes incluir el token JWT obtenido en el inicio de sesión en los encabezados con el formato `Authorization: Bearer {token}`.

> [!IMPORTANT]
> Asegúrate de usar el token correcto según el rol, ya que algunas acciones están restringidas a usuarios con rol `ADMIN`.

## 🖥️Tecnologías Usadas

Este proyecto se construye utilizando una serie de tecnologías modernas que permiten la creación, gestión y despliegue de microservicios de forma eficiente:

- **Spring Boot (3.3.5-SNAPSHOT)**: Framework de Java que facilita el desarrollo de aplicaciones backend con configuraciones mínimas. Proporciona soporte para construir microservicios y permite una integración sencilla con otras tecnologías.

- **Docker y Docker Compose**: Docker se utiliza para contenerizar los servicios y sus bases de datos, asegurando que cada servicio pueda ejecutarse de forma aislada y simplificada. Docker Compose facilita el despliegue y la orquestación de múltiples contenedores, como los servicios de MySQL, PostgreSQL y los microservicios de Spring Boot, en un solo archivo de configuración.

- **MySQL 8.4**: Base de datos relacional utilizada para almacenar información de inmuebles en el servicio correspondiente. MySQL permite el almacenamiento persistente y consulta eficiente de datos estructurados.

- **PostgreSQL 14.4**: Base de datos relacional que se utiliza en el API Gateway para gestionar y almacenar la información de los usuarios registrados. PostgreSQL se elige por su robustez en la gestión de datos y su compatibilidad con consultas avanzadas.

- **H2 en memoria**: Base de datos en memoria usada en el servicio de compras para almacenar datos temporales. H2 facilita la manipulación rápida de datos volátiles, útil para entornos de prueba y operaciones ligeras.

- **JWT (JSON Web Token)**: Mecanismo de autenticación que genera un token seguro para autenticar a los usuarios. JWT se utiliza para asegurar las solicitudes, proporcionando un token que controla el acceso a los endpoints en función del rol del usuario (admin o user).

- **Eureka**: Servidor de descubrimiento de servicios que permite la comunicación entre microservicios de forma dinámica. Eureka facilita la escalabilidad y robustez del sistema, permitiendo a los servicios registrarse y descubrirse automáticamente en una arquitectura distribuida.
