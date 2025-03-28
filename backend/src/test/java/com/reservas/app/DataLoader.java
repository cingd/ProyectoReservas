package com.reservas.app;

import model.Imagen;
import model.Producto;
import repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    private final ProductoRepository productoRepository;

    public DataLoader(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica si ya hay productos para no duplicar datos
        if (productoRepository.count() == 0) {
            Producto producto1 = new Producto();
            producto1.setNombre("Hotel Plaza");
            producto1.setDescripcion("Un hotel de lujo en el centro de la ciudad.");

            Imagen imagen1 = new Imagen();
            imagen1.setUrl("https://example.com/hotel-plaza.jpg");
            imagen1.setProducto(producto1);

            producto1.setImagenes(Arrays.asList(imagen1));

            productoRepository.save(producto1);
            System.out.println("Datos de prueba cargados exitosamente!");
        }
    }
}
