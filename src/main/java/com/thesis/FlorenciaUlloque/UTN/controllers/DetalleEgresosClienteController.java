package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.*;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.*;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleEgreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import com.thesis.FlorenciaUlloque.UTN.entiities.SalidaProducto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleEgresoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProductoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.SalidaProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.StockRepository;
import com.thesis.FlorenciaUlloque.UTN.services.DetalleEgresoService;
import com.thesis.FlorenciaUlloque.UTN.services.ProductoService;
import com.thesis.FlorenciaUlloque.UTN.services.SalidaProductosService;
import com.thesis.FlorenciaUlloque.UTN.services.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/detalleEgresosCliente")
public class DetalleEgresosClienteController {

    private final DetalleEgresoService detalleEgresoService;
    private final DetalleEgresoRepository repository;
    private final ProductoRepository productoRepository;
    private final SalidaProductosRepository salidaProductosRepository;
    private final ProductoService productoService;
    private final StockRepository stockRepository;
    private final StockService stockService;
    private final SalidaProductosService salidaProductosService;

    public DetalleEgresosClienteController(DetalleEgresoService detalleEgresoService, DetalleEgresoRepository repository,
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
            return "redirect:/detalleEgresosCliente/"+idEgresos+"?errorNombre";
        }

        return "redirect:/detalleEgresosCliente/"+idEgresos;
    }
    @GetMapping("/buscarNombreVentaGramo")
    public String buscarNombreVentaGramo(@ModelAttribute("productoSoloNombre") ProductoSoloNombre productoSoloNombre) {

        nombre = productoSoloNombre.getNombre();
        List<ProductoDetalleVentaParaDetalle> listaProductos = detalleEgresoService.getDetalleByNombreProductoYTipoVenta(nombre);

        if(listaProductos.size() < 1){
            return "redirect:/detalleEgresosCliente/crearDetalleKG/"+idEgresos+"?errorNombre";
        }

        return "redirect:/detalleEgresosCliente/crearDetalleKG/"+idEgresos;
    }


    @GetMapping("/buscarNombreDetalleEspecifico")
    public String buscarNombre2(@ModelAttribute("productoSoloNombre") ProductoSoloNombre productoSoloNombre) {

        nombre = productoSoloNombre.getNombre();
        List<ProductoDetalleVenta> listaProductos = detalleEgresoService.getDetalleByNombreProducto(nombre);

        if(listaProductos.size() < 1){
            return "redirect:/detalleEgresosCliente/crear/"+idEgresos+"?errorNombre";
        }

        return "redirect:/detalleEgresosCliente/crear/"+idEgresos;
    }
    @GetMapping("/buscarNombreDetalleEspecificoGramo")
    public String buscarNombre3(@ModelAttribute("productoSoloNombre") ProductoSoloNombre productoSoloNombre) {

        nombre = productoSoloNombre.getNombre();
        List<ProductoDetalleVentaParaDetalle> listaProductos = detalleEgresoService.getDetalleByNombreProductoYTipoVenta(nombre);

        if(listaProductos.size() < 1){
            return "redirect:/detalleEgresosCliente/crearEgresoKG/"+idEgresos+"?errorNombre";
        }

        return "redirect:/detalleEgresosCliente/crearEgresoKG/"+idEgresos;
    }



    long codBarras;
    @GetMapping("/buscarCodBarrasDetalleEspecifico")
    public String buscarCodBarras(@ModelAttribute("productoSoloCodBarras") ProductoSoloCodBarras productoSoloCodBarras) {

        codBarras = productoSoloCodBarras.getCodBarras();

        if(codBarras == 0){
            return "redirect:/detalleEgresosCliente/"+idEgresos+"?errorCodigo";
        }
        return "redirect:/detalleEgresosCliente/"+idEgresos;
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

    //mostrarFormCrearDetalleKG
    @GetMapping("crearDetalleKG/{idEgreso}")
    public String mostrarFormularioConIDKG(Model model, @PathVariable int idEgreso) {

        idEgresos = idEgreso;

        List<ProductoDetalleVentaParaDetalle> listaProductos = detalleEgresoService.getDetalleByNombreProductoYTipoVenta(nombre);


        DetalleIEgresoDtos detalle = new DetalleIEgresoDtos();
        model.addAttribute("detalleDtos", detalle);
        model.addAttribute("productoDetalleVentaParaDetalle", listaProductos);
        return "crearDetalleEgresoXKgVendedor";
    }
    @GetMapping("{idEgreso}")
    public String mostrarFormularioConID(Model model, @PathVariable int idEgreso) {

        idEgresos = idEgreso;
        SalidaProducto salidaProducto = salidaProductosRepository.findByIdEgreso(idEgreso);

        List<ProductoDetalleVenta> listaProductos = detalleEgresoService.getDetalleByNombreProducto(nombre);

        if(codBarras != 0){
            List<ProductoDetalleVenta> listaProductos2 = detalleEgresoService.getDetalleByCodBarrasProducto(codBarras);
            model.addAttribute("productoDetalleVenta", listaProductos2);
            model.addAttribute("salidaProducto",salidaProducto);
            return "crearDetalleEgresoVendedor";
        }


        DetalleIEgresoDtos detalle = new DetalleIEgresoDtos();
        model.addAttribute("detalleDtos", detalle);
        model.addAttribute("productoDetalleVenta", listaProductos);
        return "crearDetalleEgresoVendedor";
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

        return "redirect:/detalleEgresosCliente/"+idEgresos;

    }



    @GetMapping
    public String mostrarFormularioSINID(Model model) {

        SalidaProducto salidaProducto = salidaProductosRepository.findByIdEgreso(idEgresos);

        List<ProductoDetalleVenta> listaProductos = detalleEgresoService.getDetalleByNombreProducto(nombre);
        if(listaProductos.size() == 0){
            return "redirect:/detalleEgresosCliente/agregarDetalle/{idEgreso}?errorNombre";
        }

        if(codBarras != 0){
            List<ProductoDetalleVenta> listaProductos2 = detalleEgresoService.getDetalleByCodBarrasProducto(codBarras);
            model.addAttribute("productoDetalleVenta", listaProductos2);
            model.addAttribute("ingresoIdTotal",salidaProducto );
            return "crearDetalleEgresoVendedor";
        }


        DetalleIEgresoDtos detalle = new DetalleIEgresoDtos();
        model.addAttribute("detalleDtos", detalle);
        model.addAttribute("productoDetalleVenta", listaProductos);
        return "crearDetalleEgresoVendedor";
    }

    @RequestMapping( value = "crearDetalle/{idEgreso}")
    public String create(@ModelAttribute("detalleEgresoDtoCrear") DetalleEgresoDtoCrear detalleEgresoDtoCrear,
                         @PathVariable int idEgreso) {

        int idProd = detalleEgresoDtoCrear.getIdProducto();
        Producto producto = productoRepository.findByIdProducto(idProd);

        DetalleEgreso detalleEgreso = new DetalleEgreso();
        detalleEgreso.setProducto(producto);
        detalleEgreso.setCantidad((int) detalleEgresoDtoCrear.getCantidad());
        SalidaProducto salidaProducto = salidaProductosRepository.findByIdEgreso(detalleEgresoDtoCrear.getIdEgreso());
        detalleEgreso.setSalidaProducto(salidaProducto);
        double subtotal = producto.getPrecioVenta() * detalleEgresoDtoCrear.getCantidad();
        detalleEgreso.setPrecio(subtotal);

        float cantidadIngresada = detalleEgreso.getCantidad();

        Stock stock = stockRepository.findByProductoIdProducto(detalleEgreso.getProducto().getIdProducto());
        float cantidadFinal = stock.getCantidad() - detalleEgreso.getCantidad();

        if(detalleEgreso.getCantidad()<= 0){
            return "redirect:/detalleEgresosCliente/"+ salidaProducto.getIdEgreso() + "?errorCantidadNegativa";
        }else if(cantidadIngresada> stock.getCantidad()){
            return "redirect:/detalleEgresosCliente/"+ salidaProducto.getIdEgreso() + "?errorCantidad";
        }else{
            stock.setCantidad((int) cantidadFinal);
            stockService.update(stock);

            detalleEgresoService.save(detalleEgreso);
            return "redirect:/ventasCliente/agregarDetalle";
        }
    }
    @RequestMapping( value = "crearDetalleXKg/{idEgreso}")
    public String createVentaKg(@ModelAttribute("detalleEgresoDtoCrear") DetalleEgresoDtoCrear detalleEgresoDtoCrear,
                         @PathVariable int idEgreso) {

        int idProd = detalleEgresoDtoCrear.getIdProducto();
        Producto producto = productoRepository.findByIdProducto(idProd);

        DetalleEgreso detalleEgreso = new DetalleEgreso();
        detalleEgreso.setProducto(producto);
        SalidaProducto salidaProducto = salidaProductosRepository.findByIdEgreso(detalleEgresoDtoCrear.getIdEgreso());
        detalleEgreso.setSalidaProducto(salidaProducto);
        detalleEgreso.setCantidad(detalleEgresoDtoCrear.getCantidad());
        double subtotal = producto.getPrecioVenta() * detalleEgresoDtoCrear.getCantidad();
        double subtotalRedondeado = Math.round(subtotal*100.0/100.0);
        detalleEgreso.setPrecio(subtotalRedondeado);

        float cantidadIngresada = detalleEgreso.getCantidad();

        Stock stock = stockRepository.findByProductoIdProducto(detalleEgreso.getProducto().getIdProducto());
        float cantidadRestante = stock.getCantidadRestante() -detalleEgreso.getCantidad();

        if(detalleEgreso.getCantidad()<= 0){
            return "redirect:/detalleEgresosCliente/crearDetalleXKg/"+ salidaProducto.getIdEgreso() + "?errorCantidadNegativa";
        }else if(cantidadIngresada> stock.getCantidadRestante()){
            return "redirect:/detalleEgresosCliente/crearDetalleXKg/"+ salidaProducto.getIdEgreso() + "?errorCantidad";
        }else{
            stock.setCantidadKg(stock.getCantidadKg());
            stock.setCantidad(stock.getCantidad());
            int cantidadBolsas = (int) Math.ceil(cantidadRestante/producto.getPesoNeto());
                stock.setCantidad(cantidadBolsas);

            stock.setCantidadRestante(cantidadRestante);
            stockService.update(stock);

            detalleEgresoService.save(detalleEgreso);
            return "redirect:/ventasCliente/agregarDetalle";
        }
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
        return "listadoDetalleEgresosVendedor";
    }

    //------------------------------------------------------------------------------------------
    @GetMapping("/mostrarIdEgreso/{idEgreso}")
    public String verIdIngreso(@ModelAttribute("detalleSoloIdEgreso") DetalleSoloIdEgreso detalleSoloIdEgreso,
                               @PathVariable int idEgreso) {

        int idEgress = idEgreso;
        idEgreso = idEgr;
        detalleSoloIdEgreso.setIdEgreso(idEgreso);
        return "redirect:/detalleEgresosCliente/crear/"+idEgreso;
    }
    @GetMapping("/mostrarIdEgresoGramo/{idEgreso}")
    public String verIdIngresoGRamo(@ModelAttribute("detalleSoloIdEgreso") DetalleSoloIdEgreso detalleSoloIdEgreso,
                               @PathVariable int idEgreso) {

        int idEgress = idEgreso;
        idEgreso = idEgr;
        detalleSoloIdEgreso.setIdEgreso(idEgreso);
        return "redirect:/detalleEgresosCliente/crearEgresoKG/"+idEgreso;
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
            return "crearDetalleEgresoEspecificoVendedor";
        }


        DetalleIEgresoDtos detalle = new DetalleIEgresoDtos();
        model.addAttribute("detalleDtos", detalle);
        model.addAttribute("productoDetalleVenta", listaProductos);
        return "crearDetalleEgresoEspecificoVendedor";
    }

    @GetMapping("/crearEgresoKG/{idEgreso}")
    public String mostrarFormularioConID3(Model model, @PathVariable int idEgreso) {

        idEgreso = idEgr;

        List<ProductoDetalleVentaParaDetalle> listaProductos = detalleEgresoService.getDetalleByNombreProductoYTipoVenta(nombre);


        DetalleIEgresoDtos detalle = new DetalleIEgresoDtos();
        model.addAttribute("detalleDtos", detalle);
        model.addAttribute("productoDetalleVentaParaDetalle", listaProductos);
        return "crearDetalleEgresoEspecifico2Vendedor";
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
        double subtotal = producto.getPrecioVenta() * detalleEgresoDtoCrear.getCantidad();
        detalleEgreso.setPrecio(subtotal);

        Stock stock = stockRepository.findByProductoIdProducto(detalleEgreso.getProducto().getIdProducto());
        int cantidadFinal = (int) (stock.getCantidad() - detalleEgreso.getCantidad());
        stock.setCantidad(cantidadFinal);
        stockService.update(stock);

        detalleEgresoService.save(detalleEgreso);
        double totalIngreso = salidaProductosRepository.calcularTotalEgreso(idEgreso);
        SalidaProducto salidaProducto = detalleEgreso.getSalidaProducto();
        salidaProducto.setTotal(totalIngreso);
        salidaProductosService.updateEgreso(salidaProducto);


        return "redirect:/detalleEgresosCliente/listar/"+idEgreso;
    }
    @PostMapping("/crearDetalleDesdeListaGramo/{idEgreso}")
    public String createSinceLista2(@ModelAttribute("detalleEgresoDtoCrear") DetalleEgresoDtoCrear detalleEgresoDtoCrear,
                                   @PathVariable int idEgreso) {

        idEgreso = idEgr;
        int idProd = detalleEgresoDtoCrear.getIdProducto();
        Producto producto = productoRepository.findByIdProducto(idProd);

        DetalleEgreso detalleEgreso = new DetalleEgreso();
        detalleEgreso.setSalidaProducto(salidaProductosRepository.findByIdEgreso(idEgreso));
        detalleEgreso.setProducto(producto);
        detalleEgreso.setCantidad(detalleEgresoDtoCrear.getCantidad());
        double subtotal = producto.getPrecioVenta() * detalleEgresoDtoCrear.getCantidad();
        detalleEgreso.setPrecio(subtotal);

        Stock stock = stockRepository.findByProductoIdProducto(detalleEgreso.getProducto().getIdProducto());
        int cantidadFinal = (int) (stock.getCantidad() - detalleEgreso.getCantidad());
        stock.setCantidad(cantidadFinal);
        if(stock.getProducto().getFormaVenta().getIdFormaVenta() == 2){
            float cantidadRestante = stock.getCantidadRestante() - detalleEgreso.getCantidad();
            stock.setCantidadRestante(cantidadRestante);
            int cantidadBolsas = (int) Math.ceil(cantidadRestante/stock.getProducto().getPesoNeto());
            stock.setCantidad(cantidadBolsas);
        }

        stockService.update(stock);

        detalleEgresoService.save(detalleEgreso);
        double totalIngreso = salidaProductosRepository.calcularTotalEgreso(idEgreso);
        SalidaProducto salidaProducto = detalleEgreso.getSalidaProducto();
        salidaProducto.setTotal(totalIngreso);
        salidaProductosService.updateEgreso(salidaProducto);


        return "redirect:/detalleEgresosCliente/listar/"+idEgreso;
    }

}
