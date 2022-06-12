package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleEgreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select count(de.cantidad) 'cantidad' from detalle_egreso de join egreso_productos e on de.id_egreso" +
            " = e.id_egreso join productos p on p.id_producto = de.id_producto where p.id_categoria = 1 and e.fecha = Curdate()" +
            "group by p.id_categoria ", nativeQuery = true)
    Integer findCantidadTotalVendidoProductoCategoria1Hoy();

    @Query(value = "select count(de.cantidad) 'cantidad' from detalle_egreso de join egreso_productos e on de.id_egreso" +
            " = e.id_egreso join productos p on p.id_producto = de.id_producto where p.id_categoria = 2  and e.fecha = Curdate()" +
            "group by p.id_categoria", nativeQuery = true)
    Integer findCantidadTotalVendidoProductoCategoria2Hoy();

    @Query(value = "select count(de.cantidad) 'cantidad' from detalle_egreso de join egreso_productos e on de.id_egreso" +
            " = e.id_egreso join productos p on p.id_producto = de.id_producto where p.id_categoria = 3 and e.fecha = Curdate()" +
            "group by p.id_categoria", nativeQuery = true)
    Integer findCantidadTotalVendidoProductoCategoria3Hoy();

    @Query(value = "select count(de.cantidad) 'cantidad' from detalle_egreso de join egreso_productos e on de.id_egreso" +
            " = e.id_egreso join productos p on p.id_producto = de.id_producto where p.id_categoria = 4 and e.fecha = Curdate()" +
            "group by p.id_categoria", nativeQuery = true)
    Integer findCantidadTotalVendidoProductoCategoria4Hoy();


    @Query(value = "select (sum(de.cantidad)* p.peso_neto) from egreso_productos e " +
            "join detalle_egreso de on de.id_egreso = e.id_egreso join productos p on p.id_producto =" +
            " de.id_producto where Month(e.fecha) = month(curdate()) and p.id_forma_venta =1", nativeQuery = true)
    Integer findCantBolsasMensualesKgs();

    @Query(value = "select (sum(de.cantidad)* p.peso_neto) from egreso_productos e " +
            "join detalle_egreso de on de.id_egreso = e.id_egreso join productos p on p.id_producto =" +
            " de.id_producto where Month(e.fecha) = month(curdate()) and p.id_forma_venta =2", nativeQuery = true)
    Integer findCantKgVendidos();
}
