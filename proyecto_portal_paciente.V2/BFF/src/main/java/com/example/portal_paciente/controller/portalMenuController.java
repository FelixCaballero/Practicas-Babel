package com.example.portal_paciente.controller;

import BackOffice.portal_paciente.model.PortalMenuDTO;
import com.example.portal_paciente.DTO.portalMenuCreateDTO;
import com.example.portal_paciente.DTO.portalMenuDTO;
import com.example.portal_paciente.DTO.portalMenuUpdateDTO;
import com.example.portal_paciente.service.portalMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portal/menu")
//@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class portalMenuController {

    private final portalMenuService portalMenuService;

//    @PostMapping("/create")
//    public ResponseEntity<portalMenuDTO> save(@RequestBody portalMenuCreateDTO portalMenu) {
//        return ResponseEntity.ok(portalMenuService.save(portalMenu));
//    }

    @GetMapping()
    public ResponseEntity<List<PortalMenuDTO>> findAll() {
        return ResponseEntity.ok(portalMenuService.findAll());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<portalMenuDTO> findById(@PathVariable Integer id) {
//        return ResponseEntity.ok(portalMenuService.findById(id));
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
//        portalMenuService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<portalMenuDTO> update(@PathVariable Integer id, @RequestBody portalMenuUpdateDTO menu) {
//        return ResponseEntity.ok(portalMenuService.update(id, menu));
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<portalMenuDTO> search(@RequestParam String filtro) {
//        return ResponseEntity.ok(portalMenuService.search(filtro));
//        /*try {
//            Integer id = Integer.parseInt(filtro);
//            Optional<portalMenuDTO> menuOpt = Optional.ofNullable(portalMenuService.findById(id));
//
//            if (menuOpt.isPresent()) {//retorna true o false
//                return ResponseEntity.ok(menuOpt.get());
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (NumberFormatException e) {
//            return ResponseEntity.badRequest().build();
//        }*/
//    }
//
//    @GetMapping("/nivel/{nivel}")
//    public ResponseEntity<List<portalMenuDTO>> getByNivel(@PathVariable Integer nivel) {
//        return ResponseEntity.ok(portalMenuService.findByNivel(nivel));
//    }
//
//    @GetMapping("/padre/{padre}")
//    public ResponseEntity<List<portalMenuDTO>> findByPadre(@PathVariable Integer padre) {
//        return ResponseEntity.ok(portalMenuService.findByPadre(padre));
//    }

}
