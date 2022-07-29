package com.thesis.FlorenciaUlloque.UTN.controllers;


import com.thesis.FlorenciaUlloque.UTN.Dtos.ProveedorDto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import com.thesis.FlorenciaUlloque.UTN.repositories.MarcaRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProveedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ProveedorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;
    private final ProveedorRepository repository;
    private final MarcaRepository marcaRepository;


    public ProveedorController(ProveedorService proveedorService, ProveedorRepository repository, MarcaRepository marcaRepository) {
        this.proveedorService = proveedorService;
        this.repository = repository;

        this.marcaRepository = marcaRepository;
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
        if (registrado == true) {
            proveedorDto.setEnabled(true);
            proveedorService.saveProveedor(proveedorDto);
            return "redirect:/proveedores?error";
        } else {
            return "redirect:/proveedores?exito";
        }
    }


    @GetMapping("/update/{idProveedor}")
    public String mostrarformUpdate(@PathVariable int idProveedor, Model model) {
        model.addAttribute("proveedor", repository.findById(idProveedor));
        return "UpdateProveedor";
    }


    @PostMapping("/updateProveedor/{idProveedor}")
    public String updatear(@ModelAttribute("proveedorReal") Proveedor proveedor,
                           @PathVariable int idProveedor) {

        Proveedor proveedorExiste = repository.findById(idProveedor);

        proveedorExiste.setNombre(proveedor.getNombre());
        proveedorExiste.setTelefono(proveedor.getTelefono());
        proveedorExiste.setRepresentante(proveedor.getRepresentante());
        proveedorExiste.setEnabled(proveedorExiste.isEnabled());
        proveedorService.updateProveedor(proveedorExiste);

        return "redirect:/proveedores/update/{idProveedor}?exito";

    }


    @GetMapping({"/listar", "/"})
    public String findAll(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 7);
        Page<Proveedor> pageStock = proveedorService.getAll(pageRequest);

        int totalPage = pageStock.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("proveedorReal", pageStock.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "listadoProveedores";
    }


    @GetMapping("/delete/{idProveedor}")
    public String delete(@PathVariable int idProveedor) {

        proveedorService.deleteProveedor(idProveedor);
        List<Integer> listadoIdMArcasRelacionadas = repository.findIdMarcasProveedorDisabled();
        for (Integer idMarca : listadoIdMArcasRelacionadas) {
            Marca marca = marcaRepository.findByIdMarca(idMarca);
            marca.setEnabled(false);
            marcaRepository.save(marca);
        }
        return "redirect:/proveedores/listar?exito";

    }


    // ------------------------------- para vendedores -------------------------------

    @GetMapping("/formNuevo")
    public String mostrarFormularioCrearProv2() {
        return "crearProveedorVendedor";
    }

    @PostMapping("/nuevo")
    public String create2(@ModelAttribute("proveedor") ProveedorDto proveedorDto) {

        boolean registrado = proveedorService.saveProveedor(proveedorDto);
        if (registrado == true) {
            proveedorService.saveProveedor(proveedorDto);
            return "redirect:/proveedores/formNuevo?error";
        } else {
            return "redirect:/proveedores/formNuevo?exito";
        }
    }


    @GetMapping("/updateProveedor/{idProveedor}")
    public String mostrarformUpdate2(@PathVariable int idProveedor, Model model) {
        model.addAttribute("proveedor", repository.findById(idProveedor));
        return "UpdateProveedorVendedor";
    }


    @PostMapping("/updateProveedorVendedor/{idProveedor}")
    public String updatear2(@ModelAttribute("proveedorReal") Proveedor proveedor,
                            @PathVariable int idProveedor) {

        Proveedor proveedorExiste = repository.findById(idProveedor);

        proveedorExiste.setNombre(proveedor.getNombre());
        proveedorExiste.setTelefono(proveedor.getTelefono());
        proveedorExiste.setRepresentante(proveedor.getRepresentante());
        proveedorExiste.setEnabled(proveedorExiste.isEnabled());
        proveedorService.updateProveedor(proveedorExiste);

        return "redirect:/proveedores/updateProveedor/{idProveedor}?exito";

    }


    @GetMapping("/listarProveedores")
    public String findAll2(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 7);
        Page<Proveedor> pageStock = proveedorService.getAll(pageRequest);


        int totalPage = pageStock.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("proveedorReal", pageStock.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "listadoProveedoresFromVendedor";
    }

}
