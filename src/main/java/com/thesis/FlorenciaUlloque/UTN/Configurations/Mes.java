package com.thesis.FlorenciaUlloque.UTN.Configurations;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Mes {
    private int idMes;
    private String nombre;

    public Mes(int idMes, String nombre) {
        this.idMes = idMes;
        this.nombre = nombre;
    }

    public Mes() {
    }

    public int getIdMes() {
        return idMes;
    }

    public void setIdMes(int idMes) {
        this.idMes = idMes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Mes> crearMeses(){
        ArrayList<Mes> arrayMeses = new ArrayList<>();
        Mes enero = new Mes();
        enero.setIdMes(1);
        enero.setNombre("Enero");
        arrayMeses.add(enero);
        Mes febrero = new Mes();
        febrero.setIdMes(2);
        febrero.setNombre("Febrero");
        arrayMeses.add(febrero);
        Mes marzo = new Mes();
        marzo.setIdMes(3);
        marzo.setNombre("Marzo");
        arrayMeses.add(marzo);
        Mes abril = new Mes();
        abril.setIdMes(4);
        abril.setNombre("Abril");
        arrayMeses.add(abril);
        Mes mayo = new Mes();
        mayo.setIdMes(5);
        mayo.setNombre("Mayo");
        arrayMeses.add(mayo);
        Mes Junio = new Mes();
        Junio.setIdMes(6);
        Junio.setNombre("Junio");
        arrayMeses.add(Junio);
        Mes Julio = new Mes();
        Julio.setIdMes(7);
        Julio.setNombre("Julio");
        arrayMeses.add(Julio);
        Mes Agosto = new Mes();
        Agosto.setIdMes(8);
        Agosto.setNombre("Agosto");
        arrayMeses.add(Agosto);
        Mes Septiembre = new Mes();
        Septiembre.setIdMes(9);
        Septiembre.setNombre("Septiembre");
        arrayMeses.add(Septiembre);
        Mes Octubre = new Mes();
        Octubre.setIdMes(10);
        Octubre.setNombre("Octubre");
        arrayMeses.add(Octubre);
        Mes Noviembre = new Mes();
        Noviembre.setIdMes(11);
        Noviembre.setNombre("Noviembre");
        arrayMeses.add(Noviembre);
        Mes Diciembre = new Mes();
        Diciembre.setIdMes(12);
        Diciembre.setNombre("Diciembre");
        arrayMeses.add(Diciembre);
        return  arrayMeses;
    }
}
