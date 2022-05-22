package com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks;

import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;

public class StockDtos {
    private Producto producto;
    private int cantidad;

    public StockDtos(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public StockDtos() {
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
