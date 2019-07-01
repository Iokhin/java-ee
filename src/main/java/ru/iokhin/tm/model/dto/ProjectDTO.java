package ru.iokhin.tm.model.dto;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public class ProjectDTO extends BaseEntityDTO {
    @Override
    public String toString() {
        return getId();
    }

    public ProjectDTO(@NotNull final String userId) {
        this.userId = userId;
        this.name = "NEW PROJECT";
        this.description = "NEW DESCRIPTION";
    }
}
