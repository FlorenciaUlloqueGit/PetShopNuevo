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

            boolean registrado = clienteService.saveRegistro(clienteRegistroDto);
            long tel = clienteRegistroDto.getTelefono();
            String telefono = String.valueOf(tel);
           int tamTel =  telefono.length();

            if(tel == 0) {
                return "redirect:/registro?errorTelefono0";
            }
        if(tamTel < 6 || tamTel >= 12) {
            return "redirect:/registro?errorTelefonoLenght";
        }



        if (registrado == true){
                return "redirect:/registro?errorTelefonoLenght";
            }else {
                clienteService.saveRegistro(clienteRegistroDto);
                return "redirect:/registro?exito";
            }

        }



}
