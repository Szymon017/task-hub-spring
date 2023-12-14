package com.szymon017.taskhubspring.services;

import com.szymon017.taskhubspring.models.Project;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectService {
    public Project saveProject(Project project);
    public List<Project> getAllProjects();
    public String deleteProjectById(int id);
    public Optional<Project> updateProjectById(int id, Project project);
    public Optional<Project> getProjectById(int id);

}
