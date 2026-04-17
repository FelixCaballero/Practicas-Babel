package com.babel.crudfullstack.spring.model;

import java.io.Serializable;
import java.util.Objects;


public class MenuId implements Serializable {
    private Integer idMenu;
    private String idLang;

    public MenuId() {}

    public MenuId(Integer idMenu, String idLang) {
        this.idMenu = idMenu;
        this.idLang = idLang;
    }

    public Integer getIdMenu() { return idMenu; }
    public void setIdMenu(Integer idMenu) { this.idMenu = idMenu; }

    public String getIdLang() { return idLang; }
    public void setIdLang(String idLang) { this.idLang = idLang; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuId menuId = (MenuId) o;
        return Objects.equals(idMenu, menuId.idMenu) && Objects.equals(idLang, menuId.idLang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMenu, idLang);
    }
}
