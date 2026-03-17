package com.babel.crudfullstack.angular.controller;

import com.babel.crudfullstack.angular.model.Usuario;
import com.babel.crudfullstack.angular.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controlador REST para la autenticación y validación de credenciales de los Usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Endpoint destinado a comprobar las credenciales de acceso de un administrador.
     * Busca al usuario por su ID (DNI), y si existe compara la contraseña.
     * @param loginData Objeto Usuario que contiene el identificador y la clave enviados desde el frontend.
     * @return true si la contraseña coincide con la base de datos, repuesta HTTP OK; de lo contrario false.
     */
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody Usuario loginData) {
        return usuarioRepository.findById(loginData.getUsuario())
                .map(user -> {
                    boolean isValid = user.getPassword().trim().equals(loginData.getPassword().trim());
                    return ResponseEntity.ok(isValid);
                })
                .orElse(ResponseEntity.ok(false));
    }
}