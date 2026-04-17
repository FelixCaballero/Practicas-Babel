package com.babel.crudfullstack.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MenuDTO {

    private Integer idMenu;
    private Integer idPadre;

    @NotBlank(message = "El idioma es obligatorio")
    private String idLang;

    @NotBlank(message = "El nombre del menú es obligatorio")
    @Size(max = 120, message = "El nombre no puede superar los 120 caracteres")
    private String nomMenu;

    @Size(max = 120, message = "La ruta no puede superar los 120 caracteres")
    private String hrefMenu;

    @NotNull(message = "El nivel es obligatorio")
    private Integer nivel;

    @NotNull(message = "La posición raíz es obligatoria")
    private Integer posicionRaiz;

    @NotNull(message = "La posición es obligatoria")
    private Integer posicion;

    public MenuDTO() {}

    // Getters y Setters
    public Integer getIdMenu() { return idMenu; }
    public void setIdMenu(Integer idMenu) { this.idMenu = idMenu; }
    public Integer getIdPadre() { return idPadre; }
    public void setIdPadre(Integer idPadre) { this.idPadre = idPadre; }
    public String getIdLang() { return idLang; }
    public void setIdLang(String idLang) { this.idLang = idLang; }
    public String getNomMenu() { return nomMenu; }
    public void setNomMenu(String nomMenu) { this.nomMenu = nomMenu; }
    public String getHrefMenu() { return hrefMenu; }
    public void setHrefMenu(String hrefMenu) { this.hrefMenu = hrefMenu; }
    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }
    public Integer getPosicionRaiz() { return posicionRaiz; }
    public void setPosicionRaiz(Integer posicionRaiz) { this.posicionRaiz = posicionRaiz; }
    public Integer getPosicion() { return posicion; }
    public void setPosicion(Integer posicion) { this.posicion = posicion; }
}
