package com.szymon017.taskhubspring.repositories;

import com.szymon017.taskhubspring.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    public List<Task> getTaskByProject_Id(int id);
    public Optional<Task> getTaskById(int id);
}
