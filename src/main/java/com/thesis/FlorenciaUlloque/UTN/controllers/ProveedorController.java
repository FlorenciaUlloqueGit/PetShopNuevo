package com.thesis.FlorenciaUlloque.UTN.controllers;


import com.thesis.FlorenciaUlloque.UTN.Dtos.ProveedorDto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProveedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController  {

    private final ProveedorService proveedorService;
    private final ProveedorRepository  repository;


    public ProveedorController(ProveedorService proveedorService, ProveedorRepository repository) {
        this.proveedorService = proveedorService;
        this.repository = repository;

    }

    @GetMapping("/nombre/{nombre}")
    public Proveedor getProveedorByNombre(@PathVariable String nombre) {
        return repository.findByNombre(nombre);
    }


    @ModelAttribute("proveedor")
    public ProveedorDto mapearProveedor() {
        return new ProveedorDto();
    }

    @ModelAttribute("proveedorReal")
    public Proveedor mapearREal() {
        return new Proveedor();
    }

    @GetMapping
    public String mostrarFormularioCrearProv() {
        return "crearProveedor";
    }

    @PostMapping
    public String create(@ModelAttribute("proveedor") ProveedorDto proveedorDto) {

        boolean registrado = proveedorService.saveProveedor(proveedorDto);
        if (registrado == true){
            proveedorService.saveProveedor(proveedorDto);
            return "redirect:/proveedores?error";
        }else {
            return "redirect:/proveedores?exito";
        }
    }


    @GetMapping("/update/{idProveedor}")
    public String mostrarformUpdate(@PathVariable int idProveedor, Model model){
        model.addAttribute("proveedor", repository.findById(idProveedor));
        return "UpdateProveedor";
    }


    @PostMapping("/updateProveedor/{idProveedor}")
    public String updatear(@ModelAttribute("proveedorReal")Proveedor proveedor,
                                   @PathVariable int idProveedor){

        Proveedor proveedorExiste = repository.findById(idProveedor);

        proveedorExiste.setNombre(proveedor.getNombre());
        proveedorExiste.setTelefono(proveedor.getTelefono());
        proveedorService.updateProveedor(proveedorExiste);

        return "redirect:/proveedores/update/{idProveedor}?exito";

    }


    @GetMapping({"/listar", "/"})
    public String listar(Model model){
        model.addAttribute("proveedorReal", proveedorService.findAllProveedor());
        return "listadoProveedores";
    }


    @GetMapping("/delete/{idProveedor}")
    public String delete( @PathVariable int idProveedor){
        proveedorService.deleteProveedor(idProveedor);
        return "redirect:/proveedores/listar?exito";

    }
}
