package ru.iokhin.tm.repositoryC;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.api.repositroy.ITaskRepository;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.model.entity.Task;
import ru.iokhin.tm.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository(TaskRepository.NAME)
public class TaskRepository implements ITaskRepository {

    @NotNull
    public static final String NAME = "taskRepository";

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Task> findAllByUserId(@NotNull User user) {
        return em.createQuery("SELECT e FROM Task e WHERE e.user = :user", Task.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public void removeAllByUserId(@NotNull User user) {
        @NotNull final List<Task> tasks = findAllByUserId(user);
        if (tasks.size() == 0) return;
        tasks.forEach(em::remove);
    }

    @Override
    public void persist(@NotNull final Task entity) {
        em.persist(entity);
    }

    @Override
    public void merge(@NotNull final Task entity) {
        em.merge(entity);
    }

    @Override
    public void removeById(@NotNull String id) {
        em.remove(em.find(Task.class, id));
    }

    @Override
    public Task findOne(@NotNull final String id) {
        return em.find(Task.class, id);
    }

    @Override
    public List<Task> findAll() {
        return em.createQuery("SELECT e FROM Task e", Task.class).getResultList();
    }

    @Override
    public List<Task> sortByUserId(@NotNull final User user, @NotNull final String parameter) {
        @NotNull final CriteriaBuilder cb = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<Task> cq = cb.createQuery(Task.class);
        @NotNull final Root<Task> taskRoot = cq.from(Task.class);
        @NotNull final Predicate condition = cb.equal(taskRoot.get("user"), user);
        cb.conjunction();
        cq.select(taskRoot).where(condition);
        cq.orderBy(cb.desc(taskRoot.get(parameter)));
        @NotNull final TypedQuery<Task> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Task> findByPartOfNameOrDescription(@NotNull final User user, @NotNull final String keyWord) {
        return em.createQuery("SELECT p FROM Task p WHERE p.user = :user AND " +
                "(p.name LIKE :part OR p.description LIKE :part)", Task.class)
                .setParameter("user", user)
                .setParameter("part", "%" + keyWord + "%")
                .getResultList();
    }

    @Override
    public Task findOneByUserId(@NotNull User user, @NotNull String id) {
        final List<Task> tasks = em.createQuery("SELECT e FROM Task e WHERE e.user = :user AND e.id = :id", Task.class)
                .setParameter("user", user)
                .setParameter("id", id)
                .getResultList();
        if (tasks.size() > 0) return tasks.get(0);
        return null;
    }

    @Override
    public List<Task> findAllByProjectId(@NotNull User user, @NotNull Project project) {
        return em.createQuery("SELECT e FROM Task e WHERE e.user = :user AND e.project = :project", Task.class)
                .setParameter("user", user)
                .setParameter("project", project)
                .getResultList();
    }

}
