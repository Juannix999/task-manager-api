package com.portfolio.taskmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.taskmanager.exception.GlobalExceptionHandler;
import com.portfolio.taskmanager.exception.TaskNotFoundException;
import com.portfolio.taskmanager.model.Task;
import com.portfolio.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
@Import(GlobalExceptionHandler.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskService taskService;

    @Test
    void shouldReturnAllTasks() throws Exception {
        Task task = new Task("Aprender Spring", "Crear API", false);
        task.setId(1L);
        when(taskService.getAllTasks()).thenReturn(List.of(task));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Aprender Spring"));
    }

    @Test
    void shouldReturnTaskById() throws Exception {
        Task task = new Task("Task", "Detail", false);
        task.setId(2L);
        when(taskService.getTaskById(2L)).thenReturn(task);

        mockMvc.perform(get("/api/tasks/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    void shouldCreateTask() throws Exception {
        Task request = new Task("Nueva tarea", "Desc", false);
        Task created = new Task("Nueva tarea", "Desc", false);
        created.setId(3L);
        when(taskService.createTask(any(Task.class))).thenReturn(created);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3));
    }

    @Test
    void shouldUpdateTask() throws Exception {
        Task updated = new Task("Actualizada", "Detalle", true);
        updated.setId(1L);
        when(taskService.updateTask(eq(1L), any(Task.class))).thenReturn(updated);

        mockMvc.perform(put("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundWhenTaskDoesNotExist() throws Exception {
        doThrow(new TaskNotFoundException(99L)).when(taskService).getTaskById(99L);

        mockMvc.perform(get("/api/tasks/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No se encontró la tarea con id: 99"));
    }

    @Test
    void shouldFailValidationWhenTitleIsBlank() throws Exception {
        Task invalidTask = new Task(" ", "Desc", false);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidTask)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").exists());
    }
}
