package ru.iokhin.tm.model;

import org.jetbrains.annotations.NotNull;

public class Project extends BaseEntity {
    @Override
    public String toString() {
        return getId();
    }

    public Project(@NotNull final String userId) {
        this.userId = userId;
        this.name = "NEW PROJECT";
        this.description = "NEW DESCRIPTION";
    }

    public Project() {
    }
}
