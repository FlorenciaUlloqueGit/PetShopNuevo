package com.thesis.FlorenciaUlloque.UTN.services;

import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;

import java.util.List;

public interface ProveedorService /*extends UserDetailsService */{
    Proveedor createProveedor(Proveedor proveedor);
    List<Proveedor> getAllProveedores(int page, int limit);
    Proveedor updateProveedor(long id, Proveedor proveedor);
    void deleteProveedor(long id);
}
