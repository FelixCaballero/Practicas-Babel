package com.example.portal_paciente.controller;

import com.example.portal_paciente.DTO.LoginRequest;
import com.example.portal_paciente.service.portalUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RestController
@RequestMapping("/api/portal/usuario")
//@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class portalUsuarioController {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final portalUsuarioService portalUsuarioService;

    @PostMapping("/create")
    public ResponseEntity<LoginRequest> save(@RequestBody LoginRequest portalUsuario) {
        return ResponseEntity.ok(portalUsuarioService.save(portalUsuario));
    }

    @GetMapping()
    public ResponseEntity<List<LoginRequest>> findAll() {
        return ResponseEntity.ok(portalUsuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoginRequest> findById(@PathVariable String id) {
        return ResponseEntity.ok(portalUsuarioService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        portalUsuarioService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<LoginRequest> update(@RequestBody LoginRequest portalUsuario) {
        return ResponseEntity.ok(portalUsuarioService.update(portalUsuario));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        LoginRequest usuario = portalUsuarioService.findById(req.getUsuario());

        if (usuario != null && passwordEncoder.matches(req.getPassword(), usuario.getPassword())) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
