package com.thesis.FlorenciaUlloque.UTN.entiities;

import javax.persistence.*;

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

    public Cliente(int idCliente, String email, String pass, String nombre, String apellido, long telefono) {
        this.idCliente = idCliente;

        this.email = email;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
