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
    @Column(name = "producto_edad")
    private List<Producto> productos;

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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Edad(int idEdad, String nombre, List<Producto> productos) {
        this.idEdad = idEdad;
        this.nombre = nombre;
        this.productos = productos;
    }

    public Edad() {
    }
}
