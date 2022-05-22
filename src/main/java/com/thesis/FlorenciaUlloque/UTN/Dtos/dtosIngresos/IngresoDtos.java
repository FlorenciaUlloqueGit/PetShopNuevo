package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos;

import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.FormaPago;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class IngresoDtos {
    private Proveedor proveedor;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private double total;
    private FormaPago formaPago;
    private List<DetalleIngreso> detalleIngresoList;

    public List<DetalleIngreso> getDetalleIngresoList() {
        return detalleIngresoList;
    }

    public void setDetalleIngresoList(List<DetalleIngreso> detalleIngresoList) {
        this.detalleIngresoList = detalleIngresoList;
    }

    public IngresoDtos(Proveedor proveedor, LocalDate fecha, double total, FormaPago formaPago, List<DetalleIngreso> detalleIngresoList) {
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.total = total;
        this.formaPago = formaPago;
        this.detalleIngresoList = detalleIngresoList;
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

    public IngresoDtos() {
    }
}
