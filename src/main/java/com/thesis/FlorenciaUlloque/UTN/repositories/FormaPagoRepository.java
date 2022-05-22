package com.thesis.FlorenciaUlloque.UTN.repositories;

import com.thesis.FlorenciaUlloque.UTN.entiities.FormaPago;
import com.thesis.FlorenciaUlloque.UTN.entiities.FormaVenta;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagoRepository extends PagingAndSortingRepository<FormaPago, Integer> {

}
