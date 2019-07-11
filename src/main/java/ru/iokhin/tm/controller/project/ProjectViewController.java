package ru.iokhin.tm.controller.project;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.dto.ProjectDTO;
import ru.iokhin.tm.util.DataValidator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void removeById(@NotNull final String id) {
        projectService.removeById(id);
        reload();
    }

    public void projectCreate() throws AuthException {
        projectService.merge(new ProjectDTO(getUserId()));
        reload();
    }

    public String projectEdit(@NotNull final String id) throws AuthException {
        projectToEdit = projectService.findOneByUserId(getUserId(), id);
        return "pretty:project-edit";
    }

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
        @Nullable final FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) throw new AuthException("USER NOT FOUND");
        @NotNull final HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        @Nullable final String userId = (String) session.getAttribute("userId");
        if (userId == null || userId.isEmpty()) throw new AuthException("USER NOT FOUND");
        return userId;
    }
}
