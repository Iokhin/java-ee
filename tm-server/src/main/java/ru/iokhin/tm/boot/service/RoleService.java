package ru.iokhin.tm.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iokhin.tm.boot.api.service.IRoleService;
import ru.iokhin.tm.boot.model.entity.Role;
import ru.iokhin.tm.boot.repository.RoleRepository;

@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    @Override
    public void save(Role entity) {
        roleRepository.save(entity);
    }
}
