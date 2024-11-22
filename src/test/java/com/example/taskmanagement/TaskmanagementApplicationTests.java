package com.example.taskmanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.taskmanagement.controller.TaskController;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class TaskmanagementApplicationTests {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void testCreateTask() throws Exception {
        // Arrange: Create a Task object for testing
        Task task = new Task();
        task.setTitle("New Task");
        task.setDescription("This is a new task.");
        task.setStatus(Task.Status.PENDING);

        // Mock the service layer method to return the task when it's created
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        // Act: Perform a POST request to create a new task
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(task)))
                .andExpect(status().isCreated())  // Check if the status is CREATED (201)
                .andExpect(jsonPath("$.title").value("New Task"))  // Check if the title matches
                .andExpect(jsonPath("$.status").value("PENDING"));  // Check if the status matches
    }
}
