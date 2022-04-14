package com.thesis.FlorenciaUlloque.UTN.entiities;

import javax.persistence.*;

@Entity(name = "usuario")
//@Table(name = "usuarios")
//@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    /*
    private String usuario;
    private String contra;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="rol_idRol")
    private Rol rol;

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public Usuario(int idUsuario, String usuario, String contra, Rol rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contra = contra;
        this.rol = rol;
    }

    public Usuario() {
    }
    */
}
