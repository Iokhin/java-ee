package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repositroy.IProjectRepository;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.model.Project;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.util.ComparatorUtil;
import ru.iokhin.tm.util.StringValidator;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public enum ProjectService implements IProjectService {

    INSTANCE;

    @NotNull
    private final IProjectRepository projectRepository = ProjectRepository.INSTANCE;

    @Override
    public List<Project> findAllByUserId(@NotNull final String userId) {
        StringValidator.validate(userId);
        return projectRepository.findAllByUserId(userId);
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        StringValidator.validate(userId);
        for (Project project : findAllByUserId(userId)) {
            remove(project);
        }
    }

    @Override
    public List<Project> sortByUserId(@NotNull final String userId, @NotNull final String parameter) {
        StringValidator.validate(userId, parameter);
        if ("order".equals(parameter)) return findAllByUserId(userId);
        @Nullable final Comparator<Project> comparator = ComparatorUtil.getProjectComparator(parameter);
        if (comparator == null) return null;
        return projectRepository.sortByUserId(userId, comparator);
    }

    @Override
    public List<Project> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord) {
        StringValidator.validate(userId, keyWord);
        return projectRepository.findByPartOfNameOrDescription(userId, keyWord);
    }

    @Override
    public Project findOneById(@NotNull final String userId, @NotNull final String id) {
        Project project = findOne(id);
        if (!project.getUserId().equals(userId)) return null;
        return project;
    }

    @Override
    public Project persist(@NotNull final Project entity) {
        return projectRepository.persist(entity);
    }

    @Override
    public Project merge(@NotNull final Project entity) {
        return projectRepository.merge(entity);
    }

    @Override
    public Project remove(@NotNull final Project entity) {
        return projectRepository.remove(entity);
    }

    @Override
    public Project findOne(@NotNull final String id) {
        StringValidator.validate(id);
        return projectRepository.findOne(id);
    }

    @Override
    public Collection<Project> findAll() {
        return projectRepository.findAll();
    }
}
