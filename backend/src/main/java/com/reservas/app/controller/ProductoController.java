package com.reservas.app.controller;

import com.reservas.app.model.Producto;
import com.reservas.app.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Collections;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping("/admin/productos")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.agregarProducto(producto));
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> listarProductosAleatorios(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamaño) {
        Pageable pageable = PageRequest.of(pagina, tamaño);
        List<Producto> productos = productoRepository.findAll(pageable).getContent();
        Collections.shuffle(productos);
        return ResponseEntity.ok(productos.stream().limit(tamaño).toList());
    }

    @GetMapping("/admin/productos")
    public ResponseEntity<List<Producto>> listarTodosProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @DeleteMapping("/admin/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
