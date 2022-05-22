package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos;

import com.thesis.FlorenciaUlloque.UTN.entiities.FormaPago;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class IngresoDto {
    private int idIngreso;
    private Proveedor proveedor;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private double total;
    private FormaPago formaPago;

    public IngresoDto(int idIngreso, Proveedor proveedor, LocalDate fecha, double total, FormaPago formaPago) {
        this.idIngreso = idIngreso;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.total = total;
        this.formaPago = formaPago;
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

    public IngresoDto() {
    }
}
