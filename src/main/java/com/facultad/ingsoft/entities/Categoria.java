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
public class Categoria {

    private String denominacion;

    //@Builder.Default
    //private List<Articulo> articulo = new ArrayList<>();
}
