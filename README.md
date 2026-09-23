# Task Manager API

API REST para gestión de tareas desarrollada con **Java 17** y **Spring Boot 3.2.5**.

## Tecnologías
- Spring Web
- Spring Data JPA
- Spring Validation
- H2 Database
- Swagger / OpenAPI (`springdoc`)
- JUnit + MockMvc

## Estructura
- `controller/`: endpoints REST
- `service/`: lógica de negocio
- `repository/`: acceso a datos
- `model/`: entidad `Task`
- `exception/`: manejo global de errores

## Endpoints
Base path: `/api/tasks`

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/tasks` | Lista todas las tareas |
| GET | `/api/tasks/{id}` | Obtiene una tarea por ID |
| POST | `/api/tasks` | Crea una tarea |
| PUT | `/api/tasks/{id}` | Actualiza una tarea existente |
| DELETE | `/api/tasks/{id}` | Elimina una tarea |

## Payload JSON
```json
{
  "title": "Aprender Spring Boot",
  "description": "Completar API para portafolio",
  "completed": false
}
```

## Validaciones
- `title` obligatorio
- `title` máximo 100 caracteres
- `description` máximo 300 caracteres

## Respuestas de error
- `404 Not Found` cuando la tarea no existe
- `400 Bad Request` cuando falla validación

## Ejecutar local
```bash
mvn clean spring-boot:run
```

Swagger UI:
- `http://localhost:8080/swagger-ui/index.html`

H2 Console:
- `http://localhost:8080/h2-console`

## Ejecutar pruebas
```bash
mvn test
```
