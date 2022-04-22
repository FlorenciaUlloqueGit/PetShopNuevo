package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.services.IngresoProductosService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/ingresos")
public class IngresoProductosController {

    private final IngresoProductosService ingresoProductosService;
    private final IngresoProductosRepository repository;


    public IngresoProductosController(IngresoProductosService ingresoProductosService, IngresoProductosRepository repository) {
        this.ingresoProductosService = ingresoProductosService;
        this.repository = repository;

    }


    //funcionaaa
    @GetMapping("")
    public List<IngresoProductos> getAllIngresos(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return ingresoProductosService.getAllIngresos(page, limit); //agregar limite de pagina
    }

    //averiguar como funciona
    @GetMapping("/date/{date}")
    public IngresoProductos getIngresobyDate(@PathVariable Date date) {
        return repository.findByDate(date);
    }

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

    //funcionaa
    @GetMapping("/total/{total}")
    public IngresoProductos getClienteByProveedorName(@PathVariable double total) {
        return repository.findByTotal(total);
    }


    //funciona
    @PostMapping("")
    public IngresoProductos createIngreso(@RequestBody IngresoProductos ingresoProductos){
        return ingresoProductosService.createIngreso(ingresoProductos);
    }

    //funcionaa
    @PutMapping("/{idIngreso}")
    public IngresoProductos updateIngreso(@PathVariable int idIngreso, @RequestBody IngresoProductos ingresoProductos){

        return ingresoProductosService.updateIngreso(idIngreso, ingresoProductos);
    }

    //funcionaa
    @DeleteMapping("/{idIngreso}")
    public String deleteIngreso( @PathVariable int idIngreso){
        ingresoProductosService.deleteIngreso(idIngreso);
        return "El ingreso ha sido eliminado exitosamente";

    }
}
