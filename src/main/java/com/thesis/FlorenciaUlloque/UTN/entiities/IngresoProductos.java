package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity(name = "ingresoProductos")
@Table(name = "Ingreso_Productos")
public class IngresoProductos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIngreso;

    @ManyToOne
    @JoinColumn(name ="idProveedor")
    private Proveedor proveedor;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private double total;

    @JoinColumn(name = "idFormaPago")
    @OneToOne //(fetch =FetchType.LAZY)
    private FormaPago formaPago;

    @OneToMany(mappedBy = "ingresoProductos" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DetalleIngreso> listadoDetalleIngresos;

    public List<DetalleIngreso> getListadoDetalleIngresos() {
        return listadoDetalleIngresos;
    }

    public void setListadoDetalleIngresos(List<DetalleIngreso> listadoDetalleIngresos) {
        this.listadoDetalleIngresos = listadoDetalleIngresos;
    }

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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


    public IngresoProductos(int idIngreso, Proveedor proveedor, LocalDate fecha, double total, FormaPago formaPago, List<DetalleIngreso> listadoDetalleIngresos) {
        this.idIngreso = idIngreso;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.total = total;
        this.formaPago = formaPago;
        this.listadoDetalleIngresos = listadoDetalleIngresos;
    }

    public IngresoProductos(Proveedor proveedor, LocalDate fecha, double total, FormaPago formaPago, List<DetalleIngreso> listadoDetalleIngresos) {
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.total = total;
        this.formaPago = formaPago;
        this.listadoDetalleIngresos = listadoDetalleIngresos;
    }

    public IngresoProductos() {
    }


}

