package com.example.portal_paciente.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class portalPaginaUpdateDTO {
    private String descripcion;

    private String pagina;

    private String migasPan;

    private String titulo;

    private Integer idLang;

    private Integer idCompleto;
}
