package com.thesis.FlorenciaUlloque.UTN.entiities;


import javax.persistence.*;

@Entity (name = "administrador")
@Table(name = "Administradores")
public class Administrador  {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int idAdmin;

    private String nombre;
    private String usuario;
    private String pass;

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Administrador(int idAdmin, String nombre, String usuario, String pass) {
        this.idAdmin = idAdmin;

        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
    }

    public Administrador() {
    }




}
