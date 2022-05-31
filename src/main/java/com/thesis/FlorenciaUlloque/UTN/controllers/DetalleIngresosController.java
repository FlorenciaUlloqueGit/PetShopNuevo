package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks.StockDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks.StockDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.*;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDetalle;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDtoDetalle;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoSoloCodBarras;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoSoloNombre;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleIngresoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProductoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.StockRepository;
import com.thesis.FlorenciaUlloque.UTN.services.DetalleIngresoService;
import com.thesis.FlorenciaUlloque.UTN.services.IngresoProductosService;
import com.thesis.FlorenciaUlloque.UTN.services.ProductoService;
import com.thesis.FlorenciaUlloque.UTN.services.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/detalleIngresos")
public class DetalleIngresosController {

    private final DetalleIngresoService detalleIngresoService;
    private final DetalleIngresoRepository repository;
    private final ProductoRepository productoRepository;
    private final IngresoProductosRepository ingresoProductosRepository;
    private final ProductoService productoService;
    private final StockRepository stockRepository;
    private final StockService stockService;
    private final IngresoProductosService ingresoProductosService;



    public DetalleIngresosController(DetalleIngresoService detalleIngresoService, DetalleIngresoRepository repository, ProductoRepository productoRepository, IngresoProductosRepository ingresoProductosRepository, ProductoService productoService, StockRepository stockRepository, StockService stockService, IngresoProductosService ingresoProductosService) {
        this.detalleIngresoService = detalleIngresoService;
        this.repository = repository;

        this.productoRepository = productoRepository;
        this.ingresoProductosRepository = ingresoProductosRepository;
        this.productoService = productoService;
        this.stockRepository = stockRepository;
        this.stockService = stockService;
        this.ingresoProductosService = ingresoProductosService;
    }

    //funciona
    @GetMapping("/ingreso/id/{idIngreso}")
    public DetalleIngreso getDetalleByIdIngreso(@PathVariable int idIngreso) {
        return repository.findByIngresoProductosIdIngreso(idIngreso);
    }

    //funciona
    @GetMapping("/id/{idDetalle}")
    public DetalleIngreso getDetalleById(@PathVariable int idDetalle) {
        return repository.findByIdDetalle(idDetalle);
    }

    //funciona
    @GetMapping("/producto/codBarras/{codBarras}")
    public DetalleIngreso getDetalleByCodBarrasProducto(@PathVariable Long codBarras) {
        return repository.findByProductoCodBarras(codBarras);
    }

    //funciona
    @GetMapping("/producto/id/{idProducto}")
    public DetalleIngreso getClienteByProveedorName(@PathVariable int idProducto) {
        return repository.findByProductoIdProducto(idProducto);
    }


    @ModelAttribute("productoDetalle")
    public ProductoDetalle mapearproducto() {
        return new ProductoDetalle();
    }

    @ModelAttribute("productoSoloNombre")
    public ProductoSoloNombre mapeaDtoSoloNombre() {
        return new ProductoSoloNombre();
    }

    @ModelAttribute("DetalleIngresoDtoSinIdIngreso")
    public DetalleIngresoDtoSinIdIngreso DetalleIngresoDtoSinIdIngreso() {
        return new DetalleIngresoDtoSinIdIngreso();
    }

    @ModelAttribute("productoSoloCodBarras")
    public ProductoSoloCodBarras mapeaProdStockSoloCod() {
        return new ProductoSoloCodBarras();
    }

    @ModelAttribute("ingresoIdTotal")
    public IngresoIdTotal mapearIdTOtal() {
        return new IngresoIdTotal();
    }

    int idIngresos ;
    String nombre;
    @GetMapping("/buscarNombre")
    public String buscarNombre(@ModelAttribute("productoSoloNombre") ProductoSoloNombre productoSoloNombre) {

        nombre = productoSoloNombre.getNombre();
        List<ProductoDetalle> falseList = new ArrayList<>();
        List<ProductoDetalle> listaProductos = detalleIngresoService.getDetalleByNombreProducto(nombre);

        if(listaProductos.size() < 1){
            return "redirect:/detalleIngresos/"+idIngresos+"?errorNombre";
        }

        return "redirect:/detalleIngresos/"+idIngresos;
    }
    @GetMapping("/buscarNombreDetalleEspecifico")
    public String buscarNombre2(@ModelAttribute("productoSoloNombre") ProductoSoloNombre productoSoloNombre) {

        nombre = productoSoloNombre.getNombre();
        List<ProductoDetalle> falseList = new ArrayList<>();
        List<ProductoDetalle> listaProductos = detalleIngresoService.getDetalleByNombreProducto(nombre);

        if(listaProductos.size() < 1){
            return "redirect:/detalleIngresos/crear/"+idIngresos+"?errorNombre";
        }

        return "redirect:/detalleIngresos/crear/"+idIngresos;
    }


    long codBarras;
    @GetMapping("/buscarCodBarrasDetalleEspecifico")
    public String buscarCodBarras(@ModelAttribute("productoSoloCodBarras") ProductoSoloCodBarras productoSoloCodBarras) {

        codBarras = productoSoloCodBarras.getCodBarras();

        if(codBarras == 0){
            return "redirect:/detalleIngresos/"+idIngresos+"?errorCodigo";
        }
        return "redirect:/detalleIngresos/"+idIngresos;
    }

    @ModelAttribute("detalleDtos")
    public DetalleIngresoDtos mapeaMarcaDtos() {
        return new DetalleIngresoDtos();
    }

    @ModelAttribute("detalleIngresoDtoCrear")
    public DetalleIngresoDtosCrear mapeacrearDtos() {
        return new DetalleIngresoDtosCrear();
    }

    @ModelAttribute("detalleReal")
    public DetalleIngreso mapearVendedorREal() {
        return new DetalleIngreso();
    }

    @GetMapping("{idIngreso}")
    public String mostrarFormularioConID(Model model, @PathVariable int idIngreso) {

        idIngresos = idIngreso;
        IngresoProductos ingresoProductos = ingresoProductosRepository.findByIdIngreso(idIngreso);

        List<ProductoDetalle> listaProductos = detalleIngresoService.getDetalleByNombreProducto(nombre);

        if(codBarras != 0){
            List<ProductoDetalle> listaProductos2 = detalleIngresoService.getDetalleByCodBarrasProducto(codBarras);
            model.addAttribute("productoDetalle", listaProductos2);
            model.addAttribute("ingresoIdTotal",ingresoProductos);
            return "crearDetalleIngreso";
        }


        DetalleIngresoDtos detalle = new DetalleIngresoDtos();
        model.addAttribute("detalleDtos", detalle);
        model.addAttribute("productoDetalle", listaProductos);
        return "crearDetalleIngreso";
    }


    @ModelAttribute("detalleIngresoDtoIdIngreso")
    public DetalleIngresoDtoIdIngreso mapeardtodetalleConIdIngreso() {
        return new DetalleIngresoDtoIdIngreso();
    }

    @ModelAttribute("detalleIngresoDtoUpdate")
    public DetalleIngresoDtoUpdate mapeardtodetalleCondtoUpdate() {
        return new DetalleIngresoDtoUpdate();
    }



    @ModelAttribute("productoDtoDetalle")
    public ProductoDtoDetalle mapeardtodetalle() {
        return new ProductoDtoDetalle();
    }


    @ModelAttribute("detalleSoloIdIngreso")
    public detalleSoloIdIngreso detalleSoloIdIngreso() {
        return new detalleSoloIdIngreso();
    }



    @PostMapping("/updateProductoToDetalle/{idProducto}")
    public String updatearprodDetalle(@ModelAttribute("ProductoDtoDetalle") ProductoDtoDetalle producto,
                                      @PathVariable int idProducto){

        Producto productoExiste = productoRepository.findByIdProducto(idProducto);

        productoExiste.setIdProducto(producto.getIdProducto());
        productoExiste.setCodBarras(productoExiste.getCodBarras());
        productoExiste.setNombre(productoExiste.getNombre());
        productoExiste.setFechaVencimiento(productoExiste.getFechaVencimiento());
        productoExiste.setPrecioCompra(producto.getPrecioCompra());
        productoExiste.setPrecioVenta(producto.getPrecioVenta());
        productoExiste.setMarca(productoExiste.getMarca());
        productoExiste.setFormaVenta(productoExiste.getFormaVenta());
        productoExiste.setCategoria(productoExiste.getCategoria());
        productoExiste.setEdad(productoExiste.getEdad());
        productoExiste.setTamano(productoExiste.getTamano());
        productoExiste.setTipoAnimal(productoExiste.getTipoAnimal());
        productoExiste.setPesoNeto(productoExiste.getPesoNeto());
        productoExiste.setUnidadMedida(productoExiste.getUnidadMedida());

        productoService.update(productoExiste);

        return "redirect:/detalleIngresos/"+idIngresos;

    }



    @GetMapping
    public String mostrarFormularioSINID(Model model) {

        IngresoProductos ingresoProductos = ingresoProductosRepository.findByIdIngreso(idIngresos);

        List<ProductoDetalle> listaProductos = detalleIngresoService.getDetalleByNombreProducto(nombre);
        if(listaProductos.size() == 0){
            return "redirect:/detalleIngresos/agregarDetalle/{idIngreso}?errorNombre";
        }

        if(codBarras != 0){
            List<ProductoDetalle> listaProductos2 = detalleIngresoService.getDetalleByCodBarrasProducto(codBarras);
            model.addAttribute("productoDetalle", listaProductos2);
            model.addAttribute("ingresoIdTotal",ingresoProductos );
            return "crearDetalleIngreso";
        }


        DetalleIngresoDtos detalle = new DetalleIngresoDtos();
        model.addAttribute("detalleDtos", detalle);
        model.addAttribute("productoDetalle", listaProductos);
        return "crearDetalleIngreso";
    }

    @PostMapping("crearDetalle/{idIngreso}")
    public String create(@ModelAttribute("detalleIngresoDtoCrear") DetalleIngresoDtosCrear detalleIngresoDtosCrear,
                         @PathVariable int idIngreso) {

        int idProd = detalleIngresoDtosCrear.getIdProducto();
        Producto producto = productoRepository.findByIdProducto(idProd);

        DetalleIngreso detalleIngreso = new DetalleIngreso();
        detalleIngreso.setIngresoProductos(ingresoProductosRepository.findByIdIngreso(idIngreso));
        detalleIngreso.setProducto(producto);
        detalleIngreso.setCantidad(detalleIngresoDtosCrear.getCantidad());
        IngresoProductos ingresoProductos = ingresoProductosRepository.findByIdIngreso(detalleIngresoDtosCrear.getIdIngreso());
        detalleIngreso.setIngresoProductos(ingresoProductos);
        double subtotal = detalleIngresoDtosCrear.getPrecioCompra() * detalleIngresoDtosCrear.getCantidad();
        detalleIngreso.setPrecio(subtotal);

        Stock stock = stockRepository.findByProductoIdProducto(detalleIngreso.getProducto().getIdProducto());
        if(stock == null){
            StockDtos newStock = new StockDtos(producto, detalleIngreso.getCantidad());
            if(Objects.equals(newStock.getProducto().getFormaVenta().getNombre(), "Por peso")){
                newStock.setCantidadKg((int) (newStock.getProducto().getPesoNeto() * newStock.getCantidad()));
                newStock.setCantidadRestante(newStock.getCantidadKg());
            }
            stockService.save(newStock);
        } else{
            int cantidadFinal = stock.getCantidad() + detalleIngreso.getCantidad();
            stock.setCantidad(cantidadFinal);
            if(Objects.equals(stock.getProducto().getFormaVenta().getNombre(), "Por peso")){
                stock.setCantidadKg((int) (stock.getProducto().getPesoNeto() * stock.getCantidad()));
                stock.setCantidadRestante(stock.getCantidadKg());
            }
            stockService.update(stock);
        }


        detalleIngresoService.save(detalleIngreso);

        // TODO cambiar el redirect al listado.
        return "redirect:/ingresos/agregarDetalle";
    }

    @GetMapping("/update/{idDetalle}")
    public String mostrarformUpdate(@PathVariable int idDetalle, Model model){

        List<Producto> productosList= (List<Producto>) productoRepository.findAll();
        DetalleIngreso detalle= repository.findByIdDetalle(idDetalle);
        DetalleIngresoDtoUpdate dto = new DetalleIngresoDtoUpdate();
        dto.setIdDetalle(detalle.getIdDetalle());
        dto.setIdIngreso(detalle.getIngresoProductos().getIdIngreso());
        dto.setCantidad(detalle.getCantidad());
        dto.setProducto(detalle.getProducto());

        model.addAttribute("detalleIngresoDtoUpdate", dto);
        model.addAttribute("productos", productosList);
        return "UpdateDetalleIngreso";
    }


    @PostMapping("/updateDetalle/{idDetalle}")
    public String updatearVendedor(@ModelAttribute("detalleIngresoDtoUpdate")DetalleIngresoDtoUpdate detalleIngreso,
                                   @PathVariable int idDetalle) {

        DetalleIngreso ingresoExiste = repository.findByIdDetalle(idDetalle);

        IngresoProductos ingresoProductos = ingresoProductosRepository.findByIdIngreso(ingresoExiste.getIngresoProductos().
                getIdIngreso());
        ingresoExiste.setIdDetalle(idDetalle);
        ingresoExiste.setIngresoProductos(ingresoProductos);
        ingresoExiste.setProducto(detalleIngreso.getProducto());

        int cantVieja = ingresoExiste.getCantidad();
        int cantNueva = detalleIngreso.getCantidad();
        int totalASumar = 0;
        int totalARestar = 0;
        boolean esSuma = false;
        Stock stock = stockRepository.findByProductoIdProducto(ingresoExiste.getProducto().getIdProducto());
        if (cantVieja > cantNueva) {
            totalARestar = cantVieja - cantNueva;
            esSuma = false;
        } else if (cantVieja < cantNueva) {
            totalASumar = cantNueva - cantVieja;
            esSuma = true;
        } else if (cantVieja == cantNueva) {
            stock.setCantidad(stock.getCantidad());
        }
        ingresoExiste.setCantidad(detalleIngreso.getCantidad());
        double precio = ingresoExiste.getProducto().getPrecioCompra() * ingresoExiste.getCantidad();
        ingresoExiste.setPrecio(precio);
        detalleIngresoService.update(ingresoExiste);

        int cantFinal;
        if (esSuma == true) {
            cantFinal = stock.getCantidad() + totalASumar;
            stock.setCantidad(cantFinal);
        } else {
            cantFinal = stock.getCantidad() - totalARestar;
            stock.setCantidad(cantFinal);
        }

        if(Objects.equals(stock.getProducto().getFormaVenta().getNombre(), "Por peso")){
            stock.setCantidadKg((int) (stock.getProducto().getPesoNeto() * stock.getCantidad()));
            stock.setCantidadRestante(stock.getCantidadKg());
        }

        stockService.update(stock);
        double total = ingresoProductosRepository.calcularTotalIngreso(ingresoProductos.getIdIngreso());
        ingresoProductos.setTotal(total);
        ingresoProductosService.updateIngreso(ingresoProductos);


        return "redirect:/detalleIngresos/update/{idDetalle}?exito";

    }

    int idIngr ;
    @GetMapping("/listar/{idIngreso}")
    public String listar(Model model, @PathVariable int idIngreso){
        List<DetalleIngreso> detalleIngresoList = repository.findAllByIngresoProductosIdIngreso(idIngreso);
        List<DetalleIngresoDtoIdIngreso> listaFalsa = new ArrayList<>();

        idIngr = idIngreso;
        for (DetalleIngreso detalle: detalleIngresoList ) {
            DetalleIngresoDtoIdIngreso dtoSinIdIngreso = new DetalleIngresoDtoIdIngreso();
            dtoSinIdIngreso.setIdIngreso(idIngreso);
            dtoSinIdIngreso.setIdDetalle(detalle.getIdDetalle());
            dtoSinIdIngreso.setTotal(detalle.getPrecio());
            dtoSinIdIngreso.setCantidad(detalle.getCantidad());
            dtoSinIdIngreso.setProducto(detalle.getProducto());
            listaFalsa.add(dtoSinIdIngreso);
        }

        model.addAttribute("DetalleIngresoDtoIdIngreso",listaFalsa);
        return "listadoDetalleIngresos";
    }

    //------------------------------------------------------------------------------------------
    @GetMapping("/mostrarIdIngreso/{idIngreso}")
    public String verIdIngreso(@ModelAttribute("detalleSoloIdIngreso") detalleSoloIdIngreso detalleSoloIdIngreso,
                               @PathVariable int idIngreso) {

        idIngreso = idIngr;
        detalleSoloIdIngreso.setIdIngreso(idIngreso);
        return "redirect:/detalleIngresos/crear/"+idIngreso;
    }

    @GetMapping("/crear/{idIngreso}")
    public String mostrarFormularioConID2(Model model, @PathVariable int idIngreso) {

        idIngresos = idIngr;
        IngresoProductos ingresoProductos = ingresoProductosRepository.findByIdIngreso(idIngreso);

        List<ProductoDetalle> listaProductos = detalleIngresoService.getDetalleByNombreProducto(nombre);

        if(codBarras != 0){
            List<ProductoDetalle> listaProductos2 = detalleIngresoService.getDetalleByCodBarrasProducto(codBarras);
            model.addAttribute("productoDetalle", listaProductos2);
            model.addAttribute("ingresoIdTotal",ingresoProductos);
            return "crearDetalleIngresoEspecifico";
        }


        DetalleIngresoDtos detalle = new DetalleIngresoDtos();
        model.addAttribute("detalleDtos", detalle);
        model.addAttribute("productoDetalle", listaProductos);
        return "crearDetalleIngresoEspecifico";
    }

    @PostMapping("/crearDetalleDesdeLista/{idIngreso}")
    public String createSinceLista(@ModelAttribute("detalleIngresoDtoCrear") DetalleIngresoDtosCrear detalleIngresoDtosCrear,
                                   @PathVariable int idIngreso) {

        idIngreso = idIngr;
        int idProd = detalleIngresoDtosCrear.getIdProducto();
        Producto producto = productoRepository.findByIdProducto(idProd);

        DetalleIngreso detalleIngreso = new DetalleIngreso();
        detalleIngreso.setIngresoProductos(ingresoProductosRepository.findByIdIngreso(idIngreso));
        detalleIngreso.setProducto(producto);
        detalleIngreso.setCantidad(detalleIngresoDtosCrear.getCantidad());
        IngresoProductos ingresoProductos = ingresoProductosRepository.findByIdIngreso(detalleIngresoDtosCrear.getIdIngreso());
        detalleIngreso.setIngresoProductos(ingresoProductos);
        double subtotal = detalleIngresoDtosCrear.getPrecioCompra() * detalleIngresoDtosCrear.getCantidad();
        detalleIngreso.setPrecio(subtotal);

        Stock stock = stockRepository.findByProductoIdProducto(detalleIngreso.getProducto().getIdProducto());
        int cantidadFinal = stock.getCantidad() + detalleIngreso.getCantidad();
        stock.setCantidad(cantidadFinal);
        if(Objects.equals(stock.getProducto().getFormaVenta().getNombre(), "Por peso")){
            stock.setCantidadKg((int) (stock.getProducto().getPesoNeto() * stock.getCantidad()));
            stock.setCantidadRestante(stock.getCantidadKg());
        }
        stockService.update(stock);

        detalleIngresoService.save(detalleIngreso);
        double totalIngreso = ingresoProductosRepository.calcularTotalIngreso(idIngreso);
        ingresoProductos.setTotal(totalIngreso);
        ingresoProductosService.updateIngreso(ingresoProductos);


        return "redirect:/detalleIngresos/listar/"+idIngreso;
    }
//-------------------------------------------------------------------------------------

    @GetMapping("/delete/{idDetalle}")
    public String deleteVendedor( @PathVariable int idDetalle){
        int idIngreso = idIngr;

        DetalleIngreso detalleIngreso = repository.findByIdDetalle(idDetalle);

        Stock stock = stockRepository.findByProductoIdProducto(detalleIngreso.getProducto().getIdProducto());
        int cantidadFinal = stock.getCantidad() - detalleIngreso.getCantidad();
        stock.setCantidad(cantidadFinal);
        if(Objects.equals(stock.getProducto().getFormaVenta().getNombre(), "Por peso")){
            stock.setCantidadKg((int) (stock.getProducto().getPesoNeto() * stock.getCantidad()));
            stock.setCantidadRestante(stock.getCantidadKg());
        }
        stockService.update(stock);

        idIngreso = detalleIngreso.getIngresoProductos().getIdIngreso();
        detalleIngresoService.delete(idDetalle);

        IngresoProductos ingresoProductos = ingresoProductosRepository.findByIdIngreso(idIngreso);
        double totalIngreso = ingresoProductosRepository.calcularTotalIngreso(idIngreso);
        ingresoProductos.setTotal(totalIngreso);
        ingresoProductosService.updateIngreso(ingresoProductos);

        return "redirect:/detalleIngresos/listar/"+idIngreso+"?exito";

    }
}
