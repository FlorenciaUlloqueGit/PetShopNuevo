package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface IngresoProductosRepository extends PagingAndSortingRepository<IngresoProductos,Integer > {


    @Query(value = "SELECT sum(detalle.precio) FROM Ingreso_productos ingreso " +
            "join detalle_ingreso detalle on ingreso.id_ingreso = detalle.id_ingreso\n" +
            "where ingreso.id_ingreso = :idIngreso", nativeQuery = true)
    double calcularTotalIngreso(@Param("idIngreso") int idIngreso);


    IngresoProductos findByProveedorIdProveedor(int idProveedor);
    IngresoProductos findByProveedorNombre(String nombre);
    IngresoProductos findByIdIngreso(int idIngreso);
    IngresoProductos findByTotal(double total);

    @Query(value = "select *  from ingreso_productos i join proveedores p \n" +
            "on p.id_proveedor = i.id_proveedor\n" +
            "where Month(i.fecha) = month(curdate())\n" +
            "group by p.nombre order by i.fecha asc", nativeQuery = true)

    List<IngresoProductos> findIngresosbyMoth();

    @Query(value = "select sum(total) 'total' , p.nombre 'proveedor' from ingreso_productos i join proveedores p\n" +
            "    on p.id_proveedor = i.id_proveedor\n" +
            "    where Month(i.fecha) = month(curdate()) and p.id_proveedor = :idProveedor\n" +
            "    group by p.nombre", nativeQuery = true)
    double sumarTotalPorProveedor(@Param("idProveedor") int idProveedor);


    @Query(value = "select sum(e.total) from ingreso_productos e where Month(e.fecha) = month(:fecha);", nativeQuery = true)
    Float findVentasMesAcual(@Param("fecha") String fecha);

    @Query(value = "select sum(e.total) from ingreso_productos e where Month(e.fecha) = month(:fecha)-1;", nativeQuery = true)
    Float findVentasMesAnterior(@Param("fecha") String fecha);

    @Query(value = "select sum(e.total) from ingreso_productos e where Month(e.fecha) = month(:fecha)-2;", nativeQuery = true)
    Float findVentasMesAnteAnterior(@Param("fecha") String fecha);

}
