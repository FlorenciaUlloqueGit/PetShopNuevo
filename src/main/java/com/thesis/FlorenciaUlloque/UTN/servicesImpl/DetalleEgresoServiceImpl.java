package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleEgreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import com.thesis.FlorenciaUlloque.UTN.exceptions.ErrorMessages;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleEgresoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleIngresoRepository;
import com.thesis.FlorenciaUlloque.UTN.services.DetalleEgresoService;
import com.thesis.FlorenciaUlloque.UTN.services.DetalleIngresoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleEgresoServiceImpl implements DetalleEgresoService {

    private final DetalleEgresoRepository repository;

    public DetalleEgresoServiceImpl(DetalleEgresoRepository repository) {
        this.repository = repository;
    }


    @Override
    public DetalleEgreso createDetalle(DetalleEgreso detalleEgreso) {

        if (repository.findByIdDetalle(detalleEgreso.getIdDetalleEgreso()) != null) {
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        }
        DetalleEgreso  newDetalle = new DetalleEgreso();

        BeanUtils.copyProperties(detalleEgreso, newDetalle);
      //  TODO: Para que tome el precio se lo debe plasmar antes en el json
        double precio = detalleEgreso.getCantidad() * detalleEgreso.getProducto().getPrecioVenta();
        newDetalle.setPrecio(precio);
        repository.save(newDetalle);
        return newDetalle;
    }

    @Override
    public List<DetalleEgreso> getAllDetalles(int page, int limit) {
        List<DetalleEgreso> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<DetalleEgreso> detallePage = repository.findAll(pageableRequest);
        List<DetalleEgreso> egresos = detallePage.getContent();


        for (DetalleEgreso egreso : egresos) {
            returnValue.add(egreso);
        }

        return returnValue;
    }

    @Override
    public DetalleEgreso updateDetalle(int id, DetalleEgreso detalleEgreso) {
        DetalleEgreso egresoForUpdate = repository.findByIdDetalle(id);
        if (egresoForUpdate == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        egresoForUpdate.setSalidaProducto(detalleEgreso.getSalidaProducto());
        egresoForUpdate.setCantidad(detalleEgreso.getCantidad());
        double precio = egresoForUpdate.getCantidad() * egresoForUpdate.getProducto().getPrecioVenta();
        egresoForUpdate.setPrecio(precio);
        egresoForUpdate.setProducto(detalleEgreso.getProducto());

        DetalleEgreso returnValue = repository.save(egresoForUpdate);

        return returnValue;
    }

    @Override
    public void deleteDetalle(int id) {
        DetalleEgreso egreso = repository.findByIdDetalle(id);

        if (egreso == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        repository.delete(egreso);


    }
}
