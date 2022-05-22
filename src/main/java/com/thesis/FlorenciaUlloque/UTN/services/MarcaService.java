package com.thesis.FlorenciaUlloque.UTN.services;

import com.thesis.FlorenciaUlloque.UTN.Dtos.MarcaDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.MarcaDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;

import java.util.List;

public interface MarcaService {
    Marca updateMarca(Marca marca);
    boolean deleteMarca(int id);
    List<MarcaDto> findAllMarcas();
    boolean saveMarca(MarcaDtos marcaDto);
    List<Proveedor> listaProveedores();
}
