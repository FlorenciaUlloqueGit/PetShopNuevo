package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "proveedor")
@Table(name = "Proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Proveedor")
    private int idProveedor;
    private String nombre;
    private long telefono;

    @JsonIgnore
    @OneToMany(mappedBy = "proveedor")
    @Column(name = "marca_proveedor")
    private List<Marca> marcas;

    @JsonIgnore
    @OneToMany(mappedBy = "proveedor", orphanRemoval = false)
    @Column(name = "IngresoProductos_proveedor")
    private List<IngresoProductos> listaProductos;

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public List<IngresoProductos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<IngresoProductos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Proveedor() {
    }

    public Proveedor(String nombre, long telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                ", marcas=" + marcas +
                '}';
    }
}
