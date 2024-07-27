# ForoHub API 
ForoHub es una API RESTful para gestionar un foro, donde los usuarios pueden registrarse y crear temas (tópicos). La API está construida usando Spring Boot y asegura las operaciones con Spring Security. Este proyecto forma parte del programa Oracle Next Education


## Características 
- Registro de usuarios 
-  Autenticación de usuarios 
-  Creación, actualización, eliminación y listado de tópicos 
-  Seguridad basada en tokens JWT 
-  Validación de datos
## Requisitos

-   Java 17+
-   Spring Boot 3+
-   Base de datos MySQL 8+
## Estructura del Proyecto
El proyecto está organizado en las siguientes clases:
### **`controller`** 
- **`AutenticacionController.java`**: Controlador que maneja la autenticación de usuarios (inicio de sesión). 
- **`TopicoContorller.java`**: Controlador que gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para los tópicos.
-  **`UsuarioController.java`**: Controlador para gestionar el registro de usuarios.

### **`topico`** 
- **`DatosActualizarTopico.java`**: DTO (Data Transfer Object) para actualizar la información de un tópico. 
- **`DatosListadoTopico.java`**: DTO para listar los datos de un tópico. 
- **`DatosRegistroTopico.java`**: DTO para registrar un nuevo tópico.
-  **`Topico.java`**: Entidad JPA que representa un tópico en la base de datos. 
- **`TopicoRepository.java`**: Interfaz para el repositorio de tópicos, que maneja la persistencia de los datos de tópicos.
 
### **`usuario`**
- **`DatosAutenticacionUsuario.java`**: DTO para la autenticación de usuarios (inicio de sesión). 
- **`DatosListadoUsuario.java`**: DTO para listar los datos de un usuario. 
- **`Usuario.java`**: Entidad JPA que representa a un usuario en la base de datos. 
- **`UsuarioRepository.java`**: Interfaz para el repositorio de usuarios, que maneja la persistencia de los datos de usuarios.

### **`errores`** 
- **`TratadorDeErrores.java`**: Maneja y personaliza el tratamiento de errores globales en la aplicación. 

### **`security`** 
- **`AutenticacionService.java`**: Implementa `UserDetailsService` para cargar los detalles del usuario durante la autenticación. 
- **`DatosJWTToken.java`**: DTO que encapsula el token JWT
- **`SecurityConfigurations.java`**: Configuración de seguridad para la aplicación, incluyendo la configuración de filtros de seguridad y la gestión del cifrado de contraseñas. 
- **`SecurityFilter.java`**: Filtro de seguridad personalizado para manejar la autenticación y autorización basada en JWT. 
- **`TokenService.java`**: Servicio para crear y validar tokens JWT.
## Configuración

1. **Clonar el repositorio**:

    ```sh
    git clone https://github.com/BrandonSdvl/ForoHub.git
    cd ForoHub
    ```

2. **Configura las variables de entorno para la base de datos:**

    En linux o macOS:
    ```bash
    export DB_HOST=tu-host
    export DB_NAME=foro_hub
    export DB_USER=tu-usuario
    export DB_PASSWORD=tu-contraseña
    ```
    
    En windows
    ```bash
    set DB_HOST=tu-host
    set DB_NAME=foro_hub
    set DB_USER=tu-usuario
    set DB_PASSWORD=tu-contraseña
    ```
    
    O puedes modificar el archivo `application.yml`
    ```yaml
    datasource: 
	    url: jdbc:mysql://tu-host/foro_hub 
	    username: tu-usuario
	    password: tu-contraseña
    ```

## Endpoints
### Registro de Usuarios

-   **POST** `/registrar`: Registrar un nuevo usuario
    -   Request Body:
	```json
	{ 
		"nombre": "string", 
		"clave": "string" 
	}
	```
### Autenticación

-   **POST** `/login`: Autenticar un usuario
	- Request Body:
	```json
	{ 
		"nombre": "string", 
		"clave": "string" 
	}
	```
	- Response: La respuesta incluirá el token JWT que debe ser usado para autenticar las solicitudes subsecuentes.
	```json
	{ 
		"token": "string" 
	}
	```

### Gestión de Tópicos

Una vez que tienes el token JWT, debes incluirlo en el encabezado `Authorization` de las solicitudes a los endpoints protegidos.

-   **POST** `/topicos`: Crear un nuevo tópico
    
    -   Request Body:
	```json
	{ 
		"titulo": "string", 
		"mensaje": "string", 
		"curso": "string"
	}
	```
- **GET** `/topicos`: Listar tópicos (con paginación)

	-   Response:
	```json
	[ 
		{ 
			"id": "number", 
			"titulo": "string", 
			"mensaje": "string", 
			"fecha": "string", 
			"estado": "string", 
			"autor": "string"
			"curso": "string", 
		 } 
	 ]
	```
-   **GET** `/topicos/{id}`: Obtener un tópico por ID

    
-   **PUT** `/topicos/{id}`: Actualizar un tópico existente
    
-   **DELETE** `/topicos/{id}`: Eliminar un tópico por ID

### Restricciones del Token JWT
-   **Expiración del Token:** El token JWT expira dos horas después de su creación. Después de que el token expire, deberás iniciar sesión nuevamente para obtener un nuevo token.
-   **Endpoints Públicos:** Los endpoints `/login` y `/registrar` no requieren autenticación y no necesitan el token JWT.
-   **Endpoints Protegidos:** Todos los demás endpoints requieren que el token JWT esté presente en el encabezado `Authorization`.
-   **Secret:** El secret utilizado para firmar y verificar los tokens JWT se define en el archivo `application.yml`. Debes establecer el valor de `api.security.secret` para asegurar la integridad y la seguridad de los tokens. 
	```yaml
    api:
      security:
      secret: ${JWT_SECRET:123456}
	 ```
	    



