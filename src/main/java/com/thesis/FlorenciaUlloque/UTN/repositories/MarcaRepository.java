package com.thesis.FlorenciaUlloque.UTN.repositories;

import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository  extends PagingAndSortingRepository<Marca, Integer> {
    Marca findByNombre(String nombre);
    Marca findByIdMarca(int id);
    List<Marca> findAllByOrderByNombreAsc();
    @Query(value = "select * from marcas m where m.id_marca != 5 order by m.nombre asc", nativeQuery = true)
    Page<Marca> findAllByOrderByNombreAsc(Pageable pageable);
    @Query(value = "select  m.nombre from marcas m", nativeQuery = true)
    List<String> findAllMarcas(int page, int limit); //REVISAR
}
