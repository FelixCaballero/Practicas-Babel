package com.babel.crudfullstack.angular.controller;

import com.babel.crudfullstack.angular.model.Pagina;
import com.babel.crudfullstack.angular.repository.PaginaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST que expone los endpoints para la gestión de Páginas.
 * Permite listar, crear, modificar y eliminar páginas desde el Frontend.
 */
@RestController
@RequestMapping("/api/paginas")
@CrossOrigin(origins = "http://localhost:4200")
public class PaginaController {

    @Autowired
    private PaginaRepository paginaRepository;

    /**
     * Recupera todas las páginas registradas en la base de datos.
     * @return Lista de objetos Pagina.
     */
    @GetMapping
    public List<Pagina> getAll() { return paginaRepository.findAll(); }

    /**
     * Recupera una página específica por su identificador.
     * @param id Identificador de la página buscada.
     * @return ResponseEntity con la Página si existe, o 404 (Not Found) en caso contrario.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pagina> getById(@PathVariable String id) {
        return paginaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea y persiste una nueva página en el sistema.
     * Si no se proporciona un ID válido, se genera automáticamente de manera correlativa (ej. P000000001).
     * @param pagina Datos de la página recibidos en la petición HTTP.
     * @return La nueva página con su ID autogenerado o persistido.
     */
    @PostMapping
    public Pagina create(@RequestBody Pagina pagina) {
        if (pagina.getIdPagina() == null || pagina.getIdPagina().isEmpty() || pagina.getIdPagina().equals("0")) {
            String maxId = paginaRepository.findMaxId();
            if (maxId == null || !maxId.startsWith("P")) {
                pagina.setIdPagina("P000000001");
            } else {
                try {
                    int nextNum = Integer.parseInt(maxId.substring(1)) + 1;
                    pagina.setIdPagina(String.format("P%09d", nextNum));
                } catch(Exception e) {
                    pagina.setIdPagina("P" + java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 9).toUpperCase());
                }
            }
        }
        return paginaRepository.save(pagina); 
    }

    /**
     * Actualiza los datos de una página existente.
     * @param id Identificador de la página a modificar.
     * @param pagina Información actualizada de la página.
     * @return La página modificada tras ser guardada en base de datos.
     */
    @PutMapping("/{id}")
    public Pagina update(@PathVariable String id, @RequestBody Pagina pagina) {
        pagina.setIdPagina(id);
        return paginaRepository.save(pagina);
    }

    /**
     * Borra permanentemente una página de la base de datos por su ID.
     * @param id Identificador de la página a borrar.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) { paginaRepository.deleteById(id); }
}