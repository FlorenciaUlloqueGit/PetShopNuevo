package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;

import java.util.List;

public interface ProductoService {

    Producto createProducto(Producto producto);
    List<Producto> getAllProductos(int page, int limit);
    Producto updateProductos(int id, Producto producto);
    void deleteProductos(int id);
}
