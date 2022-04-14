package com.thesis.FlorenciaUlloque.UTN.entiities;

import javax.persistence.*;

@Entity(name = "vendedor")
@Table(name = "vendedores")
public class Vendedor  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVendedor;
   /* @ManyToOne
    @JoinColumn(name = "rol_idRol")
        private Rol rol;

    */

    private String usuario;
    private String pass;
    private String nombre;



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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Vendedor() {
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "idVendedor=" + idVendedor +
                ", usuario='" + usuario + '\'' +
                ", pass='" + pass + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
