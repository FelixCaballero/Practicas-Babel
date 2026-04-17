package com.babel.crudfullstack.spring.controller;

import com.babel.crudfullstack.spring.dto.LoginRequestDTO;
import com.babel.crudfullstack.spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para la autenticación y validación de credenciales de los Usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Usuarios", description = "Endpoints para la gestión y autenticación de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Endpoint destinado a comprobar las credenciales de acceso de un administrador.
     * Busca al usuario por su ID (DNI), y si existe compara la contraseña.
     * @param loginData Objeto DTO que contiene el identificador y la clave enviados desde el frontend.
     * @return true si la contraseña coincide con la base de datos; de lo contrario false.
     */
    @PostMapping("/login")
    @Operation(summary = "Login de usuario", description = "Valida las credenciales de un usuario y devuelve true si son correctas.")
    public ResponseEntity<Boolean> login(@Valid @RequestBody LoginRequestDTO loginData) {
        return usuarioRepository.findById(loginData.getUsuario())
                .map(user -> {
                    boolean isValid = user.getPassword().trim().equals(loginData.getPassword().trim());
                    return ResponseEntity.ok(isValid);
                })
                .orElse(ResponseEntity.ok(false));
    }
}