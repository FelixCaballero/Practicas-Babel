package com.example.portal_paciente.controller;
import com.example.portal_paciente.model.portalPagina;
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
    public ResponseEntity<portalPagina> save(@RequestBody portalPagina portalPagina) {
        return ResponseEntity.ok(portalPaginaService.save(portalPagina));
    }
    @GetMapping()
    public ResponseEntity<List<portalPagina>> findAll() {
        return ResponseEntity.ok(portalPaginaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<portalPagina> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(portalPaginaService.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        portalPaginaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<portalPagina> update(@PathVariable Long id,@RequestBody portalPagina portalPagina) {
        return ResponseEntity.ok(portalPaginaService.update(portalPagina));
    }
    @GetMapping("/search/{filtro}")
    public ResponseEntity<portalPagina> search(@PathVariable String filtro) {
        Optional<portalPagina> pagina;

        if (filtro.matches("\\d+")) {
            pagina = Optional.ofNullable(portalPaginaService.findById((int) Long.parseLong(filtro)));
        } else {
            pagina = portalPaginaService.findByDescripcion(filtro);
        }

        return pagina.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
