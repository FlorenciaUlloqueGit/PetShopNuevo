package com.thesis.FlorenciaUlloque.UTN.services;

import com.thesis.FlorenciaUlloque.UTN.Dtos.ProveedorDto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProveedorService /*extends UserDetailsService */{

    Proveedor updateProveedor(Proveedor proveedor);
    boolean deleteProveedor(int id);
    List<Proveedor> findAllProveedor();
    boolean saveProveedor(ProveedorDto proveedor);
    Page<Proveedor> getAll(Pageable pageable);
}
