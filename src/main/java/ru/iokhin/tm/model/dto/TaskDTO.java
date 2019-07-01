package ru.iokhin.tm.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO extends BaseEntityDTO {

    public TaskDTO(@NotNull final String userId) {
        this.userId = userId;
        this.name = "NEW TASK";
        this.description = "NEW DESCRIPTION";
    }

    @Nullable
    private String projectId;

}
