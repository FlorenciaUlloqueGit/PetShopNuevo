package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;

import java.util.List;

public interface StockService {

    Stock createStock(Stock stock);
    List<Stock> getAllStocks(int page, int limit);
    Stock updateStock(int id, Stock stock);
    void deleteStock(int id);
}
