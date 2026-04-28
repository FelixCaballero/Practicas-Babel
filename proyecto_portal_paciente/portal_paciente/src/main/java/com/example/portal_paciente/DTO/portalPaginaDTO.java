package com.example.portal_paciente.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class portalPaginaDTO {
    private Integer id;

    private String descripcion;

    private String pagina;

    private String migasPan;

    private String titulo;

    private Integer idLang;

    private Integer idCompleto;
}
