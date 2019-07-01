package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.api.repositroy.IUserRepository;
import ru.iokhin.tm.model.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(UserRepository.NAME)
public class UserRepository implements IUserRepository {

    @NotNull
    public static final String NAME = "userRepository";

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findByLogin(@NotNull String login) {
        List<User> user = em.createQuery("SELECT e FROM User e WHERE e.login = :login", User.class)
                .setParameter("login", login)
                .getResultList();
        return user.size() > 0 ? user.get(0) : null;
    }

    @Override
    public void persist(@NotNull User entity) {
        em.persist(entity);
    }

    @Override
    public void merge(@NotNull User entity) {
        em.merge(entity);
    }

    @Override
    public void removeById(@NotNull String id) {
        em.remove(findOne(id));
    }


    @Override
    public User findOne(@NotNull String id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT e FROM User e", User.class).getResultList();
    }
}
