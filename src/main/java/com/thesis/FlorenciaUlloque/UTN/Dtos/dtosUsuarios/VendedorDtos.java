package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios;

public class VendedorDtos {
    private int idVendedor;
    private String usuario;
    private String pass;

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


    public VendedorDtos(int idVendedor, String usuario, String pass) {
        this.idVendedor = idVendedor;
        this.usuario = usuario;
        this.pass = pass;
    }

    public VendedorDtos() {
    }
}
