package com.example.portal_paciente.controller;

import com.example.portal_paciente.model.portalMenu;
import com.example.portal_paciente.service.portalMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/portal/menu")
@CrossOrigin(origins = "http://localhost:4200")
public class portalMenuController {
    @Autowired
    private portalMenuService portalMenuService;

    @PostMapping("/create")
    public ResponseEntity<portalMenu> save(@RequestBody portalMenu portalMenu) {
        return ResponseEntity.ok(portalMenuService.save(portalMenu));
    }
    @GetMapping()
    public ResponseEntity<List<portalMenu>> findAll() {
        return ResponseEntity.ok(portalMenuService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<portalMenu> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(portalMenuService.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        portalMenuService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<portalMenu> update(@PathVariable Long id, @RequestBody portalMenu menu) {
        return ResponseEntity.ok(portalMenuService.update(menu));
    }
    @GetMapping("/search")
    public ResponseEntity<portalMenu> search(@RequestParam String filtro) {
        try {
            Integer id = Integer.parseInt(filtro);
            Optional<portalMenu> menuOpt = Optional.ofNullable(portalMenuService.findById(id));

            if (menuOpt.isPresent()) {
                return ResponseEntity.ok(menuOpt.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<portalMenu>> getByNivel(@PathVariable Integer nivel) {
        List<portalMenu> menus = portalMenuService.findByNivel(nivel);
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/padre/{padre}")
    public ResponseEntity<List<portalMenu>> findByPadre(@PathVariable Integer padre) {
        return ResponseEntity.ok(portalMenuService.findByPadre(padre));
    }

}
