package com.example.portal_paciente.service;

import com.example.portal_paciente.DTO.portalPaginaCreateDTO;
import com.example.portal_paciente.DTO.portalPaginaDTO;
import com.example.portal_paciente.DTO.portalPaginaUpdateDTO;
import com.example.portal_paciente.model.portalPagina;
import com.example.portal_paciente.repository.portalPaginaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class portalPaginaService {
    @Autowired
    private portalPaginaRepository portalPaginaRepository;

    //método para guardar un portalPagina
    public portalPaginaDTO save(portalPaginaCreateDTO portalPagina) {
        portalPagina portalPagina1 = new portalPagina();
        portalPagina1.setDescripcion(portalPagina.getDescripcion());
        portalPagina1.setPagina(portalPagina.getPagina());
        portalPagina1.setMigasPan(portalPagina.getMigasPan());
        portalPagina1.setTitulo(portalPagina.getTitulo());
        portalPagina1.setIdLang(portalPagina.getIdLang());
        return toDTO( portalPaginaRepository.save(portalPagina1));
    }
    private static portalPaginaDTO toDTO(portalPagina pP){
        portalPaginaDTO portalPaginaDTO= new portalPaginaDTO();
        portalPaginaDTO.setId(pP.getId());
        portalPaginaDTO.setDescripcion(pP.getDescripcion());
        portalPaginaDTO.setPagina(pP.getPagina());
        portalPaginaDTO.setMigasPan(pP.getMigasPan());
        portalPaginaDTO.setTitulo(pP.getTitulo());
        portalPaginaDTO.setIdLang(pP.getIdLang());
        portalPaginaDTO.setIdCompleto(pP.getIdCompleto());
        return portalPaginaDTO;
    }
    //método para listar todos los portalPagina
    public List<portalPaginaDTO> findAll() {
        List<portalPaginaDTO> portalPaginaDTOS = new ArrayList<>();
        List<portalPagina> portalPaginaList = portalPaginaRepository.findAll();
        for (portalPagina pP : portalPaginaList) {
            portalPaginaDTOS.add(toDTO(pP));
        }
        return portalPaginaDTOS;
    }
    //método para buscar un portalPagina determinado
    //control excep
    public portalPaginaDTO findById(Integer id) {
        portalPagina portalPagina = portalPaginaRepository.findById(id).orElse(null);
        portalPaginaDTO portalPaginaDTO = toDTO(portalPagina);
        return portalPaginaDTO;
    }
    //método para Eliminar un portalPagina determinado
    public void deleteById(Integer id) {
        portalPaginaRepository.deleteById(id);
    }
    //método para actualizar un portalPagina

    public portalPaginaDTO update(Integer id,portalPaginaUpdateDTO portalPagina) {
        portalPagina portalPagina1 = portalPaginaRepository.findById(id).orElse(null);

        portalPagina1.setDescripcion(portalPagina.getDescripcion());
        portalPagina1.setPagina(portalPagina.getPagina());
        portalPagina1.setMigasPan(portalPagina.getMigasPan());
        portalPagina1.setTitulo(portalPagina.getTitulo());
        portalPagina1.setIdLang(portalPagina.getIdLang());
        return toDTO( portalPaginaRepository.save(portalPagina1));
    }

    public portalPaginaDTO search(String filtro) {
        Integer id = Integer.parseInt(filtro);
        Optional<portalPagina> porId = portalPaginaRepository.findById(id);
        portalPaginaDTO portalPaginaDTO = toDTO(porId.get());
        if (!porId.isEmpty()) {
            return portalPaginaDTO;

        }
        portalPagina portalPagina = portalPaginaRepository.findByDescripcionContainingIgnoreCase(filtro);
        portalPaginaDTO = toDTO(portalPagina);
        return portalPaginaDTO;
    }
    public portalPaginaDTO findByDescripcion(String descripcion) {
        portalPagina portalPagina =portalPaginaRepository.findByDescripcionContainingIgnoreCase(descripcion);
        portalPaginaDTO portalPaginaDTO = toDTO(portalPagina);
        return portalPaginaDTO;
    }

}
