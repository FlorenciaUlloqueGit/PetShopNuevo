package com.thesis.FlorenciaUlloque.UTN.Dtos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;

public class MarcaDto {

    private int idMarca;
    private String nombre;
    private Proveedor proveedor;
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public MarcaDto(int idMarca, String nombre, Proveedor proveedor, boolean enabled) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.enabled = enabled;
    }

    public MarcaDto(String nombre, Proveedor proveedor) {
        this.nombre = nombre;
        this.proveedor = proveedor;
    }

    public MarcaDto(int idMarca) {
        this.idMarca = idMarca;
    }

    public MarcaDto() {
    }
}
