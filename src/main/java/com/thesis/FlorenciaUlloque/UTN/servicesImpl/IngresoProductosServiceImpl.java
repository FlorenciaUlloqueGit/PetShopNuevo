package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosIngresos.IngresoDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.FormaPago;
import com.thesis.FlorenciaUlloque.UTN.entiities.IngresoProductos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import com.thesis.FlorenciaUlloque.UTN.repositories.FormaPagoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.IngresoProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProveedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.IngresoProductosService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class IngresoProductosServiceImpl implements IngresoProductosService {

    private final IngresoProductosRepository repository;
    private final ProveedorRepository proveedorRepository;
    private final FormaPagoRepository formaPagoRepository;

    public IngresoProductosServiceImpl(IngresoProductosRepository repository, ProveedorRepository proveedorRepository, FormaPagoRepository formaPagoRepository) {
        this.repository = repository;
        this.proveedorRepository = proveedorRepository;
        this.formaPagoRepository = formaPagoRepository;
    }


    @Override
    public IngresoProductos updateIngreso(IngresoProductos ingresoProductos) {
        IngresoProductos returnValue;

        IngresoProductos ingresoForUpdate = new IngresoProductos();

        ingresoForUpdate.setIdIngreso(ingresoProductos.getIdIngreso());
        ingresoForUpdate.setTotal(ingresoProductos.getTotal());
        ingresoForUpdate.setFormaPago(ingresoProductos.getFormaPago());
        ingresoForUpdate.setFecha(ingresoProductos.getFecha());
        ingresoForUpdate.setProveedor(ingresoProductos.getProveedor());
        ingresoForUpdate.setListadoDetalleIngresos(ingresoProductos.getListadoDetalleIngresos());

        returnValue = repository.save(ingresoForUpdate);
        return returnValue;
    }

    @Override
    public boolean deleteIngreso(int id) {
        boolean existe = true;
        IngresoProductos ingresoProductos = repository.findByIdIngreso(id);
        if (ingresoProductos == null) {
            existe = false;
        }
        repository.delete(ingresoProductos);
        return existe;
    }

    @Override
    public List<IngresoProductos> getAllIngresos() {
        List <IngresoProductos>listaReal  = (List<IngresoProductos>) repository.findAll();

        return listaReal;
    }


    @Override
    public IngresoProductos saveIngreso(IngresoProductos ingreso) {

        LocalDate fecha = ingreso.getFecha();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaFormato = LocalDate.parse(fecha.format(formatter));

        ingreso.setTotal(0);
        ingreso.setIdIngreso(0);

            IngresoProductos ingresoProductos = new IngresoProductos(ingreso.getIdIngreso(), ingreso.getProveedor(), fechaFormato,
                    ingreso.getTotal(), ingreso.getFormaPago(), ingreso.getListadoDetalleIngresos());

            repository.save(ingresoProductos);
            return ingresoProductos;
    }

    @Override
    public List<Proveedor> listaProveedores() {
        List<Proveedor> proveedorList;
        proveedorList = (List<Proveedor>) proveedorRepository.findAll();
        return  proveedorList;
    }

    @Override
    public List<FormaPago> listaFormasPagos() {
        List<FormaPago> formaPagoList;
        formaPagoList = (List<FormaPago>) formaPagoRepository.findAll();
        return  formaPagoList;
    }
}
