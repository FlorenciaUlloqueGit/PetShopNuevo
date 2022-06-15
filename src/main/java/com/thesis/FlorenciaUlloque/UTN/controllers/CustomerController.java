package com.thesis.FlorenciaUlloque.UTN.controllers;

import com.lowagie.text.DocumentException;
import com.thesis.FlorenciaUlloque.UTN.Dtos.SoloFecha;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosEgresos.DetalleEgresoDtoIdEgreso;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteRegistroDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.soloEmail;
import com.thesis.FlorenciaUlloque.UTN.Util.UsersPDfExporterListadoClientes;
import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleEgreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.SalidaProducto;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.ClienteRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/customer")
public class CustomerController {


    private final ClienteService clienteService;
    private final ClienteRepository repository;

    public CustomerController(ClienteService clienteService, ClienteRepository repository) {
        this.clienteService = clienteService;
        this.repository = repository;
    }


    @ModelAttribute("clienteReal")
    public Cliente mapearClienteREal() {
        return new Cliente();
    }
    @ModelAttribute("soloEmail")
    public soloEmail mapearMeses() {
        return new soloEmail();
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

    Cliente cliente ;
    @GetMapping("/buscarEmail")
    public String buscarMesPorFecha(@ModelAttribute("soloEmail") soloEmail soloEmail) {



        if (repository.findByEmail(soloEmail.getEmail()) == null) {

            return "redirect:/customer/listarClientesAdmin?errorEmail";
        }else{
            cliente = repository.findByEmail(soloEmail.getEmail());
            return "redirect:/customer/listadoFiltradoCliente";
        }
    }

    @GetMapping("/listadoFiltradoCliente")
    public String listar(Model model){

        model.addAttribute("customer",cliente);
        return "listadoFiltradoCliente";
    }

    @GetMapping({"/listar", "/"})
    public String listarClientes(@RequestParam Map<String, Object> params, Model model){
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) :0;
        PageRequest pageRequest = PageRequest.of(page, 7);
        Page<Cliente> pageCliente = clienteService.getAll(pageRequest);

        int totalPage = pageCliente.getTotalPages();
        if(totalPage> 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages",pages);
        }
        model.addAttribute("customer", pageCliente.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "listadoClientes";
    }



    @GetMapping("/listarClientesAdmin")
    public String listarClientes2(@RequestParam Map<String, Object> params, Model model){
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) :0;
        PageRequest pageRequest = PageRequest.of(page, 7);
        Page<Cliente> pageCliente = clienteService.getAll(pageRequest);

        int totalPage = pageCliente.getTotalPages();
        if(totalPage> 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages",pages);
        }
        model.addAttribute("customer", pageCliente.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "listadoClientesAdmin";
    }

    @GetMapping("/listarClientesPetShop")
    public String listarClientesPetshop(@RequestParam Map<String, Object> params, Model model){
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) :0;
        PageRequest pageRequest = PageRequest.of(page, 7);
        Page<Cliente> pageCliente = clienteService.getAll(pageRequest);

        int totalPage = pageCliente.getTotalPages();
        if(totalPage> 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages",pages);
        }
        model.addAttribute("customer", pageCliente.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "listadoClientesVendedor2";
    }

    @GetMapping("/delete/{idCliente}")
    public String deleteVendedor2( @PathVariable int idCliente){
        clienteService.deleteCliente(idCliente);
        return "redirect:/customer/listarClientesAdmin?exito";

    }
    Cliente clienteNuevo ;
    @GetMapping("/buscarEmailCliente")
    public String buscarMesPorFecha2(@ModelAttribute("soloEmail") soloEmail soloEmail) {


        if (repository.findByEmail(soloEmail.getEmail()) == null) {

            return "redirect:/customer/listarClientesVendedor?errorEmail";
        }else{
            clienteNuevo = repository.findByEmail(soloEmail.getEmail());
            return "redirect:/customer/listadoFiltradoClienteVendedor";
        }
    }

    @GetMapping("/listadoFiltradoClienteVendedor")
    public String listar2(Model model){

        model.addAttribute("customer",clienteNuevo);
        return "listadoFiltradoClienteVendedor";
    }

    @GetMapping("/listarClientesVendedor")
    public String listarClientesvendedor(@RequestParam Map<String, Object> params, Model model){
    int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) :0;
    PageRequest pageRequest = PageRequest.of(page, 7);
    Page<Cliente> pageCliente = clienteService.getAll(pageRequest);

    int totalPage = pageCliente.getTotalPages();
        if(totalPage> 0){
        List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
        model.addAttribute("pages",pages);
    }
        model.addAttribute("customer", pageCliente.getContent());
        model.addAttribute("current", page + 1);
        model.addAttribute("next", page + 2);
        model.addAttribute("prev", page);
        model.addAttribute("last", totalPage);

        return "listadoClientesVendedor";
}




    @GetMapping("/updateFromVendedor/{idCliente}") //update cliente
    public String mostrarformUpdateCliente2(@PathVariable int idCliente, Model model){{
        model.addAttribute("cliente", repository.findByIdCliente(idCliente));
    }
        return "updateClienteFromVendedor";
    }


    @PostMapping("/updateClienteFromVendedor/{idCliente}")
    public String updatearCliente2(@ModelAttribute("cliente") ClienteRegistroDto clienteRegistroDto,
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
            return "redirect:/customer/updateFromVendedor/{idCliente}?error";
        } else {
            clienteService.updateCliente(clienteExiste);
            return "redirect:/customer/updateFromVendedor/{idCliente}?exito";
        }

    }

    @GetMapping("/listarClientesPetShop/export")
    public void exportToPDFListaClientes(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.pdf";

        response.setHeader(headerKey, headerValue);

        List<ClienteDto> listUsers = clienteService.findAllClientes();
        UsersPDfExporterListadoClientes exporter = new UsersPDfExporterListadoClientes(listUsers);
        exporter.export(response);


    }


}
