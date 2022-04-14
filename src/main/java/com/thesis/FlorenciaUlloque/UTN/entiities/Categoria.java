package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "categoria")
@Table(name = "Categoria_Producto")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
//    @Column(name = "subcategoria_categoria")
    private List<Subcategoria> listadoSubcategorias;



    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Subcategoria> getListadoSubcategorias() {
        return listadoSubcategorias;
    }

    public void setListadoSubcategorias(List<Subcategoria> listadoSubcategorias) {
        this.listadoSubcategorias = listadoSubcategorias;
    }

    public Categoria(int idCategoria, String nombre, List<Subcategoria> listadoSubcategorias) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.listadoSubcategorias = listadoSubcategorias;

    }

    public Categoria() {
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                ", listadoSubcategorias=" + listadoSubcategorias +
                '}';
    }
}
