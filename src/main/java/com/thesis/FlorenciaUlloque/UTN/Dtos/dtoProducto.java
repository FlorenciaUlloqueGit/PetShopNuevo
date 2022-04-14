package com.thesis.FlorenciaUlloque.UTN.Dtos;

public class dtoProducto {
    private int id_producto;
    private String nombre;
    private String marca;
    private double precio_venta;
    private float peso;
    private String unidadMedida;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public dtoProducto(int id_producto, String nombre, String marca, double precio_venta, float peso, String unidadMedida) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio_venta = precio_venta;
        this.peso = peso;
        this.unidadMedida = unidadMedida;
    }

    public dtoProducto() {
    }
}
