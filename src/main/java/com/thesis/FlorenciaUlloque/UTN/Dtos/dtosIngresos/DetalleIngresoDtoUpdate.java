package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;

public class DetalleIngresoDtoUpdate {
    private int idDetalle;
    private int idIngreso;
    private Producto producto;
    private int cantidad;

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
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
