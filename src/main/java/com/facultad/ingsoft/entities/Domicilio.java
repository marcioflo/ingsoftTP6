package com.facultad.ingsoft.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Domicilio {

	private String nombreCalle;

	private int numero;

	private Cliente cliente;
}
