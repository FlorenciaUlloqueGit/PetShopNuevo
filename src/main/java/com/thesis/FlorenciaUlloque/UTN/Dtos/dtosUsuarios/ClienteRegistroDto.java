package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios;

import com.thesis.FlorenciaUlloque.UTN.entiities.Rol;

public class ClienteRegistroDto {

    private int idCliente ;
    private String email;
    private String nombre;
    private String apellido;
    private long telefono;
    private String direccion;
    private Rol rol;
    private boolean enabled;
    private long dni;

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public ClienteRegistroDto(int idCliente, String email, String nombre, String apellido, long telefono, String direccion, Rol rol) {
        this.idCliente = idCliente;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rol = rol;
    }

    public ClienteRegistroDto() {
    }

    public ClienteRegistroDto(String email, String nombre, String apellido, long telefono, String direccion, Rol rol) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rol = rol;
    }

    public ClienteRegistroDto(String email) {
        this.email = email;
    }
}
