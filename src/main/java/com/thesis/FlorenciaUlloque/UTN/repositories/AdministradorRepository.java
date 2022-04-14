package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.Administrador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdministradorRepository extends PagingAndSortingRepository<Administrador,Integer > {


    Administrador findByNombre(String email);
    @Query(value = "SELECT  a.nombre FROM administradores  a ;", nativeQuery = true)
    List<String> findAllAdmins(int page, int limit); //REVISAR

}
