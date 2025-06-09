package com.api.reporte.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private Boolean activo;
}
