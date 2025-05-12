package com.restapi.todoapi.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.todoapi.model.Task;
import com.restapi.todoapi.service.TaskService;

@RestController
@RequestMapping("/")
public class TaskController {

    @Autowired
    private final TaskService taskService;
    
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id)));
    }

    @PostMapping("/tasks") 
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @DeleteMapping("task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
