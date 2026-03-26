package com.babel.crudfullstack.spring.controller;

import com.babel.crudfullstack.spring.model.Pagina;
import com.babel.crudfullstack.spring.repository.PaginaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST que expone los endpoints para la gestión de Páginas.
 * Permite listar, crear, modificar y eliminar páginas desde el Frontend.
 */
@RestController
@RequestMapping("/api/paginas")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Páginas", description = "Endpoints para la gestión de páginas de contenido")
public class PaginaController {

    @Autowired
    private PaginaRepository paginaRepository;

    /**
     * Recupera todas las páginas registradas en la base de datos.
     * @return Lista de objetos Pagina.
     */
    @GetMapping
    @Operation(summary = "Obtener todas las páginas", description = "Devuelve una lista completa de todas las páginas configuradas.")
    public List<Pagina> getAll() { return paginaRepository.findAll(); }

    /**
     * Recupera una página específica por su identificador.
     * @param id Identificador de la página buscada.
     * @return ResponseEntity con la Página si existe, o 404 (Not Found) en caso contrario.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una página por ID", description = "Devuelve los datos de una página pasándole su ID.")
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
    @Operation(summary = "Crear nueva página", description = "Persiste una nueva página en el sistema. Genera automáticamente el ID si no se especifica.")
    public Pagina create(@Valid @RequestBody Pagina pagina) {
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
    @Operation(summary = "Actualizar página", description = "Actualiza los datos de una página existente en la base de datos.")
    public Pagina update(@PathVariable String id, @Valid @RequestBody Pagina pagina) {
        pagina.setIdPagina(id);
        return paginaRepository.save(pagina);
    }

    /**
     * Borra permanentemente una página de la base de datos por su ID.
     * @param id Identificador de la página a borrar.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar página", description = "Elimina permanentemente una página por su identificador.")
    public void delete(@PathVariable String id) { paginaRepository.deleteById(id); }
}