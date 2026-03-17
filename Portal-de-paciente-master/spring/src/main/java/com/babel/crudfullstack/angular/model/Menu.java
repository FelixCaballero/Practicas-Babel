package com.babel.crudfullstack.angular.model;

import jakarta.persistence.*;

@Entity
@Table(name = "portal_menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MENU")
    private Integer idMenu; // Identificador

    @Column(name = "ID_PADRE")
    private Integer idPadre; // ID del padre para jerarquía

    @Column(name = "ID_LANG", length = 1)
    private String idLang; // Id del lenguaje

    @Column(name = "NOM_MENU", length = 120)
    private String nomMenu;

    @Column(name = "HREF_MENU", length = 120)
    private String hrefMenu;

    @Column(name = "NIVEL")
    private Integer nivel;

    @Column(name = "POSICION_RAIZ")
    private Integer posicionRaiz;

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