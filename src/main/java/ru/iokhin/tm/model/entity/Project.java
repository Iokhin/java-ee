package ru.iokhin.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.model.dto.ProjectDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class Project extends BaseEntity implements Serializable {

    @Nullable
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectParticipant> projectParticipant;

    @Nullable
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks;

    @Override
    public String toString() {
        return getId();
    }

    public Project(@NotNull final User user) {
        this.user = user;
        this.name = "NEW PROJECT";
        this.description = "NEW DESCRIPTION";
    }

    public ProjectDTO getProjectDTO() {
        final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(id);
        projectDTO.setUserId(user.getId());
        projectDTO.setName(name);
        projectDTO.setDescription(description);
        projectDTO.setDateStart(dateStart);
        projectDTO.setDateEnd(dateEnd);
        projectDTO.setStatus(status);
        return projectDTO;
    }
}
