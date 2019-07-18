package ru.iokhin.tm.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDTO extends BaseEntityDTO {

    @Nullable
    private String projectId;

    public TaskDTO(@NotNull final String userId) {
        this.userId = userId;
        this.name = "NEW TASK";
        this.description = "NEW DESCRIPTION";
    }

    public TaskDTO(@NotNull final String userId, @NotNull final String projectId) {
        this.userId = userId;
        this.projectId = projectId;
        this.name = "NEW TASK";
        this.description = "NEW DESCRIPTION";
    }

}
