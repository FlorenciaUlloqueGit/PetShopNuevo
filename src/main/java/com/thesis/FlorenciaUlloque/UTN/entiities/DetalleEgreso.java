package com.thesis.FlorenciaUlloque.UTN.entiities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "detalleEgreso")
@Table(name = "Detalle_Egreso")
public class DetalleEgreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Detalle")
    private int idDetalleEgreso;

    @ManyToOne
    @JoinColumn(name = "idEgreso")
    private SalidaProducto salidaProducto;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    private int cantidad;
    private double precio;

    public int getIdDetalleEgreso() {
        return idDetalleEgreso;
    }

    public void setIdDetalleEgreso(int idDetalleEgreso) {
        this.idDetalleEgreso = idDetalleEgreso;
    }

    public SalidaProducto getSalidaProducto() {
        return salidaProducto;
    }

    public void setSalidaProducto(SalidaProducto salidaProducto) {
        this.salidaProducto = salidaProducto;
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

    public DetalleEgreso(int idDetalleEgreso, SalidaProducto salidaProducto, Producto producto, int cantidad, double precio) {
        this.idDetalleEgreso = idDetalleEgreso;
        this.salidaProducto = salidaProducto;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleEgreso() {

    }


}
