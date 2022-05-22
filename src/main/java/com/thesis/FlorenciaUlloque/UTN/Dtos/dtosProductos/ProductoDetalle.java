package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Categoria;

public class ProductoDetalle {
    private int idProducto;
    private String nombre;
    private long codBarras;
    private double precioCompra;
    private Categoria categoria;

    public ProductoDetalle(int idProducto, String nombre, long codBarras, double precioCompra, Categoria categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codBarras = codBarras;
        this.precioCompra = precioCompra;
        this.categoria = categoria;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ProductoDetalle(int idProducto, String nombre, long codBarras) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codBarras = codBarras;
    }

    public ProductoDetalle() {
    }

    public ProductoDetalle(int idProducto) {
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
