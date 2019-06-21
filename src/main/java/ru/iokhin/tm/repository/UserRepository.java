package ru.iokhin.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repositroy.IUserRepository;
import ru.iokhin.tm.enumerated.Role;
import ru.iokhin.tm.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public enum UserRepository implements IUserRepository {

    INSTANCE;

    @NotNull
    private final Map<String, User> repository;

    private UserRepository() {
        this.repository = new HashMap<>();
        generateUsers();
    }

    private void generateUsers() {
        persist(new User("user", "user", Role.USER));
        persist(new User("admin", "admin", Role.ADMIN));
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
