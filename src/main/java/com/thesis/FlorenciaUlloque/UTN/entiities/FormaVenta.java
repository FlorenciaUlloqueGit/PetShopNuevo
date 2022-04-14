package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "formaVenta")
@Table(name = "FormaVentas")
public class FormaVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFormaVenta;
    private String nombre;
    @OneToOne(mappedBy = "formaVenta", fetch = FetchType.LAZY/* , cascade = CascadeType.ALL, orphanRemoval = true*/)
    private Producto producto;

    public int getIdFormaVenta() {
        return idFormaVenta;
    }

    public void setIdFormaVenta(int idFormaVenta) {
        this.idFormaVenta = idFormaVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Producto getProducto() {
        return producto;
    }

    @JsonIgnore
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public FormaVenta(int idFormaVenta, String nombre, Producto producto) {
        this.idFormaVenta = idFormaVenta;
        this.nombre = nombre;
        this.producto = producto;
    }

    public FormaVenta() {
    }

    public FormaVenta(int idFormaVenta, String nombre) {
        this.idFormaVenta = idFormaVenta;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "FormaVenta{" +
                "idFormaVenta=" + idFormaVenta +
                ", nombre='" + nombre + '\'' +
                ", producto=" + producto +
                '}';
    }
}
