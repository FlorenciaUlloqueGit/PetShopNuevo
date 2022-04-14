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

    @OneToMany(mappedBy = "detalleEgreso")//  ?
    @Column(name = "producto_detalleEgreso")
    private List<Producto> productos;

    private int cantidad;
    private float precio;

    public long getIdDetalleEgreso() {
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
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

    public DetalleEgreso(int idDetalleEgreso, SalidaProducto salidaProducto, List<Producto> listadoProductos, int cantidad, float precio) {
        this.idDetalleEgreso = idDetalleEgreso;
        this.salidaProducto = salidaProducto;
        this.productos = listadoProductos;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "DetalleSalida{" +
                "idDetalle=" + idDetalleEgreso +
                ", salidaProducto=" + salidaProducto +
                ", listadoProductos=" + productos +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }

    public DetalleEgreso() {

    }


}
