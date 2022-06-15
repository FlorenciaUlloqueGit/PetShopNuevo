package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import com.thesis.FlorenciaUlloque.UTN.repositories.FormaPagoRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.SalidaProductosRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.usersRepositories.ClienteRepository;
import com.thesis.FlorenciaUlloque.UTN.services.SalidaProductosService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SalidaProductosServiceImpl implements SalidaProductosService {


    private final SalidaProductosRepository repository;
    private final ClienteRepository clienteRepository;
    private final FormaPagoRepository formaPagoRepository;

    public SalidaProductosServiceImpl(SalidaProductosRepository repository, ClienteRepository clienteRepository, FormaPagoRepository formaPagoRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.formaPagoRepository = formaPagoRepository;
    }


    @Override
    public SalidaProducto updateEgreso(SalidaProducto salidaProducto) {
        SalidaProducto returnValue;

        SalidaProducto salidaForUpdate = new SalidaProducto();

        salidaForUpdate.setIdEgreso(salidaProducto.getIdEgreso());
        salidaForUpdate.setTotal(salidaProducto.getTotal());
        salidaForUpdate.setFormaPago(salidaProducto.getFormaPago());
        salidaForUpdate.setFecha(salidaProducto.getFecha());
        salidaForUpdate.setCliente(salidaProducto.getCliente());
        salidaForUpdate.setListadoSalidaProducto(salidaProducto.getListadoSalidaProducto());

        returnValue = repository.save(salidaForUpdate);
        return returnValue;
    }

    @Override
    public boolean deleteEgreso(int id) {
        boolean existe = true;
        SalidaProducto salidaProducto = repository.findByIdEgreso(id);
        if (salidaProducto == null) {
            existe = false;
        }
        repository.delete(salidaProducto);
        return existe;
    }

    @Override
    public List<SalidaProducto> getAllEgresos() {
        List <SalidaProducto>listaReal  = repository.findAllByOrderByFechaDesc();

        return listaReal;
    }


    @Override
    public SalidaProducto saveEgreso(SalidaProducto salida) {

        LocalDate fecha = salida.getFecha();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaFormato = LocalDate.parse(fecha.format(formatter));

        salida.setTotal(0);
        salida.setIdEgreso(0);

        SalidaProducto salidaProducto = new SalidaProducto(salida.getIdEgreso(), salida.getTotal(), fechaFormato,
                salida.getFormaPago(), salida.getCliente(), salida.getListadoSalidaProducto());

        repository.save(salidaProducto);
        return salidaProducto;
    }

    @Override
    public List<Cliente> listaClientes() {
        List<Cliente> clienteList;
        clienteList = (List<Cliente>) clienteRepository.findAll();
        return  clienteList;
    }

    @Override
    public Page<SalidaProducto> getAllReporte(String month, Pageable pageable) {
        return repository.findEgresosByMonthPageable(month, pageable);
    }

    @Override
    public Page<SalidaProducto> getAll(Pageable pageable) {
        return repository.findAllByOrderByFechaDesc(pageable);
    }

    @Override
    public List<FormaPago> listaFormasPagos() {
        List<FormaPago> formaPagoList;
        formaPagoList = (List<FormaPago>) formaPagoRepository.findAll();
        return  formaPagoList;
    }
}
