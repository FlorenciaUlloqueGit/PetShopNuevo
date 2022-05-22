package com.thesis.FlorenciaUlloque.UTN.repositories;

import com.thesis.FlorenciaUlloque.UTN.entiities.FormaVenta;
import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaVentaRepository extends PagingAndSortingRepository<FormaVenta, Integer> {

}
