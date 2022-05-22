package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;

public class DetalleEgresoDtoUpdate {
    private int idDetalleEgreso;
    private int idEgreso;
    private Producto producto;
    private int cantidad;

    public int getIdDetalleEgreso() {
        return idDetalleEgreso;
    }

    public void setIdDetalleEgreso(int idDetalleEgreso) {
        this.idDetalleEgreso = idDetalleEgreso;
    }

    public int getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(int idEgreso) {
        this.idEgreso = idEgreso;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
