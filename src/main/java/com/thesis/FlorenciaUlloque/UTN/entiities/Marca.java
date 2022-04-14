package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "marca")
@Table(name = "Marcas")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMarca;
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "marca")
    @Column(name = "producto_marca")
    private List<Producto> producto;

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

    @JsonIgnore //@JsonIgnoreProperties(value={"entidad1", "entidad2"}
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "marca_proveedor",joinColumns = @JoinColumn(name = "idMarca", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "idProveedor", nullable = false))
    private List<Proveedor> proveedores;


    public void addProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
        proveedor.getMarcas().add(this);
    }
    public void removeProveedor(Proveedor proveedor) {
        proveedores.remove(proveedor);
        proveedor.getMarcas().remove(this);
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public Marca(int idMarca, String nombre, List<Proveedor> proveedores) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.proveedores = proveedores;
    }

    public Marca(int idMarca, String nombre) {
        this.idMarca = idMarca;
        this.nombre = nombre;
    }

    public Marca() {
    }

    @Override
    public String toString() {
        return "Marca{" +
                "idMarca=" + idMarca +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
