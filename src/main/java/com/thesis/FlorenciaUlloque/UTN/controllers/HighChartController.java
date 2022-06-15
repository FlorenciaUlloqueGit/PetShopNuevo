package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.controllers.Reportes.ReporteController;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleEgresoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.SalidaProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/graficos")
public class HighChartController{
    private final DetalleEgresoRepository detalleEgresoRepository;
    private final SalidaProductosRepository salidaProductosRepository;
    private final IngresoProductosRepository ingresoProductosRepository;
    private final ReporteController reporteController;

    public HighChartController(DetalleEgresoRepository detalleEgresoRepository, SalidaProductosRepository salidaProductosRepository, IngresoProductosRepository ingresoProductosRepository, ReporteController reporteController, ReporteController reporteController1) {
        this.detalleEgresoRepository = detalleEgresoRepository;
        this.salidaProductosRepository = salidaProductosRepository;
        this.ingresoProductosRepository = ingresoProductosRepository;
        this.reporteController = reporteController1;
    }

    @GetMapping("/reporteTotalVentaHoy")
    public String barChart(Model model){
        int cantidadCategoriaAlimentos;
        int cantCatAccesorios;
        int cantCatHigiene;
        int cantCatSalud;

        LocalDate dia = reporteController.obtenerFechaDiaria();
        if(null == detalleEgresoRepository.findCantidadTotalVendidoProductoCategoria1Hoy(dia)){
            cantidadCategoriaAlimentos = 0;
        } else {
             cantidadCategoriaAlimentos = detalleEgresoRepository.findCantidadTotalVendidoProductoCategoria1Hoy(dia);
        }

        if( detalleEgresoRepository.findCantidadTotalVendidoProductoCategoria2Hoy(dia) == null){
            cantCatAccesorios = 0;
        }else{
            cantCatAccesorios = detalleEgresoRepository.findCantidadTotalVendidoProductoCategoria2Hoy(dia);
        }


        if(detalleEgresoRepository.findCantidadTotalVendidoProductoCategoria3Hoy(dia) == null ){
            cantCatHigiene = 0;
        }else{
            cantCatHigiene = detalleEgresoRepository.findCantidadTotalVendidoProductoCategoria3Hoy(dia);
        }

        if(detalleEgresoRepository.findCantidadTotalVendidoProductoCategoria4Hoy(dia)==null){
            cantCatSalud = 0;
        }else{
            cantCatSalud = detalleEgresoRepository.findCantidadTotalVendidoProductoCategoria4Hoy(dia);
        }

        Map<String, Integer> data = new LinkedHashMap<String, Integer>();
        data.put("Alimentos", cantidadCategoriaAlimentos);
        data.put("Higiene", cantCatHigiene);
        data.put("Accesorios", cantCatAccesorios);
        data.put("Salud", cantCatSalud);

        model.addAttribute("keySet", data.keySet());
        model.addAttribute("values", data.values());
        return "barChart";
    }

    @GetMapping("/pieChartVentaBolsa")
    public String pieChart(Model model) {

        int cantKgsBolsasVendidas ;
        if(detalleEgresoRepository.findCantBolsasMensualesKgs() == null ){
            cantKgsBolsasVendidas = 0;
        }else{
            cantKgsBolsasVendidas = detalleEgresoRepository.findCantBolsasMensualesKgs();
        }
        int cantKgsVendidos ;
        if(detalleEgresoRepository.findCantKgVendidos() == null ){
            cantKgsVendidos = 0;
        }else{
            cantKgsVendidos = detalleEgresoRepository.findCantKgVendidos();
        }
        double totalKgs = cantKgsBolsasVendidas + cantKgsVendidos;
        float porcentajeVentaKg = (float) ((cantKgsVendidos*100)/totalKgs);
        float porcentajeBolsasVenta = (float) ((cantKgsBolsasVendidas * 100)/totalKgs);

        model.addAttribute("bolsa", porcentajeBolsasVenta);
        model.addAttribute("kg", porcentajeVentaKg);
        return "pieChartBolsa";

    }
    @GetMapping("/pieChartVentaKG")
    public String pieChartKG(Model model) {

        int cantKgsBolsasVendidas ;
        if(detalleEgresoRepository.findCantBolsasMensualesKgs() == null ){
            cantKgsBolsasVendidas = 0;
        }else{
            cantKgsBolsasVendidas = detalleEgresoRepository.findCantBolsasMensualesKgs();
        }
        int cantKgsVendidos ;
        if(detalleEgresoRepository.findCantKgVendidos() == null ){
            cantKgsVendidos = 0;
        }else{
            cantKgsVendidos = detalleEgresoRepository.findCantKgVendidos();
        }
        double totalKgs = cantKgsBolsasVendidas + cantKgsVendidos;
        float porcentajeVentaKg = (float) ((cantKgsVendidos*100)/totalKgs);
        float porcentajeBolsasVenta = (float) ((cantKgsBolsasVendidas * 100)/totalKgs);

        model.addAttribute("bolsa", porcentajeBolsasVenta);
        model.addAttribute("kg", porcentajeVentaKg);
        return "pieChartKg";

    }

    public String evaluarMes(int mes){
        String nombreMes;
        switch (mes) {
            case 1:
                nombreMes = "Enero";
                break;
            case 2:
                nombreMes = "Febrero";
                break;
            case 3:
                nombreMes = "Marzo";
                break;
            case 4:
                nombreMes = "Abril";
                break;
            case 5:
                nombreMes = "Mayo";
                break;
            case 6:
                nombreMes = "Junio";
                break;
            case 7:
                nombreMes = "Julio";
                break;
            case 8:
                nombreMes = "Agosto";
                break;
            case 9:
                nombreMes = "Septiembre";
                break;
            case 10:
                nombreMes = "Octubre";
                break;
            case 11:
                nombreMes = "Noviembre";
                break;
            default:
                nombreMes = "Diciembre";
        }
        return nombreMes;
    }


    @GetMapping("/reporteEgresos")
    public String barChartIngresos(Model model){

        LocalDate fecha = reporteController.fechaVentas();
        //TODO: cambiar fecha
    //    LocalDate fecha = LocalDate.parse("2022-06-06");

        float totalMesActual = 0;
        float totalMesAnterior = 0;
        float totalMesAnteAnterior = 0;
        String nombreMesActual = "";
        String nombreMesAnterior = "";
        String nombreMesAnteAnterior = "";

        if(null == salidaProductosRepository.findIngresosMesActual(String.valueOf(fecha))){
            totalMesActual = 0;
            int mesActual = fecha.getMonth().getValue();
            nombreMesActual = evaluarMes(mesActual);
        } else {
            totalMesActual =  salidaProductosRepository.findIngresosMesActual(String.valueOf(fecha));
            int mesActual = fecha.getMonth().getValue();
             nombreMesActual = evaluarMes(mesActual);

        }

        if(null == salidaProductosRepository.findIngresosMesAnterior(String.valueOf(fecha))){
            totalMesAnterior = 0;
            int  nuevaFecha = fecha.getMonth().getValue() -1;
            int mesAnterior = nuevaFecha;
            nombreMesAnterior = evaluarMes(mesAnterior);
        } else {
            totalMesAnterior =salidaProductosRepository.findIngresosMesAnterior(String.valueOf(fecha));
            int  nuevaFecha = fecha.getMonth().getValue() -1;
            int mesAnterior = nuevaFecha;
             nombreMesAnterior = evaluarMes(mesAnterior);
        }


        if(salidaProductosRepository.findIngresosMesAnteriorX2(String.valueOf(fecha)).isNaN() ){
            totalMesAnteAnterior = 0;
            int  nuevaFecha = fecha.getMonth().getValue() -2;
            int mesAnteAnterior = nuevaFecha;
            nombreMesAnteAnterior = evaluarMes(mesAnteAnterior);
        } else {
            totalMesAnteAnterior = salidaProductosRepository.findIngresosMesAnteriorX2(String.valueOf(fecha));
           int  nuevaFecha = fecha.getMonth().getValue() -2;
            int mesAnteAnterior = nuevaFecha;
             nombreMesAnteAnterior = evaluarMes(mesAnteAnterior);
        }


        Map<String, Float> data = new LinkedHashMap<String, Float>();
        data.put( nombreMesAnteAnterior, totalMesAnteAnterior);
        data.put(nombreMesAnterior,  totalMesAnterior);
        data.put( nombreMesActual,  totalMesActual);

        model.addAttribute("keySet", data.keySet());
        model.addAttribute("values", data.values());
        return "barChartEgresos";
    }
    @GetMapping("/reporteIngresos")
    public String barChartEgresos(Model model){

        //TODO: cambiar fecha
        LocalDate fecha = reporteController.obtenerFecha();
      //  LocalDate fecha = LocalDate.parse("2022-06-06"); //cambiarFecha

        float totalMesActual = 0;
        float totalMesAnterior = 0;
        float totalMesAnteAnterior = 0;
        String nombreMesActual = "";
        String nombreMesAnterior = "";
        String nombreMesAnteAnterior = "";

        if(null == ingresoProductosRepository.findVentasMesAcual(String.valueOf(fecha))){
            totalMesActual = 0;
            int mesActual = fecha.getMonth().getValue();
            nombreMesActual = evaluarMes(mesActual);
        } else {
            totalMesActual =  ingresoProductosRepository.findVentasMesAcual(String.valueOf(fecha));
            int mesActual = fecha.getMonth().getValue();
            nombreMesActual = evaluarMes(mesActual);

        }

        if(null == ingresoProductosRepository.findVentasMesAnterior(String.valueOf(fecha))){
            totalMesAnterior = 0;
            int  nuevaFecha = fecha.getMonth().getValue() -1;
            int mesAnterior = nuevaFecha;
            nombreMesAnterior = evaluarMes(mesAnterior);
        } else {
            totalMesAnterior =ingresoProductosRepository.findVentasMesAnterior(String.valueOf(fecha));
            int  nuevaFecha = fecha.getMonth().getValue() -1;
            int mesAnterior = nuevaFecha;
            nombreMesAnterior = evaluarMes(mesAnterior);
        }


        if(ingresoProductosRepository.findVentasMesAnteAnterior(String.valueOf(fecha))== null ){
            totalMesAnteAnterior = 0;
            int  nuevaFecha = fecha.getMonth().getValue() -2;
            int mesAnteAnterior = nuevaFecha;
            nombreMesAnteAnterior = evaluarMes(mesAnteAnterior);
        } else {
            totalMesAnteAnterior = ingresoProductosRepository.findVentasMesAnteAnterior(String.valueOf(fecha));
            int  nuevaFecha = fecha.getMonth().getValue() -2;
            int mesAnteAnterior = nuevaFecha;
            nombreMesAnteAnterior = evaluarMes(mesAnteAnterior);
        }


        Map<String, Float> data = new LinkedHashMap<String, Float>();
        data.put( nombreMesAnteAnterior, totalMesAnteAnterior);
        data.put(nombreMesAnterior,  totalMesAnterior);
        data.put( nombreMesActual,  totalMesActual);

        model.addAttribute("keySet", data.keySet());
        model.addAttribute("values", data.values());
        return "barChartIngresos";
    }


}
