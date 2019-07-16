package ru.iokhin.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iokhin.tm.model.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(String name);

}
