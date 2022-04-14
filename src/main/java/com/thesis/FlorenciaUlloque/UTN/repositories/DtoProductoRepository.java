package com.thesis.FlorenciaUlloque.UTN.repositories;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtoProducto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DtoProductoRepository  extends PagingAndSortingRepository<dtoProducto, Integer> {




    @Query(value =" SELECT   p.id_producto as id_producto , p.nombre as nombre, p.precio_venta, p.peso_neto as peso , uni.nombre as unidad_medida\n" +
            "FROM productos p  join marcas m on p.id_marca = m.id_marca\n" +
            "join Unidad_medidas uni on p.id_unidad_medida_peso = uni.id_unidad_medida\n" +
            "where m.nombre like %:nombre%" )
    List<dtoProducto> findByMarcaNombre(String nombre);


}

