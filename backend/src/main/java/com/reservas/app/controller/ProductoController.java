package controller;  // Paquete corregido

import model.Producto;
import service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Endpoint ADMIN para crear productos
    @PostMapping("/admin/productos")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.agregarProducto(producto));
    }

    // Endpoint público para productos aleatorios
    @GetMapping("/productos/aleatorios")
    public ResponseEntity<List<Producto>> listarProductosAleatorios(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamaño) {

        Pageable pageable = PageRequest.of(pagina, tamaño);
        List<Producto> productos = productoService.listarProductosPagina(pageable).getContent();
        Collections.shuffle(productos);
        return ResponseEntity.ok(productos.stream().limit(tamaño).toList());
    }

    // Endpoint ADMIN para todos los productos
    @GetMapping("/admin/productos")
    public ResponseEntity<Page<Producto>> listarTodosProductos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamaño) {

        Pageable pageable = PageRequest.of(pagina, tamaño);
        return ResponseEntity.ok(productoService.listarProductosPagina(pageable).map(producto -> (Producto) producto));
    }

    // Endpoint ADMIN para eliminar
    @DeleteMapping("/admin/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint público para categorías
    @GetMapping("/categorias")
    public ResponseEntity<List<String>> listarCategorias() {
        return ResponseEntity.ok(List.of("Hoteles", "Vuelos", "Autos", "Moda"));
    }
}


