package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository("userRepo")
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByLogin(@NotNull String login);

    @Override
    List<User> findAll();
}
