package com.thesis.FlorenciaUlloque.UTN.controllers.Reportes;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoFaltantes;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoReporte2;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoReporteBolsaCerrada;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProductoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.SalidaProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.StockRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/reportes")
public class ReporteController {
    private final StockRepository stockRepository;
    private final ProductoRepository productoRepository;
    private final SalidaProductosRepository salidaProductosRepository;
    private final IngresoProductosRepository ingresoProductosRepository;

    public ReporteController(StockRepository stockRepository, ProductoRepository productoRepository, SalidaProductosRepository salidaProductosRepository, IngresoProductosRepository ingresoProductosRepository) {
        this.stockRepository = stockRepository;
        this.productoRepository = productoRepository;
        this.salidaProductosRepository = salidaProductosRepository;
        this.ingresoProductosRepository = ingresoProductosRepository;
    }

    //  ---------listado de faltantes
    @ModelAttribute("producto")
    public Producto mapear() {
        return new Producto();
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
        if (productoFaltantesList.size() < 1) {
            return "redirect:/reportes/faltantes?error";
        } else {
            model.addAttribute("productoFaltantes", productoFaltantesList);
            return "ReporteListadoFaltantes";
        }

    }

    @GetMapping("/productosDiarios")
    public String mostrarFormularioProductosVendidosHoy(Model model) {
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
                    acumTotales += productoReporte2.getTotal();
                }

            }
            reporte2List.add(productoReporte2);
        }

        model.addAttribute("productoReporte2", reporte2List);
        model.addAttribute("acumTotales", acumTotales);
        return "ReporteListadoVentasHoy";
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

    @GetMapping("/ingresosPorVentas")
    public String mostrarFormularioVentasMensuales(Model model) {

        List<SalidaProducto> salidaProductos = salidaProductosRepository.findEgresoByMonth();
        float totalVentas = salidaProductosRepository.findTotalSumaEgresosByMonth();
        float totalVentasRedondeado = Math.round((totalVentas*100.0)/100.0);


        model.addAttribute("salidaProducto", salidaProductos);
        model.addAttribute("totalVentasRedondeado", totalVentasRedondeado);
        return "ReporteMensualVentas";

    }
    @GetMapping("/ventasPorBolsa")
    public String mostrarVentasBolsaCerrada(Model model) {

        int i = 0;
        List<Producto> productosBolsaCerrada = productoRepository.findProductosVendidosBolsa();
        List<ProductoReporteBolsaCerrada> list = new ArrayList<>();
        for (Producto producto: productosBolsaCerrada ) {
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

        model.addAttribute("productoReporteBolsaCerrada", list);
       // model.addAttribute("totalVentasRedondeado", totalVentasRedondeado);
        return "ReporteBolsasMensualesVendidas";

    }


    @GetMapping("/ventasPorKg")
    public String mostrarVentasKg(Model model) {

        int i = 0;
        List<Producto> productoVentaKg = productoRepository.findProductosVendidosKG();
        List<ProductoReporteBolsaCerrada> list = new ArrayList<>();
        for (Producto producto: productoVentaKg ) {
            ProductoReporteBolsaCerrada ventaKg = new ProductoReporteBolsaCerrada();
            ventaKg.setIdProducto(producto.getIdProducto());
            ventaKg.setNombre(producto.getNombre());
            ventaKg.setCategoria(producto.getCategoria());
            ventaKg.setUnidadMedida(producto.getUnidadMedida());
            ventaKg.setPesoNeto((int) producto.getPesoNeto());
            ventaKg.setMarca(producto.getMarca());
            float cantidad = productoRepository.findCantidadProductoVendidoXKg().get(i);
            float cantidadFormateada = Math.round(cantidad*100.0/100.0);
            i++;
            ventaKg.setCantidad(cantidadFormateada);
            list.add(ventaKg);

        }

        model.addAttribute("productoReporteBolsaCerrada", list);
        // model.addAttribute("totalVentasRedondeado", totalVentasRedondeado);
        return "ReporteBolsasMensualesVendidas";

    }

    @GetMapping("/GastosMensualesProveedores")
    public String mostrarGastosProveedores(Model model) {

        int i = 0;
        List<IngresoProductos> ingresoProductos = ingresoProductosRepository.findIngresosbyMoth();
        List<IngresoProductos> list = new ArrayList<>();
        for (IngresoProductos ingreso: ingresoProductos ) {
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

        model.addAttribute("ingresoProducto", list);

        return "ReportePagosMensualesProveedores";
    }


}
