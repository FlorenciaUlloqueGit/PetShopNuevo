package com.thesis.FlorenciaUlloque.UTN.controllers.usuarios;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.VendedorDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.VendedorDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Rol;
import com.thesis.FlorenciaUlloque.UTN.entiities.Vendedor;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.VendedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.VendedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;
    private final VendedorRepository repository;

    public VendedorController(VendedorService vendedorService, VendedorRepository repository) {
        this.vendedorService = vendedorService;
        this.repository = repository;
    }


    @GetMapping("/usuario/{usuario}")
    public Vendedor getVendedorByUsuario(@PathVariable String usuario) {
        return repository.findByUsuario(usuario);
    }


    @ModelAttribute("vendedor")
    public VendedorDto mapearVendedor() {
        return new VendedorDto();
    }

    @ModelAttribute("vendedors")
    public VendedorDtos mapearVendedorReal() {
        return new VendedorDtos();
    }

    @ModelAttribute("vendedorReal")
    public Vendedor mapearVendedorREal() {
        return new Vendedor();
    }

    @GetMapping
    public String mostrarFormularioDeRegistroVendedor() {
        return "crearVendedor";
    }

    @PostMapping
    public String createVendedor(@ModelAttribute("vendedor") VendedorDto vendedorDto) {

        boolean registrado = vendedorService.saveVendedor(vendedorDto);
        if (registrado == true){
            vendedorService.saveVendedor(vendedorDto);
            return "redirect:/vendedores?error";
        }else {
            return "redirect:/vendedores?exito";
        }
    }


    @GetMapping("/update/{idVendedor}")
    public String mostrarformUpdateVendedor(@PathVariable int idVendedor, Model model){
        model.addAttribute("vendedor", repository.findByIdVendedor(idVendedor));
        return "UpdateVendedor";
    }


    @PostMapping("/updateVendedor/{idVendedor}")
    public String updatearVendedor(@ModelAttribute("vendedorReal")Vendedor vendedor,
                                   @PathVariable int idVendedor){

        Rol rol = new Rol(2,"vendedor");

        Vendedor vendedorExiste = repository.findByIdVendedor(idVendedor);
        String usuario = vendedorExiste.getUsuario();

        vendedor.setUsuario(usuario);
        vendedorService.updateVendedor(vendedor);

            return "redirect:/vendedores/update/{idVendedor}?exito";

    }


    @GetMapping({"/listar", "/"})
    public String listarVendedores(Model model){
        model.addAttribute("vendedors", vendedorService.findAllVendedores());
        return "listadoVendedores";
    }


    @GetMapping("/delete/{idVendedor}")
    public String deleteVendedor( @PathVariable int idVendedor){
       vendedorService.deleteVendedor(idVendedor);
            return "redirect:/vendedores/listar?exito";

    }


}
