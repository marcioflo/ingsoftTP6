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
public class Articulo {

    private String denominacion;

    private String descripcion;

    private int cantidad;

    private int precio;

    private boolean activo;

    @Builder.Default
    private List<Categoria> categorias = new ArrayList<>();

    //@Builder.Default
    //private List<DetalleCarrito> detalleCarritos = new ArrayList<>();
}
