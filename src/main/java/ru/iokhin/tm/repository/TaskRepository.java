package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.api.repositroy.ITaskRepository;
import ru.iokhin.tm.model.Task;

import java.util.*;

@Repository(TaskRepository.NAME)
public class TaskRepository implements ITaskRepository {

    @NotNull
    public static final String NAME = "taskRepository";

    @NotNull
    private final Map<String, Task> repository;

    private TaskRepository() {
        this.repository = new LinkedHashMap<>();
        generateTasks();
    }

    @Override
    public List<Task> findAllByUserId(@NotNull String userId) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : repository.values()) {
            if (task.getUserId().equals(userId))
                tasks.add(task);
        }
        return tasks;
    }

    @Override
    public void removeAllByUserId(@NotNull String userId) {
        for (Task project : findAllByUserId(userId)) {
            repository.remove(project.getId());
        }
    }

    @Override
    public Task persist(@NotNull final Task entity) {
        return repository.put(entity.getId(), entity);
    }

    @Override
    public Task merge(@NotNull final Task entity) {
        return repository.put(entity.getId(), entity);
    }

    @Override
    public Task remove(@NotNull final Task entity) {
        return repository.remove(entity.getId());
    }

    @Override
    public Task findOne(@NotNull final String id) {
        return repository.get(id);
    }

    @Override
    public Collection<Task> findAll() {
        return repository.values();
    }

    @Override
    public List<Task> sortByUserId(@NotNull final String userId, @NotNull final Comparator<Task> comparator) {
        List<Task> projectList = new ArrayList<>(findAllByUserId(userId));
        projectList.sort(comparator);
        return projectList;
    }

    @Override
    public List<Task> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord) {
        List<Task> projectList = new ArrayList<>(0);
        for (Task project : findAllByUserId(userId)) {
            if (project.getName().toLowerCase().contains(keyWord.toLowerCase()) ||
                    project.getDescription().toLowerCase().contains(keyWord.toLowerCase())) {
                projectList.add(project);
            }
        }
        return projectList;
    }

    void generateTasks() {
        persist(new Task("58607299-b756-4f72-922d-07e3c9f1448d"));
        persist(new Task("58607299-b756-4f72-922d-07e3c9f1448d"));
        persist(new Task("58607299-b756-4f72-922d-07e3c9f1448d"));
        persist(new Task("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e"));
        persist(new Task("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e"));
        persist(new Task("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e"));
    }
}
