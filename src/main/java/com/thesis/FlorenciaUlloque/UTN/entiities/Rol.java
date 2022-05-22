package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "rol")
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy ="rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cliente> clientes;

    @JsonIgnore
    @OneToMany(mappedBy ="rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vendedor> vendedores;

    @JsonIgnore
    @OneToMany(mappedBy ="rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Administrador> administradores;

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol(int idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public Rol() {
    }

    public Rol(int idRol) {
        this.idRol = idRol;
    }
}
