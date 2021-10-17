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
			Exception ex = Assertions.assertThrows(Exception.class, () -> carrito.esArticuloInvalido(articulo));

			Assertions.assertEquals("El artículo no es válido", ex.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName(value = "Si tiene al menos 1 artículo devuelve True")
    public void testCarritoConArticulosDevuelveTrue() {
    	articulo = Articulo.builder().
    				cantidad(5).activo(true).
    				denominacion("Zapatillas nike").
    				descripcion("Zapatillas para mujer talle 38").
    				precio(4000).build();
    	detalleCarrito = DetalleCarrito.builder().
    									articulo(articulo).
    									cantidad(2).subtotal(8000).
    									build();
    	
    	carrito = new Carrito();
    	carrito.getDetalleCarritos().add(detalleCarrito);
    	Assertions.assertTrue(carrito.esCarritoValido(carrito));
    }
	
	@Test
	@DisplayName(value = "Valida devolución de excepción")
	public void testSuperaStockCheckException() {
		articulo = Articulo.builder().
				cantidad(5).activo(true).
				denominacion("Zapatillas nike").
				descripcion("Zapatillas para mujer talle 38").
				precio(4000).build();
		detalleCarrito = DetalleCarrito.builder().
										articulo(articulo).
										cantidad(7).subtotal(8000).
										build();
	
		carrito = new Carrito();
		carrito.getDetalleCarritos().add(detalleCarrito);
		try {
			Exception ex = Assertions.assertThrows(Exception.class, () -> carrito.superaStockCantidadElegida(carrito.getDetalleCarritos()));

			Assertions.assertEquals("La cantidad elegida de "+detalleCarrito.getArticulo().getDenominacion()+" es mayor al stock existente", ex.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
    public void testDetalleCarritoSinCantidadDevuelveFalse() {
    	articulo = Articulo.builder().
    				cantidad(5).activo(true).
    				denominacion("Zapatillas nike").
    				descripcion("Zapatillas para mujer talle 38").
    				precio(4000).build();
    	detalleCarrito = DetalleCarrito.builder().
    									articulo(articulo).
    									build();
    	
    	carrito = new Carrito();
    	carrito.getDetalleCarritos().add(detalleCarrito);
    	Assertions.assertFalse(carrito.esDetalleCarritoValido(detalleCarrito));
    }
	
	@Test
	public void testCalcTotal() {
		int esperado=30000;
		
		carrito = new Carrito();
		carrito.getDetalleCarritos().add(DetalleCarrito.builder().subtotal(8000).build());
		carrito.getDetalleCarritos().add(DetalleCarrito.builder().subtotal(15000).build());
		carrito.getDetalleCarritos().add(DetalleCarrito.builder().subtotal(7000).build());
		carrito.calcTotal();
		Assertions.assertEquals(esperado, carrito.getTotal());
	}
	
}
