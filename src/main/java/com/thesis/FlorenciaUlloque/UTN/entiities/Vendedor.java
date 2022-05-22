package com.thesis.FlorenciaUlloque.UTN.entiities;

import javax.persistence.*;

@Entity(name = "vendedor")
@Table(name = "vendedores")
public class Vendedor  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVendedor;

    private String usuario;
    private String pass;


    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;


    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Vendedor(int idVendedor, String usuario, String pass, Rol rol) {
        this.idVendedor = idVendedor;
        this.usuario = usuario;
        this.pass = pass;
        this.rol = rol;
    }

    public Vendedor(String usuario, String pass, Rol rol) {
        this.usuario = usuario;
        this.pass = pass;
        this.rol = rol;
    }

    public Vendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }


    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Vendedor() {
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "idVendedor=" + idVendedor +
                ", usuario='" + usuario + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
