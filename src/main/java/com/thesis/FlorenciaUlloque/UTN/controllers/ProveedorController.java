package com.thesis.FlorenciaUlloque.UTN.controllers;


import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProveedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ProveedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController  {

    private final ProveedorService proveedorService;
    private final ProveedorRepository  repository;


    public ProveedorController(ProveedorService proveedorService, ProveedorRepository repository) {
        this.proveedorService = proveedorService;
        this.repository = repository;

    }

    @GetMapping("/lista")
    public List<String> geListProveedors(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "25") int limit) {

        return repository.findAllProveedors(page, limit); //agregar limite de pagina
    }




    @GetMapping
    public List<Proveedor> getAllProveedores(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "25") int limit) {

        return proveedorService.getAllProveedores(page, limit); //agregar limite de pagina
    }




    @GetMapping("{nombre}")
    public Proveedor getProveedorByName(@PathVariable String nombre) {
        return repository.findByNombre(nombre);
    }



    @PostMapping("")
    public Proveedor createProveedor(@RequestBody Proveedor proveedor){
        return proveedorService.createProveedor(proveedor);
    }

    @PutMapping("{id}")
    public Proveedor updateProveedor(@PathVariable long id, @RequestBody Proveedor proveedor){

        return proveedorService.updateProveedor(id, proveedor);
    }

    @DeleteMapping("{id}")
    public String deleteMovie( @PathVariable long id){
        proveedorService.deleteProveedor(id);
        return "El proveedor ha sido eliminado exitosamente";

    }
}
