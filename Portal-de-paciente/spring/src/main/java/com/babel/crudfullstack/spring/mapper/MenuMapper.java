package com.babel.crudfullstack.spring.mapper;

import com.babel.crudfullstack.spring.dto.MenuDTO;
import com.babel.crudfullstack.spring.model.Menu;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    public MenuDTO toDTO(Menu menu) {
        if (menu == null) {
            return null;
        }

        MenuDTO dto = new MenuDTO();
        dto.setIdMenu(menu.getIdMenu());
        dto.setIdPadre(menu.getIdPadre());
        dto.setIdLang(menu.getIdLang());
        dto.setNomMenu(menu.getNomMenu());
        dto.setHrefMenu(menu.getHrefMenu());
        dto.setNivel(menu.getNivel());
        dto.setPosicionRaiz(menu.getPosicionRaiz());
        dto.setPosicion(menu.getPosicion());

        return dto;
    }

    public Menu toEntity(MenuDTO dto) {
        if (dto == null) {
            return null;
        }

        Menu menu = new Menu();
        menu.setIdMenu(dto.getIdMenu());
        menu.setIdPadre(dto.getIdPadre());
        menu.setIdLang(dto.getIdLang());
        menu.setNomMenu(dto.getNomMenu());
        menu.setHrefMenu(dto.getHrefMenu());
        menu.setNivel(dto.getNivel());
        menu.setPosicionRaiz(dto.getPosicionRaiz());
        menu.setPosicion(dto.getPosicion());

        return menu;
    }
}
