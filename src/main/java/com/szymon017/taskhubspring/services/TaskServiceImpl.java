package com.szymon017.taskhubspring.services;

import com.szymon017.taskhubspring.models.Project;
import com.szymon017.taskhubspring.models.Task;
import com.szymon017.taskhubspring.repositories.ProjectRepository;
import com.szymon017.taskhubspring.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    TaskRepository taskRepository;

    @Autowired
    ProjectService projectService;
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task saveTask(Task task) {
        task.setIsDone(false);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks(int id) {
        return taskRepository.getTaskByProject_Id(id);
    }

    @Override
    public String deleteTaskById(int id) {
        taskRepository.deleteById(id);
        return "Successfully deleted task";
    }

    @Override
    public ResponseEntity<Task> editTaskById(int id, Task task) {
        Optional<Task> taskOptional = taskRepository.getTaskById(id);
        if(!taskOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id = " + id + " was not found");
        }
        Task existingTask = taskOptional.get();

        existingTask.setDescription(task.getDescription());
        existingTask.setIsDone(task.getIsDone());

        taskRepository.save(existingTask);

        return new ResponseEntity<>(existingTask, HttpStatus.OK);
    }
}
