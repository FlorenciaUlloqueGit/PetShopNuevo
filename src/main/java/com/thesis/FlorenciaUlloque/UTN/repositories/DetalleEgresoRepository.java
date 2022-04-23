package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleEgreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DetalleEgresoRepository extends PagingAndSortingRepository<DetalleEgreso,Integer> {


    DetalleEgreso findBySalidaProductosIdEgreso(int idIngreso);
    DetalleEgreso findByIdDetalle(int idDetalle);
    DetalleEgreso findByProductoCodBarras(long codBarras);
    DetalleEgreso findByProductoIdProducto(int idProducto);


}
