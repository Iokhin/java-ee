package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.model.entity.User;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository("userRepo")
public interface UserRepository extends JpaRepository<User, String> {

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<User> findUserByLogin(@NotNull String login);

    @Override
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<User> findAll();

    @Override
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<User> findById(String id);
}
