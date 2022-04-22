package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import com.thesis.FlorenciaUlloque.UTN.repositories.StockRepository;
import com.thesis.FlorenciaUlloque.UTN.services.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;
    private final StockRepository repository;

    public StockController(StockService stockService, StockRepository repository) {
        this.stockService = stockService;
        this.repository = repository;

    }

    //funcionaaa
    @GetMapping("")
    public List<Stock> getAllStocks(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return stockService.getAllStocks(page, limit); //agregar limite de pagina
    }

    //funcionaaaa
    @GetMapping("/producto/codigo/{codBarras}")
    public Stock getStockByCodBarrasProducto(@PathVariable long codBarras) {
        return repository.findByProductoCodBarras(codBarras);
    }

    //funcionaaaa
    @GetMapping("/producto/{idProducto}")
    public Stock getStockByIdProducto(@PathVariable int idProducto) {
        return repository.findByProductoIdProducto(idProducto);
    }

    //funcionaaa
    @GetMapping("/producto/marca/nombre/{nombre}")
    public List<Stock> getStockByNombreMarcaProducto(@PathVariable String nombre) {
        return repository.findByProductoMarcaNombre(nombre);
    }

    //funcionaaa
    @GetMapping("/producto/marca/{idMarca}")
    public Stock getStockByMarcaProducto(@PathVariable int idMarca) {
        return repository.findByProductoMarcaIdMarca(idMarca);
    }

    //funcionaaaa
    @PostMapping("")
    public Stock createStock(@RequestBody Stock stock){
        return stockService.createStock(stock);
    }

    //funcionaa
    @PutMapping("/{idStock}")
    public Stock updateStock(@PathVariable int idStock, @RequestBody Stock stock){

        return stockService.updateStock(idStock, stock);
    }

    //funcionaaaa
    @DeleteMapping("/{idStock}")
    public String deleteStock( @PathVariable int idStock){
        stockService.deleteStock(idStock);
        return "Stock eliminado exitosamente";

    }
}
