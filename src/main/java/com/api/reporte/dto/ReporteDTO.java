package com.api.reporte.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO extends RepresentationModel<ReporteDTO> {

    private Integer id;
    private String titulo;
    private String descripcion;
    private String fechaCreacion;
    private String estado;
}
