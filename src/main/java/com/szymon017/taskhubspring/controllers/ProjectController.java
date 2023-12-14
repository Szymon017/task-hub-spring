package com.szymon017.taskhubspring.controllers;

import com.szymon017.taskhubspring.models.Project;
import com.szymon017.taskhubspring.services.ProjectService;
import com.szymon017.taskhubspring.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public ResponseEntity<List<Project>> getAllProjects(){
        List<Project> projectsList = new ArrayList<>();
        projectService.getAllProjects().forEach(projectsList::add);
        if(projectsList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(projectsList, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<Project> addNewProject(@RequestBody Project project){
        Project project_ = projectService.saveProject(project);
        return new ResponseEntity<>(project_, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable int id){
        projectService.deleteProjectById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable int id, @RequestBody Project project){
        Optional<Project> project_ = projectService.updateProjectById(id, project);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
