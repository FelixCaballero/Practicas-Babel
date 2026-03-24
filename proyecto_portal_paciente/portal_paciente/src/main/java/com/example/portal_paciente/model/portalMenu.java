package com.example.portal_paciente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "portal_menu")
public class portalMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_column",length = 4)
    private Integer id;

    @Column(name = "id_padre",length = 4)
    private Integer idPadre;

    @Column(name = "id_lang", length = 1)
    private Integer idLang;

    @Column(name = "nom_menu", length = 120)
    private String nomMenu;

    @Column(name = "href_menu", length = 120)
    private String hrefMenu;

    @Column(name = "nivel",length = 3)
    private Integer nivel;

    @Column(name = "posicion_raiz",length = 3)
    private Integer posicionRaiz;

    @Column(name = "posicion",length = 3)
    private Integer posicion;

    @Column(name = "id_completo")
    private Integer idCompleto;

    public portalMenu(Integer id, Integer idPadre, Integer idLang, String nomMenu, String hrefMenu,
                      Integer nivel, Integer posicionRaiz, Integer posicion) {
        this.id = id;
        this.idPadre = idPadre;
        this.idLang = idLang;
        this.nomMenu = nomMenu;
        this.hrefMenu = hrefMenu;
        this.nivel = nivel;
        this.posicionRaiz = posicionRaiz;
        this.posicion = posicion;
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
