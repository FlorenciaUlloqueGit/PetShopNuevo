package com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks;

import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;

public class StockDto {
    private int idStock;
    private float cantidad;
    private Producto producto;
    private float cantidadRestante;
    private int cantidadKg;

    public float getCantidadRestante() {
        return cantidadRestante;
    }

    public void setCantidadRestante(float cantidadRestante) {
        this.cantidadRestante = cantidadRestante;
    }

    public int getCantidadKg() {
        return cantidadKg;
    }

    public void setCantidadKg(int cantidadKg) {
        this.cantidadKg = cantidadKg;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public StockDto(int idStock, int cantidad, Producto producto) {
        this.idStock = idStock;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public StockDto(int idStock, int cantidad, Producto producto, float cantidadRestante, int cantidadKg) {
        this.idStock = idStock;
        this.cantidad = cantidad;
        this.producto = producto;
        this.cantidadRestante = cantidadRestante;
        this.cantidadKg = cantidadKg;
    }

    public StockDto() {
    }


    public StockDto(float cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }
}
