package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.DetalleIngresoDtoIdIngreso;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.IngresoDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.IngresoDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.IngresoIdTotal;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleIngresoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.StockRepository;
import com.thesis.FlorenciaUlloque.UTN.services.IngresoProductosService;
import com.thesis.FlorenciaUlloque.UTN.services.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/ingresos")
public class IngresoProductosController {

    private final IngresoProductosService ingresoProductosService;
    private final IngresoProductosRepository repository;
    private final DetalleIngresoRepository detalleIngresoRepository;
    private final StockRepository stockRepository;
    private final StockService stockService;


    public IngresoProductosController(IngresoProductosService ingresoProductosService, IngresoProductosRepository repository, DetalleIngresoRepository detalleIngresoRepository, StockRepository stockRepository, StockService stockService) {
        this.ingresoProductosService = ingresoProductosService;
        this.repository = repository;

        this.detalleIngresoRepository = detalleIngresoRepository;
        this.stockRepository = stockRepository;
        this.stockService = stockService;
    }

    /*
    //averiguar como funciona
    @GetMapping("/date/{date}")
    public IngresoProductos getIngresobyDate(@PathVariable Date date) {
        return repository.findByDate(date);
    }

     */

    //funcionaaa
    @GetMapping("/proveedor/id/{idProveedor}")
    public IngresoProductos getClienteByIdProveedor(@PathVariable int idProveedor) {
        return repository.findByProveedorIdProveedor(idProveedor);
    }

    //funcionaaa
    @GetMapping("/proveedor/nombre/{nombre}")
    public IngresoProductos getClienteByProveedorName(@PathVariable String nombre) {
        return repository.findByProveedorNombre(nombre);
    }

    //funcionaa
    @GetMapping("/total/{total}")
    public IngresoProductos getClienteByProveedorName(@PathVariable double total) {
        return repository.findByTotal(total);
    }
    @ModelAttribute("ingreso")
    public IngresoDto mapeaDto() {
        return new IngresoDto();
    }

    @ModelAttribute("ingresoDtos")
    public IngresoDtos mapeaDtos() {
        return new IngresoDtos();
    }

    @ModelAttribute("ingresoReal")
    public IngresoProductos mapearVendedorREal() {
        return new IngresoProductos();
    }

    @ModelAttribute("ingresoIdTotal")
    public IngresoIdTotal mapearIdTOtal() {
        return new IngresoIdTotal();
    }

    @ModelAttribute("detalleIngresoReal")
    public DetalleIngreso mapeardetalleIngreso() {
        return new DetalleIngreso();
    }


    @GetMapping
    public String mostrarFormulario(Model model) {

        IngresoProductos ingresoProductos = new IngresoProductos();
        List<Proveedor> proveedores = ingresoProductosService.listaProveedores();
        List<FormaPago> formasPagos = ingresoProductosService.listaFormasPagos();

        model.addAttribute("proveedores", proveedores);
        model.addAttribute("ingresoReal", ingresoProductos);
        model.addAttribute("formasPagos", formasPagos);
        return "crearIngreso";
    }


   int idIngres;

    @PostMapping
    public String create(@ModelAttribute("ingresoReal") IngresoProductos ingresoProductos) {
        ingresoProductosService.saveIngreso(ingresoProductos);
        IngresoProductos ingreso = ingresoProductosService.saveIngreso(ingresoProductos);
        idIngres = ingreso.getIdIngreso();
        return "redirect:/ingresos/agregarDetalles";

    }

    @GetMapping("/agregarDetalle")
    public String mostrarFormularioIntermedioPArt2(Model model) {

        IngresoProductos ingreso = repository.findByIdIngreso((idIngres));

        double total = repository.calcularTotalIngreso(idIngres);
        ingreso.setTotal(total);
        ingresoProductosService.updateIngreso(ingreso);

        List<DetalleIngreso> listaDetalles = detalleIngresoRepository.findAllByIngresoProductosIdIngreso(idIngres);
        List<DetalleIngresoDtoIdIngreso> listaDetalleFormat = new ArrayList<>();
        for (DetalleIngreso detalle: listaDetalles) {
            DetalleIngresoDtoIdIngreso dtoIdIngreso = new DetalleIngresoDtoIdIngreso();
            dtoIdIngreso.setIdDetalle(detalle.getIdDetalle());
            dtoIdIngreso.setIdIngreso(detalle.getIngresoProductos().getIdIngreso());
            dtoIdIngreso.setProducto(detalle.getProducto());
            dtoIdIngreso.setCantidad(detalle.getCantidad());
            dtoIdIngreso.setTotal(detalle.getPrecio());
            listaDetalleFormat.add(dtoIdIngreso);

        }

        IngresoIdTotal ingresoIdTotal = new IngresoIdTotal();
        ingresoIdTotal.setIdIngreso(ingreso.getIdIngreso());
        ingresoIdTotal.setTotal(ingreso.getTotal());

        model.addAttribute("ingresoIdTotal",ingreso);
        model.addAttribute("DetalleIngresoDtoIdIngreso",listaDetalleFormat);
        return "pagIntermediaIngresosDetalle2";
    }

    @GetMapping("/agregarDetalles")
    public String mostrarFormularioIntermedio(Model model) {

        IngresoProductos ingresoProductos = repository.findByIdIngreso((idIngres));
        IngresoIdTotal ingresoIdTotal = new IngresoIdTotal();
        ingresoIdTotal.setIdIngreso(ingresoProductos.getIdIngreso());
        ingresoIdTotal.setTotal(ingresoProductos.getTotal());

        List<DetalleIngreso> listaDetalles = detalleIngresoRepository.findAllByIngresoProductosIdIngreso(idIngres);

        model.addAttribute("ingresoIdTotal",ingresoIdTotal);
        model.addAttribute("detalleIngresoReal",listaDetalles);
        return "pagIntermediaIngresosDetalle";
    }

    int idIngr;
    @GetMapping("/update/{idIngreso}")
    public String mostrarformUpdate(@PathVariable int idIngreso, Model model){

     //   IngresoProductos ingreso = repository.findByIdIngreso(idIngreso);
        List<Proveedor> proveedores = ingresoProductosService.listaProveedores();
        List<FormaPago> formaPagos = ingresoProductosService.listaFormasPagos();

        idIngr = idIngreso;

        model.addAttribute("ingreso", repository.findByIdIngreso(idIngreso));
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("formaPagos", formaPagos);
        return "UpdateIngreso";
    }


    @PostMapping("/updateIngreso/{idIngreso}")
    public String updatearVendedor(@ModelAttribute("ingreso")IngresoDto ingresoDto,
                                   @PathVariable int idIngreso){

       idIngreso = idIngr;
        IngresoProductos ingresoExiste = repository.findByIdIngreso(idIngreso);
        ingresoExiste.setIdIngreso(ingresoExiste.getIdIngreso());
        ingresoExiste.setFecha(ingresoDto.getFecha());
        ingresoExiste.setProveedor(ingresoDto.getProveedor());
        ingresoExiste.setListadoDetalleIngresos(ingresoExiste.getListadoDetalleIngresos());
        ingresoExiste.setFormaPago(ingresoDto.getFormaPago());

        double total = 0;
        List<DetalleIngreso> listaDetalle = detalleIngresoRepository.
                findAllByIngresoProductosIdIngreso(ingresoExiste.getIdIngreso());
        for (DetalleIngreso detalle : listaDetalle) {
           total += detalle.getPrecio();

        }

        ingresoExiste.setTotal(total);
        ingresoProductosService.updateIngreso(ingresoExiste);

        return "redirect:/ingresos/update/" + idIngr + "?exito";

    }




    @GetMapping("/listar")
    public String listar(Model model){

        List<IngresoProductos> listaIngresos = ingresoProductosService.getAllIngresos();
        for (IngresoProductos ingresos: listaIngresos) {
            if(ingresos.getListadoDetalleIngresos().size() < 1){
                ingresoProductosService.deleteIngreso(ingresos.getIdIngreso());
            }
        }


        model.addAttribute("ingresoDto", ingresoProductosService.getAllIngresos());
        return "listadoIngresos";
    }


    @GetMapping("/delete/{idIngreso}")
    public String deleteVendedor( @PathVariable int idIngreso){

        List<DetalleIngreso> detalleIngresoList = detalleIngresoRepository.findAllByIngresoProductosIdIngreso(idIngreso);
        for (DetalleIngreso detalleIngreso: detalleIngresoList) {
           Producto producto =  detalleIngreso.getProducto();
           int cant = detalleIngreso.getCantidad();
           Stock stock = stockRepository.findByProductoIdProducto(producto.getIdProducto());
           stock.setCantidad(stock.getCantidad()- cant);
           stockService.update(stock);
            ingresoProductosService.deleteIngreso(idIngreso);
        }
        return "redirect:/ingresos/listar?exito";

    }


}
