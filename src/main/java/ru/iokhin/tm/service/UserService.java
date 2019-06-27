package ru.iokhin.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iokhin.tm.api.repositroy.IUserRepository;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.User;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.util.StringValidator;

import java.util.Collection;

@NoArgsConstructor
@Service(UserService.NAME)
public class UserService implements IUserService {

    @NotNull
    public static final String NAME = "userService";

    @NotNull
    @Autowired
    private IUserRepository userRepository;

    @Override
    public User findByLogin(@NotNull String login) {
        @Nullable final Collection<User> users = findAll();
        if (users == null) return null;
        for (User user : users) {
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }

    @Override
    public User persist(@NotNull User entity) {
        return userRepository.persist(entity);
    }

    @Override
    public User merge(@NotNull User entity) {
        return userRepository.merge(entity);
    }

    @Override
    public User remove(@NotNull User entity) {
        return userRepository.remove(entity);
    }

    @Override
    public User findOne(@NotNull String id) {
        return userRepository.findOne(id);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User authUser(@NotNull String login, @NotNull String password) throws AuthException {
        StringValidator.validate(login, password);
        User user = findByLogin(login);
        if (user == null) throw new AuthException("WRONG LOGIN");
        if (!user.getPasswordHash().equals(MD5Util.passwordToHash(password))) throw new AuthException("WRONG PASSWORD");
        return user;
    }
}
