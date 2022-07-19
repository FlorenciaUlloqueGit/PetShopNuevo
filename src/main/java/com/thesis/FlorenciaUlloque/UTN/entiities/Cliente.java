package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity(name = "cliente")
@Table(name = "clientes")
public class Cliente{ //crear clase cliente en la base

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente ;

    private String email;
    private String pass;
    private String nombre;
    private String apellido;
    private long telefono;
    private String direccion;
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
/*
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "cliente_rol",
            joinColumns =  @JoinColumn(
                    name = "cliente_idCliente", referencedColumnName = "idCliente"),
            inverseJoinColumns = @JoinColumn(name = "rol_idRol", referencedColumnName = "idRol")
    )
    private Collection<Rol> roles;

     */

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    @JsonIgnore
    @OneToMany(mappedBy ="cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalidaProducto> salidaProductos;

    public Cliente(String email, String pass, Rol rol) {
        this.email = email;
        this.pass = pass;
        this.rol = rol;
    }

    public Cliente(String email, String nombre, String apellido, long telefono, String direccion, Rol rol, boolean enabled,
                   long dni) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rol = rol;
        this.enabled = enabled;
        this.dni = dni;
    }

    public Rol getRol() {
        return rol;
    }


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public Cliente() {
    }

    public Cliente(int idCliente, String email, String pass, String nombre, String apellido, long telefono,
    List<SalidaProducto> salidaProductos) {
        this.idCliente = idCliente;

        this.email = email;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.salidaProductos = salidaProductos;
    }

    public Cliente(String email, String pass, String nombre, String apellido, long telefono, String direccion, Rol rol) {
        this.email = email;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rol = rol;
    }


}
