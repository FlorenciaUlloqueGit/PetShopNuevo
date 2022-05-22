package com.thesis.FlorenciaUlloque.UTN.Dtos;

public class ProveedorDto {

    private String nombre;
    private long telefono;

    public ProveedorDto(String nombre, long telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public ProveedorDto() {
    }

    public ProveedorDto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
}
