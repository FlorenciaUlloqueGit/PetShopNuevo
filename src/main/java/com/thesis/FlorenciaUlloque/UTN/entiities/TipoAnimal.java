package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipoAnimal")
public class TipoAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Tipo")
    private int idTipo;
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoAnimal", cascade = CascadeType.ALL) //all propaga la operacion hasta la ultima dirección. si elimino user details tambien se propaga a sus listas
    @Column(name = "producto_tipoAnimal")
    private List <Producto> productos;

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
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

    public TipoAnimal(int idTipo, String nombre, List<Producto> productos) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.productos = productos;
    }

    public TipoAnimal() {
    }

}
