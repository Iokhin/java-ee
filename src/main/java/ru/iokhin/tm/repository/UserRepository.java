package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.api.repositroy.IUserRepository;
import ru.iokhin.tm.enumerated.Role;
import ru.iokhin.tm.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository(UserRepository.NAME)
public class UserRepository implements IUserRepository {

    @NotNull
    public static final String NAME = "userRepository";

    @NotNull
    private final Map<String, User> repository;

    UserRepository() {
        this.repository = new HashMap<>();
        generateUsers();
        System.out.println(repository.values());
    }

    private void generateUsers() {
        persist(new User("58607299-b756-4f72-922d-07e3c9f1448d", "user", "user", Role.USER));
        persist(new User("ada5b8d2-1181-4db7-b0ac-8430d2fcfa6e", "admin", "admin", Role.ADMIN));
    }

    @Override
    public User findByLogin(@NotNull String login) {
        for (User user : repository.values()) {
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    @Override
    public User persist(@NotNull User entity) {
        return repository.put(entity.getId(), entity);
    }

    @Override
    public User merge(@NotNull User entity) {
        return repository.put(entity.getId(), entity);
    }

    @Override
    public User remove(@NotNull User entity) {
        return repository.remove(entity.getId());
    }

    @Override
    public User findOne(@NotNull String id) {
        return repository.get(id);
    }

    @Override
    public Collection<User> findAll() {
        return repository.values();
    }
}
