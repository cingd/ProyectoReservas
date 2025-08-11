package service;

import model.Producto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;
import repository.ProductoRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductoServiceTest {

    private final ProductoRepository productoRepository = Mockito.mock(ProductoRepository.class);
    private final ProductoService productoService = new ProductoService();

    @Test
    public void listarProductosPaginaDevuelvePagina() {
        Pageable pageable = PageRequest.of(0, 2);
        List<Producto> productos = List.of(new Producto(), new Producto());
        Page<Producto> page = new PageImpl<>(productos, pageable, productos.size());
        Mockito.when(productoRepository.findAll(pageable)).thenReturn(page);
        ReflectionTestUtils.setField(productoService, "productoRepository", productoRepository);

        Page<Producto> resultado = productoService.listarProductosPagina(pageable);

        assertEquals(2, resultado.getTotalElements());
        Mockito.verify(productoRepository).findAll(pageable);
    }
}
