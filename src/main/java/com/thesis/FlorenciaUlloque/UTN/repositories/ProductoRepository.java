package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ProductoRepository extends CrudRepository<Producto,Integer > {



    Producto findByFechaVencimiento(Date fechaVencimiento);

    Producto findByCodBarras(long codBarras);

    Producto findByNombre(String nombre);

    Producto findByIdProducto(int id);

    List<Producto> findAllByCodBarras(long codBarras);

    //trae TODOS los datos de los productos por marca
    List<Producto> findAllDatesByMarcaNombre(String nombre);


    @Query(value = "select * from productos p where p.nombre LIKE %:nombre%", nativeQuery = true)
    List<Producto> findAllProductosByName(@Param("nombre") String nombre);

    //FUNCIONA
    @Query(value = "SELECT  p.nombre, p.precio_compra, p.precio_venta,  concat(p.peso_neto,\" \", uni.nombre) as unidadMedida FROM productos p\n" +
            "join unidad_medidas uni on p.id_unidad_medida_peso = uni.id_unidad_medida ;", nativeQuery = true)
    List<String> findAllProductosWithAllCost(int page, int limit);


    //FUNCIONa
    @Query(value = "SELECT  p.nombre, p.precio_venta,  concat(p.peso_neto,\" \", uni.nombre) as unidadMedida FROM " +
            "productos p join unidad_medidas uni on p.id_unidad_medida_peso = uni.id_unidad_medida ;", nativeQuery = true)
    List<String> findAllProductosWithSellCost(int page, int limit);
}