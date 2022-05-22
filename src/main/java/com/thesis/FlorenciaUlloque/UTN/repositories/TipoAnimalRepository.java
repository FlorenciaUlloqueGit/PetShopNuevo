package com.thesis.FlorenciaUlloque.UTN.repositories;

import com.thesis.FlorenciaUlloque.UTN.entiities.Edad;
import com.thesis.FlorenciaUlloque.UTN.entiities.TipoAnimal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAnimalRepository extends PagingAndSortingRepository<TipoAnimal, Integer> {

}
