package com.example.portal_paciente.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private String idLang;

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
}
