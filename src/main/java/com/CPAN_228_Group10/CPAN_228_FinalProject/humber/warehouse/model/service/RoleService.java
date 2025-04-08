package com.CPAN_228_Group10.CPAN_228_FinalProject.humber.warehouse.model.service;

import com.CPAN_228_Group10.CPAN_228_FinalProject.humber.warehouse.model.Role;
import com.CPAN_228_Group10.CPAN_228_FinalProject.humber.warehouse.model.RoleName;
import com.CPAN_228_Group10.CPAN_228_FinalProject.humber.warehouse.repository.RoleRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        if (roleRepository.findByName(RoleName.USER).isEmpty()) {
            Role userRole = new Role();
            userRole.setName(RoleName.USER);
            roleRepository.save(userRole);
        }
        if (roleRepository.findByName(RoleName.ADMIN).isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName(RoleName.ADMIN);
            roleRepository.save(adminRole);
        }
    }

    public Role getUserRole() {
        return roleRepository.findByName(RoleName.USER)
            .orElseThrow(() -> new RuntimeException("Default USER role not found"));
    }
}
