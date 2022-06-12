package com.thesis.FlorenciaUlloque.UTN.controllers.SignIn;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteRegistroDto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.ClienteRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    private final ClienteService clienteService;
    private final ClienteRepository repository;

    public RegistroController(ClienteService clienteService, ClienteRepository repository) {
        this.clienteService = clienteService;
        this.repository = repository;
    }

    @ModelAttribute("cliente") //crea nuevo clienteDto
    public ClienteRegistroDto retornarNuevoUsuarioRegistroDto() {
        return new ClienteRegistroDto();
    }


    //para  el registro
    @GetMapping //muestra el formulario
    public String mostrarFormularioDeRegistro() {
        return "registro";
    }

    @PostMapping //registra el usuario y redirige.
    public String registrarUsuario(@ModelAttribute("cliente") ClienteRegistroDto clienteRegistroDto) {

        Cliente clienteRegistrado = repository.findByEmail(clienteRegistroDto.getEmail());
        boolean registrado = false;
        if(clienteRegistrado != null){
            registrado = true;

        }
        long tel = clienteRegistroDto.getTelefono();
        String telefono = String.valueOf(tel);
        int tamTel = telefono.length();
        if (tel == 0) {
            return "redirect:/registro?errorTelefono0";

        }
        if (tamTel < 6 || tamTel >= 12) {

            return "redirect:/registro?errorTelefonoLenght";

        }
       if (registrado == true) {
            return "redirect:/registro?error";
        }else {
            clienteService.saveRegistro(clienteRegistroDto);
            return "redirect:/registro?exito";
        }
    }

    //para  el registro
    @GetMapping("/nuevo") //muestra el formulario
    public String mostrarFormularioDeRegistro2() {
        return "registroClienteVendedor";
    }

    @PostMapping("/registrar") //registra el usuario y redirige.
    public String registrarUsuario2(@ModelAttribute("cliente") ClienteRegistroDto clienteRegistroDto) {

        Cliente clienteRegistrado = repository.findByEmail(clienteRegistroDto.getEmail());
        boolean registrado = false;
        if(clienteRegistrado != null){
            registrado = true;

        }
        long tel = clienteRegistroDto.getTelefono();
        String telefono = String.valueOf(tel);
        int tamTel = telefono.length();


        if (tel == 0) {
            return "redirect:/registro/nuevo?errorTelefono0";

        }
        if (tamTel < 6 || tamTel >= 12) {

            return "redirect:/registro/nuevo?errorTelefonoLenght";

        }
        if (registrado == true) {
            return "redirect:/registro/nuevo?error";
        }else {
            clienteService.saveRegistro(clienteRegistroDto);
            return "redirect:/registro/nuevo?exito";
        }
    }


}
