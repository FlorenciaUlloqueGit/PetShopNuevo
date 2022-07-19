package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos;

public class dtoVentaConfirmada {
    private float total;
    private float cantidadEntrante;
    private float vuelto;

    public dtoVentaConfirmada(float total, float cantidadEntrante, float vuelto) {
        this.total = total;
        this.cantidadEntrante = cantidadEntrante;
        this.vuelto = vuelto;
    }

    public dtoVentaConfirmada() {
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getCantidadEntrante() {
        return cantidadEntrante;
    }

    public void setCantidadEntrante(float cantidadEntrante) {
        this.cantidadEntrante = cantidadEntrante;
    }

    public float getVuelto() {
        return vuelto;
    }

    public void setVuelto(float vuelto) {
        this.vuelto = vuelto;
    }
}
