package com.example.portal_paciente.service;

import com.example.portal_paciente.Configuration.portalUsuarioValidator;
import com.example.portal_paciente.model.portalUsuario;
import com.example.portal_paciente.repository.portalUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class portalUsuarioService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final portalUsuarioRepository portalUsuarioRepository;
    private final portalUsuarioValidator portalUsuarioValidator;

    public portalUsuario save (portalUsuario portalUsuario) {
        portalUsuario.setPassword(encoder.encode(portalUsuario.getPassword()));  // Cifrado
        return portalUsuarioRepository.save(portalUsuario);
    }

    public List<portalUsuario> findAll(){
        return portalUsuarioRepository.findAll();
    }

    public portalUsuario findById(String usuario) {
        return portalUsuarioRepository.findByusuario(portalUsuarioValidator.validar(usuario));
    }

    public void deleteById(String usuario) {
        portalUsuarioRepository.deleteById(usuario);
    }

    public portalUsuario update(portalUsuario portalUsuario) {
        portalUsuario.setPassword(encoder.encode(portalUsuario.getPassword()));
        return portalUsuarioRepository.save(portalUsuario);
    }

}
