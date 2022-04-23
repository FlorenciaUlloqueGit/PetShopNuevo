package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.entiities.SalidaProducto;
import com.thesis.FlorenciaUlloque.UTN.exceptions.ErrorMessages;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.SalidaProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.services.IngresoProductosService;
import com.thesis.FlorenciaUlloque.UTN.services.SalidaProductosService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalidaProductosServiceImpl implements SalidaProductosService {

    private final SalidaProductosRepository repository;

    public SalidaProductosServiceImpl(SalidaProductosRepository repository) {
        this.repository = repository;
    }



    @Override
    public SalidaProducto createSalida(SalidaProducto salida) {

        if (repository.findByIdEgreso(salida.getIdEgreso()) != null) {
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        }
        SalidaProducto newSalida = new SalidaProducto();

        BeanUtils.copyProperties(salida, newSalida);
        repository.save(newSalida);
        return newSalida;
    }

    @Override
    public List<SalidaProducto> getAllSalidas(int page, int limit) {
        List<SalidaProducto> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<SalidaProducto> clientePage   = repository.findAll(pageableRequest);
        List<SalidaProducto> salidas = clientePage.getContent();


        for (SalidaProducto salida : salidas) {
            returnValue.add(salida);
        }

        return returnValue;
    }

    @Override
    public SalidaProducto updateSalidas(int id, SalidaProducto salidaProducto) {
        SalidaProducto salidaForUpdate = repository.findByIdEgreso(id);
        if (salidaForUpdate == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        salidaForUpdate.setFormaPago(salidaProducto.getFormaPago());
        salidaForUpdate.setFecha(salidaProducto.getFecha());
       // salidaForUpdate.setCliente(salidaProducto.getCliente());
        salidaForUpdate.setTotal(salidaProducto.getTotal());

        SalidaProducto returnValue = repository.save(salidaForUpdate);

        return returnValue;
    }

    @Override
    public void deleteSalidas(int id) {
        SalidaProducto salida = repository.findByIdEgreso(id);

        if (salida == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        repository.delete(salida);

    }
}
