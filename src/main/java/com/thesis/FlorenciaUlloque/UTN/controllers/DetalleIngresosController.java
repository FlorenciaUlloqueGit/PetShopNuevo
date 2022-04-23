package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleIngresoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.services.DetalleIngresoService;
import com.thesis.FlorenciaUlloque.UTN.services.IngresoProductosService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/detalleIngresos")
public class DetalleIngresosController {

    private final DetalleIngresoService detalleIngresoService;
    private final DetalleIngresoRepository repository;


    public DetalleIngresosController(DetalleIngresoService detalleIngresoService, DetalleIngresoRepository repository) {
        this.detalleIngresoService = detalleIngresoService;
        this.repository = repository;

    }

    //funciona
    @GetMapping("")
    public List<DetalleIngreso> getAllDetallesIngresos(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return detalleIngresoService.getAllDetalles(page, limit); //agregar limite de pagina
    }

    //funciona
    @GetMapping("/ingreso/id/{idIngreso}")
    public DetalleIngreso getDetalleByIdIngreso(@PathVariable int idIngreso) {
        return repository.findByIngresoProductosIdIngreso(idIngreso);
    }

    //funciona
    @GetMapping("/id/{idDetalle}")
    public DetalleIngreso getDetalleById(@PathVariable int idDetalle) {
        return repository.findByIdDetalle(idDetalle);
    }

    //funciona
    @GetMapping("/producto/codBarras/{codBarras}")
    public DetalleIngreso getDetalleByCodBarrasProducto(@PathVariable Long codBarras) {
        return repository.findByProductoCodBarras(codBarras);
    }

    //funciona
    @GetMapping("/producto/id/{idProducto}")
    public DetalleIngreso getClienteByProveedorName(@PathVariable int idProducto) {
        return repository.findByProductoIdProducto(idProducto);
    }

    //funciona ---- agregar precio = cant * precio producto en el controller o en la DB
    @PostMapping("")
    public DetalleIngreso createDetalle(@RequestBody DetalleIngreso detalleIngreso){
        return detalleIngresoService.createDetalle(detalleIngreso);
    }

    //funciona
    @PutMapping("/{idDetalle}")
    public DetalleIngreso updateIngreso(@PathVariable int idDetalle, @RequestBody DetalleIngreso detalleIngreso){

        return detalleIngresoService.updateDetalle(idDetalle, detalleIngreso);
    }

    //funcionaa
    @DeleteMapping("/{idDetalle}")
    public String deleteIngreso( @PathVariable int idDetalle){
        detalleIngresoService.deleteDetalle(idDetalle);
        return "El detalle ha sido eliminado exitosamente";

    }
}
