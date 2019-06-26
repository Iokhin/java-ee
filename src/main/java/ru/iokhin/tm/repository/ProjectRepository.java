package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repositroy.IProjectRepository;
import ru.iokhin.tm.model.Project;

import java.util.*;

public enum ProjectRepository implements IProjectRepository {

    INSTANCE;

    @NotNull
    private Map<String, Project> repository;

    private ProjectRepository() {
        this.repository = new LinkedHashMap<>();
        generateProjects();
    }

    @Override
    public List<Project> findAllByUserId(@NotNull final String userId) {
        List<Project> projects = new ArrayList<>();
        for (Project project : repository.values()) {
            if (project.getUserId().equals(userId))
                projects.add(project);
        }
        return projects;
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        for (Project project : findAllByUserId(userId)) {
            repository.remove(project.getId());
        }
    }

    @Override
    public Project persist(@NotNull final Project entity) {
        return repository.put(entity.getId(), entity);
    }

    @Override
    public Project merge(@NotNull final Project entity) {
        return repository.put(entity.getId(), entity);
    }

    @Override
    public Project remove(@NotNull final Project entity) {
        return repository.remove(entity.getId());
    }

    @Override
    public Project findOne(@NotNull final String id) {
        @Nullable final Project project = repository.get(id);
        return project;
    }

    @Override
    public Collection<Project> findAll() {
        return repository.values();
    }

    @Override
    public List<Project> sortByUserId(@NotNull final String userId, @NotNull final Comparator<Project> comparator) {
        List<Project> projectList = new ArrayList<>(findAllByUserId(userId));
        projectList.sort(comparator);
        return projectList;
    }

    @Override
    public List<Project> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord) {
        List<Project> projectList = new ArrayList<>(0);
        for (Project project : findAllByUserId(userId)) {
            if (project.getName().toLowerCase().contains(keyWord.toLowerCase()) ||
                    project.getDescription().toLowerCase().contains(keyWord.toLowerCase())) {
                projectList.add(project);
            }
        }
        return projectList;
    }

    private void generateProjects() {
        persist(new Project("58607299-b756-4f72-922d-07e3c9f1448d"));
        persist(new Project("58607299-b756-4f72-922d-07e3c9f1448d"));
        persist(new Project("58607299-b756-4f72-922d-07e3c9f1448d"));
        persist(new Project("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e"));
        persist(new Project("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e"));
        persist(new Project("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e"));
    }
}
