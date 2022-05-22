package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.VendedorDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.VendedorDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Vendedor;

import java.util.List;

public interface VendedorService {

    Vendedor updateVendedor(Vendedor vendedor);
    boolean deleteVendedor(int id);
    List<VendedorDtos> findAllVendedores();
    boolean saveVendedor(VendedorDto vendedorDto);
}
