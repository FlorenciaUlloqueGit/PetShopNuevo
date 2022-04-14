package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "producto")
@Table(name = "Productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    private long codBarras;
    private String nombre;
    private Date fechaVencimiento;
    private double precioCompra;
    private double precioVenta;

    @ManyToOne
    @JoinColumn(name = "idMarca")
    private Marca marca;

    @JoinColumn(name = "idFormaVenta")
    @OneToOne //(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private FormaVenta formaVenta;

    @ManyToOne
    @JoinColumn(name = "idSubCategoria")
    private Subcategoria subcategoria;
    @ManyToOne
    @JoinColumn(name = "idAnimal")
    private Animal animal;
    private float pesoNeto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idDetalleIngreso", insertable = false, updatable = false)
    private DetalleIngreso detalleIngreso;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idDetalleEgreso", insertable = false, updatable = false)
    private DetalleEgreso detalleEgreso;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idStock")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "idUnidadMedidaPeso")
    private UnidadMedida unidadMedida;

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public DetalleIngreso getDetalleIngreso() {
        return detalleIngreso;
    }

    public void setDetalleIngreso(DetalleIngreso detalleIngreso) {
        this.detalleIngreso = detalleIngreso;
    }

    public DetalleEgreso getDetalleEgreso() {
        return detalleEgreso;
    }

    public void setDetalleEgreso(DetalleEgreso detalleEgreso) {
        this.detalleEgreso = detalleEgreso;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public long getIdProducto() {
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

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
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

    public FormaVenta getFormaVenta() {
        return formaVenta;
    }

    public void setFormaVenta(FormaVenta formaVenta) {
        this.formaVenta = formaVenta;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public float getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(float pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public DetalleIngreso getDetalleEntrada() {
        return detalleIngreso;
    }

    public void setDetalleEntrada(DetalleIngreso detalleIngreso) {
        this.detalleIngreso = detalleIngreso;
    }

    public DetalleEgreso getDetalleSalida() {
        return detalleEgreso;
    }

    public void setDetalleSalida(DetalleEgreso detalleEgreso) {
        this.detalleEgreso = detalleEgreso;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }



    public Producto(int idProducto, long codBarras, String nombre, Date fechaVencimiento, double precioCompra, double precioVenta, FormaVenta formaVenta, Marca marca, Subcategoria subcategoria, Animal animal, float pesoNeto, DetalleIngreso detalleIngreso, DetalleEgreso detalleEgreso, Stock stock, UnidadMedida unidadMedida) {
        this.idProducto = idProducto;
        this.codBarras = codBarras;
        this.nombre = nombre;
        this.fechaVencimiento = fechaVencimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.formaVenta = formaVenta;
        this.marca = marca;
        this.subcategoria = subcategoria;
        this.animal = animal;
        this.pesoNeto = pesoNeto;
        this.detalleIngreso = detalleIngreso;
        this.detalleEgreso = detalleEgreso;
        this.stock = stock;
        this.unidadMedida = unidadMedida;
    }

    public Producto() {
    }

    public Producto(int idProducto, long codBarras, String nombre, Date fechaVencimiento, double precioCompra, double precioVenta, FormaVenta formaVenta, Marca marca, Subcategoria subcategoria, Animal animal, float pesoNeto, DetalleIngreso detalleIngreso, DetalleEgreso detalleEgreso, Stock stock) {
        this.idProducto = idProducto;
        this.codBarras = codBarras;
        this.nombre = nombre;
        this.fechaVencimiento = fechaVencimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.formaVenta = formaVenta;
        this.marca = marca;
        this.subcategoria = subcategoria;
        this.animal = animal;
        this.pesoNeto = pesoNeto;
        this.detalleIngreso = detalleIngreso;
        this.detalleEgreso = detalleEgreso;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", codBarras=" + codBarras +
                ", nombre='" + nombre + '\'' +
                ", fechaVencimiento=" + fechaVencimiento +
                ", precioCompra=" + precioCompra +
                ", precioVenta=" + precioVenta +
                ", formaVentaList=" + formaVenta +
                ", marca=" + marca +
                ", subcategoria=" + subcategoria +
                ", animal=" + animal +
                ", pesoNeto=" + pesoNeto +
                ", detalleEntrada=" + detalleIngreso +
                ", detalleSalida=" + detalleEgreso +
                ", stock=" + stock +
                '}';
    }
}
