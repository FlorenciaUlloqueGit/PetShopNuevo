package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteRegistroDto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {

    List<Cliente> getAllCliente(int page, int limit);
    Cliente updateCliente(Cliente clienteRegistroDto);
    void deleteCliente(int id);

    boolean saveRegistro(ClienteRegistroDto clienteRegistroDto);

    Cliente createCliente(Cliente cliente);

    List<ClienteDto>findAllClientes();
    Page<Cliente> getAll(Pageable pageable);
}
