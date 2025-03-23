package com.reservas.app.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "productos")
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Imagen> imagenes;
}