package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.entiities.Administrador;
import com.thesis.FlorenciaUlloque.UTN.entiities.Cliente;

import java.util.List;

public interface AdministradorService {


    List<Administrador> getAllAdministradores(int page, int limit);

    //borrar


}
