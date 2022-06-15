package com.thesis.FlorenciaUlloque.UTN.repositories;

import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends PagingAndSortingRepository<Proveedor, Integer> {

    Proveedor findByNombre(String nombre);
    Proveedor findById(int id);
    List<Proveedor>findAllByOrderByNombreAsc();

    @Query(value = "select * from proveedores p where p.id_proveedor != 4 order by p.nombre asc", nativeQuery = true)
    Page<Proveedor> findAllByOrderByNombre(Pageable pageable);

    @Query(value = "select id_proveedor, nombre, telefono from proveedores", nativeQuery = true)
    List<String> findAllProveedors(int page, int limit); //REVISAR
}
