package com.example.portal_paciente.service;

import com.example.portal_paciente.model.portalMenu;
import com.example.portal_paciente.repository.portalMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class portalMenuService {
    @Autowired
    private portalMenuRepository portalMenuRepository;

    //método para guardar un portalMenu
    public portalMenu save(portalMenu portalMenu) {
        portalMenu.setId(null);
        return portalMenuRepository.save(portalMenu);
    }
    //método para listar todos los portalMenu
    public List<portalMenu> findAll() {
        return portalMenuRepository.findAll();
    }
    //método que devuelve un portalMenu filtrando por Id, null si no existe
    // añadir control basico excep
    public portalMenu findById(Integer id) {
        portalMenu portal = portalMenuRepository.findById(id).orElse(null);
        return portal;
    }
    //método para eliminar un portalMenuRepo
    public void deleteById(Integer id) {
        portalMenuRepository.deleteById(id);
    }
    //método para actualizar un portalMenu
    public portalMenu update(portalMenu portalMenu) {
        return portalMenuRepository.save(portalMenu);
    }

    public List<portalMenu> findByNivel(Integer nivel) {
        return portalMenuRepository.findByNivel(nivel);
    }
    public List<portalMenu> findByPadre(Integer idPadre) {
        return portalMenuRepository.findByIdPadre(idPadre);
    }

}
