package com.babel.crudfullstack.angular.controller;

import com.babel.crudfullstack.angular.model.Menu;
import com.babel.crudfullstack.angular.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone las operaciones (CRUD) para la jerarquía de Menús del portal.
 */
@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = "http://localhost:4200")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    /**
     * Recupera todos los menús (padres e hijos) de la base de datos.
     * @return Listado de entidades Menu.
     */
    @GetMapping
    public List<Menu> getAll() { return menuRepository.findAll(); }

    /**
     * Crea un nuevo menú de navegación.
     * El ID_MENU (PK) se generará de manera autoincremental en la base de datos.
     * @param menu Entidad Menu obtenida desde el cliente.
     * @return El propio menú creado más su identificador autogenerado.
     */
    @PostMapping
    public Menu create(@RequestBody Menu menu) {
        menu.setIdMenu(null);
        return menuRepository.save(menu);
    }

    /**
     * Actualiza un menú preexistente según el ID proporcionado por URL.
     * @param id Identificador numérico del menú.
     * @param menu Contenido de base para la actualización.
     * @return Menú modificado en BBDD.
     */
    @PutMapping("/{id}")
    public Menu update(@PathVariable Integer id, @RequestBody Menu menu) {
        menu.setIdMenu(id);
        return menuRepository.save(menu);
    }

    /**
     * Elimina el menú correspondiente al ID especificado.
     * @param id Identificador del menú que se desea borrar del sistema.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) { menuRepository.deleteById(id); }
}