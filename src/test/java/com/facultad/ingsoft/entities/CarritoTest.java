package com.facultad.ingsoft.entities;

import org.junit.jupiter.api.*;

public class CarritoTest {
    Carrito carrito;
    Articulo articulo;
    DetalleCarrito detalleCarrito;

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
    //Test (1) Juan Ignacio Barranco realizando cobertura de  "Detalle de Carrito ES VÁLIDO"
    @Test
    public void testDetalleCarritoInvalidoCheckException(){
        carrito = new Carrito();
        detalleCarrito = new DetalleCarrito();

        try {
            Exception ex = Assertions.assertThrows(Exception.class,
                    () -> carrito.esDetalleCarritoInvalido(detalleCarrito));
            Assertions.assertEquals("El detalle del carrito no es válido", ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    //Test (2) Juan Ignacio Barranco realizando cobertura de "CANTIDAD NO VALIDA"
    @Test
    public void testArticuloCantidadInvalida() {

        carrito = new Carrito();
        articulo = Articulo.builder().activo(true).denominacion("Zapatilla Nike Air 320").descripcion("Calzado de hombre deportivo 2021").cantidad(-1).precio(15000).build();
        boolean resultado;
        resultado = carrito.esArticuloValido(articulo);
        Assertions.assertFalse(resultado);
    }

}

