package com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.Vendedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedorRepository extends PagingAndSortingRepository<Vendedor,Integer > {

    Vendedor findByUsuario(String usuario);
    Vendedor findByIdVendedor(int idVendedor);


}
