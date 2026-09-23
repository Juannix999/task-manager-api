package com.portfolio.taskmanager.controller;

import com.portfolio.taskmanager.model.Task;
import com.portfolio.taskmanager.repository.TaskRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tareas", description = "Operaciones de la API para la gestión completa de tareas")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Operation(summary = "Obtener todas las tareas", description = "Retorna una lista con todas las tareas registradas en la base de datos H2")
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Operation(summary = "Crear una nueva tarea", description = "Registra una nueva tarea enviando un objeto JSON en el cuerpo de la petición")
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @Operation(summary = "Actualizar una tarea existente", description = "Modifica los datos de una tarea buscándola por su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return taskRepository.findById(id)
            .map(task -> {
                task.setTitle(taskDetails.getTitle());
                task.setDescription(taskDetails.getDescription());
                task.setCompleted(taskDetails.isCompleted());
                Task updatedTask = taskRepository.save(task);
                return ResponseEntity.ok(updatedTask);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una tarea", description = "Borra una tarea de la base de datos utilizando su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}