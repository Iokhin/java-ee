package ru.iokhin.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import ru.iokhin.tm.model.entity.Role;

import javax.persistence.QueryHint;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Optional<Role> findByName(String name);

}
