package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.Vendedor;
import com.thesis.FlorenciaUlloque.UTN.repositories.ClienteRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.VendedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ClienteService;
import com.thesis.FlorenciaUlloque.UTN.services.VendedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;
    private final VendedorRepository repository;

    public VendedorController(VendedorService vendedorService, VendedorRepository repository) {
        this.vendedorService = vendedorService;
        this.repository = repository;
    }

    /*
    @GetMapping("/lista")
    public List<String> getClienteList(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return repository.findAllClientes(page,limit); //agregar limite de pagina
    }

     */

    @GetMapping("")
    public List<Vendedor> getAllVendedores(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return vendedorService.getAllVendedores(page, limit);
    }

    @GetMapping("/nombre/{nombre}")
    public Vendedor getVendedorByNombre(@PathVariable String nombre) {
        return repository.findByNombre(nombre);
    }

    @GetMapping("/usuario/{usuario}")
    public Vendedor getVendedorByUsuario(@PathVariable String usuario) {
        return repository.findByUsuario(usuario);
    }

    @PostMapping("")
    public Vendedor createVendedor(@RequestBody Vendedor vendedor){
        return vendedorService.createVendedor(vendedor);
    }

    @PutMapping("/{idVendedor}")
    public Vendedor updateVendedor(@PathVariable int idVendedor, @RequestBody Vendedor vendedor){

        return vendedorService.updateVendedor(idVendedor, vendedor);
    }

    @DeleteMapping("/{idVendedor}")
    public String deleteVendedor( @PathVariable int idVendedor){
        vendedorService.deleteVendedor(idVendedor);
        return "El vendedor ha sido eliminado exitosamente";

    }
}
