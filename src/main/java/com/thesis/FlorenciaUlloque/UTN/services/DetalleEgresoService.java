package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDetalle;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDetalleVenta;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDetalleVentaParaDetalle;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleEgreso;


import java.util.List;

public interface DetalleEgresoService {
    DetalleEgreso update(DetalleEgreso detalleEgreso);

    boolean delete(int id);

    List<DetalleEgreso> findAll();

    void save(DetalleEgreso detalleEgreso);

    List<ProductoDetalleVenta> getDetalleByNombreProducto(String nombre);

    List<ProductoDetalleVenta> getDetalleByCodBarrasProducto(long codBarras);

    List<ProductoDetalleVentaParaDetalle> getDetalleByNombreProductoYTipoVenta(String nombre);
}