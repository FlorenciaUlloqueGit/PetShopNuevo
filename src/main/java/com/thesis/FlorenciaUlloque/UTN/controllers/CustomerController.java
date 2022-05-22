package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteRegistroDto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.services.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/customer")
public class CustomerController {


    private final ClienteService clienteService;

    public CustomerController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @ModelAttribute("cliente") //crea nuevo clienteDto
    public ClienteRegistroDto retornarcliente(){
        return new ClienteRegistroDto();
    }
    @ModelAttribute("customer") //crea nuevo clienteDto
    public ClienteDto retornarclienteDto(){
        return new ClienteDto();
    }

    @GetMapping("update") //update cliente
    public String mostrarformUpdateCliente(){
        return "updateCliente";
    }


    @RequestMapping( method = RequestMethod.POST)
    public String updatearCliente(@ModelAttribute("cliente") ClienteRegistroDto clienteRegistroDto){
        clienteService.updateCliente(clienteRegistroDto);
        Cliente clienteTest = clienteService.updateCliente(clienteRegistroDto);
        if(clienteTest == null){
            return "redirect:/customer/update?error";
        } else {
            return "redirect:/customer/update?exito";
        }

    }

    @GetMapping({"/listar", "/"})
    public String listarClientes(Model model){
        model.addAttribute("customer", clienteService.findAllClientes());
        return "listadoClientes";
    }



}
