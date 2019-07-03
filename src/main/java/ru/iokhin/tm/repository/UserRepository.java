package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findUserByLogin(@NotNull String login);

    @Override
    List<User> findAll();
}
