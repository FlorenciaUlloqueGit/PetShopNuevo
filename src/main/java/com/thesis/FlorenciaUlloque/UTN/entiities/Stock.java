package com.thesis.FlorenciaUlloque.UTN.entiities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "stock")
@Table(name = "Stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStock;
    private int cantidad;
    private int cantidadKg;
    private float cantidadRestante;

    @JoinColumn(name = "idProducto")
    @OneToOne (fetch =FetchType.LAZY)
    private Producto producto;

    public Stock(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getCantidadKg() {
        return cantidadKg;
    }

    public void setCantidadKg(int cantidadKg) {
        this.cantidadKg = cantidadKg;
    }

    public float getCantidadRestante() {
        return cantidadRestante;
    }

    public void setCantidadRestante(float cantidadRestante) {
        this.cantidadRestante = cantidadRestante;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Stock(int idStock, int cantidad, Producto producto) {
        this.idStock = idStock;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Stock(int idStock, int cantidad, int cantidadKg, float cantidadRestante, Producto producto) {
        this.idStock = idStock;
        this.cantidad = cantidad;
        this.cantidadKg = cantidadKg;
        this.cantidadRestante = cantidadRestante;
        this.producto = producto;
    }

    public Stock(int cantidad, int cantidadKg, float cantidadRestante, Producto producto) {
        this.cantidad = cantidad;
        this.cantidadKg = cantidadKg;
        this.cantidadRestante = cantidadRestante;
        this.producto = producto;
    }

    public Stock() {
    }

}
