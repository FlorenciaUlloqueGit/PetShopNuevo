package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoReporte2;
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

    @Query(value="select* from productos p join stocks stock on p.id_producto = stock.id_producto " +
            "where stock.cantidad = 0",nativeQuery = true)
    List<Producto> findAllProductosByStockCERO();

    @Query(value = "select * from productos p where p.nombre LIKE %:nombre% and p.id_forma_venta = 2", nativeQuery = true)
    List<Producto> findAllProductosByNameXGramo(@Param("nombre") String nombre);


    @Query(value = "select * from productos p where p.nombre LIKE %:nombre% and p.id_forma_venta != 2", nativeQuery = true)
    List<Producto> findAllProductosByName(@Param("nombre") String nombre);

    //FUNCIONA
    @Query(value = "SELECT  p.nombre, p.precio_compra, p.precio_venta,  concat(p.peso_neto,\" \", uni.nombre) as unidadMedida FROM productos p\n" +
            "join unidad_medidas uni on p.id_unidad_medida_peso = uni.id_unidad_medida ;", nativeQuery = true)
    List<String> findAllProductosWithAllCost(int page, int limit);


    //FUNCIONa
    @Query(value = "SELECT  p.nombre, p.precio_venta,  concat(p.peso_neto,\" \", uni.nombre) as unidadMedida FROM " +
            "productos p join unidad_medidas uni on p.id_unidad_medida_peso = uni.id_unidad_medida ;", nativeQuery = true)
    List<String> findAllProductosWithSellCost(int page, int limit);


    @Query(value = "select prod.nombre 'producto', cat.nombre 'categoria', forma.nombre 'forma venta', " +
           "prod.precio_Venta 'precio venta ',SUM(de.cantidad) 'cantidad vendida', SUM(de.precio) 'subtotal' " +
           "from egreso_productos ep join detalle_egreso de on de.id_egreso = ep.id_egreso " +
           "join productos prod on prod.id_producto = de.id_producto " +
           "join categoria_producto cat on prod.id_categoria = cat.id_categoria " +
           "join forma_ventas forma  on forma.id_forma_venta = prod.id_forma_venta " +
           "where ep.fecha = Curdate() " +
           "group by prod.nombre, cat.nombre , forma.nombre , prod.precio_Venta ", nativeQuery = true)
    List<String> findAllProductosVendidosHoy();


    @Query(value = "select * from productos p join stocks st on st.id_producto = p.id_Producto\n" +
            "where st.cantidad != 0 and p.fecha_vencimiento  between curdate() and  date_add(curdate(), interval 3 month)\n" +
            "order by p.fecha_vencimiento asc", nativeQuery = true)
    List<Producto> findProductosPorVencer();

    @Query(value = "select * from productos p join stocks st on st.id_producto = p.id_Producto\n" +
            "where st.cantidad != 0 and p.fecha_vencimiento < Curdate()\n" +
            "order by p.fecha_vencimiento asc", nativeQuery = true)
    List<Producto> findProductosVencidos();

    @Query(value = "select * from productos p join detalle_egreso de on de.id_producto = p.id_Producto\n" +
            "    join egreso_productos e on e.id_egreso = de.id_egreso\n" +
            "    where p.id_forma_venta = 1 and Month(e.fecha) = month(curdate())\n" +
            "    group by p.nombre", nativeQuery = true)
    List<Producto> findProductosVendidosBolsa();

    @Query(value = "select sum(de.cantidad)' cantidad'  from productos p join detalle_egreso de on de.id_producto = p.id_Producto\n" +
            "join egreso_productos e on e.id_egreso = de.id_egreso\n" +
            "where p.id_forma_venta = 1 and Month(e.fecha) = month(curdate())\n" +
            "group by p.nombre", nativeQuery = true)
    List<Float> findCantidadProductoVendido();

    @Query(value = "select * from productos p join detalle_egreso de on de.id_producto = p.id_Producto\n" +
            "join egreso_productos e on e.id_egreso = de.id_egreso\n" +
            "where p.id_forma_venta = 2 and Month(e.fecha) = month(curdate())\n" +
            "group by p.nombre", nativeQuery = true)
    List<Producto> findProductosVendidosKG();

    @Query(value = "select  sum(de.cantidad)'cantidad vendida'  from productos p join detalle_egreso de on de.id_producto = p.id_Producto\n" +
            "join egreso_productos e on e.id_egreso = de.id_egreso\n" +
            "join stocks s on s.id_producto = p.id_producto\n" +
            "where p.id_forma_venta = 2 and Month(e.fecha) = month(curdate())", nativeQuery = true)
    List<Float> findCantidadProductoVendidoXKg();

}