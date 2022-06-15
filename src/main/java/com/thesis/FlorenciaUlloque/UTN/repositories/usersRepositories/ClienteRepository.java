package com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente,Integer > {


    Cliente findByApellido(String apellido);
    Cliente findByEmail(String email);
    Cliente findByIdCliente(int id);
    @Query(value = "SELECT concat_ws(' ,', c.apellido, c.nombre )FROM clientes c  order by c.nombre asc;", nativeQuery = true)
    List<String> findAllClientes(int page, int limit); //REVISAR
    List<Cliente> findAllByOrderByNombreAsc();
    @Query(value = "select * from clientes c where c.id_cliente != 2 order by c.nombre asc", nativeQuery = true)
    Page<Cliente> findAllByOrderByNombreAsc(Pageable pageable);
}

