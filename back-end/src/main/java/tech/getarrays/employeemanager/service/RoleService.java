package tech.getarrays.employeemanager.service;

import tech.getarrays.employeemanager.repo.RoleDao;
import tech.getarrays.employeemanager.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
