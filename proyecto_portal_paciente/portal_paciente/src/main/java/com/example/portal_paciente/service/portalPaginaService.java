package com.example.portal_paciente.service;

import com.example.portal_paciente.model.portalPagina;
import com.example.portal_paciente.repository.portalPaginaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class portalPaginaService {
    @Autowired
    private portalPaginaRepository portalPaginaRepository;

    //método para guardar un portalPagina
    public portalPagina save(portalPagina portalPagina) {
        portalPagina.setId(null);
        return portalPaginaRepository.save(portalPagina);
    }
    //método para listar todos los portalPagina
    public List<portalPagina> findAll() {
        return portalPaginaRepository.findAll();
    }
    //método para buscar un portalPagina determinado
    //control excep
    public portalPagina findById(Integer id) {
        return portalPaginaRepository.findById(id).orElse(null);
    }
    //método para Eliminar un portalPagina determinado
    public void deleteById(Integer id) {
        portalPaginaRepository.deleteById(id);
    }
    //método para actualizar un portalPagina
    public portalPagina update(portalPagina portalPagina) {
        return portalPaginaRepository.save(portalPagina);
    }

    public Optional<portalPagina> search(String filtro) {
        Integer id = Integer.parseInt(filtro);
        Optional<portalPagina> porId = portalPaginaRepository.findById(id);

        if (!porId.isEmpty()) {
            return porId;
        }

        return portalPaginaRepository.findByDescripcionContainingIgnoreCase(filtro);
    }
    public Optional<portalPagina> findByDescripcion(String descripcion) {
        return portalPaginaRepository.findByDescripcionContainingIgnoreCase(descripcion);
    }

}
