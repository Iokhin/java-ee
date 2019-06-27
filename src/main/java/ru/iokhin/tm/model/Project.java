package ru.iokhin.tm.model;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
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
}
