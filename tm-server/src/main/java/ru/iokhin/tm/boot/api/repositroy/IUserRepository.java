package ru.iokhin.tm.boot.api.repositroy;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.boot.model.entity.User;

public interface IUserRepository extends IRepository<User> {

    User findByLogin(@NotNull String login);

}
