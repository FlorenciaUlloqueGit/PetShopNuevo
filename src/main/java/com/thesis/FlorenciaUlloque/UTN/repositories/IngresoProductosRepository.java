package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface IngresoProductosRepository extends PagingAndSortingRepository<IngresoProductos,Integer > {


    @Query(value = "SELECT sum(detalle.precio) FROM Ingreso_productos ingreso " +
            "join detalle_ingreso detalle on ingreso.id_ingreso = detalle.id_ingreso\n" +
            "where ingreso.id_ingreso = :idIngreso", nativeQuery = true)
    double calcularTotalIngreso(@Param("idIngreso") int idIngreso);


    IngresoProductos findByProveedorIdProveedor(int idProveedor);
    IngresoProductos findByProveedorNombre(String nombre);
    IngresoProductos findByIdIngreso(int idIngreso);
    IngresoProductos findByTotal(double total);


}
