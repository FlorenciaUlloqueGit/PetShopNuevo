package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.entiities.SalidaProducto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface SalidaProductosRepository extends PagingAndSortingRepository<SalidaProducto,Integer> {

    @Query(value = "SELECT sum(detalle.precio) FROM Egreso_productos egreso join detalle_egreso detalle on " +
            "egreso.id_egreso = detalle.id_egreso where egreso.id_egreso = 1", nativeQuery = true)
    double calcularTotalEgreso(@Param("idEgreso") int idEgreso);



    @Query("from ingresoProductos i where fecha(i.fecha) = :date")
    SalidaProducto findByDate(@Param("date") Date date);
    SalidaProducto findByFormaPago(int idFormaPago);
    SalidaProducto findByFormaPagoNombre(String nombre);
    SalidaProducto findByClienteEmail(String email);
    SalidaProducto findByIdEgreso(int idEgreso);
    SalidaProducto findByTotal(double total);

}
