package com.thesis.FlorenciaUlloque.UTN.entiities;


import javax.persistence.*;

@Entity (name = "administrador")
@Table(name = "admins")
public class Administrador  {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int idAdmin;

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

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
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

    public Administrador(int idAdmin, String usuario, String pass) {
        this.idAdmin = idAdmin;
        this.usuario = usuario;
        this.pass = pass;
    }

    public Administrador() {
    }

    public Administrador(String usuario, String pass, Rol rol) {
        this.usuario = usuario;
        this.pass = pass;
        this.rol = rol;
    }
}
