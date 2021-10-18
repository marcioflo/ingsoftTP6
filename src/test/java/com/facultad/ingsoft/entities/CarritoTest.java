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

	//Test (1) Marcio Flores realizando cobertura de "Articulo INACTIVO"
	@Test
	public void testArticuloInactivo() {
		carrito = new Carrito();
		articulo = Articulo.builder()
				.activo(false)
				.cantidad(2)
				.denominacion("Nike Air 360")
				.descripcion("Calzado de mujer deportivo 2021")
				.precio(15000)
				.build();
		boolean resultado;
		resultado = carrito.esArticuloValido(articulo);
		Assertions.assertFalse(resultado);
	}

	//Test (2) Marcio Flores realizando cobertura de "Articulo NO VALIDO"
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

	//Test (1) Juan Manuel Ramirez realizando cobertura de "Carrito ES VÁLIDO"
	@Test
	@DisplayName(value = "Si tiene al menos 1 artículo devuelve True")
    public void testCarritoConArticulosDevuelveTrue() {
    	articulo = Articulo.builder()
				.cantidad(5)
				.activo(true)
				.denominacion("Zapatillas Nike")
				.descripcion("Zapatillas para mujer talle 38")
				.precio(4000)
				.build();
    	detalleCarrito = DetalleCarrito.builder()
				.articulo(articulo)
				.cantidad(2)
				.subtotal(8000)
				.build();
    	carrito = new Carrito();
    	carrito.getDetalleCarritos().add(detalleCarrito);
    	Assertions.assertTrue(carrito.esCarritoValido(carrito));
    }

	//Test (2) Juan Manuel Ramirez realizando cobertura de "Cantidad en Carrito SUPERA STOCK"
	@Test
	@DisplayName(value = "Valida devolución de excepción")
	public void testSuperaStockCheckException() {
		articulo = Articulo.builder()
				.cantidad(5)
				.activo(true)
				.denominacion("Zapatillas nike")
				.descripcion("Zapatillas para mujer talle 38")
				.precio(4000)
				.build();
		detalleCarrito = DetalleCarrito.builder()
				.articulo(articulo)
				.cantidad(7)
				.subtotal(8000)
				.build();
		carrito = new Carrito();
		carrito.getDetalleCarritos().add(detalleCarrito);

		try {
			Exception ex = Assertions.assertThrows(Exception.class,
					() -> carrito.superaStockCantidadElegida(carrito.getDetalleCarritos()));
			Assertions.assertEquals("La cantidad elegida de "+detalleCarrito.getArticulo().getDenominacion()+" es mayor al stock existente", ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Test (3) Juan Manuel Ramirez realizando cobertura de "SIN CANTIDAD en Detalle Carrito"
	@Test
    public void testDetalleCarritoSinCantidadDevuelveFalse() {
    	articulo = Articulo.builder()
				.cantidad(5)
				.activo(true)
				.denominacion("Zapatillas Nike")
				.descripcion("Zapatillas para mujer talle 38")
				.precio(4000)
				.build();
    	detalleCarrito = DetalleCarrito.builder()
				.articulo(articulo)
				.build();
    	carrito = new Carrito();
    	carrito.getDetalleCarritos().add(detalleCarrito);
    	Assertions.assertFalse(carrito.esDetalleCarritoValido(detalleCarrito));
    }

	//Test (4) Juan Manuel Ramirez realizando cobertura de "Cálculo TOTAL de Carrito"
	@Test
	public void testCalcTotal() {
		int esperado = 30000;
		
		carrito = new Carrito();
		carrito.getDetalleCarritos().add(DetalleCarrito.builder().subtotal(8000).build());
		carrito.getDetalleCarritos().add(DetalleCarrito.builder().subtotal(15000).build());
		carrito.getDetalleCarritos().add(DetalleCarrito.builder().subtotal(7000).build());
		carrito.calcTotal();
		Assertions.assertEquals(esperado, carrito.getTotal());
	}

    //Test (1) Juan Ignacio Barranco realizando cobertura de "Detalle de Carrito NO VALIDO"
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

    //Test (2) Juan Ignacio Barranco realizando cobertura de "Articulo con CANTIDAD NO VALIDA"
    @Test
    public void testArticuloCantidadInvalida() {
        carrito = new Carrito();
        articulo = Articulo.builder()
				.activo(true)
				.denominacion("Zapatilla Nike Air 320")
				.descripcion("Calzado de hombre deportivo 2021")
				.cantidad(-1)
				.precio(15000)
				.build();
        boolean resultado;
        resultado = carrito.esArticuloValido(articulo);
        Assertions.assertFalse(resultado);
    }
}

