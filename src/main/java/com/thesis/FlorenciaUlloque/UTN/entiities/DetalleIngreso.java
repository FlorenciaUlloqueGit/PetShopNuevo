package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "detalleIngreso")
@Table(name = "detalle_ingreso")
public class DetalleIngreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Detalle")
    private int idDetalle;


    @ManyToOne
    @JoinColumn(name = "idIngreso")
    private IngresoProductos ingresoProductos;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    private int cantidad;
    private double precio;

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public IngresoProductos getIngresoProductos() {
        return ingresoProductos;
    }

    public void setIngresoProductos(IngresoProductos ingresoProductos) {
        this.ingresoProductos = ingresoProductos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public DetalleIngreso() {
    }

    public DetalleIngreso(int idDetalle, IngresoProductos ingresoProductos, Producto producto, int cantidad, double precio) {
        this.idDetalle = idDetalle;
        this.ingresoProductos = ingresoProductos;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
