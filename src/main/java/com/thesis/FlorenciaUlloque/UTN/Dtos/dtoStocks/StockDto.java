package com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks;

import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;

public class StockDto {
    private int idStock;
    private int cantidad;
    private Producto producto;

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
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

    public StockDto(int idStock, int cantidad, Producto producto) {
        this.idStock = idStock;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public StockDto() {
    }


    public StockDto(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }
}
