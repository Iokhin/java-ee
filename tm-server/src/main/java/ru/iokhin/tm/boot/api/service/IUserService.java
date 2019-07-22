package ru.iokhin.tm.boot.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.boot.api.repositroy.IRepository;
import ru.iokhin.tm.boot.exception.AuthException;
import ru.iokhin.tm.boot.model.dto.UserDTO;

public interface IUserService extends IRepository<UserDTO> {

    UserDTO authUser(@NotNull String login, @NotNull String password) throws AuthException;

    UserDTO findByLogin(@NotNull String login);

}
