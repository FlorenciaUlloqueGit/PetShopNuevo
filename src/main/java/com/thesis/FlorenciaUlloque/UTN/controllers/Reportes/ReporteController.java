package com.thesis.FlorenciaUlloque.UTN.controllers.Reportes;

import com.lowagie.text.DocumentException;
import com.thesis.FlorenciaUlloque.UTN.Dtos.SoloFecha;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.*;
import com.thesis.FlorenciaUlloque.UTN.Util.*;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProductoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.SalidaProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.StockRepository;
import com.thesis.FlorenciaUlloque.UTN.services.IngresoProductosService;
import com.thesis.FlorenciaUlloque.UTN.services.ProductoService;
import com.thesis.FlorenciaUlloque.UTN.services.SalidaProductosService;
import com.thesis.FlorenciaUlloque.UTN.services.StockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/reportes")
public class ReporteController {
    private final StockRepository stockRepository;
    private final ProductoRepository productoRepository;
    private final SalidaProductosRepository salidaProductosRepository;
    private final IngresoProductosRepository ingresoProductosRepository;
    private final SalidaProductosService salidaProductosService;
    private final IngresoProductosService ingresoProductosService;
    private final ProductoService productoService;
    private final StockService stockService;

    public ReporteController(StockRepository stockRepository, ProductoRepository productoRepository, SalidaProductosRepository salidaProductosRepository, IngresoProductosRepository ingresoProductosRepository, SalidaProductosService salidaProductosService, IngresoProductosService ingresoProductosService, ProductoService productoService, StockService stockService) {
        this.stockRepository = stockRepository;
        this.productoRepository = productoRepository;
        this.salidaProductosRepository = salidaProductosRepository;
        this.ingresoProductosRepository = ingresoProductosRepository;
        this.salidaProductosService = salidaProductosService;
        this.ingresoProductosService = ingresoProductosService;
        this.productoService = productoService;
        this.stockService = stockService;
    }

    //  ---------listado de faltantes
    @ModelAttribute("producto")
    public Producto mapear() {
        return new Producto();
    }

    @ModelAttribute("soloFecha")
    public SoloFecha mapearMeses() {
        return new SoloFecha();
    }

    @ModelAttribute("ingresoProducto")
    public IngresoProductos mapearingreso() {
        return new IngresoProductos();
    }

    @ModelAttribute("productoReporteBolsaCerrada")
    public ProductoReporteBolsaCerrada mapearprodRep() {
        return new ProductoReporteBolsaCerrada();
    }

    @ModelAttribute("productosVencidos")
    public Producto mapearVencidos() {
        return new Producto();
    }

    @ModelAttribute("productoPorVencer")
    public Producto mapearPorVencer() {
        return new Producto();
    }

    @ModelAttribute("productoReporte2")
    public ProductoReporte2 mapearProductoReporte2() {
        return new ProductoReporte2();
    }

    @ModelAttribute("salidaProducto")
    public SalidaProducto mapearVentas() {
        return new SalidaProducto();
    }

    @ModelAttribute("productoFaltantes")
    public ProductoFaltantes mapearProductoFaltante() {
        return new ProductoFaltantes();
    }

    @GetMapping("/listadoReportes")
    public String mostrarFormularioListadoReportes(Model model) {

        return "listadoReportes";
    }

    @GetMapping("/faltantes")
    public String mostrarFormularioListadoFaltantes(Model model) {

        List<Producto> productos = productoRepository.findAllProductosByStockCERO();
        List<ProductoFaltantes> productoFaltantesList = new ArrayList<>();
        for (Producto producto : productos) {
            ProductoFaltantes productoFaltantes = new ProductoFaltantes();
            productoFaltantes.setIdProducto(producto.getIdProducto());
            productoFaltantes.setCodBarras(producto.getCodBarras());
            productoFaltantes.setNombre(producto.getNombre());
            productoFaltantes.setCategoria(producto.getCategoria());
            productoFaltantes.setFormaVenta(producto.getFormaVenta());
            productoFaltantes.setMarca(producto.getMarca());
            productoFaltantes.setPesoNeto(producto.getPesoNeto());
            productoFaltantes.setUnidadMedida(producto.getUnidadMedida());
            productoFaltantesList.add(productoFaltantes);
        }

        model.addAttribute("productoFaltantes", productoFaltantesList);
        return "ReporteListadoFaltantes";
    }


    @GetMapping("/faltantesProductos")
    public String mostrarFormularioListadoFaltantes2(Model model) {

        List<Producto> productos = productoRepository.findAllProductosByStockCERO();
        List<ProductoFaltantes> productoFaltantesList = new ArrayList<>();
        for (Producto producto : productos) {
            ProductoFaltantes productoFaltantes = new ProductoFaltantes();
            productoFaltantes.setIdProducto(producto.getIdProducto());
            productoFaltantes.setCodBarras(producto.getCodBarras());
            productoFaltantes.setNombre(producto.getNombre());
            productoFaltantes.setCategoria(producto.getCategoria());
            productoFaltantes.setFormaVenta(producto.getFormaVenta());
            productoFaltantes.setMarca(producto.getMarca());
            productoFaltantes.setPesoNeto(producto.getPesoNeto());
            productoFaltantes.setUnidadMedida(producto.getUnidadMedida());
            productoFaltantesList.add(productoFaltantes);
        }

        model.addAttribute("productoFaltantes", productoFaltantesList);
        return "ReporteListadoFaltantesVendedor";
    }

    LocalDate fechaDiaria;

    @GetMapping("/buscarDia")
    public String buscarDia(@ModelAttribute("soloFecha") SoloFecha soloFecha) {

        fechaDiaria = LocalDate.parse(soloFecha.getFecha());
        List<SalidaProducto> listaSalida = salidaProductosRepository.findAllByFecha(fechaDiaria);

        if (listaSalida.size() < 1) {
            return "redirect:/reportes/productosDiarios?errorFecha";
        }

        return "redirect:/reportes/productosDiarios";
    }


    @GetMapping("/productosDiarios")
    public String mostrarFormularioProductosVendidosHoy(@RequestParam Map<String, Object> params, Model model){
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) :0;
        PageRequest pageRequest = PageRequest.of(page, 7);
    //    Page<Stock> pageStock = stockService.getAll(pageRequest);

        ProductoReporte2 productoReporte2;
        List<ProductoReporte2> reporte2List = new ArrayList<>();
        List<String> listado = productoRepository.findAllProductosVendidosHoy(fechaDiaria);

        List<ArrayList> listadoFormateado = new ArrayList<>();

        for (int i = 0; i < listado.size(); i++) {
            String cadenaLarga = listado.get(i);
            cadenaLarga = cadenaLarga + ",";
            ArrayList list = new ArrayList();
            int posicionCadena = 1;

            for (int j = 0; j < 6; j++) {
                posicionCadena = cadenaLarga.indexOf(',');
                String dato = cadenaLarga.substring(0, posicionCadena);
                list.add(dato);

                cadenaLarga = cadenaLarga.substring(posicionCadena + 1, cadenaLarga.length());

            }
            listadoFormateado.add(list);
        }

        double acumTotales = 0;
        for (int i = 0; i < listadoFormateado.size(); i++) {
            productoReporte2 = new ProductoReporte2();
            for (int j = 0; j < 6; j++) {
                ArrayList array = listadoFormateado.get(i);
                if (j == 0) {
                    productoReporte2.setNombre((String) array.get(j));
                }
                if (j == 1) {
                    productoReporte2.setCategoria((String) array.get(j));
                }
                if (j == 2) {
                    productoReporte2.setFormaVenta((String) array.get(j));
                }

                if (j == 3) {
                    String precioVenta = (String) array.get(j);

                    productoReporte2.setPrecioVenta(Float.parseFloat(precioVenta));
                }
                if (j == 4) {
                    String cant = (String) array.get(j);
                    productoReporte2.setCantidad(Float.parseFloat(cant));
                }

                if (j == 5) {
                    String total = (String) array.get(j);
                    productoReporte2.setTotal(Float.parseFloat(total));
                    double totalNuevo = Double.parseDouble(String.valueOf(productoReporte2.getTotal()));
                    acumTotales += totalNuevo;
                }

            }
            reporte2List.add(productoReporte2);
        }


        Page<ProductoReporte2> pageList = new PageImpl<>(reporte2List);

        int totalPage = pageList.getTotalPages();
        if(totalPage> 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages",pages);
        }
        model.addAttribute("productoReporte2", pageList.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

       // model.addAttribute("productoReporte2", reporte2List);
        model.addAttribute("acumTotales", acumTotales);
        return "ReporteListadoVentasHoy";
    }


    @GetMapping("/productosVendidosDiarios")
    public String mostrarFormularioProductosVendidosHoy2(@RequestParam Map<String, Object> params, Model model){
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) :0;
        PageRequest pageRequest = PageRequest.of(page, 7);
        ProductoReporte2 productoReporte2;
        List<ProductoReporte2> reporte2List = new ArrayList<>();
        List<String> listado = productoRepository.findAllProductosVendidosHoy();

        List<ArrayList> listadoFormateado = new ArrayList<>();

        for (int i = 0; i < listado.size(); i++) {
            String cadenaLarga = listado.get(i);
            cadenaLarga = cadenaLarga + ",";
            ArrayList list = new ArrayList();
            int posicionCadena = 1;

            for (int j = 0; j < 6; j++) {
                posicionCadena = cadenaLarga.indexOf(',');
                String dato = cadenaLarga.substring(0, posicionCadena);
                list.add(dato);

                cadenaLarga = cadenaLarga.substring(posicionCadena + 1, cadenaLarga.length());

            }
            listadoFormateado.add(list);
        }

        double acumTotales = 0;
        for (int i = 0; i < listadoFormateado.size(); i++) {
            productoReporte2 = new ProductoReporte2();
            for (int j = 0; j < 6; j++) {
                ArrayList array = listadoFormateado.get(i);
                if (j == 0) {
                    productoReporte2.setNombre((String) array.get(j));
                }
                if (j == 1) {
                    productoReporte2.setCategoria((String) array.get(j));
                }
                if (j == 2) {
                    productoReporte2.setFormaVenta((String) array.get(j));
                }

                if (j == 3) {
                    String precioVenta = (String) array.get(j);

                    productoReporte2.setPrecioVenta(Float.parseFloat(precioVenta));
                }
                if (j == 4) {
                    String cant = (String) array.get(j);
                    productoReporte2.setCantidad(Float.parseFloat(cant));
                }

                if (j == 5) {
                    String total = (String) array.get(j);
                    productoReporte2.setTotal(Float.parseFloat(total));
                    double totalNuevo = Double.parseDouble(String.valueOf(productoReporte2.getTotal()));
                    acumTotales += totalNuevo;
                }

            }
            reporte2List.add(productoReporte2);
        }

        Page<ProductoReporte2> pageList = new PageImpl<>(reporte2List);

        int totalPage = pageList.getTotalPages();
        if(totalPage> 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages",pages);
        }
        model.addAttribute("productoReporte2", pageList.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);


      //  model.addAttribute("productoReporte2", reporte2List);
        model.addAttribute("acumTotales", acumTotales);
        return "ReporteListadoVentasHoyVendedor";
    }

    public LocalDate obtenerFechaDiaria(){
        return fechaDiaria;
    }

    @GetMapping("/homeAdmin")
    public String mostrarHomeAdmin() {
        return "homeAdmin";
    }

    @GetMapping("/vencimientos")
    public String mostrarFormularioProductosPorVencer(Model model) {

        List<Producto> productosPorVencer = productoRepository.findProductosPorVencer();
        List<Producto> productosVencidos = productoRepository.findProductosVencidos();

        model.addAttribute("productoPorVencer", productosPorVencer);
        model.addAttribute("productosVencidos", productosVencidos);
        return "ReporteListadoVencimientos";

    }

    @GetMapping("/ListadoVencimientos")
    public String mostrarFormularioProductosPorVencer2(Model model) {

        List<Producto> productosPorVencer = productoRepository.findProductosPorVencer();
        List<Producto> productosVencidos = productoRepository.findProductosVencidos();

        model.addAttribute("productoPorVencer", productosPorVencer);
        model.addAttribute("productosVencidos", productosVencidos);
        return "ReporteListadoVencimientosVendedor";

    }

    LocalDate fecha;

    @GetMapping("/buscarMesPorFecha")
    public String buscarMesPorFecha(@ModelAttribute("soloFecha") SoloFecha soloFecha) {

        LocalDate fechaRetornada = LocalDate.parse(soloFecha.getFecha());
        fecha = fechaRetornada;
        List<SalidaProducto> listaSalida = salidaProductosRepository.findEgresoByMonth(soloFecha.getFecha());

        if (listaSalida.size() < 1) {
            return "redirect:/reportes/ingresosPorVentas?errorFecha";
        }

        return "redirect:/reportes/ingresosPorVentas";
    }

    @GetMapping("/ingresosPorVentas")
    public String mostrarFormularioVentasMensuales(@RequestParam Map<String, Object> params, Model model) {

        List<SalidaProducto> salidaProductos = salidaProductosRepository.findEgresoByMonth(String.valueOf(fecha));

        String Stringfecha = String.valueOf(fecha);
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 7);
        Page<SalidaProducto> pageVenta = salidaProductosService.getAllReporte(Stringfecha, pageRequest);


        int totalPage = pageVenta.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        float totalVentas;
        if (salidaProductos.size() > 0) {
            totalVentas = salidaProductosRepository.findTotalSumaEgresosByMonth(Stringfecha);
        } else {

            totalVentas = 0;
        }
        float totalVentasRedondeado = Math.round((totalVentas * 100.0) / 100.0);

        model.addAttribute("totalVentasRedondeado", totalVentasRedondeado);
        model.addAttribute("salidaProducto", pageVenta.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return "ReporteMensualVentas";

    }

    public LocalDate fechaVentas() {
        return fecha;
    }

    @GetMapping("/ventasPorBolsa")
    public String mostrarVentasBolsaCerrada(@RequestParam Map<String, Object> params, Model model) {

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 7);


        int i = 0;
        List<Producto> productosBolsaCerrada = productoRepository.findProductosVendidosBolsa();
        List<ProductoReporteBolsaCerrada> list = new ArrayList<>();

        for (Producto producto : productosBolsaCerrada) {
            ProductoReporteBolsaCerrada bolsaCerrada = new ProductoReporteBolsaCerrada();
            bolsaCerrada.setIdProducto(producto.getIdProducto());
            bolsaCerrada.setNombre(producto.getNombre());
            bolsaCerrada.setCategoria(producto.getCategoria());
            bolsaCerrada.setUnidadMedida(producto.getUnidadMedida());
            bolsaCerrada.setPesoNeto((int) producto.getPesoNeto());
            bolsaCerrada.setMarca(producto.getMarca());
            float cantidad = productoRepository.findCantidadProductoVendido().get(i);
            i++;
            bolsaCerrada.setCantidad(cantidad);
            list.add(bolsaCerrada);

        }
        Page<ProductoReporteBolsaCerrada> pageList = new PageImpl<>(list);

        int totalPage = pageList.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }


        model.addAttribute("productoReporteBolsaCerrada", pageList.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);
        return "ReporteBolsasMensualesVendidas";

    }


    @GetMapping("/ventasPorKg")
    public String mostrarVentasKg(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 7);


        int i = 0;
        List<Producto> productoVentaKg = productoRepository.findProductosVendidosKG();
        List<ProductoReporteBolsaCerrada> list = new ArrayList<>();
        for (Producto producto : productoVentaKg) {
            ProductoReporteBolsaCerrada ventaKg = new ProductoReporteBolsaCerrada();
            ventaKg.setIdProducto(producto.getIdProducto());
            ventaKg.setNombre(producto.getNombre());
            ventaKg.setCategoria(producto.getCategoria());
            ventaKg.setUnidadMedida(producto.getUnidadMedida());
            ventaKg.setPesoNeto((int) producto.getPesoNeto());
            ventaKg.setMarca(producto.getMarca());
            float cantidad;
            float cantProdVendido = productoRepository.findCantidadProductoVendidoXKg(producto.getIdProducto());
            cantidad = cantProdVendido;
            /*
            int cantProdVendido = productoRepository.findCantidadProductoVendidoXKg().size();
            float cantidad;
            if(list.size() < cantProdVendido){
                cantidad = productoRepository.findCantidadProductoVendidoXKg().get(i);
            }else{
             cantidad = 1;
            }

             */


            DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
            separadoresPersonalizados.setDecimalSeparator('.');
            DecimalFormat formato = new DecimalFormat("#.00", separadoresPersonalizados);
            String cantFormat = formato.format(cantidad);
            float cantFormateadaFinal = Float.parseFloat(cantFormat);


            i++;
            ventaKg.setCantidad(cantFormateadaFinal);
            list.add(ventaKg);

        }
        Page<ProductoReporteBolsaCerrada> pageList = new PageImpl<>(list);

        int totalPage = pageList.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("productoReporteBolsaCerrada", pageList.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "ReporteKgMensualesVendidas";

    }

    LocalDate fechaCompras;

    @GetMapping("/buscarMesPorFechaCompra")
    public String buscarMesPorFechaCompras(@ModelAttribute("soloFecha") SoloFecha soloFecha) {

        LocalDate fechaRetornada = LocalDate.parse(soloFecha.getFecha());
        fechaCompras = fechaRetornada;
        List<IngresoProductos> listaIngresos = ingresoProductosRepository.findIngresosbyMoth(soloFecha.getFecha());

        if (listaIngresos.size() < 1) {
            return "redirect:/reportes/GastosMensualesProveedores?errorFecha";
        }

        return "redirect:/reportes/GastosMensualesProveedores";
    }

    @GetMapping("/GastosMensualesProveedores")
    public String mostrarGastosProveedores(@RequestParam Map<String, Object> params, Model model) {

        int i = 0;

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<IngresoProductos> pageIngreso = ingresoProductosService.getAllReporte(String.valueOf(fechaCompras), pageRequest);
        List<IngresoProductos> ingresoProductos = ingresoProductosRepository.findIngresosbyMoth(String.valueOf(fechaCompras));
        int totalPage = pageIngreso.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("ingresoProducto", pageIngreso.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        List<IngresoProductos> list = new ArrayList<>();
        for (IngresoProductos ingreso : ingresoProductos) {
            IngresoProductos ingresoAGuardar = new IngresoProductos();

            ingresoAGuardar.setIdIngreso(ingreso.getIdIngreso());
            ingresoAGuardar.setProveedor(ingreso.getProveedor());
            LocalDate fecha = ingreso.getFecha();
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String text = fecha.format(formatter);
            final LocalDate parseDate = LocalDate.parse(text, formatter);
            System.out.println(parseDate);
            ingresoAGuardar.setFecha(parseDate);
            ingresoAGuardar.setFormaPago(ingreso.getFormaPago());
            ingresoAGuardar.setListadoDetalleIngresos(ingreso.getListadoDetalleIngresos());
            ingresoAGuardar.setTotal(ingreso.getTotal());
            list.add(ingresoAGuardar);
        }

        //   model.addAttribute("ingresoProducto", list);

        return "ReportePagosMensualesProveedores";
    }

    public LocalDate obtenerFecha() {
        return fechaCompras;
    }


    @GetMapping("/listarFaltantes/export")
    public void exportToPDFFaltantes(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.pdf";

        response.setHeader(headerKey, headerValue);


        List<Producto> listaProductos = productoRepository.findAllProductosByStockCERO();

        UsersPDfExporterStockCero exporter = new UsersPDfExporterStockCero(listaProductos);
        exporter.export(response);


    }

    @GetMapping("/listadoProductosVencidos/export")
    public void exportToPDFProductosVencidos(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.pdf";

        response.setHeader(headerKey, headerValue);

        List<Producto> listaProductos = productoRepository.findProductosVencidos();


        UsersPDfProductosVencidos exporter = new UsersPDfProductosVencidos(listaProductos);
        exporter.export(response);
    }

    @GetMapping("/listadoProductosPorVencer/export")
    public void exportToPDFProductosXVencer(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.pdf";

        response.setHeader(headerKey, headerValue);

        List<Producto> listaProductos = productoRepository.findProductosPorVencer();


        UsersPDfProductosPorVencer exporter = new UsersPDfProductosPorVencer(listaProductos);
        exporter.export(response);
    }

    @GetMapping("/ventasDelDia/export")
    public void exportToPDFVentasHoy(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.pdf";

        response.setHeader(headerKey, headerValue);

        List<ProductoReporte2> reporte2List = new ArrayList<>();
        List<String> listado = productoRepository.findAllProductosVendidosHoy(fechaDiaria);

        List<ArrayList> listadoFormateado = new ArrayList<>();

        for (int i = 0; i < listado.size(); i++) {
            String cadenaLarga = listado.get(i);
            cadenaLarga = cadenaLarga + ",";
            ArrayList list = new ArrayList();
            int posicionCadena = 1;

            for (int j = 0; j < 6; j++) {
                posicionCadena = cadenaLarga.indexOf(',');
                String dato = cadenaLarga.substring(0, posicionCadena);
                list.add(dato);

                cadenaLarga = cadenaLarga.substring(posicionCadena + 1, cadenaLarga.length());

            }
            listadoFormateado.add(list);
        }

        double acumTotales = 0;
        for (int i = 0; i < listadoFormateado.size(); i++) {
            ProductoReporte2 productoReporte2 = new ProductoReporte2();
            for (int j = 0; j < 6; j++) {
                ArrayList array = listadoFormateado.get(i);
                if (j == 0) {
                    productoReporte2.setNombre((String) array.get(j));
                }
                if (j == 1) {
                    productoReporte2.setCategoria((String) array.get(j));
                }
                if (j == 2) {
                    productoReporte2.setFormaVenta((String) array.get(j));
                }

                if (j == 3) {
                    String precioVenta = (String) array.get(j);

                    productoReporte2.setPrecioVenta(Float.parseFloat(precioVenta));
                }
                if (j == 4) {
                    String cant = (String) array.get(j);
                    productoReporte2.setCantidad(Float.parseFloat(cant));
                }

                if (j == 5) {
                    String total = (String) array.get(j);
                    productoReporte2.setTotal(Float.parseFloat(total));
                    double totalNuevo = Double.parseDouble(String.valueOf(productoReporte2.getTotal()));
                    acumTotales += totalNuevo;
                }

            }
            reporte2List.add(productoReporte2);
        }


        ReporteVentasHoyPDF exporter = new ReporteVentasHoyPDF(reporte2List, fechaDiaria);
        exporter.export(response);
    }

    @GetMapping("/ventasBolsas/export")
    public void exportToPDFProductosBolsas(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");


        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.pdf";

        response.setHeader(headerKey, headerValue);

        int i = 0;
        List<Producto> productosBolsaCerrada = productoRepository.findProductosVendidosBolsa();
        List<ProductoReporteBolsaCerrada> listaProductos = new ArrayList<>();
        for (Producto producto : productosBolsaCerrada) {
            ProductoReporteBolsaCerrada bolsaCerrada = new ProductoReporteBolsaCerrada();
            bolsaCerrada.setIdProducto(producto.getIdProducto());
            bolsaCerrada.setNombre(producto.getNombre());
            bolsaCerrada.setCategoria(producto.getCategoria());
            bolsaCerrada.setUnidadMedida(producto.getUnidadMedida());
            bolsaCerrada.setPesoNeto((int) producto.getPesoNeto());
            bolsaCerrada.setMarca(producto.getMarca());
            float cantidad = productoRepository.findCantidadProductoVendido().get(i);
            i++;
            bolsaCerrada.setCantidad(cantidad);
            listaProductos.add(bolsaCerrada);

        }


        ReporteVentasBolsasPDF exporter = new ReporteVentasBolsasPDF(listaProductos);
        exporter.export(response);
    }

    @GetMapping("/ventasKgs/export")
    public void exportToPDFProductoKGS(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.pdf";

        response.setHeader(headerKey, headerValue);
        int i = 0;
        List<Producto> productoVentaKg = productoRepository.findProductosVendidosKG();
        List<ProductoReporteBolsaCerrada> listaProductos = new ArrayList<>();
        for (Producto producto : productoVentaKg) {
            ProductoReporteBolsaCerrada ventaKg = new ProductoReporteBolsaCerrada();
            ventaKg.setIdProducto(producto.getIdProducto());
            ventaKg.setNombre(producto.getNombre());
            ventaKg.setCategoria(producto.getCategoria());
            ventaKg.setUnidadMedida(producto.getUnidadMedida());
            ventaKg.setPesoNeto((int) producto.getPesoNeto());
            ventaKg.setMarca(producto.getMarca());
            float cantidad = productoRepository.findCantidadProductoVendidoXKg(producto.getIdProducto());
            float cantidadFormateada = Math.round(cantidad * 100.0 / 100.0);

            DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
            separadoresPersonalizados.setDecimalSeparator('.');
            DecimalFormat formato = new DecimalFormat("#.00", separadoresPersonalizados);
            String cantFormat = formato.format(cantidad);
            float cantFormateadaFinal = Float.parseFloat(cantFormat);


            i++;
            ventaKg.setCantidad(cantFormateadaFinal);
            listaProductos.add(ventaKg);

        }

        ReporteVentasKgsPDF exporter = new ReporteVentasKgsPDF(listaProductos);
        exporter.export(response);
    }

    @GetMapping("/ingresosMensuales/export")
    public String exportToPDFEgresosProveedores(HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.pdf";

        response.setHeader(headerKey, headerValue);

        int i = 0;
    /*    if(fechaCompras == null){
            return "redirect:/reportes/ingresosPorVentas?errorFechaSeleccion";
        }

     */
        List<IngresoProductos> ingresoProductos = ingresoProductosRepository.findIngresosbyMoth(String.valueOf(fechaCompras));
        List<IngresoProductos> listadoProductos = new ArrayList<>();
        for (IngresoProductos ingreso : ingresoProductos) {
            IngresoProductos ingresoAGuardar = new IngresoProductos();

            ingresoAGuardar.setIdIngreso(ingreso.getIdIngreso());
            ingresoAGuardar.setProveedor(ingreso.getProveedor());
            LocalDate fecha = ingreso.getFecha();
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String text = fecha.format(formatter);
            final LocalDate parseDate = LocalDate.parse(text, formatter);
            System.out.println(parseDate);
            ingresoAGuardar.setFecha(parseDate);
            ingresoAGuardar.setFormaPago(ingreso.getFormaPago());
            ingresoAGuardar.setListadoDetalleIngresos(ingreso.getListadoDetalleIngresos());
            ingresoAGuardar.setTotal(ingreso.getTotal());
            listadoProductos.add(ingresoAGuardar);
        }


        ReporteIngresosPDF exporter = new ReporteIngresosPDF(listadoProductos, fechaCompras);
        exporter.export(response);
        return currentDateTime;
    }

    @GetMapping("/ventasMensuales/export")
    public void exportToPDFVentas(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.pdf";

        response.setHeader(headerKey, headerValue);

        List<SalidaProducto> salidaProductos = salidaProductosRepository.findEgresoByMonth(String.valueOf(fecha));
        float totalVentas;
        if (salidaProductos.size() > 0) {
            totalVentas = salidaProductosRepository.findTotalSumaEgresosByMonth(String.valueOf(fecha));
        } else {
            totalVentas = 0;
        }

        float totalVentasRedondeado = Float.valueOf(Math.round((totalVentas * 100.0) / 100));


        ReporteVentasPDF exporter = new ReporteVentasPDF(salidaProductos, totalVentas);
        exporter.export(response);
    }

    @ModelAttribute("productoSoloNombre")
    public ProductoSoloNombre mapeaDtoSoloNombre() {
        return new ProductoSoloNombre();
    }

    @ModelAttribute("stock")
    public Stock mapearStock() {
        return new Stock();
    }


    @GetMapping("/corroborarStock")
    public String mostrarFormu(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) :0;
        PageRequest pageRequest = PageRequest.of(page, 7);
        Page<Stock> pageStock = stockService.getAll(pageRequest);

        int totalPage = pageStock.getTotalPages();
        if(totalPage> 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages",pages);
        }
        model.addAttribute("stock", pageStock.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "listadoStockReporte";
    }

    @ModelAttribute("productoSoloCodBarras")
    public ProductoSoloCodBarras mapeaProdStockSoloCod() {
        return new ProductoSoloCodBarras();
    }

    String nombre;
    long codBarras;

    @GetMapping("/buscarNombre")
    public String buscarNombre(@ModelAttribute("productoSoloNombre") ProductoSoloNombre productoSoloNombre) {

        nombre = productoSoloNombre.getNombre();
        List<Stock> listaProductos = stockRepository.findStockByProductoNombre(nombre);

        if (listaProductos.size() < 1) {
            return "redirect:/reportes/reporteStock?errorNombre";
        }

        return "redirect:/reportes/reporteStock";
    }

    @GetMapping("/buscarCodBarras")
    public String buscarCodBarras(@ModelAttribute("productoSoloCodBarras") ProductoSoloCodBarras productoSoloCodBarras) {

        codBarras = productoSoloCodBarras.getCodBarras();

        if (codBarras == 0) {
            return "redirect:/reportes/reporteStock?errorCodigo";
        }
        return "redirect:/reportes/reporteStock";
    }

    @GetMapping("/reporteStock")
    public String mostrarFormularioConID(Model model) {


        List<Stock> listaStock = stockRepository.findStockByProductoNombre(nombre);

        if (codBarras != 0) {
            Stock listaStock2 = stockRepository.findByProductoCodBarras(codBarras);
            model.addAttribute("stock", listaStock2);
            return "reporteStock";
        }


        model.addAttribute("stock", listaStock);
        return "reporteStock";
    }

    @GetMapping("/corroborarStocks")
    public String mostrarFormularioStok(@RequestParam Map<String, Object> params, Model model){
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) :0;
        PageRequest pageRequest = PageRequest.of(page, 7);
        Page<Stock> pageStock = stockService.getAll(pageRequest);

        int totalPage = pageStock.getTotalPages();
        if(totalPage> 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages",pages);
        }
        model.addAttribute("stock", pageStock.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "listadoStockReporteVendedor";
    }

    @ModelAttribute("buscarProductoSoloCodBarras")
    public ProductoSoloCodBarras mapeaProdStockSoloCodVendedor() {
        return new ProductoSoloCodBarras();
    }

    String nombreProdVendedor;
    long codBarrasProdVendedor;

    @GetMapping("/buscarNombreProducto")
    public String buscarNombreVendedor(@ModelAttribute("productoSoloNombre") ProductoSoloNombre productoSoloNombre) {

        nombreProdVendedor = productoSoloNombre.getNombre();
        List<Stock> listaProductos = stockRepository.findStockByProductoNombre(nombreProdVendedor);

        if (listaProductos.size() < 1) {
            return "redirect:/reportes/reporteStocks?errorNombre";
        }

        return "redirect:/reportes/reporteStocks";
    }

    @GetMapping("/buscarCodBarrasProducto")
    public String buscarCodBarrasVendedor(@ModelAttribute("productoSoloCodBarras") ProductoSoloCodBarras productoSoloCodBarras) {

        codBarrasProdVendedor = productoSoloCodBarras.getCodBarras();

        if (codBarrasProdVendedor == 0) {
            return "redirect:/reportes/reporteStocks?errorCodigo";
        }
        return "redirect:/reportes/reporteStocks";
    }

    @GetMapping("/reporteStocks")
    public String mostrarFormularioConIDVendedor(Model model) {


        List<Stock> listaStock = stockRepository.findStockByProductoNombre(nombreProdVendedor);

        if (codBarrasProdVendedor != 0) {
            Stock listaStock2 = stockRepository.findByProductoCodBarras(codBarrasProdVendedor);
            model.addAttribute("stock", listaStock2);
            return "reporteStockVendedor";
        }


        model.addAttribute("stock", listaStock);
        return "reporteStockVendedor";
    }

    @GetMapping("/corroborarStock/export")
    public void exportToPDFStock(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.pdf";

        response.setHeader(headerKey, headerValue);


        List<Stock> listaStocks = stockRepository.findAllByOrderByProductoNombreAsc();

        ReporteStockPDF exporter = new ReporteStockPDF(listaStocks);
        exporter.export(response);


    }

}