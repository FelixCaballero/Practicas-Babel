package com.example.portal_paciente.mappers;

import com.example.portal_paciente.DTO.portalMenuCreateDTO;
import com.example.portal_paciente.DTO.portalMenuDTO;
import com.example.portal_paciente.DTO.portalMenuUpdateDTO;
import com.example.portal_paciente.model.portalMenu;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface portalMenuMapper {
    portalMenuDTO toDTO(portalMenu portalMenu);

    portalMenu toEntity(portalMenu portalMenu);

    List<portalMenuDTO> toDTO(List<portalMenu> portalMenus);

    portalMenu toEntity(portalMenuCreateDTO portalMenuCreateDTODTO);

    void updateMenuFromDTO (portalMenuUpdateDTO portalMenuUpdateDTO, @MappingTarget portalMenu portalMenu);
}
