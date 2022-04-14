package com.thesis.FlorenciaUlloque.UTN.entiities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "detalleEgreso")
@Table(name = "detalle_egreso")
public class DetalleEgreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private int idDetalle;
    @ManyToOne
    @JoinColumn(name = "idEgreso")
    private SalidaProducto salidaProducto;

    @OneToMany(mappedBy = "detalleEgreso")//  ?
    @Column(name = "producto_detalleEgreso")
    private List<Producto> listadoProductos;

    private int cantidad;
    private float precio;

    public long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public SalidaProducto getSalidaProducto() {
        return salidaProducto;
    }

    public void setSalidaProducto(SalidaProducto salidaProducto) {
        this.salidaProducto = salidaProducto;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public DetalleEgreso(int idDetalle, SalidaProducto salidaProducto, List<Producto> listadoProductos, int cantidad, float precio) {
        this.idDetalle = idDetalle;
        this.salidaProducto = salidaProducto;
        this.listadoProductos = listadoProductos;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "DetalleSalida{" +
                "idDetalle=" + idDetalle +
                ", salidaProducto=" + salidaProducto +
                ", listadoProductos=" + listadoProductos +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }

    public DetalleEgreso() {

    }


}
