package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.ClienteRegistroDto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.Rol;
import com.thesis.FlorenciaUlloque.UTN.exceptions.ErrorMessages;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.ClienteRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ClienteService;
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
    public Cliente updateCliente(Cliente clienteRegistroDto) {

        long dni = clienteRegistroDto.getDni();
        Cliente returnValue;
        Cliente clienteForUpdate = repository.findByDni(dni);
        if (clienteForUpdate == null) {
           // throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
            returnValue = null;
        } else{
            clienteForUpdate.setDni(dni);
            clienteForUpdate.setApellido(clienteRegistroDto.getApellido());
            clienteForUpdate.setNombre(clienteRegistroDto.getNombre());
            clienteForUpdate.setTelefono(clienteRegistroDto.getTelefono());
            clienteForUpdate.setDireccion(clienteRegistroDto.getDireccion());
            clienteForUpdate.setEnabled(clienteRegistroDto.isEnabled());
            clienteForUpdate.setEmail(clienteRegistroDto.getEmail());

            returnValue = repository.save(clienteForUpdate);
        }
        return returnValue;
    }

    @Override
    public void deleteCliente(int id) {
        Cliente cliente = repository.findByIdCliente(id);

        if (cliente == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        cliente.setEnabled(false);
        repository.save(cliente);

    }


//si no anda volver a findAllByOrderByNOmbreASc2
    public List<ClienteDto> findAllClientes(){
        List <Cliente>listaReal  =  repository.findAllByOrderByNombreAsc2();
        List<ClienteDto> listaDto = new ArrayList<>();

        ClienteDto clienteDto ;
        for (Cliente cliente : listaReal) {
            clienteDto = new ClienteDto();
            clienteDto.setIdCliente(cliente.getIdCliente());
            clienteDto.setNombre(cliente.getNombre());
            clienteDto.setApellido(cliente.getApellido());
            clienteDto.setEmail(cliente.getEmail());
            clienteDto.setTelefono(cliente.getTelefono());
            clienteDto.setDireccion(cliente.getDireccion());
            clienteDto.setEnabled(true);
            clienteDto.setDni(cliente.getDni());
            listaDto.add(clienteDto);
        }

        return listaDto;
    }

    @Override
    public Page<Cliente> getAll(Pageable pageable) {
        return repository.findAllClientesEnabled(pageable);
    }

    @Override
    public boolean saveRegistro(ClienteRegistroDto clienteRegistroDto) {
        boolean registrado = false;

        if (repository.findByEmail(clienteRegistroDto.getEmail()) != null) {
            registrado = true;
            //   throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        } else{

            clienteRegistroDto.setRol(new Rol(1, "cliente"));

            Cliente  newCliente = new Cliente(clienteRegistroDto.getEmail(),
                    clienteRegistroDto.getNombre(), clienteRegistroDto.getApellido(), clienteRegistroDto.getTelefono(),
                    clienteRegistroDto.getDireccion(), clienteRegistroDto.getRol(), true,
                    clienteRegistroDto.getDni());
            repository.save(newCliente);

        }
        return registrado;
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        return null;
    }


}
