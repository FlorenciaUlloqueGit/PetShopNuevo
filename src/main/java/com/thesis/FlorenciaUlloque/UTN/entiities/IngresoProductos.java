package com.thesis.FlorenciaUlloque.UTN.entiities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "ingresoProductos")
@Table(name = "Ingreso_Productos")
public class IngresoProductos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIngreso;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="idProveedor")
    private Proveedor proveedor;
    private Date fecha;
    private double total;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idFormaPago")
    private FormaPago formaPago;

    @OneToMany(mappedBy = "ingresoProductos" , cascade = CascadeType.ALL)
    @Column(name = "detalleIngreso_ingresoProductos")
    private List<DetalleIngreso> listadoDetalleIngresos;

    public int getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DetalleIngreso> getListadoDetalleEntradas() {
        return listadoDetalleIngresos;
    }

    public void setListadoDetalleEntradas(List<DetalleIngreso> listadoDetalleIngresos) {
        this.listadoDetalleIngresos = listadoDetalleIngresos;
    }

    public IngresoProductos(int idIngreso, Proveedor proveedor, Date fecha, double total, List<DetalleIngreso> listadoDetalleIngresos) {
        this.idIngreso = idIngreso;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.total = total;
        this.listadoDetalleIngresos = listadoDetalleIngresos;
    }

    public IngresoProductos() {
    }

    public IngresoProductos(int idIngreso, Proveedor proveedor, Date fecha, double total) {
        this.idIngreso = idIngreso;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.total = total;
    }

    @Override
    public String toString() {
        return "EntradaProducto{" +
                "idIngreso=" + idIngreso +
                ", proveedor=" + proveedor +
                ", fecha=" + fecha +
                ", total=" + total +
                ", listadoDetalleEntradas=" + listadoDetalleIngresos +
                '}';
    }
}

