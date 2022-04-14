package com.thesis.FlorenciaUlloque.UTN.servicesImpl;

import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import com.thesis.FlorenciaUlloque.UTN.exceptions.ErrorMessages;
import com.thesis.FlorenciaUlloque.UTN.repositories.MarcaRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProveedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.MarcaService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository repository;

    public MarcaServiceImpl(MarcaRepository repository) {
        this.repository = repository;
    }


    @Override
    public Marca createMarca(Marca marca) {

        if (repository.findByNombre(marca.getNombre()) != null) {
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        }
            Marca newMarca = new Marca();

            BeanUtils.copyProperties(marca, newMarca);
            repository.save(newMarca);
            return newMarca;
    }

    @Override
    public List<Marca> getAllMarcas(int page, int limit) {
        List<Marca> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Marca> MarcaPage = repository.findAll(pageableRequest);
        List<Marca> marcas = MarcaPage.getContent();


        for (Marca marca : marcas) {
            returnValue.add(marca);
        }

        return returnValue;
    }

    @Override
    public Marca updateMarca(long id, Marca marca) {
        Marca marcaForUpdate = repository.findByIdMarca(id);
        if (marcaForUpdate == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        marcaForUpdate.setNombre(marca.getNombre());
        marcaForUpdate.setProveedores(marca.getProveedores());  //no estoy segura de esta

        Marca returnValue = repository.save(marcaForUpdate);

        return returnValue;
    }

    @Override
    public void deleteMarca(long id) {
        Marca marca = repository.findByIdMarca(id);

        if (marca == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        repository.delete(marca);

    }
}
