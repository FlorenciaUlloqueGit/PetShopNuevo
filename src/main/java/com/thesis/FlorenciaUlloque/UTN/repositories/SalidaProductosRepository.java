package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import com.thesis.FlorenciaUlloque.UTN.entiities.SalidaProducto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface SalidaProductosRepository extends PagingAndSortingRepository<SalidaProducto,Integer> {

    @Query(value = "SELECT sum(detalle.precio) FROM Egreso_productos egreso join detalle_egreso detalle on " +
            "egreso.id_egreso = detalle.id_egreso where egreso.id_egreso = :idEgreso", nativeQuery = true)
     double calcularTotalEgreso(@Param("idEgreso") int idEgreso);

    List<SalidaProducto> findAllByFecha(@Param("fecha") LocalDate fecha);

    @Query("from ingresoProductos i where fecha(i.fecha) = :date")
    SalidaProducto findByDate(@Param("date") Date date);
    SalidaProducto findByFormaPago(int idFormaPago);
    SalidaProducto findByFormaPagoNombre(String nombre);
    SalidaProducto findByClienteEmail(String email);
    SalidaProducto findByIdEgreso(int idEgreso);
    SalidaProducto findByTotal(double total);
    List<SalidaProducto> findAllByOrderByFechaDesc();
    Page<SalidaProducto> findAllByOrderByFechaDesc(Pageable pageable);

    @Query(value = "select * from egreso_productos e where Month(e.fecha) = month(:fecha) order by e.fecha asc", nativeQuery = true)
    Page<SalidaProducto> findEgresosByMonthPageable(@Param("fecha") String month, Pageable pageable);

    @Query(value = "select * from egreso_productos e where Month(e.fecha) = month(:fecha) order by e.fecha asc", nativeQuery = true)
    List<SalidaProducto> findEgresoByMonth(@Param("fecha") String month);

    @Query(value = "select * from egreso_productos e where Month(e.fecha) = month(:fecha) order by e.fecha asc", nativeQuery = true)
    List<SalidaProducto> findEgresoByDate(@Param("fecha") String month);


    @Query(value = "select SUM(e.total) 'total' from egreso_productos e where Month(e.fecha) = month(:fecha)", nativeQuery = true)
    Float findTotalSumaEgresosByMonth(@Param("fecha") String month);

    @Query(value = " select sum(e.total) from egreso_productos e where Month(e.fecha) = month(:fecha);", nativeQuery = true)
    Float findIngresosMesActual(@Param("fecha") String fecha);

    @Query(value = "  select sum(e.total) from egreso_productos e where Month(e.fecha) = month(:fecha)-1;", nativeQuery = true)
    Float findIngresosMesAnterior(@Param("fecha") String fecha);

    @Query(value = "select sum(e.total) from egreso_productos e where Month(e.fecha) = month(:fecha)-2;", nativeQuery = true)
    Float findIngresosMesAnteriorX2(@Param("fecha") String fecha);



}
