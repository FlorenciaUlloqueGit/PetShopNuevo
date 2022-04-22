package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProductoRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoRepository repository;


    public ProductoController(ProductoService productoService, ProductoRepository repository) {
        this.productoService = productoService;
        this.repository = repository;;
    }


    @GetMapping("/listadoConCostos") //FUNCIONA!!!!!!!!!!!!!!!
    public List<String> getAllProductosWithAllCost(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return repository.findAllProductosWithAllCost(page,limit); //agregar limite de pagina
    }
    @GetMapping("/listadoConCostoVenta") //funcionaaaaaaa!!!!
    public List<String> getAllProductoListWithSellCost(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return repository.findAllProductosWithSellCost(page,limit); //agregar limite de pagina
    }


    @GetMapping("") //funcionaaaaaaaaaaaaaaaaa
    public List<Producto> getAllProductos(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return productoService.getAllProductos(page, limit);
    }

    @GetMapping("/formaVenta/{idFormaVenta}") //no funciona
    public List<Producto> getProductosByFormaVenta(@PathVariable int idFormaVenta) {
        return repository.findByFormaVenta(idFormaVenta);
    }

    //trae todos los datos de los productos según el nombre de la marca
    @GetMapping("/AllProductsBymarca/{nombre}")
    public List<Producto> getAllDatesProductoByMarca(@PathVariable String nombre) {
        return repository.findAllDatesByMarcaNombre(nombre);
    }

    @GetMapping("/subcategoria/{idSubcategoria}") //no anda
    public Producto getProductoBySubcategoria(@PathVariable int idSubcategoria) {
        return repository.findBySubCategoria(idSubcategoria);
    }

    @GetMapping("/animal/{idAnimal}") //no anda
    public Producto getProductoByAnimal(@PathVariable int idAnimal) {
        return repository.findByAnimal(idAnimal);
    }

    @GetMapping("/fechaVencimiento/{fecha}") //para manejar la fecha,.... revisar y hacer
    public Producto getProductosByFechaVencimiento(@PathVariable Date fecha) {
        return repository.findByFechaVencimiento(fecha);
    }
    @GetMapping("/codBarras/{codBarras}") //funcionan todos los atributos!!
    public Producto getProductoByCodBarras(@PathVariable long codBarras) {
        return repository.findByCodBarras(codBarras);
    }


    @GetMapping("/nombre/{nombre}") //funciona
    public Producto getProductoByNombre(@PathVariable String nombre) {
        return repository.findByNombre(nombre);
    }


    @PostMapping("") //funciona!
    public Producto createProducto(@RequestBody Producto producto){
        return productoService.createProducto(producto);
    }

    @PutMapping("/{idProducto}") //FUNCIONAAA
    public Producto updateProducto(@PathVariable int idProducto, @RequestBody Producto producto){
        return productoService.updateProductos(idProducto, producto);
    }

    @DeleteMapping("/{idProducto}") //FUNCIONAAA
    public String deleteProducto( @PathVariable int idProducto){
        productoService.deleteProductos(idProducto);
        return "El producto ha sido eliminado exitosamente";

    }
}
