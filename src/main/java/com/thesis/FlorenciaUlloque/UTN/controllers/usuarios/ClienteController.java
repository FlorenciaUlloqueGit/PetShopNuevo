package com.thesis.FlorenciaUlloque.UTN.controllers.usuarios;

import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.ClienteRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteRepository repository;


    public ClienteController(ClienteService clienteService, ClienteRepository repository) {
        this.clienteService = clienteService;
        this.repository = repository;

    }


    @GetMapping("/lista")
    public List<String> getClienteList(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return repository.findAllClientes(page,limit); //agregar limite de pagina
    }


    @GetMapping("/")
    public List<Cliente> getAllClientes(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return clienteService.getAllCliente(page, limit); //agregar limite de pagina
    }

    @GetMapping("/email/{email}")
    public Cliente getClienteByEmail(@PathVariable String email) {
        return repository.findByEmail(email);
    }

    @GetMapping("/apellido/{apellido}")
    public Cliente getClienteByApellido(@PathVariable String apellido) {
        return repository.findByApellido(apellido);
    }

    @PostMapping("")
    public Cliente createCliente(@RequestBody Cliente cliente ){
        return clienteService.createCliente(cliente);
    }


    @DeleteMapping("/{idCliente}")
    public String deleteCliente( @PathVariable int idCliente){
        Cliente cliente = repository.findByIdCliente(idCliente);
        cliente.setEnabled(false);
        clienteService.updateCliente(cliente);
      //  clienteService.deleteCliente(idCliente);
        return "El cliente ha sido eliminado exitosamente";

    }
}
