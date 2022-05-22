package com.thesis.FlorenciaUlloque.UTN.Dtos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;

import java.util.List;

public class MarcaDtos {

    private String nombre;
    private Proveedor proveedor;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public MarcaDtos(String nombre) {
        this.nombre = nombre;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public MarcaDtos(String nombre, Proveedor proveedor) {
        this.nombre = nombre;
        this.proveedor = proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public MarcaDtos() {
    }
}
