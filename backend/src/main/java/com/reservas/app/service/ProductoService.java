package com.reservas.app.service;

import com.reservas.app.model.Producto;
import com.reservas.app.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public Producto agregarProducto(Producto producto) {
        if (productoRepository.existsByNombre(producto.getNombre())) {
            throw new IllegalArgumentException("El nombre del producto ya existe");
        }
        return productoRepository.save(producto);
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> listarProductosAleatorios(int limite) {
        List<Producto> productos = productoRepository.findAll();
        Collections.shuffle(productos);
        return productos.stream().limit(limite).toList();
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}