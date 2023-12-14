package com.szymon017.taskhubspring.controllers;

import com.szymon017.taskhubspring.models.Project;
import com.szymon017.taskhubspring.models.Task;
import com.szymon017.taskhubspring.services.ProjectService;
import com.szymon017.taskhubspring.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TaskController {

    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @GetMapping("/project/{projectId}/tasks")
    public ResponseEntity<List<Task>> getAllTasksByProjectId(@PathVariable int projectId){
        if(projectService.getProjectById(projectId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with id = "+ projectId + " not found.");
        }
        List<Task> tasks = projectService.getProjectById(projectId).get().getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @PostMapping("/project/{projectId}/tasks")
    public ResponseEntity<Task> addNewTask(@PathVariable int projectId, @RequestBody Task task){
        Optional<Project> projectOptional = projectService.getProjectById(projectId);
        if(projectOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project with id = " + projectId + " was not found.");
        }

        Project project = projectOptional.get();
        task.setProject(project);

        Task savedTask = taskService.saveTask(task);

        return new ResponseEntity<>(savedTask, HttpStatus.OK);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task task){
        return taskService.editTaskById(id, task);
    }
}


