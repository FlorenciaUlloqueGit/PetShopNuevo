package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "unidadMedida")
@Table(name = "unidad_medidas")
public class UnidadMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUnidadMedida", nullable = false)
    private int idUnidadMedida;
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "unidadMedida")
    @Column(name = "producto_unidadMedida")
    private List<Producto> productos;

    public UnidadMedida(int idUnidadMedida, String nombre, List<Producto> productos) {
        this.idUnidadMedida = idUnidadMedida;
        this.nombre = nombre;
        this.productos = productos;
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

    public int getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(int idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public UnidadMedida() {
    }

    @Override
    public String toString() {
        return "UnidadMedida{" +
                "idUnidadMedida=" + idUnidadMedida +
                ", nombre='" + nombre + '\'' +
                ", productos=" + productos +
                '}';
    }
}
