package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteRegistroDto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.ClienteRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/customer")
public class CustomerController {


    private final ClienteService clienteService;
    private final ClienteRepository repository;

    public CustomerController(ClienteService clienteService, ClienteRepository repository) {
        this.clienteService = clienteService;
        this.repository = repository;
    }


    @ModelAttribute("clienteDtos") //crea nuevo clienteDto
    public ClienteDtos retornarclienteDtos(){
        return new ClienteDtos();
    }

    @ModelAttribute("cliente") //crea nuevo clienteDto
    public ClienteRegistroDto retornarcliente(){
        return new ClienteRegistroDto();
    }
    @ModelAttribute("customer") //crea nuevo clienteDto
    public ClienteDto retornarclienteDto(){
        return new ClienteDto();
    }

    @GetMapping("/update/{idCliente}") //update cliente
    public String mostrarformUpdateCliente(@PathVariable int idCliente, Model model){{
        model.addAttribute("cliente", repository.findByIdCliente(idCliente));
    }
        return "updateCliente";
    }


    @PostMapping("/updateCliente/{idCliente}")
    public String updatearCliente(@ModelAttribute("cliente") ClienteRegistroDto clienteRegistroDto,
                                  @PathVariable int idCliente){
        Cliente clienteExiste = repository.findByIdCliente(idCliente);
        clienteExiste.setDireccion(clienteRegistroDto.getDireccion());
        clienteExiste.setNombre(clienteRegistroDto.getNombre());
        clienteExiste.setPass("");
        clienteExiste.setApellido(clienteRegistroDto.getApellido());
        clienteExiste.setTelefono(clienteRegistroDto.getTelefono());
        clienteExiste.setEmail(clienteRegistroDto.getEmail());
        clienteService.updateCliente(clienteExiste);
        Cliente clienteTest = clienteService.updateCliente(clienteExiste);
        if(clienteTest == null){
            return "redirect:/customer/update/{idCliente}?error";
        } else {
            clienteService.updateCliente(clienteExiste);
            return "redirect:/customer/update/{idCliente}?exito";
        }

    }

    @GetMapping({"/listar", "/"})
    public String listarClientes(Model model){
        List<ClienteDto> clienteList = clienteService.findAllClientes();
        List<ClienteDto> clienteArray= new ArrayList<>();

        for (ClienteDto clienteDto: clienteList) {
            if(clienteDto.getIdCliente() != 2){
                ClienteDto dto = clienteDto;
                clienteArray.add(dto);
            }

        }
        model.addAttribute("customer", clienteArray);
        return "listadoClientes";
    }
    @GetMapping("/listarClientesAdmin")
    public String listarClientes2(Model model){
        List<ClienteDto> clienteList = clienteService.findAllClientes();
        List<ClienteDto> clienteArray= new ArrayList<>();

        for (ClienteDto clienteDto: clienteList) {
            if(clienteDto.getIdCliente() != 2){
                ClienteDto dto = clienteDto;
                clienteArray.add(dto);
            }

        }
        model.addAttribute("customer", clienteArray);
        return "listadoClientesAdmin";
    }

    @GetMapping("/delete/{idCliente}")
    public String deleteVendedor( @PathVariable int idCliente){
        clienteService.deleteCliente(idCliente);
        return "redirect:/customer/listarClientesAdmin?exito";

    }

}
