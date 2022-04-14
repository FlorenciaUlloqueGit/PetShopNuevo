package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "animal")
@Table(name = "Animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Animal")
    private int idAnimal;

    @ManyToOne
    @JoinColumn(name = "idTipoAnimal")
    private TipoAnimal tipoAnimal;

    @ManyToOne
    @JoinColumn(name= "idRaza")
    private Tamano tamano;

    @ManyToOne
    @JoinColumn(name = "idEdad")
    private Edad edad;

    @JsonIgnore
    @OneToMany(mappedBy = "animal")
    @Column(name = "producto_animal")
    private List<Producto> productos;


    public Animal() {
    }

    public Animal(int idAnimal, TipoAnimal tipoAnimal, Tamano tamano, Edad edad, List<Producto> productos) {
        this.idAnimal = idAnimal;
        this.tipoAnimal = tipoAnimal;
        this.tamano = tamano;
        this.edad = edad;
        this.productos = productos;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
    }

    public Edad getEdad() {
        return edad;
    }

    public void setEdad(Edad edad) {
        this.edad = edad;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
