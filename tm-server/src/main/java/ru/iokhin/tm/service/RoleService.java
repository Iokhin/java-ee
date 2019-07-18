package ru.iokhin.tm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iokhin.tm.api.service.IRoleService;
import ru.iokhin.tm.model.entity.Role;
import ru.iokhin.tm.repository.RoleRepository;

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
