package com.thesis.FlorenciaUlloque.UTN.servicesImpl;

import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import com.thesis.FlorenciaUlloque.UTN.exceptions.ErrorMessages;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProveedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ProveedorService;
import org.springframework.beans.BeanUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorServiceImpl  implements ProveedorService {

    private final ProveedorRepository repository;

    public ProveedorServiceImpl(ProveedorRepository repository) {
        this.repository = repository;
    }



    @Override
    public Proveedor createProveedor(Proveedor proveedor) {
        //si no es nulo, osea si ya el email existe, lanza la excerpción.


        if (repository.findById(proveedor.getIdProveedor()) != null) {
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        }
            Proveedor nuevoProveedor = new Proveedor();

            BeanUtils.copyProperties(proveedor, nuevoProveedor);
            repository.save(nuevoProveedor);
            return nuevoProveedor;

        }


        @Override
        public List<Proveedor> getAllProveedores ( int page, int limit){
            List<Proveedor> returnValue = new ArrayList<>();
            Pageable pageableRequest = PageRequest.of(page, limit);
            Page<Proveedor> proveedorPage = repository.findAll(pageableRequest);
            List<Proveedor> proveedors = proveedorPage.getContent();


            for (Proveedor proveedor1 : proveedors) {
                returnValue.add(proveedor1);
            }

            return returnValue;
        }

        @Override
        public Proveedor updateProveedor ( long id, Proveedor proveedor){

            Proveedor proveedorForUpdate = repository.findById(id);
            if (proveedorForUpdate == null) {
                throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
            }


            proveedorForUpdate.setNombre(proveedor.getNombre());
            proveedorForUpdate.setTelefono(proveedor.getTelefono());
            //    proveedorForUpdate.setMarcas(proveedor.getMarcas());  //no estoy segura de esta


            Proveedor returnValue = repository.save(proveedorForUpdate);

            return returnValue;
        }


    @Override
    public void deleteProveedor(long id) {
        Proveedor proveedorForDelete = repository.findById(id);

        if (proveedorForDelete == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        repository.delete(proveedorForDelete);

    }


}
