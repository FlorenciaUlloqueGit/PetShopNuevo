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

    @JoinColumn(name = "idProducto")
    @OneToOne (fetch =FetchType.LAZY)
    private Producto producto;

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

    public Stock() {
    }

}
