package ru.iokhin.tm.repositoryC;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.api.repositroy.IProjectRepository;
import ru.iokhin.tm.model.entity.Project;
import ru.iokhin.tm.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository(ProjectRepository.NAME)
public class ProjectRepository implements IProjectRepository {

    @NotNull
    public static final String NAME = "projectRepository";

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Project> findAllByUserId(@NotNull final User user) {
        return em.createQuery("SELECT e FROM Project e WHERE e.user = :user", Project.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public void removeAllByUserId(@NotNull final User user) {
        @NotNull final List<Project> projects = findAllByUserId(user);
        projects.forEach(em::remove);
    }

    @Override
    public void persist(@NotNull final Project entity) {
        em.persist(entity);
    }

    @Override
    public void merge(@NotNull final Project entity) {
        em.merge(entity);
    }

    @Override
    public void removeById(@NotNull final String id) {
        em.remove(findOne(id));
    }

    @Override
    public Project findOne(@NotNull final String id) {
        return em.find(Project.class, id);
    }

    @Override
    public List<Project> findAll() {
        return em.createQuery("SELECT e FROM Project e", Project.class).getResultList();
    }

    @Override
    public List<Project> sortByUserId(@NotNull final User user, @NotNull final String parameter) {
        @NotNull final CriteriaBuilder cb = em.getCriteriaBuilder();
        @NotNull final CriteriaQuery<Project> cq = cb.createQuery(Project.class);
        @NotNull final Root<Project> projectRoot = cq.from(Project.class);
        @NotNull final Predicate condition = cb.equal(projectRoot.get("user"), user);
        cb.conjunction();
        cq.select(projectRoot).where(condition);
        cq.orderBy(cb.desc(projectRoot.get(parameter)));
        @NotNull final TypedQuery<Project> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Project> findByPartOfNameOrDescription(@NotNull final User user, @NotNull final String keyWord) {
        return em.createQuery("SELECT p FROM Project p WHERE p.user = :user AND " +
                "(p.name LIKE :part OR p.description LIKE :part)", Project.class)
                .setParameter("user", user)
                .setParameter("part", "%" + keyWord + "%")
                .getResultList();
    }

    @Override
    public Project findOneByUserId(@NotNull User user, @NotNull String id) {
        final List<Project> projects = em.createQuery("SELECT e FROM Project e WHERE e.user = :user", Project.class)
                .setParameter("user", user)
                .getResultList();
        if (projects.size() > 0)
            return projects.get(0);
        return null;
    }
}
