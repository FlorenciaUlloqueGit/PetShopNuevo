package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.EgresoDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.EgresoDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.EgresoIdTotal;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.DetalleEgresoDtoIdEgreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import com.thesis.FlorenciaUlloque.UTN.repositories.*;
import com.thesis.FlorenciaUlloque.UTN.services.SalidaProductosService;
import com.thesis.FlorenciaUlloque.UTN.services.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/ventas")
public class SalidaProductosController {

    private final SalidaProductosService salidaProductosService;
    private final SalidaProductosRepository repository;
    private final DetalleEgresoRepository detalleEgresoRepository;
    private final StockRepository stockRepository;
    private final StockService stockService;

    public SalidaProductosController(SalidaProductosService salidaProductosService, SalidaProductosRepository repository,
                                     DetalleEgresoRepository detalleEgresoRepository, StockRepository stockRepository,
                                     StockService stockService) {
        this.salidaProductosService = salidaProductosService;
        this.repository = repository;
        this.detalleEgresoRepository = detalleEgresoRepository;
        this.stockRepository = stockRepository;
        this.stockService = stockService;
    }


    //averiguar como funciona
    @GetMapping("/date/{date}")
    public SalidaProducto getEgresosbyDate(@PathVariable Date date) {
        return repository.findByDate(date);
    }

    //funciona
    @GetMapping("/cliente/email/{email}")
    public SalidaProducto getClienteByEmail(@PathVariable String email) {
        return repository.findByClienteEmail(email);
    }


    //funcionaaa
    @GetMapping("/formaPago/nombre/{nombre}")
    public SalidaProducto getSalidaByFormaPagoNombre(@PathVariable String nombre) {
        return repository.findByFormaPagoNombre(nombre);
    }

    //funcionaa
    @GetMapping("/total/{total}")
    public SalidaProducto getSalidaByTotal(@PathVariable double total) {
        return repository.findByTotal(total);
    }

    @ModelAttribute("egreso")
    public EgresoDto mapeaDto() {
        return new EgresoDto();
    }

    @ModelAttribute("egresoDtos")
    public EgresoDtos mapeaDtos() {
        return new EgresoDtos();
    }

    @ModelAttribute("egresoReal")
    public SalidaProducto mapearVendedorREal() {
        return new SalidaProducto();
    }

    @ModelAttribute("egresoIdTotal")
    public EgresoIdTotal mapearIdTOtal() {
        return new EgresoIdTotal();
    }

    @ModelAttribute("detalleEgresoReal")
    public DetalleEgreso mapeardetalleIngreso() {
        return new DetalleEgreso();
    }

    @ModelAttribute("detalleEgresoDtoIdEgreso")
    public DetalleEgresoDtoIdEgreso mapeardtodetalleConIdIngreso() {
        return new DetalleEgresoDtoIdEgreso();
    }


    @GetMapping
    public String mostrarFormulario(Model model) {

        SalidaProducto salidaProducto = new SalidaProducto();
        List<Cliente> clientes = salidaProductosService.listaClientes();
        List<FormaPago> formasPagos = salidaProductosService.listaFormasPagos();

        model.addAttribute("clientes", clientes);
        model.addAttribute("ingresoReal", salidaProducto);
        model.addAttribute("formasPagos", formasPagos);
        return "crearEgreso";
    }


    int idEgres;

    @PostMapping
    public String create(@ModelAttribute("egresoReal") SalidaProducto salidaProducto) {
        salidaProductosService.saveEgreso(salidaProducto);
        SalidaProducto salida = salidaProductosService.saveEgreso(salidaProducto);
        idEgres = salida.getIdEgreso();
        return "redirect:/ventas/agregarDetalles";

    }

    @GetMapping("/agregarDetalle")
    public String mostrarFormularioIntermedioPArte2(Model model) {

        SalidaProducto egreso = repository.findByIdEgreso((idEgres));

        double total = repository.calcularTotalEgreso(idEgres);
        egreso.setTotal(total);
        salidaProductosService.updateEgreso(egreso);

        List<DetalleEgreso> listaDetalles = detalleEgresoRepository.findAllBySalidaProductoIdEgreso(idEgres);
        List<DetalleEgresoDtoIdEgreso> listaDetalleFormat = new ArrayList<>();
        for (DetalleEgreso detalle : listaDetalles) {
            DetalleEgresoDtoIdEgreso dtoIdIngreso = new DetalleEgresoDtoIdEgreso();
            dtoIdIngreso.setIdDetalleEgreso(detalle.getIdDetalleEgreso());
            dtoIdIngreso.setIdEgreso(detalle.getSalidaProducto().getIdEgreso());
            dtoIdIngreso.setProducto(detalle.getProducto());
            dtoIdIngreso.setCantidad(detalle.getCantidad());
            dtoIdIngreso.setTotal(detalle.getPrecio());
            listaDetalleFormat.add(dtoIdIngreso);

        }

        EgresoIdTotal egresoIdTotal = new EgresoIdTotal();
        egresoIdTotal.setIdEgreso(egreso.getIdEgreso());
        egresoIdTotal.setTotal(egreso.getTotal());

        model.addAttribute("egresoIdTotal", egreso);
        model.addAttribute("detalleEgresoDtoIdEgreso", listaDetalleFormat);
        return "pagIntermediaEgresosDetalle2";
    }

    @GetMapping("/agregarDetalles")
    public String mostrarFormularioIntermedio(Model model) {

        SalidaProducto salidaProducto = repository.findByIdEgreso((idEgres));
        EgresoIdTotal egresoIdTotal = new EgresoIdTotal();
        egresoIdTotal.setIdEgreso(salidaProducto.getIdEgreso());
        egresoIdTotal.setTotal(salidaProducto.getTotal());

        List<DetalleEgreso> listaDetalles = detalleEgresoRepository.findAllBySalidaProductoIdEgreso(idEgres);

        model.addAttribute("egresoIdTotal", egresoIdTotal);
        model.addAttribute("detalleEgresoReal", listaDetalles);
        return "pagIntermediaEgresosDetalle";
    }

    int idEgreso;

    @GetMapping("/update/{idIngreso}")
    public String mostrarformUpdate(@PathVariable int idEgr, Model model) {

        //   IngresoProductos ingreso = repository.findByIdIngreso(idIngreso);
        List<Cliente> clientes = salidaProductosService.listaClientes();
        List<FormaPago> formaPagos = salidaProductosService.listaFormasPagos();

        idEgreso = idEgr;

        model.addAttribute("egreso", repository.findByIdEgreso(idEgr));
        model.addAttribute("clientes", clientes);
        model.addAttribute("formaPagos", formaPagos);
        return "UpdateEgreso";
    }


    @PostMapping("/updateEgreso/{idEgreso}")
    public String updatearVendedor(@ModelAttribute("ingreso") EgresoDto egresoDto,
                                   @PathVariable int idEgr) {

        idEgreso = idEgr;
        SalidaProducto egresoExiste = repository.findByIdEgreso(idEgr);
        egresoExiste.setIdEgreso(egresoExiste.getIdEgreso());
        egresoExiste.setFecha(egresoDto.getFecha());
        egresoExiste.setCliente(egresoDto.getCliente());
        egresoExiste.setListadoSalidaProducto(egresoExiste.getListadoSalidaProducto());
        egresoExiste.setFormaPago(egresoDto.getFormaPago());

        double total = 0;
        List<DetalleEgreso> listaDetalle = detalleEgresoRepository.findAllBySalidaProductoIdEgreso(egresoExiste.getIdEgreso());
        for (DetalleEgreso detalle : listaDetalle) {
            total += detalle.getPrecio();

        }

        egresoExiste.setTotal(total);
        salidaProductosService.updateEgreso(egresoExiste);

        return "redirect:/ventas/update/" + idEgreso + "?exito";

    }


    @GetMapping("/listar")
    public String listar(Model model) {

        List<SalidaProducto> listaEgresos = salidaProductosService.getAllEgresos();
        for (SalidaProducto egresos : listaEgresos) {
            if (egresos.getListadoSalidaProducto().size() < 1) {
                salidaProductosService.deleteEgreso(egresos.getIdEgreso());
            }
        }


        model.addAttribute("egresoDto", salidaProductosService.getAllEgresos());
        return "listadoEgresos";
    }


    @GetMapping("/delete/{idEgreso}")
    public String deleteVendedor(@PathVariable int idEgreso) {

        List<DetalleEgreso> detalleIngresoList = detalleEgresoRepository.findAllBySalidaProductoIdEgreso(idEgreso);
        for (DetalleEgreso detalleEgreso : detalleIngresoList) {
            Producto producto = detalleEgreso.getProducto();
            int cant = detalleEgreso.getCantidad();
            Stock stock = stockRepository.findByProductoIdProducto(producto.getIdProducto());
            stock.setCantidad(stock.getCantidad() + cant);
            stockService.update(stock);
            salidaProductosService.deleteEgreso(idEgreso);
        }
        return "redirect:/ventas/listar?exito";

    }
}

