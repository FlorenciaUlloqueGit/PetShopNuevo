package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios;

import com.thesis.FlorenciaUlloque.UTN.entiities.Rol;

public class ClienteDtos {

    private String email;
    private String nombre;
    private String apellido;
    private long telefono;
    private String direccion;
    private Rol rol;

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }


    public ClienteDtos() {
    }

    public ClienteDtos(String email, String nombre, String apellido, long telefono, String direccion, Rol rol) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rol = rol;
    }

    public ClienteDtos(String email) {
        this.email = email;
    }
}
