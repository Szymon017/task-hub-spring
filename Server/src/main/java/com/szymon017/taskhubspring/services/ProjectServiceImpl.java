package com.szymon017.taskhubspring.services;

import com.szymon017.taskhubspring.models.Project;
import com.szymon017.taskhubspring.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProjectServiceImpl implements ProjectService{

    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects;
    }

    @Override
    public String deleteProjectById(int id) {
        projectRepository.deleteById(id);
        return "Successfully deleted";
    }

    @Override
    public Optional<Project> updateProjectById(int id, Project project) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if(optionalProject.isPresent()){
            Project existingProject = optionalProject.get();
            existingProject.setName(project.getName());
            existingProject.setTasks(project.getTasks());
            projectRepository.save(existingProject);
            return Optional.of(existingProject);
        }else{
            return Optional.empty();
        }
    }


    @Override
    public Optional<Project> getProjectById(int id) {
        return projectRepository.getProjectById(id);
    }
}
