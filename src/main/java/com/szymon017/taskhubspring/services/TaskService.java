package com.szymon017.taskhubspring.services;

import com.szymon017.taskhubspring.models.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {

    public Task saveTask(Task task);
    public List<Task> getAllTasks(int id);
    public String deleteTaskById(int id);
    public ResponseEntity<Task> editTaskById(int id, Task task);

}
