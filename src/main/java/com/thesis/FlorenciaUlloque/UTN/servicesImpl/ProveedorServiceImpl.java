package com.thesis.FlorenciaUlloque.UTN.servicesImpl;

import com.thesis.FlorenciaUlloque.UTN.Dtos.ProveedorDto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProveedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ProveedorService;

import org.springframework.data.domain.Page;
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

    public Page<Proveedor> getAll(Pageable pageable){
        return repository.findAllByOrderByNombre(pageable);
    }

    public List<Proveedor> findAllProveedor(){
        List <Proveedor>listaReal  = repository.findAllByOrderByNombreAsc();
        List<Proveedor> listaDto = new ArrayList<>();

        Proveedor proveedor;
        for (Proveedor Prov: listaReal) {
            proveedor = new Proveedor();
            proveedor.setIdProveedor((int) Prov.getIdProveedor());
            proveedor.setNombre(Prov.getNombre());
            proveedor.setTelefono(Prov.getTelefono());
            listaDto.add(proveedor);
        }

        return listaDto;
    }

    @Override
    public boolean deleteProveedor(int id) {
        boolean existe = true;
        Proveedor proveedor = repository.findById(id);
        if (proveedor == null) {
            existe = false;
        }
        proveedor.setEnabled(false);
        repository.save(proveedor);
        return existe;
    }


    @Override
    public Proveedor updateProveedor(Proveedor proveedor) {
        Proveedor returnValue;

        Proveedor proveedorForUpdate = new Proveedor();

        proveedorForUpdate.setNombre(proveedor.getNombre());
        proveedorForUpdate.setTelefono(proveedor.getTelefono());
        proveedorForUpdate.setIdProveedor((int) proveedor.getIdProveedor());
        proveedorForUpdate.setRepresentante(proveedor.getRepresentante());
        proveedorForUpdate.setEnabled(proveedor.isEnabled());

        returnValue = repository.save(proveedorForUpdate);
        return returnValue;
    }


    @Override
    public boolean saveProveedor(ProveedorDto proveedor) {

        boolean registrado;
        if (repository.findByNombre(proveedor.getNombre()) != null) {
            registrado = true;
        } else{
            registrado = false;
            Proveedor newProveedor = new Proveedor(proveedor.getNombre(), proveedor.getTelefono(), proveedor.getRepresentante(), true);

            repository.save(newProveedor);
        }
        return registrado;
    }
}
