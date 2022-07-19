package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.SoloFecha;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.DetalleIngresoDtoIdIngreso;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.IngresoDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.IngresoDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.IngresoIdTotal;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleIngresoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.StockRepository;
import com.thesis.FlorenciaUlloque.UTN.services.DetalleIngresoService;
import com.thesis.FlorenciaUlloque.UTN.services.IngresoProductosService;
import com.thesis.FlorenciaUlloque.UTN.services.StockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/ingresosProveedor")
public class IngresoProductosControllerVendedor {

    private final IngresoProductosService ingresoProductosService;
    private final IngresoProductosRepository repository;
    private final DetalleIngresoRepository detalleIngresoRepository;
    private final StockRepository stockRepository;
    private final StockService stockService;
    private final DetalleIngresoService detalleIngresoService;

    public IngresoProductosControllerVendedor(IngresoProductosService ingresoProductosService, IngresoProductosRepository repository, DetalleIngresoRepository detalleIngresoRepository, StockRepository stockRepository, StockService stockService, DetalleIngresoService detalleIngresoService) {
        this.ingresoProductosService = ingresoProductosService;
        this.repository = repository;

        this.detalleIngresoRepository = detalleIngresoRepository;
        this.stockRepository = stockRepository;
        this.stockService = stockService;
        this.detalleIngresoService = detalleIngresoService;
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

    @ModelAttribute("soloFecha")
    public SoloFecha mapearMeses() {
        return new SoloFecha();
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
        return "crearIngresoVendedor";
    }


   int idIngres;

    @PostMapping
    public String create(@ModelAttribute("ingresoReal") IngresoProductos ingresoProductos) {
        ingresoProductosService.saveIngreso(ingresoProductos);
        IngresoProductos ingreso = ingresoProductosService.saveIngreso(ingresoProductos);
        idIngres = ingreso.getIdIngreso();
        return "redirect:/ingresosProveedor/agregarDetalles";

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
        return "pagIntermediaIngresosDetalle2Vendedor";
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
        return "pagIntermediaIngresosDetalleVendedor";
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
        return "UpdateIngresoVendedor";
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

        return "redirect:/ingresosProveedor/update/" + idIngr + "?exito";

    }




    @GetMapping("/listar")
    public String findAll(@RequestParam Map<String, Object> params, Model model){
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) :0;
        PageRequest pageRequest = PageRequest.of(page, 7);


        List<IngresoProductos> lista = repository.findAllByOrderByFechaDesc();
        for (IngresoProductos list:  lista) {
            if(list.getTotal() == 0) {
                int id = list.getIdIngreso();
                ingresoProductosService.deleteIngreso(id);
            }
        }

        Page<IngresoProductos> pageIngresos = ingresoProductosService.getAll(pageRequest);

        int totalPage = pageIngresos.getTotalPages();
        if(totalPage> 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages",pages);
        }
        model.addAttribute("ingresoDto", pageIngresos.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "listadoIngresosVendedor";
    }


    @GetMapping("/delete/{idIngreso}")
    public String deleteVendedor( @PathVariable int idIngreso){

        List<DetalleIngreso> detalleIngresoList = detalleIngresoRepository.findAllByIngresoProductosIdIngreso(idIngreso);
        for (DetalleIngreso detalleIngreso: detalleIngresoList) {
           Producto producto =  detalleIngreso.getProducto();
           int cant = detalleIngreso.getCantidad();
           Stock stock = stockRepository.findByProductoIdProducto(producto.getIdProducto());
           stock.setCantidad(stock.getCantidad()- cant);
            if(Objects.equals(stock.getProducto().getFormaVenta().getNombre(), "Por peso")){
                stock.setCantidadKg((int) (stock.getProducto().getPesoNeto() * stock.getCantidad()));
                stock.setCantidadRestante(stock.getCantidadKg());
            }
           stockService.update(stock);
           detalleIngresoService.delete(detalleIngreso.getIdDetalle());

        }
        ingresoProductosService.deleteIngreso(idIngreso);
        return "redirect:/ingresosProveedor/listar?exito";

    }


}
