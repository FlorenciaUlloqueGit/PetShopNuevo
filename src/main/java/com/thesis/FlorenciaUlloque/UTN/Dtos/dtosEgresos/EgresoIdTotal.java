package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos;

public class EgresoIdTotal {
    private int idEgreso;
    private double total;

    public EgresoIdTotal() {

    }

    public int getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(int idEgreso) {
        this.idEgreso = idEgreso;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public EgresoIdTotal(int idEgreso, double total) {
        this.idEgreso = idEgreso;
        this.total = total;
    }
}
