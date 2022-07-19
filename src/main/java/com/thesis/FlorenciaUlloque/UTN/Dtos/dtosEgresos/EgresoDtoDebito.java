package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos;

import com.thesis.FlorenciaUlloque.UTN.entiities.FormaPago;

import java.time.LocalDate;

public class EgresoDtoDebito {
    private float total;



    public EgresoDtoDebito() {
    }


    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
