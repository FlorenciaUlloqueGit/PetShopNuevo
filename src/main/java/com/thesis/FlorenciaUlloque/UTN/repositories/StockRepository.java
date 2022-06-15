package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends PagingAndSortingRepository<Stock,Integer > {


    Stock findByProductoCodBarras(long codBarras);
    Stock findByCantidad(String email); //hacer para stock minimo si lo llego a agregar
    Stock findByIdStock(int id);
    Stock findByProductoIdProducto(int idProducto);
    List<Stock> findByProductoMarcaNombre(String nombre);
    Stock findByProductoMarcaIdMarca(int idMarca);
    List<Stock> findAllByOrderByProductoNombreAsc();
    Page<Stock> findAllByOrderByProductoNombreAsc(Pageable pageable);


    @Query(value = "select * from stocks s join productos p  on p.id_producto = s.id_producto " +
            "where p.nombre LIKE %:nombre% ", nativeQuery = true)
    List<Stock> findStockByProductoNombre(@Param("nombre") String nombre);




}
