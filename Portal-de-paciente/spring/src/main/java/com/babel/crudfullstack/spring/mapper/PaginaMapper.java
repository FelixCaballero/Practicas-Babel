package com.babel.crudfullstack.spring.mapper;

import com.babel.crudfullstack.spring.dto.PaginaDTO;
import com.babel.crudfullstack.spring.model.Pagina;
import org.springframework.stereotype.Component;

@Component
public class PaginaMapper {

    public PaginaDTO toDTO(Pagina pagina) {
        if (pagina == null) {
            return null;
        }

        PaginaDTO dto = new PaginaDTO();
        dto.setIdPagina(pagina.getIdPagina());
        dto.setDescripcion(pagina.getDescripcion());
        dto.setPagina(pagina.getPagina());
        dto.setMigasPan(pagina.getMigasPan());
        dto.setTitulo(pagina.getTitulo());
        dto.setIdLang(pagina.getIdLang());

        return dto;
    }

    public Pagina toEntity(PaginaDTO dto) {
        if (dto == null) {
            return null;
        }

        Pagina pagina = new Pagina();
        pagina.setIdPagina(dto.getIdPagina());
        pagina.setDescripcion(dto.getDescripcion());
        pagina.setPagina(dto.getPagina());
        pagina.setMigasPan(dto.getMigasPan());
        pagina.setTitulo(dto.getTitulo());
        pagina.setIdLang(dto.getIdLang());

        return pagina;
    }
}
