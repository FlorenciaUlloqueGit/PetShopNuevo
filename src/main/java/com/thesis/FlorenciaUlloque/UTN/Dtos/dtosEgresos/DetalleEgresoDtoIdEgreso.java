package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import com.thesis.FlorenciaUlloque.UTN.entiities.UnidadMedida;

public class DetalleEgresoDtoIdEgreso {
    private int idDetalleEgreso;
    private int idEgreso;
    private Producto producto;
    private float cantidad;
    private double total;

    public DetalleEgresoDtoIdEgreso(int idDetalleEgreso, int idEgreso, Producto producto, float cantidad, double total) {
        this.idDetalleEgreso = idDetalleEgreso;
        this.idEgreso = idEgreso;
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
    }

    public DetalleEgresoDtoIdEgreso() {
    }

    public int getIdDetalleEgreso() {
        return idDetalleEgreso;
    }

    public void setIdDetalleEgreso(int idDetalleEgreso) {
        this.idDetalleEgreso = idDetalleEgreso;
    }

    public int getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(int idEgreso) {
        this.idEgreso = idEgreso;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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

    public void setTotal(double total) {
        this.total = total;
    }
}
