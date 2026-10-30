package com.api_salud.api_salud.controller;

import com.api_salud.api_salud.entity.Role;
import com.api_salud.api_salud.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/roles")
//@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // GET: Obtener todos los roles
    @GetMapping
    public ResponseEntity<List<Role>> obtenerRoles() {
        List<Role> roles = roleService.listarRoles();
        return ResponseEntity.ok(roles);
    }

    // POST: Registrar un nuevo rol
    @PostMapping
    public ResponseEntity<?> crearRol(@RequestBody Map<String, String> requestBody) {
        try {
            String nombre = requestBody.get("name");
            Role nuevoRol = roleService.registrarRol(nombre);
            
            if (nuevoRol != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo registrar el rol. Es posible que ya exista.");
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}