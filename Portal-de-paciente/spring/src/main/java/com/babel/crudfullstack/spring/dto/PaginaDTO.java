package com.babel.crudfullstack.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PaginaDTO {

    private String idPagina;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 120, message = "La descripción no puede superar los 120 caracteres")
    private String descripcion;

    @NotBlank(message = "El contenido de la página es obligatorio")
    private String pagina;

    private String migasPan;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 120, message = "El título debe tener entre 3 y 120 caracteres")
    private String titulo;

    @NotBlank(message = "Debe seleccionar un idioma válido")
    private String idLang;

    public PaginaDTO() {}

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
