package com.babel.crudfullstack.spring.controller;

import com.babel.crudfullstack.spring.model.Menu;
import com.babel.crudfullstack.spring.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST que expone las operaciones (CRUD) para la jerarquía de Menús del portal.
 */
@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Menús", description = "Endpoints para la gestión de menús de navegación")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    /**
     * Recupera todos los menús (padres e hijos) de la base de datos.
     * @return Listado de entidades Menu.
     */
    @GetMapping
    @Operation(summary = "Obtener todos los menús", description = "Devuelve los menús de navegación registrados en base de datos.")
    public List<Menu> getAll() { return menuRepository.findAll(); }

    /**
     * Crea un nuevo menú de navegación.
     * Genera un nuevo ID_MENU si no se proporciona (para el primer lenguaje).
     * @param menu Entidad Menu obtenida desde el cliente.
     * @return El propio menú creado más su identificador.
     */
    @PostMapping
    @Operation(summary = "Crear un nuevo menú", description = "Persiste un menú. Autogenera el ID incremental si se omite.")
    public Menu create(@Valid @RequestBody Menu menu) {
        if (menu.getIdMenu() == null) {
            Integer maxId = menuRepository.findMaxIdMenu();
            menu.setIdMenu(maxId == null ? 1 : maxId + 1);
        }
        return menuRepository.save(menu);
    }

    /**
     * Actualiza un menú preexistente según el ID proporcionado por URL.
     * @param id Identificador numérico del menú.
     * @param menu Contenido de base para la actualización.
     * @return Menú modificado en BBDD.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un menú por ID", description = "Actualiza los datos de un menú existente.")
    public Menu update(@PathVariable Integer id, @Valid @RequestBody Menu menu) {
        menu.setIdMenu(id);
        return menuRepository.save(menu);
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