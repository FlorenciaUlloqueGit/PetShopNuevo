package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.Dtos.MarcaDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.MarcaDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.IngresoDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.IngresoDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;

import java.util.List;

public interface IngresoProductosService {

    IngresoProductos updateIngreso(IngresoProductos ingresoProductos);
    boolean deleteIngreso(int id);
    List<IngresoProductos> getAllIngresos();
    IngresoProductos saveIngreso(IngresoProductos ingresoProductos);
    List<Proveedor> listaProveedores();
    List<FormaPago> listaFormasPagos();
}
