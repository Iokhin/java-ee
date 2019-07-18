package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repositroy.IRepository;
import ru.iokhin.tm.api.repositroy.IUserRepository;
import ru.iokhin.tm.exception.AuthException;
import ru.iokhin.tm.model.dto.UserDTO;
import ru.iokhin.tm.model.entity.User;

public interface IUserService extends IRepository<UserDTO> {

    UserDTO authUser(@NotNull String login, @NotNull String password) throws AuthException;

    UserDTO findByLogin(@NotNull String login);

}
