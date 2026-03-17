package com.babel.crudfullstack.angular.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "portal_pagina")
public class Pagina {
    @Id
    @Column(name = "id_pagina", length = 10)
    private String idPagina;

    @Column(name = "descripcion", length = 120)
    private String descripcion;

    @Lob
    @Column(name = "pagina", columnDefinition = "TEXT")
    private String pagina;

    @Column(name = "migas_pan", length = 120)
    private String migasPan;

    @Column(name = "titulo", length = 120)
    private String titulo;

    @Column(name = "ID_LANG", length = 1)
    private String idLang;

    public Pagina() {}

    @PrePersist
    public void prePersist() {
        if (this.idPagina == null || this.idPagina.isEmpty()) {
            // Generate a random 10-character ID
            this.idPagina = "P" + UUID.randomUUID().toString().replace("-", "").substring(0, 9).toUpperCase();
        }
    }

    // Getters y Setters
    public String getIdPagina() { return idPagina; }
    public void setIdPagina(String idPagina) { this.idPagina = idPagina; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getPagina() { return pagina; }
    public void setPagina(String pagina) { this.pagina = pagina; }
    public String getMigasPan() { return migasPan; }
    public void setMigasPan(String migasPan) { this.migasPan = migasPan; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getIdLang() { return idLang; }
    public void setIdLang(String idLang) { this.idLang = idLang; }
}