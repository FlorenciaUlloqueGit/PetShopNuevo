package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos;

import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class EgresoDtos {
    private Cliente cliente;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private double total;
    private FormaPago formaPago;
    private List<DetalleEgreso> detalleEgresoList;
    private int cantidadCuotas;
    private float procentajeInteres;

    public int getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(int cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public float getProcentajeInteres() {
        return procentajeInteres;
    }

    public void setProcentajeInteres(float procentajeInteres) {
        this.procentajeInteres = procentajeInteres;
    }

    public EgresoDtos(Cliente cliente, LocalDate fecha, double total, FormaPago formaPago, List<DetalleEgreso> detalleEgresoList) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.formaPago = formaPago;
        this.detalleEgresoList = detalleEgresoList;
    }

    public EgresoDtos() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public List<DetalleEgreso> getDetalleEgresoList() {
        return detalleEgresoList;
    }

    public void setDetalleEgresoList(List<DetalleEgreso> detalleEgresoList) {
        this.detalleEgresoList = detalleEgresoList;
    }
}
