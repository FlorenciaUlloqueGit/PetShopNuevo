package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "edades")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "edad")
public class Edad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEdad;
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "edad", cascade = CascadeType.ALL)
    @Column(name = "animal_edad")
    private List<Animal> animales;

    public int getIdEdad() {
        return idEdad;
    }

    public void setIdEdad(int idEdad) {
        this.idEdad = idEdad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    public void setAnimales(List<Animal> animales) {
        this.animales = animales;
    }
/*
    public Edad(int idEdad, String nombre, List<Animal> animales) {
        this.idEdad = idEdad;
        this.nombre = nombre;
        this.animales = animales;
    }

    public Edad(int idEdad, String nombre) {
        this.idEdad = idEdad;
        this.nombre = nombre;
    }

 */

    @Override
    public String toString() {
        return "Edad{" +
                "idEdad=" + idEdad +
                ", nombre='" + nombre + '\'' +
                ", animales=" + animales +
                '}';
    }
}
