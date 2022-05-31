package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
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



}
