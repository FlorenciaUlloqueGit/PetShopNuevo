package com.thesis.FlorenciaUlloque.UTN.entiities;

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

    @ManyToMany(mappedBy = "proveedores", fetch = FetchType.EAGER)
    private List<Marca> marcas;

    @OneToMany(mappedBy = "proveedor")
    @Column(name = "IngresoProductos_proveedor")
    private List<IngresoProductos> ingresoProductosList;

    public long getIdProveedor() {
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

    public List<IngresoProductos> getIngresoProductosList() {
        return ingresoProductosList;
    }

    public void setIngresoProductosList(List<IngresoProductos> ingresoProductosList) {
        this.ingresoProductosList = ingresoProductosList;
    }

    public Proveedor() {
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
