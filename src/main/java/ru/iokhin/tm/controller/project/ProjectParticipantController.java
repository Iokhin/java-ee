package ru.iokhin.tm.controller.project;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.iokhin.tm.api.service.IProjectParticipantService;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.model.dto.ProjectParticipantDTO;
import ru.iokhin.tm.model.dto.UserDTO;
import ru.iokhin.tm.model.entity.ProjectParticipant;
import ru.iokhin.tm.repository.ProjectParticipantRepository;
import ru.iokhin.tm.util.OptionsUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Controller
@URLMappings(mappings = {
        @URLMapping(id = "project-participants",
                pattern = "/project-participants",
                viewId = "/WEB-INF/view/project/project-participants.xhtml"),
        @URLMapping(id = "project-participants-add",
                pattern = "/project-participants-add",
                viewId = "/WEB-INF/view/project/project-participants-add.xhtml")
})
public class ProjectParticipantController {

    private String projectId;

    private Set<ProjectParticipantDTO> participants;

    private ProjectParticipantDTO selectedParticipant;

    private List<UserDTO> users;

    private UserDTO selectedUser;

    @Autowired
    private IProjectParticipantService projectParticipantService;

    @Autowired
    private IUserService userService;

    public Set<ProjectParticipantDTO> getParticipants() {
        return new HashSet<>(projectParticipantService.findAllByProjectId(projectId));
    }

    public List<UserDTO> getUsers() {
        return userService.findAll();
    }

    public String showForm(@NotNull String projectId) {
        this.projectId = projectId;
        return "pretty:project-participants";
    }

    public void participantAdd() {
        if (selectedUser == null) {
            FacesMessage msg = new FacesMessage("ERROR:", "USER WAS NOT SELECTED");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        final ProjectParticipantDTO projectParticipant = convertToParticipant(selectedUser);
        projectParticipantService.save(projectParticipant);
        PrimeFaces.current().dialog().closeDynamic("project-participant-add");
    }

    public void participantShow() {
        PrimeFaces.current().dialog().openDynamic("project-participant-add", OptionsUtil.getWindowOptions(), null);
    }

    public void participantKick() {
        if (selectedParticipant == null) {
            FacesMessage msg = new FacesMessage("ERROR:", "USER WAS NOT SELECTED");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        projectParticipantService.delete(selectedParticipant.getUserId(), selectedParticipant.getProjectId());
    }

    public void createParticipantByIds(@NotNull final String userId, @NotNull final String projectId) {
        final ProjectParticipantDTO projectParticipantDTO = new ProjectParticipantDTO();
        projectParticipantDTO.setUserId(userId);
        projectParticipantDTO.setProjectId(projectId);
        projectParticipantService.save(projectParticipantDTO);
    }

    public void onRowSelect(SelectEvent event) {
        if (event.getObject() instanceof UserDTO) {
            FacesMessage msg = new FacesMessage("User selected", ((UserDTO) event.getObject()).getId());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        if (event.getObject() instanceof ProjectParticipantDTO) {
            FacesMessage msg = new FacesMessage("Participant selected", ((ProjectParticipantDTO) event.getObject()).getId());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onRowUnselect(UnselectEvent event) {
        if (event.getObject() instanceof UserDTO) {
            FacesMessage msg = new FacesMessage("User unselected", ((UserDTO) event.getObject()).getId());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        if (event.getObject() instanceof ProjectParticipantDTO) {
            FacesMessage msg = new FacesMessage("Participant unselected", ((ProjectParticipantDTO) event.getObject()).getId());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    private ProjectParticipantDTO convertToParticipant(@NotNull final UserDTO userDTO) {
        final ProjectParticipantDTO projectParticipantDTO = new ProjectParticipantDTO();
        projectParticipantDTO.setUserId(userDTO.getId());
        projectParticipantDTO.setProjectId(projectId);
        projectParticipantDTO.setUsername(userDTO.getLogin());
        return projectParticipantDTO;
    }
}
