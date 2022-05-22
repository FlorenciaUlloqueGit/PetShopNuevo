package com.thesis.FlorenciaUlloque.UTN.services;

import com.thesis.FlorenciaUlloque.UTN.Dtos.ProveedorDto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;

import java.util.List;

public interface ProveedorService /*extends UserDetailsService */{

    Proveedor updateProveedor(Proveedor proveedor);
    boolean deleteProveedor(int id);
    List<Proveedor> findAllProveedor();
    boolean saveProveedor(ProveedorDto proveedor);
}
