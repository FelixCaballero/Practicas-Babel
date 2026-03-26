package com.babel.crudfullstack.spring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "portal_menu")
@IdClass(MenuId.class)
public class Menu {
    @Id
    @Column(name = "ID_MENU")
    private Integer idMenu; // Identificador

    @Column(name = "ID_PADRE")
    private Integer idPadre; // ID del padre para jerarquía

    @Id
    @NotBlank(message = "El idioma es obligatorio")
    @Column(name = "ID_LANG", length = 1)
    private String idLang; // Id del lenguaje

    @NotBlank(message = "El nombre del menú es obligatorio")
    @Size(max = 120, message = "El nombre no puede superar los 120 caracteres")
    @Column(name = "NOM_MENU", length = 120)
    private String nomMenu;

    @Size(max = 120, message = "La ruta no puede superar los 120 caracteres")
    @Column(name = "HREF_MENU", length = 120)
    private String hrefMenu;

    @NotNull(message = "El nivel es obligatorio")
    @Column(name = "NIVEL")
    private Integer nivel;

    @NotNull(message = "La posición raíz es obligatoria")
    @Column(name = "POSICION_RAIZ")
    private Integer posicionRaiz;

    @NotNull(message = "La posición es obligatoria")
    @Column(name = "POSICION")
    private Integer posicion;

    public Menu() {}

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