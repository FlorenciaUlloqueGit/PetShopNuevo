package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface DetalleIngresoRepository extends PagingAndSortingRepository<DetalleIngreso,Integer> {


    DetalleIngreso findByIngresoProductosIdIngreso(int idIngreso);
    DetalleIngreso findByIdDetalle(int idDetalle);
    DetalleIngreso findByProductoCodBarras(long codBarras);
    DetalleIngreso findByProductoIdProducto(int idProducto);
    List<DetalleIngreso> findAllByIngresoProductosIdIngreso(int idIngreso);



}
