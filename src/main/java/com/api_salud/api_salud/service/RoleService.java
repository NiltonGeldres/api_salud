package com.api_salud.api_salud.service;

import com.api_salud.api_salud.entity.Role;
import com.api_salud.api_salud.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    // Listar el catálogo de roles
    public List<Role> listarRoles() {
        return roleDao.listarRolesCatalog();
    }

    // Crear un nuevo rol validando que el nombre no venga vacío
    public Role registrarRol(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío.");
        }
        return roleDao.crearRolCatalog(nombre.trim());
    }
}