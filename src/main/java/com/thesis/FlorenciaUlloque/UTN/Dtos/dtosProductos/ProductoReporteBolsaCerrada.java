package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Categoria;
import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import com.thesis.FlorenciaUlloque.UTN.entiities.UnidadMedida;

public class ProductoReporteBolsaCerrada {
    private int idProducto;
    private String nombre;
    private Categoria categoria;
    private Marca marca;
    private int pesoNeto;
    private UnidadMedida unidadMedida;
    private float cantidad;

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoReporteBolsaCerrada() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public int getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(int pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
