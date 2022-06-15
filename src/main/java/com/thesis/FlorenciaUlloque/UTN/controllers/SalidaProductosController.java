package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.SoloFecha;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.EgresoDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.EgresoDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.EgresoIdTotal;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.DetalleEgresoDtoIdEgreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import com.thesis.FlorenciaUlloque.UTN.repositories.*;
import com.thesis.FlorenciaUlloque.UTN.services.DetalleEgresoService;
import com.thesis.FlorenciaUlloque.UTN.services.SalidaProductosService;
import com.thesis.FlorenciaUlloque.UTN.services.StockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/ventas")
public class SalidaProductosController {

    private final SalidaProductosService salidaProductosService;
    private final SalidaProductosRepository repository;
    private final DetalleEgresoRepository detalleEgresoRepository;
    private final StockRepository stockRepository;
    private final StockService stockService;
    private final DetalleEgresoService detalleEgresoService;

    public SalidaProductosController(SalidaProductosService salidaProductosService, SalidaProductosRepository repository,
                                     DetalleEgresoRepository detalleEgresoRepository, StockRepository stockRepository,
                                     StockService stockService, DetalleEgresoService detalleEgresoService) {
        this.salidaProductosService = salidaProductosService;
        this.repository = repository;
        this.detalleEgresoRepository = detalleEgresoRepository;
        this.stockRepository = stockRepository;
        this.stockService = stockService;
        this.detalleEgresoService = detalleEgresoService;
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
    @ModelAttribute("soloFecha")
    public SoloFecha mapearMeses() {
        return new SoloFecha();
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
        double totalRedondeado = Math.round(total*100.0/100.0);
        egreso.setTotal(totalRedondeado);
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

    @GetMapping("/update/{idEgreso}")
    public String mostrarformUpdate(@PathVariable int idEgreso, Model model) {

        //   IngresoProductos ingreso = repository.findByIdIngreso(idIngreso);
        List<Cliente> clientes = salidaProductosService.listaClientes();
        List<FormaPago> formaPagos = salidaProductosService.listaFormasPagos();

       // idEgreso = idEgresos;

        model.addAttribute("egreso", repository.findByIdEgreso(idEgreso));
        model.addAttribute("clientes", clientes);
        model.addAttribute("formaPagos", formaPagos);
        return "UpdateEgreso";
    }
    LocalDate fechaCompras;
    @GetMapping("/buscarMesPorFechaCompra")
    public String buscarMesPorFechaCompras(@ModelAttribute("soloFecha") SoloFecha soloFecha) {

        LocalDate fechaRetornada = LocalDate.parse(soloFecha.getFecha());
        fechaCompras = fechaRetornada;
        List<SalidaProducto> listaSalidas = repository.findAllByFecha(fechaCompras);

        if (listaSalidas.size() < 1) {
            return "redirect:/ventas/listarFiltrado?errorFecha";
        }

        return "redirect:/ventas/listarFiltrado";
    }

    @PostMapping("/updateEgreso/{idEgreso}")
    public String updatearVendedor(@ModelAttribute("egreso") EgresoDto egresoDto,
                                   @PathVariable int idEgreso) {

       // idEgreso = this.idEgr;
        SalidaProducto egresoExiste = repository.findByIdEgreso(idEgreso);
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


    @GetMapping("/listarFiltrado")
    public String listar(Model model) {

        List<SalidaProducto> listaEgresos = repository.findAllByFecha(fechaCompras);
        for (SalidaProducto egresos : listaEgresos) {
            if (egresos.getListadoSalidaProducto().size() < 1) {
                salidaProductosService.deleteEgreso(egresos.getIdEgreso());
            }
        }


        model.addAttribute("egresoDto", repository.findAllByFecha(fechaCompras));
        return "listadoEgresosFiltrado";
    }

    @GetMapping("/listar")
    public String findAll(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 7);
        Page<SalidaProducto> pageSalida = salidaProductosService.getAll(pageRequest);

        int totalPage = pageSalida.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("egresoDto", pageSalida.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "listadoEgresos";
    }

        @GetMapping("/delete/{idEgreso}")
    public String deleteVendedor(@PathVariable int idEgreso) {

        List<DetalleEgreso> detalleIngresoList = detalleEgresoRepository.findAllBySalidaProductoIdEgreso(idEgreso);
        for (DetalleEgreso detalleEgreso : detalleIngresoList) {
            Producto producto = detalleEgreso.getProducto();
            int cant = (int) detalleEgreso.getCantidad();
            Stock stock = stockRepository.findByProductoIdProducto(producto.getIdProducto());
            if(stock.getProducto().getFormaVenta().getIdFormaVenta() == 2){
                float cantRestante = stock.getCantidadRestante() + detalleEgreso.getCantidad();
                stock.setCantidadRestante(cantRestante);
                int cantidadBolsas = (int) Math.ceil(cantRestante/stock.getProducto().getPesoNeto());
                stock.setCantidad(cantidadBolsas);
            }else{
                stock.setCantidad(stock.getCantidad() + cant);
            }

            stockService.update(stock);
            detalleEgresoService.delete(detalleEgreso.getIdDetalleEgreso());
        }
        salidaProductosService.deleteEgreso(idEgreso);
        return "redirect:/ventas/listar?exito";

    }
}

