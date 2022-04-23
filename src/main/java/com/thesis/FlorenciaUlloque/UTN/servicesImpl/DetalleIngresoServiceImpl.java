package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.sun.xml.bind.v2.TODO;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.exceptions.ErrorMessages;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleIngresoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.services.DetalleIngresoService;
import com.thesis.FlorenciaUlloque.UTN.services.IngresoProductosService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleIngresoServiceImpl implements DetalleIngresoService {

    private final DetalleIngresoRepository repository;

    public DetalleIngresoServiceImpl(DetalleIngresoRepository repository) {
        this.repository = repository;
    }


    @Override
    public DetalleIngreso createDetalle(DetalleIngreso detalleIngreso) {

        if (repository.findByIdDetalle(detalleIngreso.getIdDetalle()) != null) {
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        }
        DetalleIngreso  newDetalle = new DetalleIngreso();

        BeanUtils.copyProperties(detalleIngreso, newDetalle);
      //  TODO: Para que tome el precio se lo debe plasmar antes en el json
        double precio = detalleIngreso.getCantidad() * detalleIngreso.getProducto().getPrecioCompra();
        newDetalle.setPrecio(precio);
        repository.save(newDetalle);
        return newDetalle;
    }

    @Override
    public List<DetalleIngreso> getAllDetalles(int page, int limit) {
        List<DetalleIngreso> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<DetalleIngreso> detallePage   = repository.findAll(pageableRequest);
        List<DetalleIngreso> ingresos = detallePage.getContent();


        for (DetalleIngreso ingreso : ingresos) {
            returnValue.add(ingreso);
        }

        return returnValue;
    }

    @Override
    public DetalleIngreso updateDetalle(int id, DetalleIngreso detalleIngreso) {
        DetalleIngreso ingresoForUpdate = repository.findByIdDetalle(id);
        if (ingresoForUpdate == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        ingresoForUpdate.setIngresoProductos(detalleIngreso.getIngresoProductos());
        ingresoForUpdate.setCantidad(detalleIngreso.getCantidad());
        double precio = ingresoForUpdate.getCantidad() * ingresoForUpdate.getProducto().getPrecioCompra();
        ingresoForUpdate.setPrecio(precio);
        ingresoForUpdate.setProducto(detalleIngreso.getProducto());

        DetalleIngreso returnValue = repository.save(ingresoForUpdate);

        return returnValue;
    }

    @Override
    public void deleteDetalle(int id) {
        DetalleIngreso ingresoProductos = repository.findByIdDetalle(id);

        if (ingresoProductos == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        repository.delete(ingresoProductos);


    }
}
