package com.example.taskmanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskmanagement.model.Task;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
