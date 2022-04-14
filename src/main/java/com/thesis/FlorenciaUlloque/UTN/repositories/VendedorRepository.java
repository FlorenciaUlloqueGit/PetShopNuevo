package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.Vendedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedorRepository extends PagingAndSortingRepository<Vendedor,Integer > {


    Vendedor findByNombre(String apellido);
    Vendedor findByUsuario(String email);
    Vendedor findByIdVendedor(int idVendedor);


}
