package com.example.portal_paciente.service;

import com.example.portal_paciente.model.portalUsuario;
import com.example.portal_paciente.repository.portalUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class portalUsuarioService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private portalUsuarioRepository portalUsuarioRepository;

    //método para guardar un usuario
    public portalUsuario save (portalUsuario portalUsuario) {
        portalUsuario.setPassword(encoder.encode(portalUsuario.getPassword()));  // Cifrado
        return portalUsuarioRepository.save(portalUsuario);
    }
    //método para listar todos los usuaios
    public List<portalUsuario> findAll(){
        return portalUsuarioRepository.findAll();
    }
    //método para buscar un usuario en concreto
    public portalUsuario findById(String usuario) {
        return portalUsuarioRepository.findByusuario(usuario);
    }
    //método para eliminar un usuario en concreoto
    public void deleteById(String usuario) {
        portalUsuarioRepository.deleteById(usuario);
    }
    //método para actualizar un usuario
    public portalUsuario update(portalUsuario portalUsuario) {
        portalUsuario.setPassword(encoder.encode(portalUsuario.getPassword()));
        return portalUsuarioRepository.save(portalUsuario);
    }

}
