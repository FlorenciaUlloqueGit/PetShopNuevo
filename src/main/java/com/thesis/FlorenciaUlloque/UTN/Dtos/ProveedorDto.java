package com.thesis.FlorenciaUlloque.UTN.Dtos;

public class ProveedorDto {

    private String nombre;
    private long telefono;
    private String representante;
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ProveedorDto(String nombre, long telefono, String representante, boolean enabled) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.representante = representante;
        this.enabled = enabled;
    }

    public ProveedorDto() {
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
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
