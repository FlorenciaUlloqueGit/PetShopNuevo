package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos;

import com.thesis.FlorenciaUlloque.UTN.entiities.FormaVenta;

public class ProductoStock {
    private int idProducto;
    private String nombre;
    private long codBarras;
    private FormaVenta formaVenta;

    public ProductoStock(int idProducto, String nombre, long codBarras, FormaVenta formaVenta) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codBarras = codBarras;
        this.formaVenta = formaVenta;
    }

    public ProductoStock(int idProducto, String nombre, long codBarras) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codBarras = codBarras;
    }

    public ProductoStock() {
    }

    public FormaVenta getFormaVenta() {
        return formaVenta;
    }

    public void setFormaVenta(FormaVenta formaVenta) {
        this.formaVenta = formaVenta;
    }

    public ProductoStock(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(long codBarras) {
        this.codBarras = codBarras;
    }
}
