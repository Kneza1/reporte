package com.api.reporte.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")

    private Integer id;
    private String titulo;
    private String descripcion;
    private String fechaCreacion;
    private String estado;
}
