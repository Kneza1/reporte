package com.api.reporte.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {

    private Integer id;
    private String titulo;
    private String descripcion;
    private String fechaCreacion;
    private String estado;
}
