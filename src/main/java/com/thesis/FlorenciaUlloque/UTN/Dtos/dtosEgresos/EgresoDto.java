package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.FormaPago;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class EgresoDto {
    private int idSalida;
    private Cliente cliente;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private double total;
    private FormaPago formaPago;
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

    public EgresoDto() {
    }

    public EgresoDto(int idEgreso, Cliente cliente, LocalDate fecha, double total, FormaPago formaPago) {
        this.idSalida = idEgreso;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.formaPago = formaPago;
    }

    public int getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(int idSalida) {
        this.idSalida = idSalida;
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

    public static class DetalleEgresoDtoSinIdEgreso {
        private int idDetalleEgreso;
        private Producto producto;
        private int cantidad;
        private double total;

    }
}
