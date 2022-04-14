package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.Vendedor;

import java.util.List;

public interface VendedorService {

    Vendedor createVendedor(Vendedor vendedor );
    List<Vendedor> getAllVendedores(int page, int limit);
    Vendedor updateVendedor(int id, Vendedor vendedor);
    void deleteVendedor(int id);
}
