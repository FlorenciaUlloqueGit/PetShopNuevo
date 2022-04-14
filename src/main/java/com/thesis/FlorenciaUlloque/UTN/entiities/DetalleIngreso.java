package com.thesis.FlorenciaUlloque.UTN.entiities;

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

    @OneToMany(mappedBy = "detalleIngreso")//  ?
    @Column(name = "producto_detalleIngreso")
    private List<Producto> listadoProductos;

    private int cantidad;
    private double precio;

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public IngresoProductos getEntradaProducto() {
        return ingresoProductos;
    }

    public void setEntradaProducto(IngresoProductos ingresoProductos) {
        this.ingresoProductos = ingresoProductos;
    }

    public List<Producto> getListadoProductos() {
        return listadoProductos;
    }

    public void setListadoProductos(List<Producto> listadoProductos) {
        this.listadoProductos = listadoProductos;
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

    public DetalleIngreso(int idDetalleIngreso, IngresoProductos ingresoProductos, List<Producto> listadoProductos, int cantidad, double precio) {
        this.idDetalle = idDetalleIngreso;
        this.ingresoProductos = ingresoProductos;
        this.listadoProductos = listadoProductos;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleIngreso() {
    }

    @Override
    public String toString() {
        return "DetalleEntrada{" +
                "idDetalle=" + idDetalle +
                ", entradaProducto=" + ingresoProductos +
                ", listadoProductos=" + listadoProductos +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
