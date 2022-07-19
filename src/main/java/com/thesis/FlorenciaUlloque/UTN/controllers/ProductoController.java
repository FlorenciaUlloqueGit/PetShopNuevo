package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks.StockSoloCodBarras;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks.StockSoloNombre;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.DetalleIEgresoDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.*;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import com.thesis.FlorenciaUlloque.UTN.repositories.CategoriaRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProductoRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
        return repository.findAllProductosWithAllCost(page, limit); //agregar limite de pagina
    }

    @GetMapping("/listadoConCostoVenta") //funcionaaaaaaa!!!!
    public List<String> getAllProductoListWithSellCost(@RequestParam(value = "page", defaultValue = "0") int page,
                                                       @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return repository.findAllProductosWithSellCost(page, limit); //agregar limite de pagina
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

    @ModelAttribute("soloNombre")
    public ProductoSoloNombre mapearSoloNombre() {
        return new ProductoSoloNombre();
    }

    @ModelAttribute("soloCodBarras")
    public ProductoSoloCodBarras mapearSoloCodBarras() {
        return new ProductoSoloCodBarras();
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
        model.addAttribute("unidadesMedidas", unidadMedidas);

        return "crearProducto";
    }


    @PostMapping
    public String create(@ModelAttribute("productoDtos") ProductoDtos producto) {

        LocalDate fechaVencimiento = producto.getFechaVencimiento();
        ProductoDtos productoDtos = new ProductoDtos();
        LocalDate fechaDeHoy = null;
        if (producto.getCodBarras() == 0) {
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
            productoDtos.setEnabled(true);
        } else {
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
            productoDtos.setEnabled(true);

            Clock c1 = Clock.systemUTC();
            fechaDeHoy = LocalDate.now(c1);

        }

        if (productoDtos.getPesoNeto() <= 0) {
            return "redirect:/productos?errorPeso";
        }
        if (productoDtos.getFechaVencimiento().isBefore(LocalDate.now())) {
            return "redirect:/productos?errorProductoVencido";
        }
        if (productoDtos.getPrecioCompra() == 0 || productoDtos.getPrecioVenta() == 0) {
            return "redirect:/productos?errorProductoPrecio0";
        }
        if (productoDtos.getPrecioCompra() < 0 || productoDtos.getPrecioVenta() < 0) {
            return "redirect:/productos?errorProductoPrecioNegativo";
        }

        if (productoDtos.getFechaVencimiento() == null && productoDtos.getCategoria().getIdCategoria() != 2) {
            return "redirect:/productos?errorFechaVencimiento";
        }

        //CASTEARRRR
        boolean registrado = productoService.save(productoDtos);
        if (registrado == true) {
            productoService.save(productoDtos);
            return "redirect:/productos?error";
        } else {
            return "redirect:/productos?exito";
        }
    }


    @GetMapping("/update/{idProducto}")
    public String mostrarformUpdate(@PathVariable int idProducto, Model model) {


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
    public String updatearVendedor(@ModelAttribute("productoReal") Producto producto,
                                   @PathVariable int idProducto) {

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
        productoExiste.setEnabled(producto.isEnabled());

        if (productoExiste.getPesoNeto() <= 0) {
            return "redirect:/productos/update?errorPeso";
        }
        if (productoExiste.getPrecioCompra() == 0 || productoExiste.getPrecioVenta() == 0) {
            return "redirect:/productos/update?errorProductoPrecio0";
        }
        if (productoExiste.getPrecioCompra() < 0 || productoExiste.getPrecioVenta() < 0) {
            return "redirect:/productos/update?errorProductoPrecioNegativo";
        }

        if (productoExiste.getFechaVencimiento() == null && productoExiste.getCategoria().getIdCategoria() != 2) {
            return "redirect:/productos/update?errorFechaVencimiento";
        }

        productoService.update(productoExiste);
        return "redirect:/productos/update/{idProducto}?exito";

    }

    @GetMapping("/updateProductoDetalle/{idProducto}")
    public String mostrarformUpdateProductoDetalle(@PathVariable int idProducto, Model model) {

        Producto producto = repository.findByIdProducto(idProducto);
        ProductoDtoDetalle productoDtoDetalle = new ProductoDtoDetalle();
        productoDtoDetalle.setIdProducto(idProducto);
        productoDtoDetalle.setCodBarras(producto.getCodBarras());
        productoDtoDetalle.setNombre(producto.getNombre());
        productoDtoDetalle.setPrecioVenta(producto.getPrecioVenta());
        productoDtoDetalle.setPrecioCompra(producto.getPrecioCompra());
        productoDtoDetalle.setEnabled(producto.isEnabled());

        model.addAttribute("ProductoDtoDetalle", productoDtoDetalle);
        return "UpdateProductoForDetalleIngreso";
    }

    @GetMapping("/updateProductoDetalleEgreso/{idProducto}")
    public String mostrarformUpdateProductoDetalle2(@PathVariable int idProducto, Model model) {

        Producto producto = repository.findByIdProducto(idProducto);
        ProductoDtoDetalle productoDtoDetalle = new ProductoDtoDetalle();
        productoDtoDetalle.setIdProducto(idProducto);
        productoDtoDetalle.setCodBarras(producto.getCodBarras());
        productoDtoDetalle.setNombre(producto.getNombre());
        productoDtoDetalle.setPrecioVenta(producto.getPrecioVenta());
        productoDtoDetalle.setPrecioCompra(producto.getPrecioCompra());
        productoDtoDetalle.setEnabled(producto.isEnabled());

        model.addAttribute("ProductoDtoDetalle", productoDtoDetalle);
        return "UpdateProductoForDetalleEgreso";
    }

    @GetMapping({"/listar", "/"})
    public String findAll(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 7);
        Page<Producto> pageStock = productoService.getAll(pageRequest);

        int totalPage = pageStock.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("productoReal", pageStock.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "listadoProductos";
    }


    @GetMapping("/delete/{idProducto}")
    public String deleteVendedor(@PathVariable int idProducto) {
        Producto producto = repository.findByIdProducto(idProducto);
        producto.setEnabled(false);
        productoService.update(producto);
        return "redirect:/productos/listar?exito";

    }

    // ---------------------------Para vendedor--------------------------------------------------------
    @GetMapping("/mostrarForm")
    public String mostrarFormulario2(Model model) {

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
        model.addAttribute("unidadesMedidas", unidadMedidas);

        return "crearProductoVendedor";
    }


    @PostMapping("/nuevo")
    public String create2(@ModelAttribute("productoDtos") ProductoDtos producto) {

        LocalDate fechaDeHoy = null;
        LocalDate fechaVencimiento = producto.getFechaVencimiento();
        ProductoDtos productoDtos = new ProductoDtos();
        if (producto.getCodBarras() == 0) {
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
            productoDtos.setEnabled(producto.isEnabled());
        } else {
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
            productoDtos.setEnabled(producto.isEnabled());
            Clock c1 = Clock.systemUTC();
            fechaDeHoy = LocalDate.now(c1);

        }
        if (productoDtos.getPesoNeto() <= 0) {
            return "redirect:/productos/mostrarForm?errorPeso";
        }
        if (productoDtos.getFechaVencimiento() != null && productoDtos.getFechaVencimiento().isBefore(LocalDate.now())) {
            return "redirect:/productos/mostrarForm?errorProductoVencido";
        }
        if (productoDtos.getPrecioCompra() == 0 || productoDtos.getPrecioVenta() == 0) {
            return "redirect:/productos/mostrarForm?errorProductoPrecio0";
        }
        if (productoDtos.getPrecioCompra() < 0 || productoDtos.getPrecioVenta() < 0) {
            return "redirect:/productos/mostrarForm?errorProductoPrecioNegativo";
        }

        if (productoDtos.getFechaVencimiento() == null && productoDtos.getCategoria().getIdCategoria() != 2) {
            return "redirect:/productos/mostrarForm?errorFechaVencimiento";
        }
        //CASTEARRRR
        boolean registrado = productoService.save(productoDtos);
        if (registrado == true) {
            productoService.save(productoDtos);
            return "redirect:/productos/mostrarForm?error";
        } else {
            return "redirect:/productos/mostrarForm?exito";
        }
    }


    @GetMapping("/updateProducto/{idProducto}")
    public String mostrarformUpdate2(@PathVariable int idProducto, Model model) {


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


        return "UpdateProductoVendedor";
    }


    @PostMapping("/updateProductoEspecifico/{idProducto}")
    public String updatearVendedor2(@ModelAttribute("productoReal") Producto producto,
                                    @PathVariable int idProducto) {

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
        productoExiste.setEnabled(producto.isEnabled());

        if (productoExiste.getPesoNeto() <= 0) {
            return "redirect:/productos/updateProducto/{idProducto}?errorPeso";
        }
        if (productoExiste.getPrecioCompra() == 0 || productoExiste.getPrecioVenta() == 0) {
            return "redirect:/productos/updateProducto/{idProducto}?errorProductoPrecio0";
        }
        if (productoExiste.getPrecioCompra() < 0 || productoExiste.getPrecioVenta() < 0) {
            return "redirect:/productos/updateProducto/{idProducto}?errorProductoPrecioNegativo";
        }

        if (productoExiste.getFechaVencimiento() == null && productoExiste.getCategoria().getIdCategoria() != 2) {
            return "redirect:/productos/updateProducto/{idProducto}?errorFechaVencimiento";
        }
        productoService.update(productoExiste);


        return "redirect:/productos/updateProducto/{idProducto}?exito";

    }

    @GetMapping("/updateProductoDetalleVendedor/{idProducto}")
    public String mostrarformUpdateProductoDetalle23(@PathVariable int idProducto, Model model) {

        Producto producto = repository.findByIdProducto(idProducto);
        ProductoDtoDetalle productoDtoDetalle = new ProductoDtoDetalle();
        productoDtoDetalle.setIdProducto(idProducto);
        productoDtoDetalle.setCodBarras(producto.getCodBarras());
        productoDtoDetalle.setNombre(producto.getNombre());
        productoDtoDetalle.setPrecioVenta(producto.getPrecioVenta());
        productoDtoDetalle.setPrecioCompra(producto.getPrecioCompra());
        productoDtoDetalle.setEnabled(producto.isEnabled());
        model.addAttribute("ProductoDtoDetalle", productoDtoDetalle);
        return "UpdateProductoForDetalleIngresoVendedor";
    }


    @GetMapping("/updateProductoDetalleEgresoVendedor/{idProducto}")
    public String mostrarformUpdateProductoDetalle4(@PathVariable int idProducto, Model model) {

        Producto producto = repository.findByIdProducto(idProducto);
        ProductoDtoDetalle productoDtoDetalle = new ProductoDtoDetalle();
        productoDtoDetalle.setIdProducto(idProducto);
        productoDtoDetalle.setCodBarras(producto.getCodBarras());
        productoDtoDetalle.setNombre(producto.getNombre());
        productoDtoDetalle.setPrecioVenta(producto.getPrecioVenta());
        productoDtoDetalle.setPrecioCompra(producto.getPrecioCompra());
        productoDtoDetalle.setEnabled(producto.isEnabled());
        model.addAttribute("ProductoDtoDetalle", productoDtoDetalle);
        return "UpdateProductoForDetalleEgresoVendedor";
    }


    String nombre;
    @GetMapping("/buscarNombre")
    public String buscarNombre(@ModelAttribute("soloNombre") ProductoSoloNombre solonombre) {

        nombre = solonombre.getNombre();
        return "redirect:/productos/listarProductoFiltrado";
    }

    long codBarras;
    @GetMapping("/buscarCodBarras")
    public String buscarCodBarras(@ModelAttribute("soloCodBarras") ProductoSoloCodBarras soloCodBarras) {

        codBarras = soloCodBarras.getCodBarras();
        return "redirect:/productos/listarProductoFiltrado";
    }

    @GetMapping("/listarProductoFiltrado")
    public String findAllProductoFiltrado(Model model){
        List<Producto> productos = repository.findAllProductosByNameIndistinto(nombre);

        if(codBarrasProducto != 0){
            Producto prodocto = repository.findByCodBarras(codBarras);
            model.addAttribute("productoReal", prodocto);
            return "ListadoProductoEspecificoAdmin";
        }
        model.addAttribute("productoReal" , productos);

        return "ListadoProductoEspecificoAdmin";
    }

    String nombreProducto;
    @GetMapping("/buscarNombreProducto")
    public String buscarNombre2(@ModelAttribute("soloNombre") ProductoSoloNombre solonombre) {

        nombreProducto = solonombre.getNombre();
        return "redirect:/productos/listarProductoFiltradoEspecifico";
    }

    long codBarrasProducto;
    @GetMapping("/buscarCodBarrasProducto")
    public String buscarCodBarras2(@ModelAttribute("soloCodBarras") ProductoSoloCodBarras soloCodBarras) {

        codBarrasProducto = soloCodBarras.getCodBarras();
        return "redirect:/productos/listarProductoFiltradoEspecifico";
    }

    @GetMapping("/listarProductoFiltradoEspecifico")
    public String findAllProductoFiltrado2(Model model){
        List<Producto> productos = repository.findAllProductosByNameIndistinto(nombre);
        if(codBarrasProducto != 0){
            Producto prodocto = repository.findByCodBarras(codBarrasProducto);
            model.addAttribute("productoReal", prodocto);
            return "ListadoProductoEspecificoVendedor";
        }

        model.addAttribute("productoReal" , productos);

        return "ListadoProductoEspecificoVendedor";
    }



    @GetMapping("/listarProductos")
    public String findAll2(@RequestParam Map<String, Object> params, Model model){
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) :0;
        PageRequest pageRequest = PageRequest.of(page, 7);
        Page<Producto> pageStock = productoService.getAll(pageRequest);

        int totalPage = pageStock.getTotalPages();
        if(totalPage> 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages",pages);
        }
        model.addAttribute("productoReal", pageStock.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "listadoProductosVendedor";
    }

}
