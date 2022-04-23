package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleEgreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;

import java.util.List;

public interface DetalleEgresoService {

    DetalleEgreso createDetalle(DetalleEgreso detalle);
    List<DetalleEgreso> getAllDetalles(int page, int limit);
    DetalleEgreso updateDetalle(int id, DetalleEgreso detalle);
    void deleteDetalle(int id);
}
