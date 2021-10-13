package com.facultad.ingsoft.entities;

import org.junit.jupiter.api.*;

public class CarritoTest {
    Carrito carrito;
    Articulo articulo;

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {
        System.out.println("Inicia " + testInfo.getDisplayName());
    }

    @AfterEach
    public void afterEach(TestInfo testInfo) {
        System.out.println("Finaliza " + testInfo.getDisplayName());
    }

    @Test
    public void testArticuloInactivo() {

        carrito = new Carrito();
        articulo = Articulo.builder().activo(false).build();
        boolean resultado;
        resultado = carrito.esArticuloValido(articulo);
        Assertions.assertFalse(resultado);
    }

    @Test
    public void testArticuloInvalidoCheckException() {

        carrito = new Carrito();
        articulo = Articulo.builder().activo(false).build();

        try {
            Exception ex = Assertions.assertThrows(Exception.class,
                    () -> carrito.esArticuloInvalido(articulo));

            Assertions.assertEquals("El artículo no es válido", ex.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
