package controller;

import model.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.context.annotation.Import;
import service.ProductoService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductoController.class)
@Import(config.SecurityConfig.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Test
    public void productosAleatoriosDevuelveLista() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        List<Producto> productos = List.of(new Producto(), new Producto(), new Producto());
        Page<Producto> page = new PageImpl<>(productos, pageable, productos.size());
        when(productoService.listarProductosPagina(pageable)).thenReturn(page);

        mockMvc.perform(get("/api/productos/aleatorios")
                        .param("pagina", "0")
                        .param("tamaño", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
