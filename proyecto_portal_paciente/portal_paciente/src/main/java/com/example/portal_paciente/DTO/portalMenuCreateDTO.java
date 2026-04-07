package com.example.portal_paciente.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class portalMenuCreateDTO {

    private Integer idPadre;

    private Integer idLang;

    private String nomMenu;

    private String hrefMenu;

    private Integer nivel;

    private Integer posicionRaiz;

    private Integer posicion;
}
