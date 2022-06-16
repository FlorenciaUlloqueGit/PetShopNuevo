package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Cuota {
    private int numero;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Cuota> crearCuotas(){
        ArrayList<Cuota> lista = new ArrayList<>();
        Cuota cuota1 = new Cuota();
        cuota1.setNumero(1);
        lista.add(cuota1);
        Cuota cuota2 = new Cuota();
        cuota1.setNumero(2);
        lista.add(cuota2);
        Cuota cuota3 = new Cuota();
        cuota1.setNumero(3);
        lista.add(cuota3);
        Cuota cuota6 = new Cuota();
        cuota1.setNumero(6);
        lista.add(cuota6);
        Cuota cuota9 = new Cuota();
        cuota1.setNumero(9);
        lista.add(cuota9);
        return lista;
    }

}
