package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;

import java.util.List;

public interface DetalleIngresoService {

    DetalleIngreso createDetalle(DetalleIngreso detalleIngreso);
    List<DetalleIngreso> getAllDetalles(int page, int limit);
    DetalleIngreso updateDetalle(int id, DetalleIngreso detalleIngreso);
    void deleteDetalle(int id);
}
