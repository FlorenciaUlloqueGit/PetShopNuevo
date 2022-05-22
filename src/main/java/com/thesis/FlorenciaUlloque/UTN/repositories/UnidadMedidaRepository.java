package com.thesis.FlorenciaUlloque.UTN.repositories;

import com.thesis.FlorenciaUlloque.UTN.entiities.Categoria;
import com.thesis.FlorenciaUlloque.UTN.entiities.UnidadMedida;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaRepository extends PagingAndSortingRepository<UnidadMedida, Integer> {

}
