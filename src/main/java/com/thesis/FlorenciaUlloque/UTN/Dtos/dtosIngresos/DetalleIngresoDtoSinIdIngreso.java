package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;

public class DetalleIngresoDtoSinIdIngreso {
    private int idDetalle;
    private Producto producto;
    private int cantidad;
    private double total;

    public DetalleIngresoDtoSinIdIngreso() {
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
