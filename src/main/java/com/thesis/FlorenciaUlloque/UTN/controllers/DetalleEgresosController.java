package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.*;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.*;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.*;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import com.thesis.FlorenciaUlloque.UTN.repositories.*;
import com.thesis.FlorenciaUlloque.UTN.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/detalleEgresos")
public class DetalleEgresosController {

    private final DetalleEgresoService detalleEgresoService;
    private final DetalleEgresoRepository repository;
    private final ProductoRepository productoRepository;
    private final SalidaProductosRepository salidaProductosRepository;
    private final ProductoService productoService;
    private final StockRepository stockRepository;
    private final StockService stockService;
    private final SalidaProductosService salidaProductosService;

    public DetalleEgresosController(DetalleEgresoService detalleEgresoService, DetalleEgresoRepository repository,
                                    ProductoRepository productoRepository,
                                    SalidaProductosRepository salidaProductosRepository,
                                    ProductoService productoService, StockRepository stockRepository,
                                    StockService stockService, SalidaProductosService salidaProductosService) {
        this.detalleEgresoService = detalleEgresoService;
        this.repository = repository;
        this.productoRepository = productoRepository;
        this.salidaProductosRepository = salidaProductosRepository;
        this.productoService = productoService;
        this.stockRepository = stockRepository;
        this.stockService = stockService;
        this.salidaProductosService = salidaProductosService;
    }

    //funciona
    @GetMapping("/salida/id/{idEgreso}")
    public DetalleEgreso getDetalleBySalidaProducto(@PathVariable int idEgreso) {
        return repository.findBySalidaProductoIdEgreso(idEgreso);
    }

    //funciona
    @GetMapping("/id/{idDetalle}")
    public DetalleEgreso getDetalleById(@PathVariable int idDetalle) {
        return repository.findByIdDetalleEgreso(idDetalle);
    }

    //funciona
    @GetMapping("/producto/codBarras/{codBarras}")
    public DetalleEgreso getDetalleByCodBarrasProducto(@PathVariable Long codBarras) {
        return repository.findByProductoCodBarras(codBarras);
    }

    //funciona
    @GetMapping("/producto/id/{idProducto}")
    public DetalleEgreso getClienteByProveedorName(@PathVariable int idProducto) {
        return repository.findByProductoIdProducto(idProducto);
    }


    @ModelAttribute("productoDetalleVenta")
    public ProductoDetalleVenta mapearproducto() {
        return new ProductoDetalleVenta();
    }

    @ModelAttribute("productoSoloNombre")
    public ProductoSoloNombre mapeaDtoSoloNombre() {
        return new ProductoSoloNombre();
    }

    @ModelAttribute("DetalleEgresoDtoSinIdEgreso")
    public EgresoDto.DetalleEgresoDtoSinIdEgreso DetalleIngresoDtoSinIdIngreso() {
        return new EgresoDto.DetalleEgresoDtoSinIdEgreso();
    }

    @ModelAttribute("productoSoloCodBarras")
    public ProductoSoloCodBarras mapeaProdStockSoloCod() {
        return new ProductoSoloCodBarras();
    }

    @ModelAttribute("egresoIdTotal")
    public EgresoIdTotal mapearIdTOtal() {
        return new EgresoIdTotal();
    }

    int idEgresos ;
    String nombre;
    @GetMapping("/buscarNombre")
    public String buscarNombre(@ModelAttribute("productoSoloNombre") ProductoSoloNombre productoSoloNombre) {

        nombre = productoSoloNombre.getNombre();
        List<ProductoDetalleVenta> listaProductos = detalleEgresoService.getDetalleByNombreProducto(nombre);

        if(listaProductos.size() < 1){
            return "redirect:/detalleEgresos/"+idEgresos+"?errorNombre";
        }

        return "redirect:/detalleEgresos/"+idEgresos;
    }
    @GetMapping("/buscarNombreDetalleEspecifico")
    public String buscarNombre2(@ModelAttribute("productoSoloNombre") ProductoSoloNombre productoSoloNombre) {

        nombre = productoSoloNombre.getNombre();
        List<ProductoDetalleVenta> listaProductos = detalleEgresoService.getDetalleByNombreProducto(nombre);

        if(listaProductos.size() < 1){
            return "redirect:/detalleEgresos/crear/"+idEgresos+"?errorNombre";
        }

        return "redirect:/detalleEgresos/crear/"+idEgresos;
    }


    long codBarras;
    @GetMapping("/buscarCodBarrasDetalleEspecifico")
    public String buscarCodBarras(@ModelAttribute("productoSoloCodBarras") ProductoSoloCodBarras productoSoloCodBarras) {

        codBarras = productoSoloCodBarras.getCodBarras();

        if(codBarras == 0){
            return "redirect:/detalleEgresos/"+idEgresos+"?errorCodigo";
        }
        return "redirect:/detalleEgresos/"+idEgresos;
    }

    @ModelAttribute("detalleDtos")
    public DetalleIEgresoDtos mapeaMarcaDtos() {
        return new DetalleIEgresoDtos();
    }

    @ModelAttribute("detalleEgresoDtoCrear")
    public DetalleEgresoDtoCrear mapeacrearDtos() {
        return new DetalleEgresoDtoCrear();
    }

    @ModelAttribute("detalleReal")
    public DetalleEgreso mapearVendedorREal() {
        return new DetalleEgreso();
    }

    @GetMapping("{idEgreso}")
    public String mostrarFormularioConID(Model model, @PathVariable int idEgreso) {

        idEgresos = idEgreso;
        SalidaProducto salidaProducto = salidaProductosRepository.findByIdEgreso(idEgreso);

        List<ProductoDetalleVenta> listaProductos = detalleEgresoService.getDetalleByNombreProducto(nombre);

        if(codBarras != 0){
            List<ProductoDetalleVenta> listaProductos2 = detalleEgresoService.getDetalleByCodBarrasProducto(codBarras);
            model.addAttribute("ProductoDetalleVenta", listaProductos2);
            model.addAttribute("salidaProducto",salidaProducto);
            return "crearDetalleEgreso";
        }


        DetalleIEgresoDtos detalle = new DetalleIEgresoDtos();
        model.addAttribute("detalleDtos", detalle);
        model.addAttribute("ProductoDetalleVenta", listaProductos);
        return "crearDetalleEgreso";
    }


    @ModelAttribute("detalleEgresoDtoIdEgreso")
    public DetalleEgresoDtoIdEgreso mapeardtodetalleConIdIngreso() {
        return new DetalleEgresoDtoIdEgreso();
    }

    @ModelAttribute("detalleEgresoDtoUpdate")
    public DetalleEgresoDtoUpdate mapeardtodetalleCondtoUpdate() {
        return new DetalleEgresoDtoUpdate();
    }



    @ModelAttribute("productoDtoDetalle")
    public ProductoDtoDetalle mapeardtodetalle() {
        return new ProductoDtoDetalle();
    }


    @ModelAttribute("detalleSoloIdEgreso")
    public DetalleSoloIdEgreso detalleSoloIdIEgreso() {
        return new DetalleSoloIdEgreso();
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

        return "redirect:/detalleIngresos/"+idEgresos;

    }



    @GetMapping
    public String mostrarFormularioSINID(Model model) {

        SalidaProducto salidaProducto = salidaProductosRepository.findByIdEgreso(idEgresos);

        List<ProductoDetalleVenta> listaProductos = detalleEgresoService.getDetalleByNombreProducto(nombre);
        if(listaProductos.size() == 0){
            return "redirect:/detalleEgresos/agregarDetalle/{idEgreso}?errorNombre";
        }

        if(codBarras != 0){
            List<ProductoDetalleVenta> listaProductos2 = detalleEgresoService.getDetalleByCodBarrasProducto(codBarras);
            model.addAttribute("ProductoDetalleVenta", listaProductos2);
            model.addAttribute("ingresoIdTotal",salidaProducto );
            return "crearDetalleEgreso";
        }


        DetalleIEgresoDtos detalle = new DetalleIEgresoDtos();
        model.addAttribute("detalleDtos", detalle);
        model.addAttribute("ProductoDetalleVenta", listaProductos);
        return "crearDetalleEgreso";
    }

    @RequestMapping( value = "crearDetalle/{idEgreso}")
    public String create(@ModelAttribute("detalleEgresoDtoCrear") DetalleEgresoDtoCrear detalleEgresoDtoCrear,
                         @PathVariable int idEgreso) {

        int idProd = detalleEgresoDtoCrear.getIdProducto();
        Producto producto = productoRepository.findByIdProducto(idProd);

        DetalleEgreso detalleEgreso = new DetalleEgreso();
        detalleEgreso.setSalidaProducto(salidaProductosRepository.findByIdEgreso(idEgreso));
        detalleEgreso.setProducto(producto);
        detalleEgreso.setCantidad(detalleEgresoDtoCrear.getCantidad());
        SalidaProducto salidaProducto = salidaProductosRepository.findByIdEgreso(detalleEgresoDtoCrear.getIdEgreso());
        detalleEgreso.setSalidaProducto(salidaProducto);
        double subtotal = detalleEgresoDtoCrear.getPrecioVenta() * detalleEgresoDtoCrear.getCantidad();
        detalleEgreso.setPrecio(subtotal);

        int cantidadIngresada = detalleEgreso.getCantidad();

        Stock stock = stockRepository.findByProductoIdProducto(detalleEgreso.getProducto().getIdProducto());
        int cantidadFinal = stock.getCantidad() - detalleEgreso.getCantidad();

        if(detalleEgreso.getCantidad()<= 0){
            return "redirect:/detalleEgresos/"+ salidaProducto.getIdEgreso() + "?errorCantidadNegativa";
        }else if(cantidadIngresada> stock.getCantidad()){
            return "redirect:/detalleEgresos/"+ salidaProducto.getIdEgreso() + "?errorCantidad";
        }else{
            stock.setCantidad(cantidadFinal);
            stockService.update(stock);

            detalleEgresoService.save(detalleEgreso);
            return "redirect:/ventas/agregarDetalle";
        }
    }

    @GetMapping("/update/{idDetalleEgreso}")
    public String mostrarformUpdate(@PathVariable int idDetalleEgreso, Model model){

        List<Producto> productosList= (List<Producto>) productoRepository.findAll();
        DetalleEgreso detalle= repository.findByIdDetalleEgreso(idDetalleEgreso);
        DetalleEgresoDtoUpdate dto = new DetalleEgresoDtoUpdate();
        dto.setIdDetalleEgreso(detalle.getIdDetalleEgreso());
        dto.setIdEgreso(detalle.getSalidaProducto().getIdEgreso());
        dto.setCantidad(detalle.getCantidad());
        dto.setProducto(detalle.getProducto());

        model.addAttribute("detalleEgresoDtoUpdate", dto);
        model.addAttribute("productos", productosList);
        return "UpdateDetalleEgreso";
    }


    @PostMapping("/updateDetalle/{idDetalleEgreso}")
    public String updatearVendedor(@ModelAttribute("detalleEgresoDtoUpdate")DetalleEgresoDtoUpdate detalleEgreso,
                                   @PathVariable int idDetalleEgreso){

        DetalleEgreso ingresoExiste = repository.findByIdDetalleEgreso(idDetalleEgreso);

        SalidaProducto salidaProducto = salidaProductosRepository.findByIdEgreso(ingresoExiste.getSalidaProducto().
                getIdEgreso());
        ingresoExiste.setIdDetalleEgreso(idDetalleEgreso);
        ingresoExiste.setSalidaProducto(salidaProducto);
        ingresoExiste.setProducto(detalleEgreso.getProducto());

        int cantVieja = ingresoExiste.getCantidad();
        int cantNueva = detalleEgreso.getCantidad();
        int totalASumar = 0;
        int totalARestar = 0;
        boolean esSuma = false;
        Stock stock = stockRepository.findByProductoIdProducto(ingresoExiste.getProducto().getIdProducto());
        if(cantVieja> cantNueva){
            totalARestar = cantVieja - cantNueva;
            esSuma = false;
        }else if(cantVieja < cantNueva){
            totalASumar = cantNueva - cantVieja;
            esSuma = true;
        } else if(cantVieja == cantNueva){
            stock.setCantidad(stock.getCantidad());
        }
        ingresoExiste.setCantidad(detalleEgreso.getCantidad());
        double precio = ingresoExiste.getProducto().getPrecioCompra() * ingresoExiste.getCantidad();
        ingresoExiste.setPrecio(precio);
        detalleEgresoService.update(ingresoExiste);

        int cantFinal;
        if(esSuma == true){
            cantFinal = stock.getCantidad() - totalASumar;
            stock.setCantidad(cantFinal);
        }else{
            cantFinal = stock.getCantidad() + totalARestar;
            stock.setCantidad(cantFinal);
        }
        stockService.update(stock);
        double total = salidaProductosRepository.calcularTotalEgreso(salidaProducto.getIdEgreso());
        salidaProducto.setTotal(total);
        salidaProductosService.updateEgreso(salidaProducto);


        return "redirect:/detalleEgresos/update/{idDetalleEgreso}?exito";

    }

    int idEgr ;
    @GetMapping("/listar/{idEgreso}")
    public String listar(Model model, @PathVariable int idEgreso){
        List<DetalleEgreso> detalleEgresoList = repository.findAllBySalidaProductoIdEgreso(idEgreso);
        List<DetalleEgresoDtoIdEgreso> listaFalsa = new ArrayList<>();

        idEgr = idEgreso;
        for (DetalleEgreso detalle: detalleEgresoList ) {
            DetalleEgresoDtoIdEgreso dtoSinIdEgreso = new DetalleEgresoDtoIdEgreso();
            dtoSinIdEgreso.setIdEgreso(idEgreso);
            dtoSinIdEgreso.setIdDetalleEgreso(detalle.getIdDetalleEgreso());
            dtoSinIdEgreso.setTotal(detalle.getPrecio());
            dtoSinIdEgreso.setCantidad(detalle.getCantidad());
            dtoSinIdEgreso.setProducto(detalle.getProducto());
            listaFalsa.add(dtoSinIdEgreso);
        }

        model.addAttribute("detalleEgresoDtoIdEgreso",listaFalsa);
        return "listadoDetalleEgresos";
    }

    //------------------------------------------------------------------------------------------
    @GetMapping("/mostrarIdEgreso/{idEgreso}")
    public String verIdIngreso(@ModelAttribute("detalleSoloIdEgreso") DetalleSoloIdEgreso detalleSoloIdEgreso,
                               @PathVariable int idEgreso) {

        int idEgress = idEgreso;
        idEgreso = idEgr;
        detalleSoloIdEgreso.setIdEgreso(idEgreso);
        return "redirect:/detalleEgresos/crear/"+idEgreso;
    }

    @GetMapping("/crear/{idEgreso}")
    public String mostrarFormularioConID2(Model model, @PathVariable int idEgreso) {

        idEgreso = idEgr;
        SalidaProducto salidaProducto = salidaProductosRepository.findByIdEgreso(idEgreso);

        List<ProductoDetalleVenta> listaProductos = detalleEgresoService.getDetalleByNombreProducto(nombre);

        if(codBarras != 0){
            List<ProductoDetalleVenta> listaProductos2 = detalleEgresoService.getDetalleByCodBarrasProducto(codBarras);
            model.addAttribute("productoDetalleVenta", listaProductos2);
            model.addAttribute("egresoIdTotal",salidaProducto);
            return "crearDetalleEgresoEspecifico";
        }


        DetalleIEgresoDtos detalle = new DetalleIEgresoDtos();
        model.addAttribute("detalleDtos", detalle);
        model.addAttribute("ProductoDetalleVenta", listaProductos);
        return "crearDetalleEgresoEspecifico";
    }

    @PostMapping("/crearDetalleDesdeLista/{idEgreso}")
    public String createSinceLista(@ModelAttribute("detalleEgresoDtoCrear") DetalleEgresoDtoCrear detalleEgresoDtoCrear,
                                   @PathVariable int idEgreso) {

        idEgreso = idEgr;
        int idProd = detalleEgresoDtoCrear.getIdProducto();
        Producto producto = productoRepository.findByIdProducto(idProd);

        DetalleEgreso detalleEgreso = new DetalleEgreso();
        detalleEgreso.setSalidaProducto(salidaProductosRepository.findByIdEgreso(idEgreso));
        detalleEgreso.setProducto(producto);
        detalleEgreso.setCantidad(detalleEgresoDtoCrear.getCantidad());
        SalidaProducto salidaProducto = salidaProductosRepository.findByIdEgreso(detalleEgresoDtoCrear.getIdEgreso());
        detalleEgreso.setSalidaProducto(salidaProducto);
        double subtotal = detalleEgresoDtoCrear.getPrecioVenta() * detalleEgresoDtoCrear.getCantidad();
        detalleEgreso.setPrecio(subtotal);

        Stock stock = stockRepository.findByProductoIdProducto(detalleEgreso.getProducto().getIdProducto());
        int cantidadFinal = stock.getCantidad() - detalleEgreso.getCantidad();
        stock.setCantidad(cantidadFinal);
        stockService.update(stock);

        detalleEgresoService.save(detalleEgreso);
        double totalIngreso = salidaProductosRepository.calcularTotalEgreso(idEgreso);
        salidaProducto.setTotal(totalIngreso);
        salidaProductosService.updateEgreso(salidaProducto);


        return "redirect:/detalleEgresos/listar/"+idEgreso;
    }
//-------------------------------------------------------------------------------------

    @GetMapping("/delete/{idDetalleEgreso}")
    public String deleteVendedor( @PathVariable int idDetalleEgreso){
        int idEgreso = idEgr;

        DetalleEgreso detalleEgreso = repository.findByIdDetalleEgreso(idDetalleEgreso);

        Stock stock = stockRepository.findByProductoIdProducto(detalleEgreso.getProducto().getIdProducto());
        int cantidadFinal = stock.getCantidad() + detalleEgreso.getCantidad();
        stock.setCantidad(cantidadFinal);
        stockService.update(stock);

        idEgreso = detalleEgreso.getSalidaProducto().getIdEgreso();
        detalleEgresoService.delete(idDetalleEgreso);

        SalidaProducto salidaProducto = salidaProductosRepository.findByIdEgreso(idEgreso);
        double totalIngreso = salidaProductosRepository.calcularTotalEgreso(idEgreso);
        salidaProducto.setTotal(totalIngreso);
        salidaProductosService.updateEgreso(salidaProducto);

        return "redirect:/detalleEgresos/listar/"+idEgreso+"?exito";

    }
}
