package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;

import java.util.List;

public interface IngresoProductosService {

    IngresoProductos createIngreso(IngresoProductos ingresoProductos);
    List<IngresoProductos> getAllIngresos(int page, int limit);
    IngresoProductos updateIngreso(int id, IngresoProductos ingresoProductos);
    void deleteIngreso(int id);
}
