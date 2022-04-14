package com.thesis.FlorenciaUlloque.UTN.services;

import com.thesis.FlorenciaUlloque.UTN.entiities.Marca;
import com.thesis.FlorenciaUlloque.UTN.entiities.Proveedor;

import java.util.List;

public interface MarcaService {
    Marca createMarca(Marca marca);
    List<Marca> getAllMarcas(int page, int limit);
    Marca updateMarca(long id, Marca marca);
    void deleteMarca(long id);
}
