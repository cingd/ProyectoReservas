package com.reservas.app.service;

import model.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import repository.ProductoRepository;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void listarProductosPaginaDelegatesToRepository() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Producto> page = new PageImpl<>(Collections.emptyList());
        when(productoRepository.findAll(pageable)).thenReturn(page);

        Page<Producto> result = productoService.listarProductosPagina(pageable);

        assertThat(result).isEqualTo(page);
        verify(productoRepository).findAll(pageable);
    }
}
