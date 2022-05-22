package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.Dtos.MarcaDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.MarcaDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.DetalleIngresoDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDetalle;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoStock;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;

import java.util.List;

public interface DetalleIngresoService {

    DetalleIngreso update(DetalleIngreso detalleIngreso);
    boolean delete(int id);
    List<DetalleIngreso> findAll();
    void save(DetalleIngreso detalleIngreso);
    List<ProductoDetalle> getDetalleByNombreProducto(String nombre);
    List<ProductoDetalle> getDetalleByCodBarrasProducto(long codBarras);
}
