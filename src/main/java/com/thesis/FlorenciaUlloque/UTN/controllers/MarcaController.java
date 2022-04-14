package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import com.thesis.FlorenciaUlloque.UTN.repositories.MarcaRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProveedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.MarcaService;
import com.thesis.FlorenciaUlloque.UTN.services.ProveedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;
    private final MarcaRepository repository;


    public MarcaController(MarcaService marcaService, MarcaRepository repository) {
        this.marcaService = marcaService;
        this.repository = repository;

    }

   @GetMapping("/lista")
    public List<String> getALLMarcasbyName(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "limit", defaultValue = "25") int limit) {

        return repository.findAllMarcas(page, limit); //agregar limite de pagina
    }






    @GetMapping
    public List<Marca> getAllMarcas(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "limit", defaultValue = "25") int limit) {

        return marcaService.getAllMarcas(page, limit); //agregar limite de pagina
    }




    @GetMapping("{nombre}")
    public Marca getMarcaByName(@PathVariable String nombre) {
        return repository.findByNombre(nombre);
    }

    @GetMapping("/id/{idMarca}") //VEEER
    public Marca getMarcaByIdMarca(@PathVariable int idMarca ) {
        return repository.findByIdMarca(idMarca);
    }




    @PostMapping("")
    public Marca createMarca(@RequestBody Marca marca ){
        return marcaService.createMarca(marca);
    }

    @PutMapping("{idMarca}")
    public Marca updateMarca(@PathVariable long id, @RequestBody Marca marca){

        return marcaService.updateMarca(id, marca);
    }

    @DeleteMapping("{idMarca}")
    public String deleteMovie( @PathVariable long id){
        marcaService.deleteMarca(id);
        return "El proveedor ha sido eliminado exitosamente";

    }
}
