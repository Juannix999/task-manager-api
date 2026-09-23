# 🚀 Task Manager API - Backend con Spring Boot

API RESTful desarrollada en **Java 17** y **Spring Boot 3.2.5** para la gestión completa de tareas. Este proyecto forma parte de mi portafolio profesional como Analista Programador, enfocado en buenas prácticas de arquitectura backend, persistencia de datos y documentación de servicios.

---

## 🛠️ Tecnologías y Dependencias
* **Java 17**
* **Spring Boot 3.2.5**
* **Spring Data JPA** (para la capa de persistencia y abstracción de base de datos)
* **H2 Database** (Base de datos relacional en memoria para desarrollo y pruebas rápidas)
* **Springdoc OpenAPI / Swagger** (Documentación interactiva de la API)
* **Maven** (Gestión de dependencias y empaquetado)

---

## 📂 Estructura del Proyecto
El código sigue una arquitectura estándar organizada en capas bajo el paquete `com.portfolio.taskmanager`:
- **`model/`**: Entidades JPA (`Task.java`) mapeadas a la base de datos con anotaciones OpenAPI.
- **`repository/`**: Interfaces de acceso a datos que extienden de `JpaRepository` (`TaskRepository.java`).
- **`controller/`**: Controladores REST que exponen los endpoints del CRUD con anotaciones informativas (`TaskController.java`).

---

## ⚙️ Endpoints de la API (CRUD)

La API expone los siguientes endpoints bajo la ruta base `/api/tasks`:

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| **GET** | `/api/tasks` | Retorna una lista con todas las tareas registradas. |
| **POST** | `/api/tasks` | Crea y almacena una nueva tarea (recibe JSON en el cuerpo). |
| **PUT** | `/api/tasks/{id}` | Actualiza los datos de una tarea existente buscándola por su ID. |
| **DELETE** | `/api/tasks/{id}` | Elimina una tarea de la base de datos utilizando su ID. |

---

## 🧪 Ejemplo de Payload (POST / PUT)
Para registrar o actualizar una tarea, se debe enviar un objeto JSON con la siguiente estructura:

```json
{
  "title": "Aprender Spring Boot",
  "description": "Completar la configuración de la API REST en Codespaces",
  "completed": false
}

Cómo Ejecutar el Proyecto Localmente
Clona este repositorio:

Bash
git clone [https://github.com/tu-usuario/tu-repositorio.git](https://github.com/tu-usuario/tu-repositorio.git)
Entra al directorio del proyecto y compila/ejecuta usando Maven:

Bash
mvn clean spring-boot:run
La aplicación se ejecutará por defecto en el puerto 8080.


---

### ¿Cómo subirlo a tu GitHub desde Codespaces?
Abre una terminal nueva en tu Codespace y ejecuta estos comandos sencillos para guardar y subir todo tu trabajo:

```bash
git add .
git commit -m "Proyecto Task Manager completado: CRUD, H2 y Swagger"
git push origin main