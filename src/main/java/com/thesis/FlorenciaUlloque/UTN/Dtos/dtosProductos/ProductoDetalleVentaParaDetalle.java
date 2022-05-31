package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Categoria;


public class ProductoDetalleVentaParaDetalle {
    private int idProducto;
    private String nombre;
    private Categoria categoria;
    private double precioVenta;



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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

}
