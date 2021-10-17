package com.facultad.ingsoft.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Carrito {

    private	long numero;

    private	String fecha;

    private	int total;

    @Builder.Default
    private List<DetalleCarrito> detalleCarritos = new ArrayList<>();

    private Cliente cliente;

    boolean esCarritoValidoParaCompra(Carrito carrito) {
        if (carrito.getCliente() == null) {
            return  false;
        }

        if (!(esCarritoValido(carrito))) {
            return false;
        }
        return true;
    }

    boolean esCarritoValido(Carrito carrito) {
        if (carrito.detalleCarritos.isEmpty()) {
            return false;
        }

        for (DetalleCarrito detalleCarrito : carrito.detalleCarritos) {
            if (detalleCarrito == null) {
                return false;
            }
            if (!(esDetalleCarritoValido(detalleCarrito))) {
                return  false;
            }
        }
        return true;
    }

    boolean esDetalleCarritoValido(DetalleCarrito detalleCarrito) {
        if (detalleCarrito.getArticulo() == null) {
            return false;
        }

        if (!esArticuloValido(detalleCarrito.getArticulo())) {
            return false;
        }

        if (!(detalleCarrito.getCantidad() > 0)) {
            return false;
        }

        if (!(detalleCarrito.getCantidad() <= detalleCarrito.getArticulo().getCantidad())) {
            return false;
        }

        if (!(detalleCarrito.getSubtotal() == detalleCarrito.getArticulo().getPrecio() * detalleCarrito.getCantidad())) {
            return false;
        }
        return true;
    }

    public boolean esArticuloValido(Articulo articulo) {
        if (!articulo.isActivo()) {
            return false;
        }

        if (!(articulo.getCantidad() >= 0)) {
            return false;
        }

        if (isStringEmpty(articulo.getDenominacion())) {
            return false;
        }

        if (isStringEmpty(articulo.getDescripcion())) {
            return false;
        }
        return  true;
    }

    boolean  isStringEmpty(String s) {
        return s == null || s.isBlank();
    }

    void esArticuloInvalido(Articulo articulo) throws Exception {
        if (!(esArticuloValido(articulo))) {
            throw new Exception("El artículo no es válido");
        }
    }
    void esDetalleCarritoInvalido(DetalleCarrito detalleCarrito) throws Exception {
        if (!(esDetalleCarritoValido(detalleCarrito))) {
            throw new Exception("El detalle del carrito no es válido");
        }
    }
}
