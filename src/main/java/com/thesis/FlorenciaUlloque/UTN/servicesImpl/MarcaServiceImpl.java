package com.thesis.FlorenciaUlloque.UTN.servicesImpl;

import com.thesis.FlorenciaUlloque.UTN.Dtos.MarcaDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.MarcaDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;
import com.thesis.FlorenciaUlloque.UTN.repositories.MarcaRepository;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProveedorRepository;
import com.thesis.FlorenciaUlloque.UTN.services.MarcaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository repository;
    private final ProveedorRepository proveedorRepository;

    public MarcaServiceImpl(MarcaRepository repository, ProveedorRepository proveedorRepository) {
        this.repository = repository;
        this.proveedorRepository = proveedorRepository;
    }

    public List<MarcaDto> findAllMarcas(){
        List <Marca>listaReal  = repository.findAllByOrderByNombreAsc();
        List<MarcaDto> listaDto = new ArrayList<>();

        MarcaDto marcaDto;
        for (Marca marca: listaReal) {
            marcaDto = new MarcaDto();
            marcaDto.setNombre(marca.getNombre());
            marcaDto.setProveedor(marca.getProveedor());
            marcaDto.setIdMarca(marca.getIdMarca());
            listaDto.add(marcaDto);
        }

        return listaDto;
    }

    @Override
    public Marca updateMarca(Marca marca) {
        Marca returnValue;

        Marca marcaForUpdate = new Marca();

        marcaForUpdate.setNombre(marca.getNombre());
        marcaForUpdate.setIdMarca(marca.getIdMarca());
        marcaForUpdate.setProveedor(marca.getProveedor());

        returnValue = repository.save(marcaForUpdate);
        return returnValue;
    }

    @Override
    public boolean deleteMarca(int id) {
        boolean existe = true;
        Marca marca = repository.findByIdMarca(id);
        if (marca == null) {
            existe = false;
        }
        repository.delete(marca);
        return existe;
    }

    @Override
    public boolean saveMarca(MarcaDtos marcaDtos) {

        boolean registrado;
        if (repository.findByNombre(marcaDtos.getNombre()) != null) {
            registrado = true;
        } else{
            registrado = false;
            Marca newMarca = new Marca(marcaDtos.getNombre(), marcaDtos.getProveedor());

            repository.save(newMarca);
        }
        return registrado;
    }

    @Override
    public List<Proveedor> listaProveedores() {
        List<Proveedor> proveedorList;
        proveedorList = (List<Proveedor>) proveedorRepository.findAll();
        return  proveedorList;
    }

    @Override
    public Page<Marca> getAll(Pageable pageable) {
        return repository.findAllByOrderByNombreAsc(pageable);
    }
}
