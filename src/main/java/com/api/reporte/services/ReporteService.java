package com.api.reporte.services;

import com.api.reporte.models.Reporte;
import com.api.reporte.dto.ReporteDTO;
import com.api.reporte.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    private ReporteDTO toDTO(Reporte reporte) {
        return new ReporteDTO(
                reporte.getId(),
                reporte.getNombre(),
                reporte.getDescripcion(),
                reporte.getCategoria(),
                reporte.getActivo()
        );
    }

    private Reporte toEntity(ReporteDTO dto) {
        Reporte reporte = new Reporte();
        reporte.setId(dto.getId()); // importante para actualizar
        reporte.setNombre(dto.getNombre());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setCategoria(dto.getCategoria());
        reporte.setActivo(dto.getActivo());
        return reporte;
    }

    public ReporteDTO crear(ReporteDTO dto) {
        Reporte producto = toEntity(dto);
        return toDTO(reporteRepository.save(reporte));
    }

    public List<ReporteDTO> listar() {
        return reporteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReporteDTO obtenerPorId(Integer id) {
        Reporte producto = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return toDTO(reporte);
    }

    public ReporteDTO actualizar(Integer id, ReporteDTO dto) {
        Reporte existente = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setCategoria(dto.getCategoria());
        existente.setActivo(dto.getActivo());

        return toDTO(reporteRepository.save(existente));
    }

    public void eliminar(Integer id) {
        reporteRepository.deleteById(id);
    }

}
