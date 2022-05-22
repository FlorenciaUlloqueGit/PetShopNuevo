package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDtoDetalle;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import com.thesis.FlorenciaUlloque.UTN.repositories.CategoriaRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProductoRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoRepository repository;
    private final CategoriaRepository categoriaRepository;


    public ProductoController(ProductoService productoService, ProductoRepository repository,
                              CategoriaRepository categoriaRepository) {
        this.productoService = productoService;
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
        ;
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


    //trae todos los datos de los productos según el nombre de la marca
    @GetMapping("/AllProductsBymarca/{nombre}")
    public List<Producto> getAllDatesProductoByMarca(@PathVariable String nombre) {
        return repository.findAllDatesByMarcaNombre(nombre);
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

    @ModelAttribute("producto")
    public ProductoDto mapear() {
        return new ProductoDto();
    }


    @ModelAttribute("productoDtoDetalle")
    public ProductoDtoDetalle mapeardtodetalle() {
        return new ProductoDtoDetalle();
    }



    @ModelAttribute("productoDtoExtras")
    public ProductoDtos mapearExtra() {
        return new ProductoDtos();
    }


    @ModelAttribute("productoDtos")
    public ProductoDtos mapeaProdDtos() {
        return new ProductoDtos();
    }

    @ModelAttribute("productoReal")
    public Producto mapearProductoREal() {
        return new Producto();
    }


    int idCategoria = 0;

    @GetMapping
    public String mostrarFormulario(Model model) {

        ProductoDtos productoDtos = new ProductoDtos();


        List<Marca> marcas = productoService.listadoMarcas();
        List<FormaVenta> formasVentas = productoService.listaFormaVenta();
        List<Categoria> categorias = productoService.listaCategoria();
        List<Edad> edades = productoService.listaEdadAnimal();
        List<TipoAnimal> tipoAnimales = productoService.listTipoAnimal();
        List<Tamano> tamanos = productoService.listaTamano();
        List<UnidadMedida> unidadMedidas = productoService.listaUnidadMedida();

        // mapeo del producto
        model.addAttribute("marcaDtos", productoDtos);


        //mapeo de los combos
        model.addAttribute("marcas", marcas);
        model.addAttribute("formasVentas", formasVentas);
        model.addAttribute("categorias", categorias);
        model.addAttribute("edades", edades);
        model.addAttribute("tipoAnimales", tipoAnimales);
        model.addAttribute("tamanos", tamanos);
        model.addAttribute("unidadesMedidas",  unidadMedidas);

        return "crearProducto";
    }


    @PostMapping
    public String create(@ModelAttribute("productoDtos") ProductoDtos producto) {

        LocalDate fechaVencimiento = producto.getFechaVencimiento();
        ProductoDtos productoDtos = new ProductoDtos();
        if(producto.getCodBarras() == 0){
            productoDtos.setNombre(producto.getNombre());
            productoDtos.setMarca(producto.getMarca());
            productoDtos.setFechaVencimiento(fechaVencimiento);
            productoDtos.setPrecioCompra(producto.getPrecioCompra());
            productoDtos.setPrecioVenta(producto.getPrecioVenta());
            productoDtos.setUnidadMedida(producto.getUnidadMedida());
            productoDtos.setPesoNeto(producto.getPesoNeto());
            productoDtos.setCategoria(producto.getCategoria());
            productoDtos.setFormaVenta(producto.getFormaVenta());
            productoDtos.setEdad(producto.getEdad());
            productoDtos.setTamano(producto.getTamano());
            productoDtos.setTipoAnimal(producto.getTipoAnimal());
        } else{
            productoDtos.setCodBarras(producto.getCodBarras());
            productoDtos.setNombre(producto.getNombre());
            productoDtos.setMarca(producto.getMarca());
            productoDtos.setFechaVencimiento(fechaVencimiento);
            productoDtos.setPrecioCompra(producto.getPrecioCompra());
            productoDtos.setPrecioVenta(producto.getPrecioVenta());
            productoDtos.setUnidadMedida(producto.getUnidadMedida());
            productoDtos.setPesoNeto(producto.getPesoNeto());
            productoDtos.setCategoria(producto.getCategoria());
            productoDtos.setFormaVenta(producto.getFormaVenta());
            productoDtos.setEdad(producto.getEdad());
            productoDtos.setTamano(producto.getTamano());
            productoDtos.setTipoAnimal(producto.getTipoAnimal());

        }

        //CASTEARRRR
        boolean registrado = productoService.save(productoDtos);
        if (registrado == true){
            productoService.save(productoDtos);
            return "redirect:/productos?error";
        }else {
            return "redirect:/productos?exito";
        }
    }


    @GetMapping("/update/{idProducto}")
    public String mostrarformUpdate(@PathVariable int idProducto, Model model){


        List<Marca> marcas = productoService.listadoMarcas();
        List<FormaVenta> formasVentas = productoService.listaFormaVenta();
        List<Categoria> categorias = productoService.listaCategoria();
        List<Edad> edades = productoService.listaEdadAnimal();
        List<TipoAnimal> tipoAnimales = productoService.listTipoAnimal();
        List<Tamano> tamanos = productoService.listaTamano();
        List<UnidadMedida> unidadMedidas = productoService.listaUnidadMedida();

        model.addAttribute("producto", repository.findByIdProducto(idProducto));

        model.addAttribute("marcas", marcas);
        model.addAttribute("formasVentas", formasVentas);
        model.addAttribute("categorias", categorias);
        model.addAttribute("categorias", categorias);
        model.addAttribute("edades", edades);
        model.addAttribute("tipoAnimales", tipoAnimales);
        model.addAttribute("tamanos", tamanos);
        model.addAttribute("unidadesMedidas", unidadMedidas);



        return "UpdateProducto";
    }


    @PostMapping("/updateProducto/{idProducto}")
    public String updatearVendedor(@ModelAttribute("productoReal")Producto producto,
                                   @PathVariable int idProducto){

        Producto productoExiste = repository.findByIdProducto(idProducto);

        productoExiste.setIdProducto(producto.getIdProducto());
        productoExiste.setCodBarras(producto.getCodBarras());
        productoExiste.setNombre(producto.getNombre());
        productoExiste.setFechaVencimiento(producto.getFechaVencimiento());
        productoExiste.setPrecioCompra(producto.getPrecioCompra());
        productoExiste.setPrecioVenta(producto.getPrecioVenta());
        productoExiste.setMarca(producto.getMarca());
        productoExiste.setFormaVenta(producto.getFormaVenta());
        productoExiste.setCategoria(producto.getCategoria());
        productoExiste.setEdad(producto.getEdad());
        productoExiste.setTamano(producto.getTamano());
        productoExiste.setTipoAnimal(producto.getTipoAnimal());
        productoExiste.setPesoNeto(producto.getPesoNeto());
        productoExiste.setUnidadMedida(producto.getUnidadMedida());

        productoService.update(productoExiste);

        return "redirect:/productos/update/{idProducto}?exito";

    }

    @GetMapping("/updateProductoDetalle/{idProducto}")
    public String mostrarformUpdateProductoDetalle(@PathVariable int idProducto, Model model){

        Producto producto = repository.findByIdProducto(idProducto);
        ProductoDtoDetalle productoDtoDetalle = new ProductoDtoDetalle();
        productoDtoDetalle.setIdProducto(idProducto);
        productoDtoDetalle.setCodBarras(producto.getCodBarras());
        productoDtoDetalle.setNombre(producto.getNombre());
        productoDtoDetalle.setPrecioVenta(producto.getPrecioVenta());
        productoDtoDetalle.setPrecioCompra(producto.getPrecioCompra());
        model.addAttribute("ProductoDtoDetalle",productoDtoDetalle);
        return "UpdateProductoForDetalleIngreso";
    }


    @GetMapping({"/listar", "/"})
    public String listar(Model model){
        model.addAttribute("productoReal", productoService.findAllProductos());
        return "listadoProductos";
    }


    @GetMapping("/delete/{idProducto}")
    public String deleteVendedor( @PathVariable int idProducto){
        productoService.delete(idProducto);
        return "redirect:/productos/listar?exito";

    }
}
