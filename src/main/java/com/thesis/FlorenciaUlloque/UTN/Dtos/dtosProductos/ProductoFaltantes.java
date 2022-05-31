package com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos;

import com.thesis.FlorenciaUlloque.UTN.entiities.Categoria;
import com.thesis.FlorenciaUlloque.UTN.entiities.FormaVenta;
import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import com.thesis.FlorenciaUlloque.UTN.entiities.UnidadMedida;

public class ProductoFaltantes {
    private int idProducto;
    private long codBarras;
    private String nombre;
    private Categoria categoria;
    private Marca marca;
    private FormaVenta formaVenta;
    private float pesoNeto;
    private UnidadMedida unidadMedida;

    public ProductoFaltantes(int idProducto, long codBarras, String nombre, Categoria categoria, Marca marca,
                             FormaVenta formaVenta, float pesoNeto, UnidadMedida unidadMedida) {
        this.idProducto = idProducto;
        this.codBarras = codBarras;
        this.nombre = nombre;
        this.categoria = categoria;
        this.marca = marca;
        this.formaVenta = formaVenta;
        this.pesoNeto = pesoNeto;
        this.unidadMedida = unidadMedida;
    }

    public ProductoFaltantes() {
    }

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

    public FormaVenta getFormaVenta() {
        return formaVenta;
    }

    public void setFormaVenta(FormaVenta formaVenta) {
        this.formaVenta = formaVenta;
    }

    public float getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(float pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
