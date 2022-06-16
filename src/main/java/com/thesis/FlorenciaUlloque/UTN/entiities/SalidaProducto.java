package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.xml.bind.v2.TODO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity(name = "salidaProducto")
@Table(name = "Egreso_Productos")
public class SalidaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEgreso;

    private double total;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;


    @JoinColumn(name = "idFormaPago")
    @OneToOne(fetch = FetchType.LAZY)
    private FormaPago formaPago;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = true)
    private Cliente cliente;

   // TODO: asegurarme del orpham remove cuando borre una inserciòn de la clase salidaProducto
    @JsonIgnore
    @OneToMany(mappedBy = "salidaProducto" , cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "detalleSalida_salidaProducto")
    private List<DetalleEgreso> listadoSalidaProducto;

    private int cantidadCuotas;
    private float porcentajeInteres;

    public SalidaProducto(int idEgreso, double total, LocalDate fechaFormato, FormaPago formaPago, Cliente cliente,
                          List<DetalleEgreso> listadoSalidaProducto) {
        this.idEgreso = idEgreso;
        this.total = total;
        this.fecha = fechaFormato;
        this.formaPago = formaPago;
        this.cliente = cliente;
        this.listadoSalidaProducto = listadoSalidaProducto;
    }

    public int getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(int cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    public float getPorcentajeInteres() {
        return porcentajeInteres;
    }

    public void setPorcentajeInteres(float porcentajeInteres) {
        this.porcentajeInteres = porcentajeInteres;
    }

    public SalidaProducto(int idEgreso, double total, LocalDate fecha, FormaPago formaPago, Cliente cliente,
                          List<DetalleEgreso> listadoSalidaProducto, int cantidadCuotas, float porcentajeInteres) {
        this.idEgreso = idEgreso;
        this.total = total;
        this.fecha = fecha;
        this.formaPago = formaPago;
        this.cliente = cliente;
        this.listadoSalidaProducto = listadoSalidaProducto;
        this.cantidadCuotas = cantidadCuotas;
        this.porcentajeInteres = porcentajeInteres;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(int idEgreso) {
        this.idEgreso = idEgreso;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public List<DetalleEgreso> getListadoSalidaProducto() {
        return listadoSalidaProducto;
    }

    public void setListadoSalidaProducto(List<DetalleEgreso> listadoSalidaProducto) {
        this.listadoSalidaProducto = listadoSalidaProducto;
    }

    public SalidaProducto(int idEgreso, double total, LocalDate fecha, FormaPago formaPago,Cliente cliente) {
        this.idEgreso = idEgreso;
        this.total = total;
        this.fecha = fecha;
        this.formaPago = formaPago;
        this.cliente = cliente;
    }

    public SalidaProducto() {
    }

}
