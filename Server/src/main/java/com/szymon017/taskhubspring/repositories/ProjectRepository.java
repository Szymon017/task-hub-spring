package com.szymon017.taskhubspring.repositories;

import com.szymon017.taskhubspring.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    public Optional<Project> getProjectById(int id);
}
