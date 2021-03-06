package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.entiities.Administrador;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.AdministradorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.AdministradorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorRepository repository;

    public AdministradorServiceImpl(AdministradorRepository repository) {
        this.repository = repository;
    }




    @Override
    public List<Administrador> getAllAdministradores(int page, int limit) {
        List<Administrador> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Administrador> AdministradorPage = repository.findAll(pageableRequest);
        List<Administrador> admins = AdministradorPage.getContent();


        for (Administrador admin : admins) {
            returnValue.add(admin);
        }

        return returnValue;
    }


}
