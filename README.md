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
| GET | `/api/tasks` | Lista tareas con paginación, filtros y orden |
| GET | `/api/tasks/{id}` | Obtiene una tarea por ID |
| POST | `/api/tasks` | Crea una tarea |
| PUT | `/api/tasks/{id}` | Actualiza una tarea existente |
| DELETE | `/api/tasks/{id}` | Elimina una tarea |

### Paginación, filtros y orden
Parámetros opcionales en `GET /api/tasks`:
- `page` (default `0`)
- `size` (default `10`, máximo `100`)
- `completed` (`true`/`false`)
- `title` (búsqueda parcial, ignorando mayúsculas/minúsculas)
- `sortBy` (default `id`)
- `direction` (`asc` o `desc`, default `asc`)

Ejemplo:
```bash
GET /api/tasks?completed=true&title=spring&page=0&size=5&sortBy=title&direction=desc
```

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

## Ejecutar con Docker Compose
1. Construye y levanta el contenedor:
```bash
docker compose up --build
```

2. Verifica la API:
```bash
http://localhost:8080/swagger-ui/index.html
```

3. Detén el servicio:
```bash
docker compose down
```

## Deploy paso a paso en Render
1. Sube este repositorio a GitHub con `Dockerfile` y `docker-compose.yml`.
2. En Render, crea un nuevo servicio: **New +** → **Web Service**.
3. Conecta tu repositorio `task-manager-api`.
4. En configuración:
   - **Environment**: `Docker`
   - **Branch**: `main` (o tu rama de despliegue)
   - **Region**: la más cercana
5. En variables de entorno agrega:
   - `PORT=8080`
6. Guarda y presiona **Create Web Service**.
7. Cuando finalice el build, abre la URL pública y prueba:
   - `/swagger-ui/index.html`

## Deploy paso a paso en Railway
1. En Railway, crea un proyecto nuevo con **Deploy from GitHub repo**.
2. Selecciona este repositorio.
3. Railway detectará el `Dockerfile` automáticamente.
4. En variables agrega:
   - `PORT=8080`
5. Espera el build y el deploy automático.
6. Abre el dominio generado por Railway y prueba:
   - `/swagger-ui/index.html`

## Deploy local con Docker (alternativa)
Construir imagen manual:
```bash
docker build -t task-manager-api .
```

Ejecutar contenedor manual:
```bash
docker run -p 8080:8080 task-manager-api
```

El proyecto ya está preparado para plataformas como Render o Railway usando `PORT` dinámico (`server.port=${PORT:8080}`) y `Dockerfile` en la raíz.
