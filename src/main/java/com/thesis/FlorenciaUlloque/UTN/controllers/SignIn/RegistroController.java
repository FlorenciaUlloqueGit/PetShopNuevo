package com.thesis.FlorenciaUlloque.UTN.controllers.SignIn;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteRegistroDto;
import com.thesis.FlorenciaUlloque.UTN.services.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    private final ClienteService clienteService;

    public RegistroController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @ModelAttribute("cliente") //crea nuevo clienteDto
    public ClienteRegistroDto retornarNuevoUsuarioRegistroDto(){
        return new ClienteRegistroDto();
    }


    //para  el registro
    @GetMapping //muestra el formulario
    public String mostrarFormularioDeRegistro(){
        return "registro";
    }

    @PostMapping //registra el usuario y redirige.
    public String registrarUsuario(@ModelAttribute("cliente") ClienteRegistroDto clienteRegistroDto){
            clienteService.saveRegistro(clienteRegistroDto);
            boolean registrado = clienteService.saveRegistro(clienteRegistroDto);
            if (registrado == true){
                return "redirect:/registro?error";
            }else {
                return "redirect:/registro?exito";
            }

        }



}
