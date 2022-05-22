package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos;


import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class ProductoDtoLista {
    private int idProducto;
    private long codBarras;
    private String nombre;

    private LocalDate fechaVencimiento;
    private double precioCompra;
    private double precioVenta;
    private Marca marca;
    private FormaVenta formaVenta;
    private UnidadMedida unidadMedida;
    private Categoria categoria;
    private TipoAnimal tipoAnimal;
    private Edad edad;
    private Tamano tamano;
    private float pesoNeto;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public long getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(long codBarras) {
        this.codBarras = codBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public FormaVenta getFormaVenta() {
        return formaVenta;
    }

    public void setFormaVenta(FormaVenta formaVenta) {
        this.formaVenta = formaVenta;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public Edad getEdad() {
        return edad;
    }

    public void setEdad(Edad edad) {
        this.edad = edad;
    }

    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
    }

    public float getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(float pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public ProductoDtoLista(int idProducto, long codBarras, String nombre, LocalDate fechaVencimiento, double precioCompra,
                            double precioVenta, Marca marca, FormaVenta formaVenta, UnidadMedida unidadMedida,
                            Categoria categoria, TipoAnimal tipoAnimal, Edad edad, Tamano tamano, float pesoNeto) {
        this.idProducto = idProducto;
        this.codBarras = codBarras;
        this.nombre = nombre;
        this.fechaVencimiento = fechaVencimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.marca = marca;
        this.formaVenta = formaVenta;
        this.unidadMedida = unidadMedida;
        this.categoria = categoria;
        this.tipoAnimal = tipoAnimal;
        this.edad = edad;
        this.tamano = tamano;
        this.pesoNeto = pesoNeto;
    }

    public ProductoDtoLista() {
    }
}