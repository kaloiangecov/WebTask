package com.example.WebTask.modules.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/roles")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Role> findAllRoles() {
        return roleService.findAll();
    }
}
