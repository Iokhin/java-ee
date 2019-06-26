package ru.iokhin.tm.model;

import org.jetbrains.annotations.NotNull;

public class Task extends BaseEntity {

    public Task() {
    }

    public Task(@NotNull final String userId) {
        this.userId = userId;
        this.name = "NEW TASK";
        this.description = "NEW DESCRIPTION";
    }

    @NotNull
    private String projectId;

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }
}
