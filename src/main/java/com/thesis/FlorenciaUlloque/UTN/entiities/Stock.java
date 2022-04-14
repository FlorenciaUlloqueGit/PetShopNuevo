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
    @OneToMany(mappedBy = "stock")
    @Column(name = "producto_stock")
    private List<Producto> productos;

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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Stock(int idStock, int cantidad, List<Producto> productos) {
        this.idStock = idStock;
        this.cantidad = cantidad;
        this.productos = productos;
    }

    public Stock() {
    }

    @Override
    public String toString() {
        return "Stock{" +
                "idStock=" + idStock +
                ", cantidad=" + cantidad +
                ", productos=" + productos +
                '}';
    }
}
