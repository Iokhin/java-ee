package ru.iokhin.tm.api.service;

import ru.iokhin.tm.model.entity.Role;

public interface IRoleService {

    void deleteAll();

    void save(Role entity);

}
