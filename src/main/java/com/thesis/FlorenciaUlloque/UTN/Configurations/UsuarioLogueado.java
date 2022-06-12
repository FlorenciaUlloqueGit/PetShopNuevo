package com.thesis.FlorenciaUlloque.UTN.Configurations;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioLogueado {
    private static boolean logueado = false;
    private static String usuario;
    private static  int idRol;

    public boolean isLogueado() {
        return logueado;
    }

    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public void usuarioLogueado(boolean logueadoBool, int idRol, String usuario){
        if(logueadoBool == true){
            setLogueado(true);
            setIdRol(idRol);
            setUsuario(usuario);
        }
    }

    public UsuarioLogueado getThisInstance(){
        return  this;
    }



}
