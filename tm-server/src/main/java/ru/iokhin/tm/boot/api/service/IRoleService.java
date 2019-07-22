package ru.iokhin.tm.boot.api.service;

import ru.iokhin.tm.boot.model.entity.Role;

public interface IRoleService {

    void deleteAll();

    void save(Role entity);

}
