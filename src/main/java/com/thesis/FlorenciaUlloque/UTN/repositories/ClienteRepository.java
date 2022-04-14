package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente,Integer > {


    Cliente findByApellido(String apellido);
    Cliente findByEmail(String email);
    Cliente findByIdCliente(int id);
    @Query(value = "SELECT concat_ws(' ,', c.apellido, c.nombre )FROM clientes c ;", nativeQuery = true)
    List<String> findAllClientes(int page, int limit); //REVISAR
}
