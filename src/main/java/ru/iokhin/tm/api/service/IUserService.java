package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repositroy.IUserRepository;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.User;

public interface IUserService extends IUserRepository {

    User authUser(@NotNull String login, @NotNull String password) throws AuthException;

}
