package com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks;

import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;

public class StockDtos {
    private Producto producto;
    private int cantidad;
    private int cantidadKg;
    private int cantidadRestante;

    public StockDtos(Producto producto, int cantidad, int cantidadKg, int cantidadRestante) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.cantidadKg = cantidadKg;
        this.cantidadRestante = cantidadRestante;
    }

    public StockDtos(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public StockDtos() {
    }

    public int getCantidadKg() {
        return cantidadKg;
    }

    public void setCantidadKg(int cantidadKg) {
        this.cantidadKg = cantidadKg;
    }

    public int getCantidadRestante() {
        return cantidadRestante;
    }

    public void setCantidadRestante(int cantidadRestante) {
        this.cantidadRestante = cantidadRestante;
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
