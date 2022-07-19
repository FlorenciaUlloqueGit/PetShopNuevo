package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.SoloFecha;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.*;
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

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        LocalDate fechaACtual = LocalDate.from(LocalDateTime.now());

        if(salidaProducto.getFecha().isAfter(fechaACtual)){
            return "redirect:/ventas?errorFecha";
        }else{
            salidaProductosService.saveEgreso(salidaProducto);
            SalidaProducto salida = salidaProductosService.saveEgreso(salidaProducto);
            idEgres = salida.getIdEgreso();
            return "redirect:/ventas/agregarDetalles";
        }
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

    @ModelAttribute("egresoDtoFinalizar")
    public EgresoDtoFinalizar mapeaDtosOtro() {
        return new EgresoDtoFinalizar();
    }

    @GetMapping("/cobrar")
    public String mostrarFormularioFinalizarCarga1(Model model) {

        SalidaProducto egreso = repository.findByIdEgreso((idEgres));

        List<FormaPago> formasPagos = salidaProductosService.listaFormasPagos();


        SalidaProducto salidaProducto = new SalidaProducto();
        model.addAttribute("formasPagos", formasPagos);
        model.addAttribute("egresoDtoFinalizar", egreso);
        return "FinalizacionVenta";
    }
    @RequestMapping(value = "/cobrarVenta", method = { RequestMethod.GET, RequestMethod.POST })
    public String updateEgresoParaCobrar2(@ModelAttribute("egresoDtoFinalizar") EgresoDtoFinalizar egresoDtoFinalizar
    ) {

        // idEgreso = this.idEgr;
        SalidaProducto egresoExiste = repository.findByIdEgreso(idEgres);
        egresoExiste.setIdEgreso(egresoExiste.getIdEgreso());
        egresoExiste.setFecha(egresoExiste.getFecha());
        egresoExiste.setCliente(egresoExiste.getCliente());
        egresoExiste.setTotal(egresoExiste.getTotal());
        egresoExiste.setListadoSalidaProducto(egresoExiste.getListadoSalidaProducto());
        egresoExiste.setFormaPago(egresoDtoFinalizar.getFormaPago());



            if( egresoExiste.getFormaPago().getIdFormaPago() == 1 || egresoExiste.getFormaPago().getIdFormaPago() == 4){
        egresoExiste = repository.findByIdEgreso(idEgres);
        egresoExiste.setIdEgreso(egresoExiste.getIdEgreso());
        egresoExiste.setFecha(egresoExiste.getFecha());
        egresoExiste.setCliente(egresoExiste.getCliente());
        egresoExiste.setTotal(egresoExiste.getTotal());
        egresoExiste.setListadoSalidaProducto(egresoExiste.getListadoSalidaProducto());
        egresoExiste.setFormaPago(egresoDtoFinalizar.getFormaPago());
        egresoExiste.setPorcentajeInteres(0);
        egresoExiste.setCantidadCuotas(0);
        salidaProductosService.updateEgreso(egresoExiste);
        return "redirect:/ventas/cobrar?exito" ;
        }
       if(egresoExiste.getFormaPago().getIdFormaPago() == 2){
           salidaProductosService.updateEgreso(egresoExiste);
            return "redirect:/ventas/finalizarVentaCredito";
        }else {
            // idEgreso = this.idEgr;
            egresoExiste = repository.findByIdEgreso(idEgres);
            egresoExiste.setIdEgreso(egresoExiste.getIdEgreso());
            egresoExiste.setFecha(egresoExiste.getFecha());
            egresoExiste.setCliente(egresoExiste.getCliente());
            egresoExiste.setTotal(egresoExiste.getTotal());
            egresoExiste.setListadoSalidaProducto(egresoExiste.getListadoSalidaProducto());
            egresoExiste.setFormaPago(egresoDtoFinalizar.getFormaPago());
            egresoExiste.setPorcentajeInteres(0);
            egresoExiste.setCantidadCuotas(0);
            salidaProductosService.updateEgreso(egresoExiste);
            return "redirect:/ventas/finalizarVentaEfectivo";
        }


    }

    @RequestMapping(value = "/ventas/cobrarVenta", method = { RequestMethod.GET, RequestMethod.POST })
    public String updateEgresoParaCobrar(@ModelAttribute("egresoDtoFinalizar") EgresoDtoFinalizar egresoDtoFinalizar
                                        ) {

        // idEgreso = this.idEgr;
        SalidaProducto egresoExiste = repository.findByIdEgreso(idEgres);
        egresoExiste.setIdEgreso(egresoExiste.getIdEgreso());
        egresoExiste.setFecha(egresoExiste.getFecha());
        egresoExiste.setCliente(egresoExiste.getCliente());
        egresoExiste.setTotal(egresoExiste.getTotal());
        egresoExiste.setListadoSalidaProducto(egresoExiste.getListadoSalidaProducto());
        egresoExiste.setFormaPago(egresoDtoFinalizar.getFormaPago());



        if( egresoExiste.getFormaPago().getIdFormaPago() == 1 || egresoExiste.getFormaPago().getIdFormaPago() == 4){
            egresoExiste = repository.findByIdEgreso(idEgres);
            egresoExiste.setIdEgreso(egresoExiste.getIdEgreso());
            egresoExiste.setFecha(egresoExiste.getFecha());
            egresoExiste.setCliente(egresoExiste.getCliente());
            egresoExiste.setTotal(egresoExiste.getTotal());
            egresoExiste.setListadoSalidaProducto(egresoExiste.getListadoSalidaProducto());
            egresoExiste.setFormaPago(egresoDtoFinalizar.getFormaPago());
            egresoExiste.setPorcentajeInteres(0);
            egresoExiste.setCantidadCuotas(0);
            salidaProductosService.updateEgreso(egresoExiste);
            return "redirect:/ventas/cobrar?exito" ;
        }
       if(egresoExiste.getFormaPago().getIdFormaPago() == 2){
           salidaProductosService.updateEgreso(egresoExiste);
            return "redirect:/ventas/finalizarVentaCredito";
        }else {
            // idEgreso = this.idEgr;
            egresoExiste = repository.findByIdEgreso(idEgres);
            egresoExiste.setIdEgreso(egresoExiste.getIdEgreso());
            egresoExiste.setFecha(egresoExiste.getFecha());
            egresoExiste.setCliente(egresoExiste.getCliente());
            egresoExiste.setTotal(egresoExiste.getTotal());
            egresoExiste.setListadoSalidaProducto(egresoExiste.getListadoSalidaProducto());
            egresoExiste.setFormaPago(egresoDtoFinalizar.getFormaPago());
            egresoExiste.setPorcentajeInteres(0);
            egresoExiste.setCantidadCuotas(0);
            salidaProductosService.updateEgreso(egresoExiste);
            return "redirect:/ventas/finalizarVentaEfectivo";
        }

    }

    @GetMapping("/finalizarVentaCredito")
    public String mostrarFormularioFinalizarCarga2(Model model) {

        SalidaProducto egreso = repository.findByIdEgreso((idEgres));

        Cuota cuotass = new Cuota();
        List<Cuota> cuotas = cuotass.crearCuotas();
        egresoDtoCreditoFin egresoDtoCreditoFin = new egresoDtoCreditoFin();
        egresoDtoCreditoFin.setTotal(egreso.getTotal());
        egresoDtoCreditoFin.setCantidadCuotas(1);
        egresoDtoCreditoFin.setPorcentaje(0);

        model.addAttribute("cuotas", cuotas);
        model.addAttribute("egresoDtoCreditoFin", egresoDtoCreditoFin);
        return "FinalizacionVentaPagarCredito" ;
    }


    @ModelAttribute("dtoDineroEntrante")
    public DtoDineroEntrante mapeaDtosOtrod() {
        return new DtoDineroEntrante();
    }
    @ModelAttribute("dtoVuelto")
    public DtoVuelto mapeaDtosOtross() {
        return new DtoVuelto();
    }
    @ModelAttribute("egresoDtoDebito")
    public EgresoDtoDebito mapeaDtosOtross3() {
        return new EgresoDtoDebito();
    }
    @ModelAttribute("dtoVentaConfirmada")
    public dtoVentaConfirmada mapeaDtosOtrossd3() {
        return new dtoVentaConfirmada();
    }
    @ModelAttribute("dtoPago")
    public dtoPago mapeaDtosOtrossd33() {
        return new dtoPago();
    }

    @ModelAttribute("egresoDtoCreditoFin")
    public egresoDtoCreditoFin mapeaDtosOtrossd332() {
        return new egresoDtoCreditoFin();
    }


    @GetMapping("/finalizarVentaEfectivo")
    public String mostrarFormularioFinalizarCarga23(Model model) {

        SalidaProducto egreso = repository.findByIdEgreso((idEgres));
        dtoPago dtoPago = new dtoPago();
        dtoPago.setTotal((float) egreso.getTotal());



        model.addAttribute("dtoPago", dtoPago);
        return "FinalizacionVentaPagarDebito" ;
    }
    @RequestMapping(value = "/confirmacionVentaCredito", method = { RequestMethod.POST })
    public String confirmarVentacRedito(@ModelAttribute("egresoDtoCreditoFin") egresoDtoCreditoFin egresoDto) {

        // idEgreso = this.idEgr;
        SalidaProducto egresoExiste = repository.findByIdEgreso(idEgres);
        egresoExiste.setIdEgreso(egresoExiste.getIdEgreso());
        egresoExiste.setFecha(egresoExiste.getFecha());
        egresoExiste.setCliente(egresoExiste.getCliente());
        egresoExiste.setListadoSalidaProducto(egresoExiste.getListadoSalidaProducto());
        egresoExiste.setFormaPago(egresoExiste.getFormaPago());
        egresoExiste.setCantidadCuotas(egresoDto.getCantidadCuotas());
        egresoExiste.setPorcentajeInteres(egresoDto.getPorcentaje());
        double totalFinal =  ( egresoExiste.getTotal() * egresoExiste.getPorcentajeInteres() /100)+ egresoExiste.getTotal();

        double roundOff = Math.round(totalFinal * 100.0) / 100.0;
        egresoExiste.setTotal(roundOff);
        salidaProductosService.updateEgreso(egresoExiste);
        if(egresoExiste.getPorcentajeInteres() < 0){
            return "redirect:/ventas/finalizarVentaCredito?errorInteres";
        } else{

            return "redirect:/ventas/finalizarVentaCredito?exito";
        }
    }
    @RequestMapping(value = "/ventas/confirmacionVentaCredito", method = { RequestMethod.GET, RequestMethod.POST })
    public String confirmarVentacReditoe(@ModelAttribute("egresoDtoCreditoFin") egresoDtoCreditoFin egresoDto) {

        // idEgreso = this.idEgr;
        SalidaProducto egresoExiste = repository.findByIdEgreso(idEgres);
        egresoExiste.setIdEgreso(egresoExiste.getIdEgreso());
        egresoExiste.setFecha(egresoExiste.getFecha());
        egresoExiste.setCliente(egresoExiste.getCliente());
        egresoExiste.setListadoSalidaProducto(egresoExiste.getListadoSalidaProducto());
        egresoExiste.setFormaPago(egresoExiste.getFormaPago());
        egresoExiste.setCantidadCuotas(egresoDto.getCantidadCuotas());
        egresoExiste.setPorcentajeInteres(egresoDto.getPorcentaje());
        double totalFinal =  ( egresoExiste.getTotal() * egresoExiste.getPorcentajeInteres() /100) + egresoExiste.getTotal();

        double roundOff = Math.round(totalFinal * 100.0) / 100.0;
        egresoExiste.setTotal(roundOff);
        salidaProductosService.updateEgreso(egresoExiste);

        if(egresoExiste.getPorcentajeInteres() < 0){
            return "redirect:/ventas/finalizarVentaCredito?errorInteres";
        } else{
            return "redirect:/ventas/finalizarVentaCredito?exito";
        }
    }


    float vuelto = 0;
    float dineroingreso;
    @RequestMapping(value = "/confirmacionVentaDebito", method = { RequestMethod.GET, RequestMethod.POST })
    public String confirmarVentaCredito(@ModelAttribute("dtoPago") dtoPago dto) {

        SalidaProducto egresoExiste = repository.findByIdEgreso(idEgres);
        egresoExiste.setIdEgreso(egresoExiste.getIdEgreso());
        egresoExiste.setFecha(egresoExiste.getFecha());
        egresoExiste.setCliente(egresoExiste.getCliente());
        egresoExiste.setListadoSalidaProducto(egresoExiste.getListadoSalidaProducto());
        egresoExiste.setFormaPago(egresoExiste.getFormaPago());
        egresoExiste.setCantidadCuotas(0);
        egresoExiste.setPorcentajeInteres(0);
        egresoExiste.setTotal(egresoExiste.getTotal());
        dineroingreso = dto.getCantidadEntrante();
        vuelto = (float) (dto.getCantidadEntrante() -egresoExiste.getTotal());

        if(dto.getCantidadEntrante() < egresoExiste.getTotal()){
            return "redirect:/ventas/finalizarVentaEfectivo?errorMonto";
        } else{
            salidaProductosService.updateEgreso(egresoExiste);
            return "redirect:/ventas/mostrarVuelto?exito";
        }
    }
    float vuelto2 = 0;
    float dineroEntrante2 = 0;
    @RequestMapping(value = "/ventas/confirmacionVentaDebito", method = {  RequestMethod.POST })
    public String confirmarVentaCredito2(@ModelAttribute("dtoPago") dtoPago dto) {

        SalidaProducto egresoExiste = repository.findByIdEgreso(idEgres);
        egresoExiste.setIdEgreso(egresoExiste.getIdEgreso());
        egresoExiste.setFecha(egresoExiste.getFecha());
        egresoExiste.setCliente(egresoExiste.getCliente());
        egresoExiste.setListadoSalidaProducto(egresoExiste.getListadoSalidaProducto());
        egresoExiste.setFormaPago(egresoExiste.getFormaPago());
        egresoExiste.setCantidadCuotas(0);
        egresoExiste.setPorcentajeInteres(0);
        egresoExiste.setTotal(egresoExiste.getTotal());
        dineroEntrante2 = dto.getCantidadEntrante();
        vuelto2 = (float) (dto.getCantidadEntrante() -egresoExiste.getTotal());

        if(dto.getCantidadEntrante() < egresoExiste.getTotal()){
            return "redirect:/ventas/finalizarVentaEfectivo?errorMonto";
        } else{
            salidaProductosService.updateEgreso(egresoExiste);
            return "redirect:/ventas/mostrarVuelto?exito";
        }
    }
    @GetMapping("/mostrarVuelto")
    public String mostrarFormularioFinalizarCarga2e3(Model model) {
        SalidaProducto egreso = repository.findByIdEgreso((idEgres));
        dtoVentaConfirmada dto = new dtoVentaConfirmada();
        dto.setTotal((float) egreso.getTotal());
        dto.setCantidadEntrante(dineroEntrante2);
        dto.setVuelto(vuelto2);


        model.addAttribute("dtoVentaConfirmada", dto);


        return "FinalizacionVentaPagarDebito2" ;
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
       List<SalidaProducto> lista = repository.findAllByOrderByFechaDesc();
        for (SalidaProducto list:  lista) {
            if(list.getTotal() == 0) {
                int id = list.getIdEgreso();
                salidaProductosService.deleteEgreso(id);
            }
            }
        Page<SalidaProducto> pageSalida = salidaProductosService.getAll(pageRequest);
        int totalPage = pageSalida.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("page", pages);
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

