package ru.iokhin.tm.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.model.entity.AbstractEntity;
import ru.iokhin.tm.model.entity.Project;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ProjectParticipantDTO extends AbstractEntity {

    @NotNull
    private String userId = "";

    @NotNull
    private String projectId = "";

    @Nullable
    private String username;

    public ProjectParticipantDTO(@NotNull Project project) {
        this.userId = project.getUser().getId();
        this.projectId = project.getId();
    }

    public ProjectParticipantDTO(@NotNull ProjectDTO projectDTO) {
        this.userId = projectDTO.getUserId();
        this.projectId = projectDTO.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectParticipantDTO that = (ProjectParticipantDTO) o;
        return userId.equals(that.userId) &&
                projectId.equals(that.projectId) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, projectId, username);
    }
}
