package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoStock;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks.StockDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks.StockDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProductoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.StockRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ProductoService;
import com.thesis.FlorenciaUlloque.UTN.services.StockService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository repository;
    private final ProductoRepository productoRepository;
    private final ProductoService productoService;

    public StockServiceImpl(StockRepository repository, ProductoRepository productoRepository, ProductoService productoService) {
        this.repository = repository;
        this.productoRepository = productoRepository;
        this.productoService = productoService;
    }

    @Override
    public Stock update(Stock stock) {
        Stock returnValue;

        Stock stockForUpdate = new Stock();

        stockForUpdate.setIdStock(stock.getIdStock());
        stockForUpdate.setCantidad(stock.getCantidad());
        stockForUpdate.setProducto(stock.getProducto());
        stockForUpdate.setCantidadKg(stock.getCantidadKg());
        stockForUpdate.setCantidadRestante(stock.getCantidadRestante());

        returnValue = repository.save(stockForUpdate);
        return returnValue;
    }

    @Override
    public boolean delete(int id) {
        boolean existe = true;
        Stock stock = repository.findByIdStock(id);
        if (stock == null) {
            existe = false;
        }
        repository.delete(stock);
        return existe;
    }

    @Override
    public List<StockDto> findAllStocks() {
        List <Stock>listaReal  = (List<Stock>) repository.findAll();
        List<StockDto> listaDto = new ArrayList<>();

        StockDto stockDto ;
        for (Stock stock : listaReal) {
            stockDto = new StockDto();
            stockDto.setIdStock(stock.getIdStock());
            stockDto.setCantidad(stock.getCantidad());
            stockDto.setProducto(stock.getProducto());
            stockDto.setCantidadKg(stock.getCantidadKg());
            stockDto.setCantidadRestante(stock.getCantidadRestante());
            listaDto.add(stockDto);
        }

        return listaDto;
    }

    @Override
    public boolean save(StockDtos stockDtos) {
        boolean registrado;
        if (repository.findByProductoIdProducto(stockDtos.getProducto().getIdProducto()) != null) {
            registrado = true;

        } else{
            registrado = false;
            Stock newStock = new Stock(stockDtos.getCantidad(), stockDtos.getCantidadKg(),
                    stockDtos.getCantidadRestante(),stockDtos.getProducto());

            repository.save(newStock);
        }
        return registrado;
    }

    @Override
    public List<ProductoStock> getStockByNombreProducto(String nombre) {
        List <Producto>listaReal  =  productoService.listAllByName(nombre);
        List<ProductoStock> listaDto = new ArrayList<>();

        ProductoStock  productoStock ;
        for (Producto producto : listaReal) {
            productoStock = new ProductoStock();
            productoStock.setIdProducto(producto.getIdProducto());
            productoStock.setNombre(producto.getNombre());
            productoStock.setCodBarras(producto.getCodBarras());
            productoStock.setFormaVenta(producto.getFormaVenta());
            listaDto.add(productoStock);
        }

        return listaDto;
    }

    @Override
    public List<ProductoStock> getStockByCodBarrasProducto(long codBarras) {
        List <Producto>listaReal  =  productoService.listAllByCodBarras(codBarras);
        List<ProductoStock> listaDto = new ArrayList<>();

        ProductoStock  productoStock ;
        for (Producto producto : listaReal) {
            productoStock = new ProductoStock();
            productoStock.setIdProducto(producto.getIdProducto());
            productoStock.setNombre(producto.getNombre());
            productoStock.setCodBarras(producto.getCodBarras());
            productoStock.setFormaVenta(producto.getFormaVenta());
            listaDto.add(productoStock);
        }

        return listaDto;
    }
}
