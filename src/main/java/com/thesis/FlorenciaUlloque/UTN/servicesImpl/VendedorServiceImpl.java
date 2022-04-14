package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.entiities.Vendedor;
import com.thesis.FlorenciaUlloque.UTN.exceptions.ErrorMessages;
import com.thesis.FlorenciaUlloque.UTN.repositories.VendedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.VendedorService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendedorServiceImpl implements VendedorService {

    private final VendedorRepository repository;

    public VendedorServiceImpl(VendedorRepository repository) {
        this.repository = repository;
    }



    @Override
    public Vendedor createVendedor(Vendedor vendedor) {

        if (repository.findByUsuario(vendedor.getUsuario()) != null) {
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        }
        Vendedor newVendedor  = new Vendedor();

        BeanUtils.copyProperties(vendedor, newVendedor);
        repository.save(newVendedor);
        return newVendedor;

    }

    @Override
    public List<Vendedor> getAllVendedores(int page, int limit) {
        List<Vendedor> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Vendedor> clientePage   = repository.findAll(pageableRequest);
        List<Vendedor> vendedores = clientePage.getContent();


        for (Vendedor vendedor : vendedores) {
            returnValue.add(vendedor);
        }

        return returnValue;
    }

    @Override
    public Vendedor updateVendedor(int idVendedor, Vendedor vendedor) {
        Vendedor vendedorForUpdate = repository.findByIdVendedor(idVendedor);
        if (vendedorForUpdate == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        vendedorForUpdate.setNombre(vendedor.getNombre());
        vendedorForUpdate.setUsuario( vendedor.getUsuario());
        vendedorForUpdate.setPass(vendedor.getPass());

        Vendedor returnValue = repository.save(vendedorForUpdate);

        return returnValue;
    }

    @Override
    public void deleteVendedor(int id) {
        Vendedor vendedor = repository.findByIdVendedor(id);
        if (vendedor == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        repository.delete(vendedor);
    }

}
