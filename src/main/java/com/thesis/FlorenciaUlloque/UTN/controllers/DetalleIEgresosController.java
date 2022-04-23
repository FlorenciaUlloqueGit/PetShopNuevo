package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleEgreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleEgresoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleIngresoRepository;
import com.thesis.FlorenciaUlloque.UTN.services.DetalleEgresoService;
import com.thesis.FlorenciaUlloque.UTN.services.DetalleIngresoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/detalleEgresos")
public class DetalleIEgresosController {

    private final DetalleEgresoService detalleEgresoService;
    private final DetalleEgresoRepository repository;


    public DetalleIEgresosController(DetalleEgresoService detalleEgresoService, DetalleEgresoRepository repository) {
        this.detalleEgresoService = detalleEgresoService;
        this.repository = repository;

    }

    //probar
    @GetMapping("")
    public List<DetalleEgreso> getAllDetallesEgresos(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return detalleEgresoService.getAllDetalles(page, limit);
    }

    //probar
    @GetMapping("/salida/id/{idEgreso}")
    public DetalleEgreso getDetalleBySalidaProducto(@PathVariable int idEgreso) {
        return repository.findBySalidaProductosIdEgreso(idEgreso);
    }

    //probar
    @GetMapping("/id/{idDetalle}")
    public DetalleEgreso getDetalleById(@PathVariable int idDetalle) {
        return repository.findByIdDetalle(idDetalle);
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

    //probar
    @PostMapping("")
    public DetalleEgreso createDetalle(@RequestBody DetalleEgreso detalleEgreso){
        return detalleEgresoService.createDetalle(detalleEgreso);
    }

    //probar
    @PutMapping("/{idDetalle}")
    public DetalleEgreso updateIngreso(@PathVariable int idDetalle, @RequestBody DetalleEgreso detalleEgreso){

        return detalleEgresoService.updateDetalle(idDetalle, detalleEgreso);
    }

    //probar
    @DeleteMapping("/{idDetalle}")
    public String deleteIngreso( @PathVariable int idDetalle){
        detalleEgresoService.deleteDetalle(idDetalle);
        return "El detalle ha sido eliminado exitosamente";

    }
}
