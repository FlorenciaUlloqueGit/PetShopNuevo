package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos;

public class ProductoDtoDetalle {
    private int idProducto;
    private long codBarras;
    private String nombre;
    private double precioCompra;
    private double precioVenta;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private boolean enabled;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public long getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(long codBarras) {
        this.codBarras = codBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public ProductoDtoDetalle() {
    }

    public ProductoDtoDetalle(int idProducto, long codBarras, String nombre, double precioCompra, double precioVenta) {
        this.idProducto = idProducto;
        this.codBarras = codBarras;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }
}
