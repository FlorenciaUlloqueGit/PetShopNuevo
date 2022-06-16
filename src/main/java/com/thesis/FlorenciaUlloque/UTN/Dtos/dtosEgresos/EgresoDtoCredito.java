package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos;

import com.thesis.FlorenciaUlloque.UTN.entiities.FormaPago;

import java.time.LocalDate;

public class EgresoDtoCredito {
    private float total;
    private FormaPago formaPago;
    private LocalDate fecha;
    private int cantidadCuotas;
    private float porcentajeTarjeta;

    public int getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(int cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public float getPorcentajeTarjeta() {
        return porcentajeTarjeta;
    }

    public void setPorcentajeTarjeta(float porcentajeTarjeta) {
        this.porcentajeTarjeta = porcentajeTarjeta;
    }

    public EgresoDtoCredito(int cantidadCuotas, float porcentajeTarjeta) {
        this.cantidadCuotas = cantidadCuotas;
        this.porcentajeTarjeta = porcentajeTarjeta;
    }

    public EgresoDtoCredito() {
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
