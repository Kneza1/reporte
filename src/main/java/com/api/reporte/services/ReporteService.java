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
                reporte.getTitulo(),
                reporte.getDescripcion(),
                reporte.getFechaCreacion(),
                reporte.getEstado()
        );
    }

    private Reporte toEntity(ReporteDTO dto) {
        Reporte reporte = new Reporte();
        reporte.setId(dto.getId()); // importante para actualizar
        reporte.setTitulo(dto.getTitulo());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setFechaCreacion(dto.getFechaCreacion());
        reporte.setEstado(dto.getEstado());
        return reporte;
    }

    public ReporteDTO crear(ReporteDTO dto) {
        Reporte reporte = toEntity(dto);
        return toDTO(reporteRepository.save(reporte));
    }

    public List<ReporteDTO> listar() {
        return reporteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ReporteDTO obtenerPorId(Integer id) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
        return toDTO(reporte);
    }

    public ReporteDTO actualizar(Integer id, ReporteDTO dto) {
        Reporte existente = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        existente.setTitulo(dto.getTitulo());
        existente.setDescripcion(dto.getDescripcion());
        existente.setFechaCreacion(dto.getFechaCreacion());
        existente.setEstado(dto.getEstado());

        return toDTO(reporteRepository.save(existente));
    }

    public void eliminar(Integer id) {
        reporteRepository.deleteById(id);
    }

}
