package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService {

    Producto update(Producto producto);
    boolean delete(int id);
    List<ProductoDto> findAllProductos();

    List<Producto>listAllByName(String nombre);
    List<Producto>listAllByNameXKG(String nombre);
    List<Producto>listAllByCodBarras(long codBarras);

    boolean save(ProductoDtos productoDtos);
    List<Marca>listadoMarcas();
    List<FormaVenta> listaFormaVenta();
    List<Categoria> listaCategoria();
    List<Tamano> listaTamano();
    List<Edad> listaEdadAnimal();
    List<TipoAnimal> listTipoAnimal();
    List<UnidadMedida> listaUnidadMedida();
    Page<Producto> getAll(Pageable pageable);
    Page<Producto> getAllBolsas(Pageable pageable);
    Page<Producto> getAllKG(Pageable pageable);
    Page<String> getAllProdVendidosHoy(Pageable pageable);


}
