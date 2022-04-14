package com.thesis.FlorenciaUlloque.UTN.repositories;


import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.Vendedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface VendedorRepository extends PagingAndSortingRepository<Vendedor,Integer > {


    Vendedor findByNombre(String apellido);
    Vendedor findByUsuario(String email);
    Vendedor findByIdVendedor(int idVendedor);


}
