package com.example.portal_paciente.mappers;

import com.example.portal_paciente.DTO.portalPaginaCreateDTO;
import com.example.portal_paciente.DTO.portalPaginaDTO;
import com.example.portal_paciente.DTO.portalPaginaUpdateDTO;
import com.example.portal_paciente.model.portalPagina;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface portalPaginaMapper {
    portalPaginaDTO toDTO(portalPagina portalPagina);

    portalPagina toEntity(portalPagina portalPagina);

    List<portalPaginaDTO> toDTO(List<portalPagina> portalPaginas);

    portalPagina toEntity(portalPaginaCreateDTO portalPaginaCreateDTO);

    void updatePaginaFromDTO(portalPaginaUpdateDTO portalPaginaUpdateDTO, @MappingTarget portalPagina portalPagina);
}
