package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos;

public class IngresoIdTotal {
    private int idIngreso;
    private double total;

    public IngresoIdTotal() {

    }

    public int getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public IngresoIdTotal(int idIngreso, double total) {
        this.idIngreso = idIngreso;
        this.total = total;
    }

}
