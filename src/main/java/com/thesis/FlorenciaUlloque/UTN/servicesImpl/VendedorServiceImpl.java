package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.VendedorDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosUsuarios.VendedorDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Rol;
import com.thesis.FlorenciaUlloque.UTN.entiities.Vendedor;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.VendedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.VendedorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository repository;

    public VendedorServiceImpl(VendedorRepository repository) {
        this.repository = repository;
    }


    public List<VendedorDtos> findAllVendedores(){
       List <Vendedor>listaReal  = (List<Vendedor>) repository.findAll();
       List<VendedorDtos> listaDto = new ArrayList<>();

        VendedorDtos vendedorDtos ;
        for (Vendedor vendedor: listaReal) {
            vendedorDtos = new VendedorDtos();
            vendedorDtos.setIdVendedor(vendedor.getIdVendedor());
            vendedorDtos.setPass(vendedor.getPass());
            vendedorDtos.setUsuario(vendedor.getUsuario());
            listaDto.add(vendedorDtos);
        }

       return listaDto;
    }

    @Override
    public Vendedor updateVendedor(Vendedor vendedor) {
        Vendedor returnValue;

        Vendedor vendedorForUpdate = new Vendedor();

            vendedorForUpdate.setUsuario(vendedor.getUsuario());
            vendedorForUpdate.setPass(vendedor.getPass());
            vendedorForUpdate.setRol(new Rol(2,"vendedor"));
            vendedorForUpdate.setIdVendedor(vendedor.getIdVendedor());

            returnValue = repository.save(vendedorForUpdate);
        return returnValue;
    }

    @Override
    public boolean deleteVendedor(int id) {
        boolean existe = true;
        Vendedor vendedor = repository.findByIdVendedor(id);
        if (vendedor == null) {
           existe = false;
        }
        repository.delete(vendedor);
        return existe;
    }

    @Override
    public boolean saveVendedor(VendedorDto vendedorDto) {

        boolean registrado;
        if (repository.findByUsuario(vendedorDto.getUsuario()) != null) {
            registrado = true;
        } else{
            registrado = false;
            vendedorDto.setRol(new Rol(2, "vendedor"));
            Vendedor newVendedor = new Vendedor(vendedorDto.getUsuario(), vendedorDto.getPass(),
                    vendedorDto.getRol());

            repository.save(newVendedor);
        }
        return registrado;
    }
}
