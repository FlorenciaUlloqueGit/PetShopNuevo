package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ProductoRepository extends PagingAndSortingRepository<Producto,Integer > {


    Producto findByFechaVencimiento(Date fechaVencimiento);

    Producto findByCodBarras(long codBarras);

    Producto findByNombre(String nombre);

    Producto findByIdProducto(int id);

    @Query(value = "SELECT  prod.nombre as producto, prod.precio_venta, \n" +
            " concat(prod.peso_neto,\" \", uni.nombre) as unidadMedida\n" +
            " from productos prod join unidad_medidas uni " +
            "on prod.id_unidad_medida_peso = uni.id_unidad_medida\n" +
            " where prod.id_forma_venta = :idFormaVenta", nativeQuery = true)
    List<Producto> findByFormaVenta( int idFormaVenta); //NO funciona. error: me pide el idAnimal


    //trae TODOS los datos de los productos por marca
    List<Producto> findAllDatesByMarcaNombre(String nombre);

//hacer

    List<Producto> findAllDatesBySubcategoriaNombre(String nombre);

    List<Producto> findAllDatesByAnimalTipoAnimalNombre(String nombre);


    @Query(value = "SELECT  p.nombre, p.precio_venta,  p.peso_neto , m.nombre\n" +
            "FROM productos p join Marcas m on m.id_marca = p.id_marca\n" +
            "where id_Sub_Categoria = idSubcategoria;", nativeQuery = true)
    Producto findBySubCategoria(@Param("idSubcategoria") int idSubcategoria);

    @Query(value = "SELECT  p.nombre, p.precio_venta,  p.peso_neto , m.nombre, s.nombre\n" +
            "FROM productos p join Marcas m on m.id_marca = p.id_marca join subcategoria_producto s on s.id_Sub_Categoria = s.id_Sub_Categoria \n" +
            "where id_Animal = idAnimal;", nativeQuery = true)
    Producto findByAnimal(@Param("idAnimal") int idAnimal);


    //FUNCIONA
    @Query(value = "SELECT  p.nombre, p.precio_compra, p.precio_venta,  concat(p.peso_neto,\" \", uni.nombre) as unidadMedida FROM productos p\n" +
            "join unidad_medidas uni on p.id_unidad_medida_peso = uni.id_unidad_medida ;", nativeQuery = true)
    List<String> findAllProductosWithAllCost(int page, int limit);


    //FUNCIONa
    @Query(value = "SELECT  p.nombre, p.precio_venta,  concat(p.peso_neto,\" \", uni.nombre) as unidadMedida FROM " +
            "productos p join unidad_medidas uni on p.id_unidad_medida_peso = uni.id_unidad_medida ;", nativeQuery = true)
    List<String> findAllProductosWithSellCost(int page, int limit);
}