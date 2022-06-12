package com.thesis.FlorenciaUlloque.UTN.controllers.SignIn;


import com.thesis.FlorenciaUlloque.UTN.Configurations.UsuarioLogueado;
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

    /*

        @RequestMapping(value="/index", method = RequestMethod.GET)
        public String getLoginCliente(){
            return "index";
        }
        //cheching login credentials

        @RequestMapping(value="/index", method = RequestMethod.POST)
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

     */
    @RequestMapping(value = "/preguntasFrecuentesAdmin", method = RequestMethod.GET)
    public String getPregFrecuentesAdmin() {
        return "PreguntasFrecuentesAdmin";
    }

    @RequestMapping(value = "/preguntasFrecuentesVendedor", method = RequestMethod.GET)
    public String getPregFrecuenteVendedor() {
        return "PreguntasFrecuentesVendedor";
    }


    @RequestMapping(value = "/loginAdmin", method = RequestMethod.GET)
    public String getLoginAdmin() {
        return "loginAdmin";
    }
    //cheching login credentials

    @RequestMapping(value = "/homeAdmin", method = RequestMethod.GET)
    public String getHomeAdmin() {
        return "homeAdmin";
    }

    @RequestMapping(value = "/homeVendedor", method = RequestMethod.GET)
    public String getHomeVendedor() {
        return "homeVendedor";
    }
    //cheching login credentials

    @RequestMapping(value = "/loginAdministrador", method = RequestMethod.POST)
    public String loginAdmin(@ModelAttribute(name = "administrador") Administrador administrador, Model model) {

        String usuario = administrador.getUsuario();
        Administrador adminDb;

        adminDb = administradorRepository.findByUsuario(usuario);


        boolean logueado = false;

        if (adminDb == null) {
            logueado = false;
            return "redirect:/loginAdmin?error";
        }
        if (adminDb.getUsuario().equals(administrador.getUsuario()) && adminDb.getPass().equals(administrador.getPass())) {
            //if email y pass are correct
            logueado = true;
            UsuarioLogueado usuarioLogueado = new UsuarioLogueado();
            UsuarioLogueado usuarioLog = usuarioLogueado.getThisInstance();
            usuarioLog.setUsuario(administrador.getUsuario());
            //   usuarioLog.setIdRol(administrador.getRol().getIdRol());
            usuarioLog.setLogueado(logueado);

            return "homeAdmin";

        } else {
            model.addAttribute("invalidCredentials", true);
            //returnin again a login page
            return "redirect:/loginAdmin?error";
        }

    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getLoginVendedor() {
        return "index";
    }
    //cheching login credentials

    @RequestMapping(value = "/terminosCondiciones", method = RequestMethod.GET)
    public String getLoginVendedor2() {
        return "TerminosCondiciones";
    }

    @RequestMapping(value = "/terminosYCondiciones", method = RequestMethod.GET)
    public String getLoginVendedor3() {
        return "TerminosCondicionesVendedor";
    }
    //cheching login credentials

    @RequestMapping(value = "/indexVendedor", method = RequestMethod.POST)
    public String loginVendedor(@ModelAttribute(name = "vendedor") Vendedor vendedor, Model model) {

        String usuario = vendedor.getUsuario();
        Vendedor vendedorDb;

        vendedorDb = vendedoRepository.findByUsuario(usuario);

        if (vendedorDb == null) {
            return "redirect:/index?error";
        }
        if (vendedorDb.getUsuario().equals(vendedor.getUsuario()) && vendedorDb.getPass().equals(vendedor.getPass())) {
            //if email y pass are correct
            return "homeVendedor";

        } else {
            return "redirect:/index?error";
        }

    }

    boolean logoutVendedor = false;
    boolean logoutAdmin = false;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUsuario() {
        UsuarioLogueado usuarioLogueado = new UsuarioLogueado();
        usuarioLogueado = usuarioLogueado.getThisInstance();

        if (usuarioLogueado.isLogueado() == true) {

            usuarioLogueado.setLogueado(false);
            logoutAdmin = true;
            return "redirect:/loginAdmin?logoutAdmin";

        } else {
            return "redirect:/loginAdmin?logoutAdminError";
        }
    }


    @RequestMapping(value = "/logoutVendedor", method = RequestMethod.GET)
    public String logoutUsuarioVendedor() {
        UsuarioLogueado usuarioLogueado = new UsuarioLogueado();
        usuarioLogueado = usuarioLogueado.getThisInstance();

        if (usuarioLogueado.isLogueado() == true) {

            usuarioLogueado.setLogueado(false);
            logoutVendedor = true;
            return "redirect:/index?logoutVendedor";

        } else {
            return "redirect:/index?logoutErrorVendedor";
        }


    }
    

}
