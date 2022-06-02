package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.MarcaDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.MarcaDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import com.thesis.FlorenciaUlloque.UTN.repositories.MarcaRepository;
import com.thesis.FlorenciaUlloque.UTN.services.MarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;
    private final MarcaRepository repository;


    public MarcaController(MarcaService marcaService, MarcaRepository repository) {
        this.marcaService = marcaService;
        this.repository = repository;

    }


    @GetMapping("{nombre}")
    public Marca getMarcaByName(@PathVariable String nombre) {
        return repository.findByNombre(nombre);
    }

    @GetMapping("/id/{idMarca}") //VEEER
    public Marca getMarcaByIdMarca(@PathVariable int idMarca ) {
        return repository.findByIdMarca(idMarca);
    }


    @ModelAttribute("marca")
    public MarcaDto mapearMarca() {
        return new MarcaDto();
    }


    @ModelAttribute("marcaDtos")
    public MarcaDtos mapeaMarcaDtos() {
        return new MarcaDtos();
    }

    @ModelAttribute("marcaReal")
    public Marca mapearVendedorREal() {
        return new Marca();
    }

    @GetMapping("/MarcaForm")
    public String mostrarFormulario2(Model model) {

        MarcaDtos marca = new MarcaDtos();
        List<Proveedor> proveedores = marcaService.listaProveedores();

        model.addAttribute("proveedores", proveedores);
        model.addAttribute("marcaDtos", marca);
        return "crearMarcaVendedor";
    }
    @GetMapping
    public String mostrarFormulario(Model model) {

        MarcaDtos marca = new MarcaDtos();
        List<Proveedor> proveedores = marcaService.listaProveedores();

        model.addAttribute("proveedores", proveedores);
        model.addAttribute("marcaDtos", marca);
        return "crearMarca";
    }

    @PostMapping
    public String create(@ModelAttribute("marcaDtos") MarcaDtos marcaDtos) {
        boolean registrado = marcaService.saveMarca(marcaDtos);
        if (registrado == true){
            marcaService.saveMarca(marcaDtos);
            return "redirect:/marcas?error";
        }else {
            return "redirect:/marcas?exito";
        }
    }

    @PostMapping("/nuevo")
    public String create2(@ModelAttribute("marcaDtos") MarcaDtos marcaDtos) {
        boolean registrado = marcaService.saveMarca(marcaDtos);
        if (registrado == true){
            marcaService.saveMarca(marcaDtos);
            return "redirect:/marcas/MarcaForm?error";
        }else {
            return "redirect:/marcas/MarcaForm?exito";
        }
    }

    @GetMapping("/update/{idMarca}")
    public String mostrarformUpdate(@PathVariable int idMarca, Model model){

        List<Proveedor> proveedores = marcaService.listaProveedores();

        model.addAttribute("marca", repository.findByIdMarca(idMarca));
        model.addAttribute("proveedores", proveedores);
        return "UpdateMarca";
    }


    @PostMapping("/updateMarca/{idMarca}")
    public String updatearVendedor(@ModelAttribute("marcaReal")Marca marca,
                                   @PathVariable int idMarca){

        Marca marcaExiste = repository.findByIdMarca(idMarca);

        marcaExiste.setIdMarca(marca.getIdMarca());
        marcaExiste.setNombre(marca.getNombre());
        marcaExiste.setProveedor(marca.getProveedor());
        marcaService.updateMarca(marcaExiste);

        return "redirect:/marcas/update/{idMarca}?exito";

    }

    @GetMapping("/updateMarca/{idMarca}")
    public String mostrarformUpdate2(@PathVariable int idMarca, Model model){

        List<Proveedor> proveedores = marcaService.listaProveedores();

        model.addAttribute("marca", repository.findByIdMarca(idMarca));
        model.addAttribute("proveedores", proveedores);
        return "UpdateMarcaFromVendedor";
    }


    @PostMapping("/updateSpecificMarca/{idMarca}")
    public String updatearVendedor2(@ModelAttribute("marcaReal")Marca marca,
                                   @PathVariable int idMarca){

        Marca marcaExiste = repository.findByIdMarca(idMarca);

        marcaExiste.setIdMarca(marca.getIdMarca());
        marcaExiste.setNombre(marca.getNombre());
        marcaExiste.setProveedor(marca.getProveedor());
        marcaService.updateMarca(marcaExiste);

        return "redirect:/marcas/updateMarca/{idMarca}?exito";

    }



    @GetMapping({"/listar", "/"})
    public String listar(Model model){
        model.addAttribute("marcaReal", marcaService.findAllMarcas());
        return "listadoMarcas";
    }
    @GetMapping("/listarMarcas")
    public String listar2(Model model){
        model.addAttribute("marcaReal", marcaService.findAllMarcas());
        return "listadoMarcasFromVendedor";
    }


    @GetMapping("/delete/{idMarca}")
    public String deleteVendedor( @PathVariable int idMarca){
        marcaService.deleteMarca(idMarca);
        return "redirect:/marcas/listar?exito";

    }


}
