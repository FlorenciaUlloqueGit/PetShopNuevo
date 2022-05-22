package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos;

import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;

public class DetalleIngresoDtos {
    private IngresoProductos ingresoProductos;
    private int cantidad;
    private Producto producto;
    private double precio;

    public DetalleIngresoDtos() {

    }

    public IngresoProductos getIngresoProductos() {
        return ingresoProductos;
    }

    public void setIngresoProductos(IngresoProductos ingresoProductos) {
        this.ingresoProductos = ingresoProductos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


    public DetalleIngresoDtos(IngresoProductos ingresoProductos, int cantidad, Producto producto, double precio) {
        this.ingresoProductos = ingresoProductos;
        this.cantidad = cantidad;
        this.producto = producto;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public DetalleIngresoDtos(double precio) {
        this.precio = precio;
    }
}
