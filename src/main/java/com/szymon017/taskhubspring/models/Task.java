package com.szymon017.taskhubspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Setter
@Getter
@Entity
@Table(name = "Task")
@JsonIgnoreProperties({"project"})

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private Boolean isDone;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

    public Task(String description, Boolean isDone, Project project) {
        this.description = description;
        this.isDone = isDone;
        this.project = project;
    }

    public Task() {
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isDone=" + isDone +
                ", project=" + project +
                '}';
    }
}
