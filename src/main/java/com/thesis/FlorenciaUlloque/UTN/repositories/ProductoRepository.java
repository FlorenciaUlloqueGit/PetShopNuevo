package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ProductoRepository extends PagingAndSortingRepository<Producto,Long > {


    Producto findByFechaVencimiento(Date fechaVencimiento);

    Producto findByCodBarras(long codBarras);

    Producto findByNombre(String nombre);

    Producto findByIdProducto(int id);

    @Query(value = "SELECT  prod.id_Producto, prod.nombre as producto, prod.precio_compra, prod.precio_venta,  prod.peso_neto ," +
            " marca.nombre as marca, sub.nombre as categoria FROM productos prod join Marcas marca on" +
            " marca.id_marca = prod.id_marca join subcategoria_producto sub on sub.id_Sub_Categoria = prod.id_Sub_Categoria \n" +
            "join animal ani on ani.id_animal = prod.id_animal \n" +
            "where id_forma_venta = id_Forma_Venta;", nativeQuery = true)
    Producto findByFormaVenta(@Param("id_Forma_Venta") int idFormaVenta); //NO funciona. error: me pide el idAnimal

/*
   /@Query(value = "SELECT productos.nombre as producto, productos.precio_venta, concat(productos.peso_neto) as peso\n" +
            "FROM productos  join marcas m on p.id_marca = m.id_marca\n" +
            "join Unidad_medidas uni on p.id_unidad_medida_peso = uni.id_unidad_medida\n" +
            "where m.nombre like %:nombre%", nativeQuery = true)


    List<Producto> findByMarcaNombre(@Param("nombre") String nombre);

 */

    List<Producto> findAllDatesByMarcaNombre(String nombre);


    @Query(value = "SELECT  p.nombre, p.precio_venta,  p.peso_neto , m.nombre\n" +
            "FROM productos p join Marcas m on m.id_marca = p.id_marca\n" +
            "where id_Sub_Categoria = idSubcategoria;", nativeQuery = true)
    Producto findBySubCategoria(@Param("idSubcategoria") int idSubcategoria);

    @Query(value = "SELECT  p.nombre, p.precio_venta,  p.peso_neto , m.nombre, s.nombre\n" +
            "FROM productos p join Marcas m on m.id_marca = p.id_marca join subcategoria_producto s on s.id_Sub_Categoria = s.id_Sub_Categoria \n" +
            "where id_Animal = idAnimal;", nativeQuery = true)
    Producto findByAnimal(@Param("idAnimal") int idAnimal);


    @Query(value = "SELECT  p.nombre, p.precio_compra, p.precio_venta,  p.peso_neto , m.nombre\n" +
            "FROM productos p join Marcas m on m.id_marca = p.id_marca ;", nativeQuery = true)
    List<String> findAllProductosWithAllCost(int page, int limit);

    @Query(value = "SELECT  p.nombre, p.precio_venta,  p.peso_neto , m.nombre\n" +
            "FROM productos p join Marcas m on m.id_marca = p.id_marca ;", nativeQuery = true)
    List<String> findAllProductosWithSellCost(int page, int limit);
}