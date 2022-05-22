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
    @Column(name = "producto_tamano")
    private List<Producto>productos;

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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Tamano(int idTam, String nombre, List<Producto> productos) {
        this.idTam = idTam;
        this.nombre = nombre;
        this.productos = productos;
    }

    public Tamano() {
    }


}
