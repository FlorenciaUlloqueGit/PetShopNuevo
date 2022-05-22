package com.thesis.FlorenciaUlloque.UTN.controllers;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoStock;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks.*;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProductoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.StockRepository;
import com.thesis.FlorenciaUlloque.UTN.services.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;
    private final StockRepository repository;
    private final ProductoRepository productoRepository;

    public StockController(StockService stockService, StockRepository repository, ProductoRepository productoRepository) {
        this.stockService = stockService;
        this.repository = repository;

        this.productoRepository = productoRepository;
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



    @ModelAttribute("stock")
    public StockDto mapearstockDto() {
        return new StockDto();
    }


    @ModelAttribute("productoStock")
    public ProductoStock mapearproducto() {
        return new ProductoStock();
    }


    @ModelAttribute("stockDtos")
    public StockDtos mapeaDtos() {
        return new StockDtos();
    }

    @ModelAttribute("stockDtoSoloCant")
    public StockDtoSoloCant mapeaDtosStoccantidad() {
        return new StockDtoSoloCant();
    }
    @ModelAttribute("stockSoloNombre")
    public StockSoloNombre mapeaDtosStockSoloNombre() {
        return new StockSoloNombre();
    }

    @ModelAttribute("stockSoloCodBarras")
    public StockSoloCodBarras mapeaDtosStockSoloCod() {
        return new StockSoloCodBarras();
    }

    @ModelAttribute("stockReal")
    public Stock mapearVendedorReal() {
        return new Stock();
    }

    @ModelAttribute("stockDtosProdID")
    public StockDtosProdID mapearstockIdProd() {
        return new StockDtosProdID();
    }

    String nombre;
    @GetMapping("/buscarNombre")
    public String buscarNombre(@ModelAttribute("stockSoloNombre") StockSoloNombre stockSoloNombre) {

        nombre = stockSoloNombre.getNombre();
        return "redirect:/stocks";
    }

    long codBarras;
    @GetMapping("/buscarCodBarras")
    public String buscarCodBarras(@ModelAttribute("stockSoloCodBarras") StockSoloCodBarras stockSoloCodBarras) {

        codBarras = stockSoloCodBarras.getCodBarras();
        return "redirect:/stocks";
    }


    @GetMapping
    public String mostrarFormulario(Model model) {

        List<ProductoStock> listaProductos = stockService.getStockByNombreProducto(nombre);

        if(codBarras != 0){
            List<ProductoStock> listaProductos2 = stockService.getStockByCodBarrasProducto(codBarras);
            model.addAttribute("productoStock", listaProductos2);
            return "crearStock";
        }

        StockDtos stockDtos  = new StockDtos();
        model.addAttribute("stockDtos", stockDtos);
        model.addAttribute("productoStock", listaProductos);

        return "crearStock";
    }



    @PostMapping
    public String create(@ModelAttribute("stockDtosProdID") StockDtosProdID stockDtosProdID) {
        int idProd = stockDtosProdID.getIdProducto();
        System.out.println(idProd);
        Producto producto = productoRepository.findByIdProducto(stockDtosProdID.getIdProducto());
        StockDtos stockDtos = new StockDtos();
        stockDtos.setProducto(producto);
        stockDtos.setCantidad(stockDtosProdID.getCantidad());
        boolean registrado = stockService.save(stockDtos);
        if (registrado == true){
            stockService.save(stockDtos);
            return "redirect:/stocks?error";
        }else {
            return "redirect:/stocks?exito";
        }
    }


    @GetMapping("/update/{idStock}")
    public String mostrarformUpdate(@PathVariable int idStock, Model model){

     //   List<Proveedor> proveedores = marcaService.listaProveedores();

        model.addAttribute("stock", repository.findByIdStock(idStock));
 //       model.addAttribute("proveedores", proveedores);
        return "UpdateStock";
    }


    @PostMapping("/updateStock/{idStock}")
    public String updatearStock(@ModelAttribute("stockReal") Stock stock,
                                   @PathVariable int idStock){

        Stock stockExiste = repository.findByIdStock(idStock);

        stockExiste.setIdStock(stock.getIdStock());
        stockExiste.setProducto(stockExiste.getProducto());
        stockExiste.setCantidad(stock.getCantidad());
        stockService.update(stockExiste);

        return "redirect:/stocks/update/{idStock}?exito";

    }



    @GetMapping({"/listadoNombre/{nombre}"})
    public String listarStockPorNombre(Model model,  @PathVariable String nombre) {
        model.addAttribute("productoStock", stockService.getStockByNombreProducto(nombre));
        return "crearStock";
    }


    @GetMapping({"/listar", "/"})
    public String listar(Model model){
        model.addAttribute("stockReal", stockService.findAllStocks());
        return "listadoStock";
    }


    @GetMapping("/delete/{idStock}")
    public String delete( @PathVariable int idStock){
        stockService.delete(idStock);
        return "redirect:/stocks/listar?exito";

    }

}
