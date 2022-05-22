package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos;

public class DetalleIngresoDtosCrear {
    private int idIngreso;
    private int cantidad;
    private int  idProducto;
    private double precioCompra;

    public int getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public DetalleIngresoDtosCrear() {


    }

    public DetalleIngresoDtosCrear(int idIngreso, int cantidad, int idProducto, double precioCompra) {
        this.idIngreso = idIngreso;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this.precioCompra = precioCompra;
    }
}
