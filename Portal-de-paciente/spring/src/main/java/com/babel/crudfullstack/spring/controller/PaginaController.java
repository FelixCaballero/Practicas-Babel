package com.babel.crudfullstack.spring.controller;

import com.babel.crudfullstack.spring.dto.PaginaDTO;
import com.babel.crudfullstack.spring.mapper.PaginaMapper;
import com.babel.crudfullstack.spring.model.Pagina;
import com.babel.crudfullstack.spring.repository.PaginaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST que expone los endpoints para la gestión de Páginas a través de DTOs.
 */
@RestController
@RequestMapping("/api/paginas")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Páginas", description = "Endpoints para la gestión de páginas de contenido")
public class PaginaController {

    @Autowired
    private PaginaRepository paginaRepository;
    
    @Autowired
    private PaginaMapper paginaMapper;

    /**
     * Recupera todas las páginas registradas en la base de datos.
     * @return Lista de objetos PaginaDTO.
     */
    @GetMapping
    @Operation(summary = "Obtener todas las páginas", description = "Devuelve una lista completa de todas las páginas configuradas como DTOs.")
    public List<PaginaDTO> getAll() { 
        return paginaRepository.findAll().stream()
                .map(paginaMapper::toDTO)
                .collect(Collectors.toList()); 
    }

    /**
     * Recupera una página específica por su identificador.
     * @param id Identificador de la página buscada.
     * @return ResponseEntity con la Página DTO si existe, o 404 (Not Found) en caso contrario.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una página por ID", description = "Devuelve los datos de una página pasándole su ID.")
    public ResponseEntity<PaginaDTO> getById(@PathVariable String id) {
        return paginaRepository.findById(id)
                .map(paginaMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea y persiste una nueva página en el sistema.
     * @param paginaDTO Datos de la página recibidos en la petición HTTP.
     * @return La nueva página DTO.
     */
    @PostMapping
    @Operation(summary = "Crear nueva página", description = "Persiste una nueva página en el sistema. Genera automáticamente el ID si no se especifica.")
    public PaginaDTO create(@Valid @RequestBody PaginaDTO paginaDTO) {
        if (paginaDTO.getIdPagina() == null || paginaDTO.getIdPagina().isEmpty() || paginaDTO.getIdPagina().equals("0")) {
            String maxId = paginaRepository.findMaxId();
            if (maxId == null || !maxId.startsWith("P")) {
                paginaDTO.setIdPagina("P000000001");
            } else {
                try {
                    int nextNum = Integer.parseInt(maxId.substring(1)) + 1;
                    paginaDTO.setIdPagina(String.format("P%09d", nextNum));
                } catch(Exception e) {
                    paginaDTO.setIdPagina("P" + java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 9).toUpperCase());
                }
            }
        }
        Pagina entityToSave = paginaMapper.toEntity(paginaDTO);
        Pagina savedEntity = paginaRepository.save(entityToSave);
        return paginaMapper.toDTO(savedEntity); 
    }

    /**
     * Actualiza los datos de una página existente.
     * @param id Identificador de la página a modificar.
     * @param paginaDTO Información actualizada de la página.
     * @return La página modificada.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar página", description = "Actualiza los datos de una página existente en la base de datos.")
    public PaginaDTO update(@PathVariable String id, @Valid @RequestBody PaginaDTO paginaDTO) {
        paginaDTO.setIdPagina(id);
        Pagina entityToSave = paginaMapper.toEntity(paginaDTO);
        Pagina savedEntity = paginaRepository.save(entityToSave);
        return paginaMapper.toDTO(savedEntity);
    }

    /**
     * Borra permanentemente una página de la base de datos por su ID.
     * @param id Identificador de la página a borrar.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar página", description = "Elimina permanentemente una página por su identificador.")
    public void delete(@PathVariable String id) { paginaRepository.deleteById(id); }
}