package com.example.taskmanagement.service;

import com.example.taskmanagement.exception.TaskNotFoundException;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing tasks.
 * Handles business logic for Task CRUD operations.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Constructor-based injection for better testability and immutability
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Create a new task.
     *
     * @param task the task to be created
     * @return the created task
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Retrieve all tasks.
     *
     * @return a list of all tasks
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Retrieve a task by its ID.
     *
     * @param id the unique ID of the task
     * @return an optional containing the task if found
     * @throws TaskNotFoundException if the task does not exist
     */
    public Task getTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));
    }

    /**
     * Update an existing task.
     *
     * @param id          the unique ID of the task to update
     * @param taskDetails the updated task details
     * @return the updated task
     * @throws TaskNotFoundException if the task does not exist
     */
    public Task updateTask(UUID id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));
        
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        
        return taskRepository.save(task);
    }

    /**
     * Delete a task by its ID.
     *
     * @param id the unique ID of the task to delete
     * @throws TaskNotFoundException if the task does not exist
     */
    public void deleteTask(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with ID " + id + " not found"));
        
        taskRepository.delete(task);
    }
}
