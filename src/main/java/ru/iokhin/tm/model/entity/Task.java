package ru.iokhin.tm.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.model.dto.TaskDTO;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task extends BaseEntity implements Serializable {

    public Task(@NotNull final User user) {
        this.user = user;
        this.name = "NEW TASK";
        this.description = "NEW DESCRIPTION";
    }

    @Nullable
    @ManyToOne
    private Project project;

    public TaskDTO getTaskDTO() {
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(id);
        taskDTO.setUserId(user.getId());
        if (project == null) {
            taskDTO.setProjectId(null);
        } else {
            taskDTO.setProjectId(project.getId());
        }
        taskDTO.setName(name);
        taskDTO.setDescription(description);
        taskDTO.setDateStart(dateStart);
        taskDTO.setDateEnd(dateEnd);
        taskDTO.setStatus(status);
        return taskDTO;
    }

}
