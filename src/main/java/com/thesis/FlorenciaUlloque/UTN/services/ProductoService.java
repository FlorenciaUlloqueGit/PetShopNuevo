package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;

import java.util.List;

public interface ProductoService {

    Producto update(Producto producto);
    boolean delete(int id);
    List<ProductoDto> findAllProductos();

    List<Producto>listAllByName(String nombre);
    List<Producto>listAllByCodBarras(long codBarras);

    boolean save(ProductoDtos productoDtos);
    List<Marca>listadoMarcas();
    List<FormaVenta> listaFormaVenta();
    List<Categoria> listaCategoria();
    List<Tamano> listaTamano();
    List<Edad> listaEdadAnimal();
    List<TipoAnimal> listTipoAnimal();
    List<UnidadMedida> listaUnidadMedida();

}
