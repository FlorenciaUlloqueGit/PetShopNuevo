package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date fecha;
    private double total;

    @JoinColumn(name = "idFormaPago")
    @OneToOne //(fetch =FetchType.LAZY)
    private FormaPago formaPago;

    // TODO: asegurarme del orpham remove cuando borre una inserciòn de la clase salidaProducto
    @OneToMany(mappedBy = "ingresoProductos" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
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

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }


    public IngresoProductos(int idIngreso, Proveedor proveedor, Date fecha, double total, FormaPago formaPago, List<DetalleIngreso> listadoDetalleIngresos) {
        this.idIngreso = idIngreso;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.total = total;
        this.formaPago = formaPago;
        this.listadoDetalleIngresos = listadoDetalleIngresos;
    }

    public IngresoProductos() {
    }


}

