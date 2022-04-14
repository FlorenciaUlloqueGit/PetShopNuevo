package com.thesis.FlorenciaUlloque.UTN.entiities;

import javax.persistence.*;

@Entity(name = "formaPago")
@Table(name = "FormaPago")
public class FormaPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFormaPago;
    private String nombre;
    @OneToOne(mappedBy = "formaPago")
    private IngresoProductos ingresoProductos;
    @OneToOne(mappedBy = "formaPago")
    private SalidaProducto salidaProducto;

    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public FormaPago(int idFormaPago, String nombre, IngresoProductos ingresoProductos, SalidaProducto salidaProducto) {
        this.idFormaPago = idFormaPago;
        this.nombre = nombre;
        this.ingresoProductos = ingresoProductos;
        this.salidaProducto = salidaProducto;
    }

    public FormaPago() {

    }

    @Override
    public String toString() {
        return "FormaPago{" +
                "idFormaPago=" + idFormaPago +
                ", nombre='" + nombre + '\'' +
                ", entradaProducto=" + ingresoProductos +
                ", salidaProducto=" + salidaProducto +
                '}';
    }
}
