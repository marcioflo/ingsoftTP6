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
public class Cliente {

    private String nombre;

    private String apellido;

    private String dni;

    private Domicilio domicilio;

    private boolean activo;

    @Builder.Default
    private List<Carrito> carritos = new ArrayList<>();
}
