package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.entiities.Administrador;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.AdministradorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.AdministradorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/admins")
public class AdministradorController {

    private final AdministradorService  administradorService;
    private final AdministradorRepository repository;


    public AdministradorController(AdministradorService administradorService, AdministradorRepository repository) {
        this.administradorService = administradorService;
        this.repository = repository;

    }


    @GetMapping("/lista")
    public List<String> getAdminList(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return repository.findAllAdmins(page,limit); //agregar limite de pagina
    }



    @GetMapping("/nombre/{nombre}")
    public Administrador getAdminByUsuario(@PathVariable String nombre) {
        return repository.findByUsuario(nombre);
    }

    @GetMapping("/idAdmin/{id}")
    public Optional<Administrador> getAdminById(@PathVariable Integer idAdmin) {
        return repository.findById(idAdmin);
    }


}
