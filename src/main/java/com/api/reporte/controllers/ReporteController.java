package com.api.reporte.controllers;

import com.api.reporte.dto.ReporteDTO;
import com.api.reporte.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; 
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {

    @Autowired
    private ReporteService service;

    @PostMapping
    public ResponseEntity<ReporteDTO> crear(@RequestBody ReporteDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteDTO> actualizar(@PathVariable Integer id, @RequestBody ReporteDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hateoas/{id}")
    public ReporteDTO obtenerHATEOAS(@PathVariable Integer id) { 
        ReporteDTO dto = service.obtenerPorId(id);
        // Agregar enlaces HATEOAS 
        dto.add(linkTo(methodOn(ReporteController.class).obtenerHATEOAS(id)).withSelfRel()); 
        dto.add(linkTo(methodOn(ReporteController.class).obtenerTodosHATEOAS()).withRel("todos")); 
        dto.add(linkTo(methodOn(ReporteController.class).eliminar(id)).withRel("eliminar")); 
        return dto;
    }
    @GetMapping("/hateoas") 
    public List<ReporteDTO> obtenerTodosHATEOAS() { 
        List<ReporteDTO> lista = service.listar(); 
            for (ReporteDTO dto : lista) { 
            dto.add(linkTo(methodOn(ReporteController.class).obtenerHATEOAS(dto.getId())).withSelfRel()); 
    } 
    return lista; 
} 

}
