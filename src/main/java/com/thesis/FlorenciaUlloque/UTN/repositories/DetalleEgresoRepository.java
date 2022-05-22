package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleEgreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DetalleEgresoRepository extends PagingAndSortingRepository<DetalleEgreso,Integer> {


    DetalleEgreso findBySalidaProductoIdEgreso(int idEgreso);
    DetalleEgreso findByIdDetalleEgreso(int idDetalleEgreso);
    DetalleEgreso findByProductoCodBarras(long codBarras);
    DetalleEgreso findByProductoIdProducto(int idProducto);
    List<DetalleEgreso> findAllBySalidaProductoIdEgreso(int idEgreso);

}
