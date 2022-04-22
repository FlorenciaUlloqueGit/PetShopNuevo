package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import com.thesis.FlorenciaUlloque.UTN.exceptions.ErrorMessages;
import com.thesis.FlorenciaUlloque.UTN.repositories.StockRepository;
import com.thesis.FlorenciaUlloque.UTN.services.StockService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository repository;

    public StockServiceImpl(StockRepository repository) {
        this.repository = repository;
    }


    @Override
    public Stock createStock(Stock stock) {

    //verifica que el idProducto no exista
        if (repository.findByProductoIdProducto(stock.getProducto().getIdProducto()) != null) {
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        }
        Stock  newStock = new Stock();

        BeanUtils.copyProperties(stock, newStock);
        repository.save(newStock);
        return newStock;
    }

    @Override
    public List<Stock> getAllStocks(int page, int limit) {
        List<Stock> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Stock> clientePage   = repository.findAll(pageableRequest);
        List<Stock> stocks = clientePage.getContent();


        for (Stock stock : stocks) {
            returnValue.add(stock);
        }

        return returnValue;
    }

    @Override
    public Stock updateStock(int id, Stock stock) {
        Stock stockForUpdate = repository.findByIdStock(id);
        if (stockForUpdate == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }


        stockForUpdate.setProducto(stock.getProducto());
        stockForUpdate.setCantidad(stock.getCantidad());

        Stock returnValue = repository.save(stockForUpdate);

        return returnValue;
    }

    @Override
    public void deleteStock(int id) {
        Stock stock = repository.findByIdStock(id);

        if (stock == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        repository.delete(stock);
    }
}
