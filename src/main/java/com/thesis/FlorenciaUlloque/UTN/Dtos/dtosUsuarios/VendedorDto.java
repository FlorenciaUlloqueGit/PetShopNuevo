package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios;

import com.thesis.FlorenciaUlloque.UTN.entiities.Rol;

public class VendedorDto {
    private String usuario;
    private String pass;
    private Rol rol;

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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public VendedorDto(String usuario, String pass, Rol rol) {
        this.usuario = usuario;
        this.pass = pass;
        this.rol = rol;
    }

    public VendedorDto() {
    }
}
