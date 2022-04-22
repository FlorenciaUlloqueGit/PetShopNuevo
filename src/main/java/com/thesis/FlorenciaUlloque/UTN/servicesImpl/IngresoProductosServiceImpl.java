package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;
import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.exceptions.ErrorMessages;
import com.thesis.FlorenciaUlloque.UTN.repositories.ClienteRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ClienteService;
import com.thesis.FlorenciaUlloque.UTN.services.IngresoProductosService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngresoProductosServiceImpl implements IngresoProductosService {

    private final IngresoProductosRepository repository;

    public IngresoProductosServiceImpl(IngresoProductosRepository repository) {
        this.repository = repository;
    }


    @Override
    public IngresoProductos createIngreso(IngresoProductos ingresoProductos) {

        if (repository.findByIdIngreso(ingresoProductos.getIdIngreso()) != null) {
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        }
        IngresoProductos  newIngreso = new IngresoProductos();

        BeanUtils.copyProperties(ingresoProductos, newIngreso);
        repository.save(newIngreso);
        return newIngreso;
    }

    @Override
    public List<IngresoProductos> getAllIngresos(int page, int limit) {

        List<IngresoProductos> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<IngresoProductos> clientePage   = repository.findAll(pageableRequest);
        List<IngresoProductos> ingresos = clientePage.getContent();


        for (IngresoProductos ingreso : ingresos) {
            returnValue.add(ingreso);
        }

        return returnValue;
    }


    @Override
    public IngresoProductos updateIngreso(int id, IngresoProductos ingreso) {
        IngresoProductos ingresoForUpdate = repository.findByIdIngreso(id);
        if (ingresoForUpdate == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        ingresoForUpdate.setFormaPago(ingreso.getFormaPago());
        ingresoForUpdate.setFecha(ingreso.getFecha());
        ingresoForUpdate.setProveedor(ingreso.getProveedor());
        ingresoForUpdate.setTotal(ingreso.getTotal());

        IngresoProductos returnValue = repository.save(ingresoForUpdate);

        return returnValue;
    }

    @Override
    public void deleteIngreso(int id) {
        IngresoProductos ingresoProductos = repository.findByIdIngreso(id);

        if (ingresoProductos == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        repository.delete(ingresoProductos);

    }
}
