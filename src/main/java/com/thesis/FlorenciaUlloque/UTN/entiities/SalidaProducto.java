package com.thesis.FlorenciaUlloque.UTN.entiities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "salidaProducto")
@Table(name = "Egreso_Productos")
public class SalidaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEgreso;
    private double total;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date fecha;
    @OneToOne
    @JoinColumn(name = "formaPago_idFormaPago")
    private FormaPago formaPago;

    @OneToMany(mappedBy = "salidaProducto" , cascade = CascadeType.ALL)
    @Column(name = "detalleSalida_salidaProducto")
    private List<DetalleEgreso> listadoSalidaProducto;

    public long getIdEgreso() {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    public SalidaProducto(int idEgreso, double total, Date fecha, FormaPago formaPago) {
        this.idEgreso = idEgreso;
        this.total = total;
        this.fecha = fecha;
        this.formaPago = formaPago;
    }

    public SalidaProducto() {
    }

    @Override
    public String toString() {
        return "SalidaProducto{" +
                "idEgreso=" + idEgreso +
                ", total=" + total +
                ", fecha=" + fecha +
                ", formaPago=" + formaPago +
                '}';
    }
}
