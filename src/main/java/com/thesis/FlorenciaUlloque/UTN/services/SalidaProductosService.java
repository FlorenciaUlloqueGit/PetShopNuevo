package com.thesis.FlorenciaUlloque.UTN.services;


import com.thesis.FlorenciaUlloque.UTN.entiities.*;

import java.util.List;

public interface SalidaProductosService {


    SalidaProducto updateEgreso(SalidaProducto salidaProducto);
    boolean deleteEgreso(int id);
    List<SalidaProducto> getAllEgresos();
    SalidaProducto saveEgreso(SalidaProducto salidaProducto);
    List<FormaPago> listaFormasPagos();
    List<Cliente> listaClientes();
}
