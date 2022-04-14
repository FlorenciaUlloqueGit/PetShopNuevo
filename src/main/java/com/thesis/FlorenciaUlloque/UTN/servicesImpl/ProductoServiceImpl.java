package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import com.thesis.FlorenciaUlloque.UTN.exceptions.ErrorMessages;
import com.thesis.FlorenciaUlloque.UTN.repositories.ProductoRepository;
import com.thesis.FlorenciaUlloque.UTN.services.ProductoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }


    @Override
    public Producto createProducto(Producto producto) {



        if (repository.findByCodBarras(producto.getCodBarras()) != null) {
            throw new RuntimeException(ErrorMessages.RECORD_ALREADY_EXIST.getErrorMessage());
        }
            Producto  newProducto = new Producto();

            BeanUtils.copyProperties(producto, newProducto);
            repository.save(newProducto);
            return newProducto;
    }

    @Override
    public List<Producto> getAllProductos(int page, int limit) {

        List<Producto> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Producto> productoPage   = repository.findAll(pageableRequest);
        List<Producto> productos = productoPage.getContent();


        for (Producto cliente : productos) {
            returnValue.add(cliente);
        }

        return returnValue;
    }

    @Override
    public Producto updateProductos(int id, Producto producto) {
        Producto productoForUpdate = repository.findByIdProducto(id);
        if (productoForUpdate == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        productoForUpdate.setCodBarras(producto.getCodBarras());
        productoForUpdate.setNombre(producto.getNombre());
        productoForUpdate.setFechaVencimiento(producto.getFechaVencimiento());
        productoForUpdate.setPrecioCompra(producto.getPrecioCompra());
        productoForUpdate.setPrecioVenta(producto.getPrecioVenta());
        productoForUpdate.setFormaVenta(producto.getFormaVenta()); //REVISAR
        productoForUpdate.setMarca(producto.getMarca());
        productoForUpdate.setSubcategoria(producto.getSubcategoria());
        productoForUpdate.setAnimal(producto.getAnimal());
        productoForUpdate.setPesoNeto(producto.getPesoNeto());
        productoForUpdate.setUnidadMedida(producto.getUnidadMedida()); //UNIDAD MEDIDA


        Producto returnValue = repository.save(productoForUpdate);

        return returnValue;
    }

    @Override
    public void deleteProductos(int id) {
        Producto producto = repository.findByIdProducto(id);

        if (producto == null) {
            throw new RuntimeException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        repository.delete(producto);
    }

}
