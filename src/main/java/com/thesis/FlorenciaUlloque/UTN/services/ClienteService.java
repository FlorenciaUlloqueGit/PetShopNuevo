package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente createCliente(Cliente cliente);
    List<Cliente> getAllCliente(int page, int limit);
    Cliente updateCliente(int id, Cliente cliente);
    void deleteCliente(int id);
}
