package com.facultad.ingsoft.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DetalleCarrito {

	private int cantidad;

	private int subtotal;

	private Articulo articulo;

	//private Carrito carrito;
	

}
