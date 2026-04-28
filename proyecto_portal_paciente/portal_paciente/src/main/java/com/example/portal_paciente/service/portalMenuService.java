package com.example.portal_paciente.service;

import com.example.portal_paciente.Configuration.portalMenuValidator;
import com.example.portal_paciente.DTO.portalMenuCreateDTO;
import com.example.portal_paciente.DTO.portalMenuDTO;
import com.example.portal_paciente.DTO.portalMenuUpdateDTO;
import com.example.portal_paciente.mappers.portalMenuMapper;
import com.example.portal_paciente.model.portalMenu;
import com.example.portal_paciente.repository.portalMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class portalMenuService {

    private final portalMenuRepository portalMenuRepository;
    private final portalMenuMapper portalMenuMapper;
    private final portalMenuValidator portalMenuValidator;


    public portalMenuDTO save(portalMenuCreateDTO portalMenu) {

        return portalMenuMapper.toDTO(portalMenuRepository.save(portalMenuMapper.toEntity(portalMenu)));
    }

    public List<portalMenuDTO> findAll() {
      return portalMenuMapper.toDTO(portalMenuRepository.findAll());
    }

    public portalMenuDTO findById(Integer id) {
       return portalMenuMapper.toDTO(portalMenuValidator.validar(id));
    }

    public void deleteById(Integer id) {
        portalMenuRepository.deleteById(id);
    }

    public portalMenuDTO update(Integer id, portalMenuUpdateDTO portalMenu) {
        portalMenu  p1 = portalMenuValidator.validar(id);
        portalMenuMapper.updateMenuFromDTO(portalMenu, p1);
        return portalMenuMapper.toDTO(portalMenuRepository.save(p1));
    }

    public List<portalMenuDTO> findByNivel(Integer nivel) {
        List<portalMenu> pP = portalMenuRepository.findByNivel(nivel);
        List<portalMenuDTO> portalMenuDTOS = new ArrayList<>();
        for (portalMenu portalMenu : pP) {
            portalMenuDTOS.add(portalMenuMapper.toDTO(portalMenu));
        }
        return portalMenuDTOS;
    }
    public List<portalMenuDTO> findByPadre(Integer idPadre) {
        List<portalMenu> pP = portalMenuRepository.findByIdPadre(idPadre);
        List<portalMenuDTO> portalMenuDTOS = new ArrayList<>();
        for (portalMenu portalMenu : pP) {
            portalMenuDTOS.add(portalMenuMapper.toDTO(portalMenu));
        }
        return portalMenuDTOS;
    }

}
