package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Categoria;

public class ProductoDetalleVenta {
    private int idProducto;
    private String nombre;
    private long codBarras;
    private double precioVenta;
    private Categoria categoria;

    public ProductoDetalleVenta(int idProducto, String nombre, long codBarras, double precioVenta, Categoria categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codBarras = codBarras;
        this.precioVenta = precioVenta;
        this.categoria = categoria;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioCompra) {
        this.precioVenta = precioVenta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ProductoDetalleVenta(int idProducto, String nombre, long codBarras) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codBarras = codBarras;
    }

    public ProductoDetalleVenta() {
    }

    public ProductoDetalleVenta(int idProducto) {
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
