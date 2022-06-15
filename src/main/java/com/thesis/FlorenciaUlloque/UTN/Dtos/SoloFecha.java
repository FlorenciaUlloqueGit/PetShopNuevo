package com.thesis.FlorenciaUlloque.UTN.Dtos;

import org.springframework.format.annotation.DateTimeFormat;

public class SoloFecha {
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public SoloFecha() {
    }
}
