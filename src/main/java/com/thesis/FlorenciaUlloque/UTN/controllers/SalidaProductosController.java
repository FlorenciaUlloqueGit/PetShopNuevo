package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.entiities.SalidaProducto;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.SalidaProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.services.IngresoProductosService;
import com.thesis.FlorenciaUlloque.UTN.services.SalidaProductosService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/salidas")
public class SalidaProductosController {

    private final SalidaProductosService salidaProductosService;
    private final SalidaProductosRepository repository;


    public SalidaProductosController(SalidaProductosService salidaProductosService, SalidaProductosRepository repository) {
        this.salidaProductosService = salidaProductosService;
        this.repository = repository;

    }


    @GetMapping("")
    public List<SalidaProducto> getAllEgresos(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return salidaProductosService.getAllSalidas(page, limit); //agregar limite de pagina
    }

    //averiguar como funciona
    @GetMapping("/date/{date}")
    public SalidaProducto getEgresosbyDate(@PathVariable Date date) {
        return repository.findByDate(date);
    }


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


    //funciona
    @PostMapping("")
    public SalidaProducto createEgreso(@RequestBody SalidaProducto salidaProducto){
        return salidaProductosService.createSalida(salidaProducto);
    }

    //funcionaa
    @PutMapping("/{idIngreso}")
    public SalidaProducto updateSalida(@PathVariable int idEgreso, @RequestBody SalidaProducto salidaProducto){

        return salidaProductosService.updateSalidas(idEgreso, salidaProducto);
    }

    //funcionaa
    @DeleteMapping("/{idIngreso}")
    public String deleteSalida( @PathVariable int idEgreso){
        salidaProductosService.deleteSalidas(idEgreso);
        return "la venta ha sido eliminado exitosamente";

    }
}
