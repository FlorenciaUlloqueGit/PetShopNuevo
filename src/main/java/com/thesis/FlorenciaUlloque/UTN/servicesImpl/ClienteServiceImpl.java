package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.exceptions.ErrorMessages;
import com.thesis.FlorenciaUlloque.UTN.repositories.ClienteRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }


    @Override
    public Cliente createCliente(Cliente cliente) {



        if (repository.findByEmail(cliente.getEmail()) != null) {
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        }
            Cliente  newCliente = new Cliente();

            BeanUtils.copyProperties(cliente, newCliente);
            repository.save(newCliente);
            return newCliente;
    }

    @Override
    public List<Cliente> getAllCliente(int page, int limit) {

        List<Cliente> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Cliente> clientePage   = repository.findAll(pageableRequest);
        List<Cliente> clientes = clientePage.getContent();


        for (Cliente cliente : clientes) {
            returnValue.add(cliente);
        }

        return returnValue;
    }

    @Override
    public Cliente updateCliente(int id, Cliente cliente) {
        Cliente clienteForUpdate = repository.findByIdCliente(id);
        if (clienteForUpdate == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }


        clienteForUpdate.setEmail(cliente.getEmail());
        clienteForUpdate.setApellido(cliente.getApellido());
        clienteForUpdate.setNombre(cliente.getNombre());
        clienteForUpdate.setTelefono(cliente.getTelefono());

        Cliente returnValue = repository.save(clienteForUpdate);

        return returnValue;
    }

    @Override
    public void deleteCliente(int id) {
        Cliente cliente = repository.findByIdCliente(id);

        if (cliente == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        repository.delete(cliente);


    }
}
