package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks.StockDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks.StockDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoStock;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;

import java.util.List;

public interface StockService {

    Stock update(Stock stock);
    boolean delete(int id);
    List<StockDto> findAllStocks();
    boolean save(StockDtos stockDtos);
    List<ProductoStock> getStockByNombreProducto(String nombre);
    List<ProductoStock> getStockByCodBarrasProducto(long codBarras);


}
