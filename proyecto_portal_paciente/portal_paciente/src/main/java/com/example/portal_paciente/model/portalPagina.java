package com.example.portal_paciente.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
    private Integer idLang;

    @Column(name = "id_completo")
    private Integer idCompleto;

    public portalPagina(Integer id, String descripcion, String pagina, String migasPan, String titulo, Integer idLang) {
        this.id = id;
        this.descripcion = descripcion;
        this.pagina = pagina;
        this.migasPan = migasPan;
        this.titulo = titulo;
        this.idLang = idLang;
        this.idCompleto = this.idLang*10000+this.id;
    }
    @PostLoad
    @PostPersist
    @PostUpdate
    public void calculateIdCompleto() {
        if (idLang != null && id != null) {
            this.idCompleto = idLang * 10000 + id;
        }
    }
}
