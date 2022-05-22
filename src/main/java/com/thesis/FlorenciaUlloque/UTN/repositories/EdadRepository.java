package com.thesis.FlorenciaUlloque.UTN.repositories;

import com.thesis.FlorenciaUlloque.UTN.entiities.Edad;
import com.thesis.FlorenciaUlloque.UTN.entiities.Tamano;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdadRepository extends PagingAndSortingRepository<Edad, Integer> {

}
