package com.example.portal_paciente.service;

import com.example.portal_paciente.DTO.portalMenuCreateDTO;
import com.example.portal_paciente.DTO.portalMenuDTO;
import com.example.portal_paciente.DTO.portalMenuUpdateDTO;
import com.example.portal_paciente.DTO.portalPaginaDTO;
import com.example.portal_paciente.model.portalMenu;
import com.example.portal_paciente.model.portalPagina;
import com.example.portal_paciente.repository.portalMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class portalMenuService {
    @Autowired
    private portalMenuRepository portalMenuRepository;

    //método para guardar un portalMenu
    public portalMenuDTO save(portalMenuCreateDTO portalMenu) {
        portalMenu p1 = new portalMenu();
        p1.setIdPadre(portalMenu.getIdPadre());
        p1.setIdLang(portalMenu.getIdLang());
        p1.setNomMenu(portalMenu.getNomMenu());
        p1.setHrefMenu(portalMenu.getHrefMenu());
        p1.setNivel(portalMenu.getNivel());
        p1.setPosicionRaiz(portalMenu.getPosicionRaiz());
        p1.setPosicion(portalMenu.getPosicion());

        return toDTO(portalMenuRepository.save(p1));
    }
    private static portalMenuDTO toDTO(portalMenu pP){
        portalMenuDTO portalMenuDTO= new portalMenuDTO();
        portalMenuDTO.setId(pP.getId());
        portalMenuDTO.setIdPadre(pP.getIdPadre());
        portalMenuDTO.setIdLang(pP.getIdLang());
        portalMenuDTO.setNomMenu(pP.getNomMenu());
        portalMenuDTO.setHrefMenu(pP.getHrefMenu());
        portalMenuDTO.setNivel(pP.getNivel());
        portalMenuDTO.setPosicionRaiz(pP.getPosicionRaiz());
        portalMenuDTO.setPosicion(pP.getPosicion());
        portalMenuDTO.setIdCompleto(pP.getIdCompleto());
        return portalMenuDTO;
    }

    //método para listar todos los portalMenu
    public List<portalMenuDTO> findAll() {
        List<portalMenu> pP = portalMenuRepository.findAll();
        List<portalMenuDTO> portalMenuDTOS = new ArrayList<>();
        for (portalMenu portalMenu : pP) {
            portalMenuDTOS.add(toDTO(portalMenu));
        }
        return portalMenuDTOS;
    }
    //método que devuelve un portalMenu filtrando por Id, null si no existe
    public portalMenuDTO findById(Integer id) {
        portalMenu portal = portalMenuRepository.findById(id).orElse(null);
        portalMenuDTO portalMenuDTO = toDTO(portal);
        return portalMenuDTO;
    }
    //método para eliminar un portalMenuRepo
    public void deleteById(Integer id) {
        portalMenuRepository.deleteById(id);
    }
    //método para actualizar un portalMenu
    public portalMenuDTO update(Integer id, portalMenuUpdateDTO portalMenu) {
        portalMenu  p1 = portalMenuRepository.findById(id).orElse(null);
        p1.setIdPadre(portalMenu.getIdPadre());
        p1.setIdLang(portalMenu.getIdLang());
        p1.setNomMenu(portalMenu.getNomMenu());
        p1.setHrefMenu(portalMenu.getHrefMenu());
        p1.setNivel(portalMenu.getNivel());
        p1.setPosicionRaiz(portalMenu.getPosicionRaiz());
        p1.setPosicion(portalMenu.getPosicion());
        return toDTO(portalMenuRepository.save(p1));
    }

    public List<portalMenuDTO> findByNivel(Integer nivel) {
        List<portalMenu> pP = portalMenuRepository.findByNivel(nivel);
        List<portalMenuDTO> portalMenuDTOS = new ArrayList<>();
        for (portalMenu portalMenu : pP) {
            portalMenuDTOS.add(toDTO(portalMenu));
        }
        return portalMenuDTOS;
    }
    public List<portalMenuDTO> findByPadre(Integer idPadre) {
        List<portalMenu> pP = portalMenuRepository.findByIdPadre(idPadre);
        List<portalMenuDTO> portalMenuDTOS = new ArrayList<>();
        for (portalMenu portalMenu : pP) {
            portalMenuDTOS.add(toDTO(portalMenu));
        }
        return portalMenuDTOS;
    }

}
