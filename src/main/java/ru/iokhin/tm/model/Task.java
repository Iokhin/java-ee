package ru.iokhin.tm.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class Task extends BaseEntity {

    @NotNull
    private String projectId;

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }
}
