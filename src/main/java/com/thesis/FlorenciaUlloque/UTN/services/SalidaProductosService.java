package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.entiities.SalidaProducto;

import java.util.List;

public interface SalidaProductosService {

    SalidaProducto createSalida(SalidaProducto salidaProducto);
    List<SalidaProducto> getAllSalidas(int page, int limit);
    SalidaProducto updateSalidas(int id, SalidaProducto salidaProducto);
    void deleteSalidas(int id);
}
