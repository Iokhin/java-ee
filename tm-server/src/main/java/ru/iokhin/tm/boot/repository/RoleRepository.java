package ru.iokhin.tm.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import ru.iokhin.tm.boot.model.entity.Role;

import javax.persistence.QueryHint;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<Role> findByName(String name);

}
