package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity(name = "producto")
@Table(name = "Productos")
public class Producto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    @Column(nullable = true)
    private long codBarras;
    private String nombre;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    private LocalDate fechaVencimiento;
    private double precioCompra;
    private double precioVenta;

    @ManyToOne
    @JoinColumn(name = "idMarca")
    private Marca marca;

    @JoinColumn(name = "idFormaVenta")
    @OneToOne
    private FormaVenta formaVenta;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idTipoAnimal")
    private TipoAnimal tipoAnimal;

    @ManyToOne
    @JoinColumn(name = "idEdad")
    private Edad edad;

    @ManyToOne
    @JoinColumn(name = "idTam")
    private Tamano tamano;

    private float pesoNeto;

    @JsonIgnore
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleIngreso> detalleIngreso;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idDetalleEgreso", insertable = false, updatable = false)
    private DetalleEgreso detalleEgreso;

    @JsonIgnore
    @OneToOne(mappedBy = "producto" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "idUnidadMedidaPeso")
    private UnidadMedida unidadMedida;

    public Producto(long codBarras, String nombre, LocalDate fechaVencimiento, double precioCompra,
                    double precioVenta, Marca marca, FormaVenta formaVenta, Categoria categoria,
                   float pesoNeto, UnidadMedida unidadMedida, TipoAnimal tipoAnimal,
                    Tamano tamano, Edad edad) {
        this.codBarras = codBarras;
        this.nombre = nombre;
        this.fechaVencimiento = fechaVencimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.marca = marca;
        this.formaVenta = formaVenta;
        this.categoria = categoria;
        this.pesoNeto = pesoNeto;
        this.unidadMedida = unidadMedida;
        this.tipoAnimal = tipoAnimal;
        this.tamano = tamano;
        this.edad = edad;
    }
    public Producto(String nombre, LocalDate fechaVencimiento, double precioCompra,
                    double precioVenta, Marca marca, FormaVenta formaVenta, Categoria categoria,
                    float pesoNeto, UnidadMedida unidadMedida, TipoAnimal tipoAnimal,
                    Tamano tamano, Edad edad) {
        this.nombre = nombre;
        this.fechaVencimiento = fechaVencimiento;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.marca = marca;
        this.formaVenta = formaVenta;
        this.categoria = categoria;
        this.pesoNeto = pesoNeto;
        this.unidadMedida = unidadMedida;
        this.tipoAnimal = tipoAnimal;
        this.tamano = tamano;
        this.edad = edad;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<DetalleIngreso> getDetalleIngreso() {
        return detalleIngreso;
    }

    public void setDetalleIngreso(List<DetalleIngreso> detalleIngreso) {
        this.detalleIngreso = detalleIngreso;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
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

    public float getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(float pesoNeto) {
        this.pesoNeto = pesoNeto;
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

    public Producto() {
    }






}
