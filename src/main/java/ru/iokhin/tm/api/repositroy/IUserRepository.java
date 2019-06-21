package ru.iokhin.tm.api.repositroy;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.model.User;

public interface IUserRepository extends IRepository<User> {

    User findByLogin(@NotNull String login);

}
