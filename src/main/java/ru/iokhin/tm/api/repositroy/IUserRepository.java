package ru.iokhin.tm.api.repositroy;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.model.entity.User;

public interface IUserRepository extends IRepository<User> {

    User findByLogin(@NotNull String login);

}
