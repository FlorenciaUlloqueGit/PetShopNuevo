package com.thesis.FlorenciaUlloque.UTN.repositories;

import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends PagingAndSortingRepository<Proveedor, Long> {

    Proveedor findByNombre(String nombre);
    Proveedor findById(long id);

    @Query(value = "select id_proveedor, nombre, telefono from proveedores", nativeQuery = true)
    List<String> findAllProveedors(int page, int limit); //REVISAR
}
