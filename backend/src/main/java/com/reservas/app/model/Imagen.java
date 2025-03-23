package com.reservas.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "imagenes")
@Data
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
}