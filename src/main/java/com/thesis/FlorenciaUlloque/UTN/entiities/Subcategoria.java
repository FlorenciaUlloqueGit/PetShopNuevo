package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "subcategoria")
@Table(name = "subcategoria_producto")
public class Subcategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id_Sub_categoria")
    private int idSubCategoria;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idCategoria") //AGREGARRRR
    private Categoria categoria;

    private String nombre;


    @JsonIgnore
    @OneToMany(mappedBy = "subcategoria")
    @Column(name = "producto_subcategoria" )
    private List<Producto> producto;

    public int getIdSubCategoria() {
        return idSubCategoria;
    }

    public void setIdSubCategoria(int idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

    public Subcategoria(int idSubCategoria, Categoria categoria, String nombre) {
        this.idSubCategoria = idSubCategoria;
        this.categoria = categoria;
        this.nombre = nombre;
    }

    public Subcategoria(int idSubCategoria, Categoria categoria, String nombre, List<Producto> producto) {
        this.idSubCategoria = idSubCategoria;
        this.categoria = categoria;
        this.nombre = nombre;
        this.producto = producto;
    }

    public Subcategoria() {
    }

    @Override
    public String toString() {
        return "Subcategoria{" +
                "idSubcategoria=" + idSubCategoria +
                ", categoria=" + categoria +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

