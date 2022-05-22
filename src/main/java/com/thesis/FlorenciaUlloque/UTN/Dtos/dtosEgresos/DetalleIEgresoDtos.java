package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import com.thesis.FlorenciaUlloque.UTN.entiities.SalidaProducto;

public class DetalleIEgresoDtos {
    private SalidaProducto salidaProducto;
    private int cantidad;
    private Producto producto;
    private double precio;

    public DetalleIEgresoDtos() {

    }

    public SalidaProducto getSalidaProducto() {
        return salidaProducto;
    }

    public void setSalidaProducto(SalidaProducto salidaProducto) {
        this.salidaProducto = salidaProducto;
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


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public DetalleIEgresoDtos(double precio) {
        this.precio = precio;
    }


}
