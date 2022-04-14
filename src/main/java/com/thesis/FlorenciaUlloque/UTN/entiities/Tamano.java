package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name="tamano")
@Table(name = "raza")
public class Tamano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Tam")
    private int idTam;
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "tamano", cascade = CascadeType.ALL) //all propaga la operacion hasta la ultima dirección. si elimino user details tambien se propaga a sus listas
    @Column(name = "animal_tamano")
    private List<Animal>animales;

    public int getIdTam() {
        return idTam;
    }

    public void setIdTam(int idTam) {
        this.idTam = idTam;
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

    public Tamano(int idTam, String nombre, List<Animal> animales) {
        this.idTam = idTam;
        this.nombre = nombre;
        this.animales = animales;
    }

    public Tamano() {
    }

    @Override
    public String toString() {
        return "Tamano{" +
                "idTam=" + idTam +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
