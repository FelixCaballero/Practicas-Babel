package com.example.portal_paciente.controller;
import com.example.portal_paciente.DTO.portalPaginaCreateDTO;
import com.example.portal_paciente.DTO.portalPaginaDTO;
import com.example.portal_paciente.DTO.portalPaginaUpdateDTO;
import com.example.portal_paciente.service.portalPaginaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/portal/pagina")
@CrossOrigin(origins = "http://localhost:4200")
public class portalPaginaController {
    @Autowired
    private portalPaginaService portalPaginaService;

    @PostMapping("/create")
    public ResponseEntity<portalPaginaDTO> save(@RequestBody portalPaginaCreateDTO portalPagina) {
        return ResponseEntity.ok(portalPaginaService.save(portalPagina));
    }
    @GetMapping()
    public ResponseEntity<List<portalPaginaDTO>> findAll() {
        return ResponseEntity.ok(portalPaginaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<portalPaginaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(portalPaginaService.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        portalPaginaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<portalPaginaDTO> update(@PathVariable Integer id,@RequestBody portalPaginaUpdateDTO portalPagina) {
        return ResponseEntity.ok(portalPaginaService.update(id,portalPagina));
    }
    @GetMapping("/search/{filtro}")
    public ResponseEntity<portalPaginaDTO> search(@PathVariable String filtro) {
        Optional<portalPaginaDTO> pagina;

        if (filtro.matches("\\d+")) {
            pagina = Optional.ofNullable(portalPaginaService.findById((int) Long.parseLong(filtro)));
        } else {
            pagina = Optional.ofNullable(portalPaginaService.findByDescripcion(filtro));
        }

        return pagina.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
