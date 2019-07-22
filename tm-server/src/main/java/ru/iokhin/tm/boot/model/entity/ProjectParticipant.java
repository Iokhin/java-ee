package ru.iokhin.tm.boot.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.iokhin.tm.boot.model.dto.ProjectParticipantDTO;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "project_participant")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
public class ProjectParticipant extends AbstractEntity {

    @JoinColumn(name = "user_id")
    @OneToOne(targetEntity = User.class)
    private User user;

    @JoinColumn(name = "project_id")
    @ManyToOne(targetEntity = Project.class)
    private Project project;

    private String username;

    public ProjectParticipantDTO getProjectParticipantDTO() {
        final ProjectParticipantDTO projectParticipantDTO = new ProjectParticipantDTO();
        projectParticipantDTO.setId(id);
        projectParticipantDTO.setUserId(user.getId());
        projectParticipantDTO.setProjectId(project.getId());
        projectParticipantDTO.setUsername(user.getLogin());
        return projectParticipantDTO;
    }

}
