package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos;

import com.lowagie.text.pdf.PdfPCell;

public class ProductoReporte2
{
    private String nombre;
    private String categoria;
    private float precioVenta;
    private float cantidad;
    private float total;
    private String formaVenta;

    public ProductoReporte2(String nombre, String categoria, float precioVenta, float cantidad, float total, String formaVenta) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
        this.total = total;
        this.formaVenta = formaVenta;
    }

    public ProductoReporte2() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFormaVenta() {
        return formaVenta;
    }

    public void setFormaVenta(String formaVenta) {
        this.formaVenta = formaVenta;
    }
}
