package ru.iokhin.tm.boot.controller.project;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.iokhin.tm.boot.api.service.IProjectService;
import ru.iokhin.tm.boot.api.service.ISecurityService;
import ru.iokhin.tm.boot.enumerated.Status;
import ru.iokhin.tm.boot.exception.AuthException;
import ru.iokhin.tm.boot.model.dto.ProjectDTO;
import ru.iokhin.tm.boot.model.dto.UserDTO;
import ru.iokhin.tm.boot.util.DataValidator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import org.springframework.security.access.prepost.PreAuthorize;

@Getter
@Setter
@Controller
@URLMappings(mappings = {
        @URLMapping(id = "project-list",
                pattern = "/project-list",
                viewId = "/WEB-INF/view/project/project-list.xhtml"),
        @URLMapping(id = "project-edit",
                pattern = "/project-edit",
                viewId = "/WEB-INF/view/project/project-edit.xhtml")
})
public class ProjectViewController implements Serializable {

    private List<ProjectDTO> projects = new ArrayList<>();

    private ProjectDTO projectToEdit;

    private List<Status> statuses = Arrays.asList(Status.values());

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ISecurityService securityService;

    @PostConstruct
    public void init() {
        reload();
    }

    public void reload() {
        try {
            projects = projectService.findAllByUserId(getUserId());
        } catch (AuthException e) {
            e.printStackTrace();
        }
    }

//    @PreAuthorize("hasRole('USER')")
    public void removeById(@NotNull final String id) {
        projectService.removeById(id);
        reload();
    }

//    @PreAuthorize("hasRole('USER')")
    public void projectCreate() throws AuthException {
        projectService.merge(new ProjectDTO(getUserId()));
        reload();
    }

    public String projectEdit(@NotNull final String id) throws AuthException {
        projectToEdit = projectService.findOneByUserId(getUserId(), id);
        return "pretty:project-edit";
    }

//    @PreAuthorize("hasRole('USER')")
    public void projectSave() {
        try {
            DataValidator.validate(projectToEdit);
            projectService.merge(projectToEdit);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "SUCCESS. TASK WAS SAVED"));
        }
        catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "ERROR. INVALID DATA"));
        }
    }

    private String getUserId() throws AuthException {
        @Nullable UserDTO loggedUser = securityService.getLoggedUser();
        if (loggedUser == null) throw new AuthException("USER NOT FOUND");
        return loggedUser.getId();
    }
}
