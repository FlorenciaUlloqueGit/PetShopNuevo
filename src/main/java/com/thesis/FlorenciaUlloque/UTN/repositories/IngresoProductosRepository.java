package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface IngresoProductosRepository extends PagingAndSortingRepository<IngresoProductos,Integer > {


    @Query("from ingresoProductos i where fecha(i.fecha) = :date")
     IngresoProductos findByDate(@Param("date") Date date);

   // IngresoProductos findByDate2(Date date);
    IngresoProductos findByProveedorIdProveedor(int idProveedor);
    IngresoProductos findByProveedorNombre(String nombre);
    IngresoProductos findByIdIngreso(int idIngreso);
    IngresoProductos findByTotal(double total);

}
