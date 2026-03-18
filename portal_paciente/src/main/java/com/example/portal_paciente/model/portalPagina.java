package com.example.portal_paciente.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "portal_pagina")
public class portalPagina {
    @Id
    @Column(name = "id_pagina", length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 120)
    private String descripcion;

    @Column(name = "pagina", columnDefinition = "LONGTEXT")
    private String pagina;

    @Column(name = "migas_pan", length = 120)
    private String migasPan;

    @Column(length = 120)
    private String titulo;

    @Column(name = "id_lang", length = 1)
    private String idLang;
}
