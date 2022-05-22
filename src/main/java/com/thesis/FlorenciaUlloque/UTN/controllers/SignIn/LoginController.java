package com.thesis.FlorenciaUlloque.UTN.controllers.SignIn;


import com.thesis.FlorenciaUlloque.UTN.entiities.Administrador;
import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.Vendedor;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.AdministradorRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.ClienteRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.VendedorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final ClienteRepository repository;
    private final VendedorRepository vendedoRepository;
    private final AdministradorRepository administradorRepository;

    public LoginController(ClienteRepository repository, VendedorRepository vendedoRepository, AdministradorRepository administradorRepository) {
        this.repository = repository;
        this.vendedoRepository = vendedoRepository;
        this.administradorRepository = administradorRepository;
    }


    @RequestMapping(value="/loginNuevo", method = RequestMethod.GET)
    public String getLoginCliente(){
        return "loginNuevo";
    }
    //cheching login credentials

    @RequestMapping(value="/loginNuevo", method = RequestMethod.POST)
    public String loginCliente (@ModelAttribute(name = "cliente") Cliente cliente, Model model){

        String email = cliente.getEmail();
        System.out.println(cliente.getEmail());
        System.out.println(cliente.getPass());
        Cliente clienteDB;

        clienteDB = repository.findByEmail(email);

        if(clienteDB.getEmail().equals(cliente.getEmail()) && clienteDB.getPass().equals(cliente.getPass())) {
            //if email y pass are correct
            return "homeCliente";

        } else {
            model.addAttribute("invalidCredentials", true);
            //returnin again a login page
            return "redirect:/loginNuevo?error";
        }

    }



    @RequestMapping(value="/loginAdmin", method = RequestMethod.GET)
    public String getLoginAdmin(){
        return "loginAdmin";
    }
    //cheching login credentials

    @RequestMapping(value="/loginAdmin", method = RequestMethod.POST)
    public String loginAdmin (@ModelAttribute(name = "administrador") Administrador administrador, Model model){

        String usuario = administrador.getUsuario();
        Administrador adminDb;

        adminDb = administradorRepository.findByUsuario(usuario);

        if(adminDb.getUsuario().equals(administrador.getUsuario()) && adminDb.getPass().equals(administrador.getPass())) {
            //if email y pass are correct
            return "homeAdmin";

        } else {
            model.addAttribute("invalidCredentials", true);
            //returnin again a login page
            return "redirect:/loginAdmin?error";
        }

    }


    @RequestMapping(value="/loginVendedor", method = RequestMethod.GET)
    public String getLoginVendedor(){
        return "loginVendedor";
    }
    //cheching login credentials

    @RequestMapping(value="/loginVendedor", method = RequestMethod.POST)
    public String loginVendedor (@ModelAttribute(name = "vendedor") Vendedor vendedor, Model model){

        String usuario = vendedor.getUsuario();
        Vendedor vendedorDb;

        vendedorDb = vendedoRepository.findByUsuario(usuario);

        if(vendedorDb.getUsuario().equals(vendedor.getUsuario()) && vendedorDb.getPass().equals(vendedor.getPass())) {
            //if email y pass are correct
            return "homeVendedor";

        } else {
            model.addAttribute("invalidCredentials", true);
            //returnin again a login page
            return "redirect:/loginVendedor?error";
        }

    }


}
