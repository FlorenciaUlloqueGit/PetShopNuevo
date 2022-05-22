package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos;

public class ProductoStock {
    private int idProducto;
    private String nombre;
    private long codBarras;

    public ProductoStock(int idProducto, String nombre, long codBarras) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codBarras = codBarras;
    }

    public ProductoStock() {
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
