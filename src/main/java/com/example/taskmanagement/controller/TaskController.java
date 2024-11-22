package com.example.taskmanagement.controller;

import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * REST Controller for managing tasks.
 * Handles HTTP requests related to Task CRUD operations.
 */
@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Create a new task.
     *
     * @param task the task details (validated)
     * @return the created task with HTTP status 201 (CREATED)
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    /**
     * Retrieve all tasks.
     *
     * @return a list of all tasks
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Retrieve a task by its ID.
     *
     * @param id the unique ID of the task
     * @return the task if found, or HTTP status 404 (NOT FOUND)
     */

     @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable @NotNull UUID id) {
        try {
            Task task = taskService.getTaskById(id);
            return ResponseEntity.ok(task);
        } catch (TaskNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    /**
     * Update an existing task.
     *
     * @param id          the unique ID of the task to update
     * @param taskDetails the updated task details (validated)
     * @return the updated task with HTTP status 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable @NotNull UUID id, @Valid @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Delete a task by its ID.
     *
     * @param id the unique ID of the task to delete
     * @return HTTP status 204 (NO CONTENT) if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable @NotNull UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
