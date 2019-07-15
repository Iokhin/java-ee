package ru.iokhin.tm.controller.task;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.controller.project.ProjectParticipantController;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.dto.ProjectDTO;
import ru.iokhin.tm.model.dto.TaskDTO;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.util.DataValidator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Controller
@URLMappings(
        mappings = {@URLMapping(id = "project-task-list",
                pattern = "/project-task-list",
                viewId = "/WEB-INF/view/task/project-task-list.xhtml"),
        })
public class ProjectTaskViewController {

    private String projectId;

    private ProjectDTO project;

    private List<TaskDTO> tasks;

    private TaskDTO taskToEdit;

    private List<Status> statuses = Arrays.asList(Status.values());

    private List<ProjectDTO> projects;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ProjectParticipantController projectParticipantController;

    @PostConstruct
    public void init() {
        reload();
    }

    public void reload() {
        try {
            tasks = taskService.findAllByProjectId(getUserId(), projectId);
            projects = projectService.findAllByUserId(getUserId());
            project = projectService.findOneByUserId(getUserId(), projectId);
        } catch (AuthException e) {
            e.printStackTrace();
        }
    }

    public String showTaskList(@NotNull final String projectId) {
        this.projectId = projectId;
        projectParticipantController.setProjectId(projectId);
        return "pretty:project-task-list";
    }

    public void removeById(@NotNull final String id) {
        taskService.removeById(id);
        reload();
    }

    public void taskCreate() throws AuthException {
        taskService.merge(new TaskDTO(getUserId(), projectId));
        reload();
    }

    public String taskEdit(@NotNull final String id) throws AuthException {
        taskToEdit = taskService.findOneByUserId(getUserId(), id);
        return "pretty:task-edit";
    }

    public void taskSave() {
        try {
            DataValidator.validate(taskToEdit);
            taskService.merge(taskToEdit);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "SUCCESS. TASK WAS SAVED"));
        } catch (Exception e) {
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