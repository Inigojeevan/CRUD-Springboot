package com.restapi.todoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.todoapi.model.Task;

public interface  TaskRepository extends JpaRepository<Task, Long> {
    
}
