package com.babel.crudfullstack.spring.controller;

import com.babel.crudfullstack.spring.dto.MenuDTO;
import com.babel.crudfullstack.spring.mapper.MenuMapper;
import com.babel.crudfullstack.spring.model.Menu;
import com.babel.crudfullstack.spring.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST que expone las operaciones (CRUD) para la jerarquía de Menús del portal usando DTOs.
 */
@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Menús", description = "Endpoints para la gestión de menús de navegación")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    /**
     * Recupera todos los menús (padres e hijos) de la base de datos.
     * @return Listado de entidades MenuDTO.
     */
    @GetMapping
    @Operation(summary = "Obtener todos los menús", description = "Devuelve los menús de navegación como DTOs registrados en base de datos.")
    public List<MenuDTO> getAll() { 
        return menuRepository.findAll().stream()
                .map(menuMapper::toDTO)
                .collect(Collectors.toList()); 
    }

    /**
     * Crea un nuevo menú de navegación.
     * Genera un nuevo ID_MENU si no se proporciona (para el primer lenguaje).
     * @param menuDTO Entidad Menu obtenida desde el cliente.
     * @return El propio menú creado más su identificador.
     */
    @PostMapping
    @Operation(summary = "Crear un nuevo menú", description = "Persiste un menú. Autogenera el ID incremental si se omite.")
    public MenuDTO create(@Valid @RequestBody MenuDTO menuDTO) {
        if (menuDTO.getIdMenu() == null) {
            Integer maxId = menuRepository.findMaxIdMenu();
            menuDTO.setIdMenu(maxId == null ? 1 : maxId + 1);
        }
        Menu entityToSave = menuMapper.toEntity(menuDTO);
        Menu savedEntity = menuRepository.save(entityToSave);
        return menuMapper.toDTO(savedEntity);
    }

    /**
     * Actualiza un menú preexistente según el ID proporcionado por URL.
     * @param id Identificador numérico del menú.
     * @param menuDTO Contenido de base para la actualización.
     * @return Menú modificado en BBDD.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un menú por ID", description = "Actualiza los datos de un menú existente.")
    public MenuDTO update(@PathVariable Integer id, @Valid @RequestBody MenuDTO menuDTO) {
        menuDTO.setIdMenu(id);
        Menu entityToSave = menuMapper.toEntity(menuDTO);
        Menu savedEntity = menuRepository.save(entityToSave);
        return menuMapper.toDTO(savedEntity);
    }

    /**
     * Elimina todos los menús (de todos los lenguajes) correspondientes al ID especificado.
     * @param id Identificador del menú que se desea borrar del sistema.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar un menú por ID", description = "Elimina permanentemente un menú por su identificador.")
    public void delete(@PathVariable Integer id) { menuRepository.deleteByIdMenu(id); }

    @DeleteMapping("/{id}/lang/{idLang}")
    @Operation(summary = "Borrar un menú por ID y Lenguaje", description = "Elimina permanentemente un menú por su identificador y lenguaje.")
    public void deleteByLanguage(@PathVariable Integer id, @PathVariable String idLang) {
        menuRepository.deleteByIdMenuAndIdLang(id, idLang);
    }
}