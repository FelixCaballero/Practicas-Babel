package com.example.portal_paciente.service;

import com.example.portal_paciente.Configuration.portalPaginaValidator;
import com.example.portal_paciente.DTO.portalPaginaCreateDTO;
import com.example.portal_paciente.DTO.portalPaginaDTO;
import com.example.portal_paciente.DTO.portalPaginaUpdateDTO;
import com.example.portal_paciente.mappers.portalPaginaMapper;
import com.example.portal_paciente.model.portalPagina;
import com.example.portal_paciente.repository.portalPaginaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class portalPaginaService {
    private final portalPaginaRepository portalPaginaRepository;
    private final portalPaginaMapper portalPaginaMapper;
    private final portalPaginaValidator portalPaginaValidator;

    public portalPaginaDTO save(portalPaginaCreateDTO portalPagina) {
        return portalPaginaMapper.toDTO( portalPaginaRepository.save(portalPaginaMapper.toEntity(portalPagina)));
    }

    public List<portalPaginaDTO> findAll() {
       return portalPaginaMapper.toDTO(portalPaginaRepository.findAll());
    }

    public portalPaginaDTO findById(Integer id) {
        return portalPaginaMapper.toDTO(portalPaginaValidator.validar(id));
    }

    public void deleteById(Integer id) {
        portalPaginaRepository.deleteById(id);
    }

    public portalPaginaDTO update(Integer id,portalPaginaUpdateDTO portalPagina) {
        portalPagina portalPagina1 = portalPaginaValidator.validar(id);
        portalPaginaMapper.updatePaginaFromDTO(portalPagina,portalPagina1);
        return portalPaginaMapper.toDTO( portalPaginaRepository.save(portalPagina1));
    }

    public portalPaginaDTO findByDescripcion(String descripcion) {
        portalPagina portalPagina =portalPaginaRepository.findByDescripcionContainingIgnoreCase(descripcion);
        portalPaginaDTO portalPaginaDTO = portalPaginaMapper.toDTO(portalPagina);
        return portalPaginaDTO;
    }
}
