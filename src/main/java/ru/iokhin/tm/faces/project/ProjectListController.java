package ru.iokhin.tm.faces.project;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.dto.ProjectDTO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Controller
@URLMapping(id = "project-list",
        pattern = "/project-list",
        viewId = "/WEB-INF/view/project/project-list.xhtml")
public class ProjectListController implements Serializable {

    private List<ProjectDTO> projects = new ArrayList<>();

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

    public void createProject() throws AuthException {
        projectService.merge(new ProjectDTO(getUserId()));
        reload();
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
