package com.thesis.FlorenciaUlloque.UTN.repositories;

import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository  extends PagingAndSortingRepository<Marca, Integer> {
    Marca findByNombre(String nombre);
    Marca findByIdMarca(long id);
    @Query(value = "select  m.nombre from marcas m", nativeQuery = true)
    List<String> findAllMarcas(int page, int limit); //REVISAR
}
