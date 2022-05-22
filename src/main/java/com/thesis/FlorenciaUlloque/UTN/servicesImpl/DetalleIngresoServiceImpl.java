package com.thesis.FlorenciaUlloque.UTN.servicesImpl;

import com.thesis.FlorenciaUlloque.UTN.Dtos.dtoStocks.StockDtos;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDetalle;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoStock;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleIngreso;
import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleIngresoRepository;
import com.thesis.FlorenciaUlloque.UTN.services.DetalleIngresoService;
import com.thesis.FlorenciaUlloque.UTN.services.ProductoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleIngresoServiceImpl implements DetalleIngresoService {

    private final DetalleIngresoRepository repository;
    private final ProductoService productoService;

    public DetalleIngresoServiceImpl(DetalleIngresoRepository repository, ProductoService productoService) {
        this.repository = repository;
        this.productoService = productoService;
    }

    public List<DetalleIngreso> findAll(){
        List <DetalleIngreso>listaReal  = (List<DetalleIngreso>) repository.findAll();
        return listaReal;
    }

    @Override
    public void save(DetalleIngreso detalleIngreso) {

        repository.save(detalleIngreso);

    }

    @Override
    public DetalleIngreso update(DetalleIngreso detalle) {
        DetalleIngreso returnValue;

        DetalleIngreso detalleForUpdate = new DetalleIngreso();

        detalleForUpdate.setIdDetalle(detalle.getIdDetalle());
        detalleForUpdate.setProducto(detalle.getProducto());
        detalleForUpdate.setIngresoProductos(detalle.getIngresoProductos());
        detalleForUpdate.setCantidad(detalle.getCantidad());
        detalleForUpdate.setPrecio(detalle.getPrecio());

        returnValue = repository.save(detalleForUpdate);
        return returnValue;
    }

    @Override
    public boolean delete(int id) {
        boolean existe = true;
        DetalleIngreso detalleIngreso = repository.findByIdDetalle(id);
        if (detalleIngreso == null) {
            existe = false;
        }
        repository.delete(detalleIngreso);
        return existe;
    }

    @Override
    public List<ProductoDetalle> getDetalleByNombreProducto(String nombre) {
        List <Producto>listaReal  =  productoService.listAllByName(nombre);
        List<ProductoDetalle> listaDto = new ArrayList<>();

        ProductoDetalle productoDetalle ;
        for (Producto producto : listaReal) {
            productoDetalle = new ProductoDetalle();
            productoDetalle.setIdProducto(producto.getIdProducto());
            productoDetalle.setNombre(producto.getNombre());
            productoDetalle.setCodBarras(producto.getCodBarras());
            productoDetalle.setCategoria(producto.getCategoria());
            productoDetalle.setPrecioCompra(producto.getPrecioCompra());
            listaDto.add(productoDetalle);
        }

        return listaDto;
    }

    @Override
    public List<ProductoDetalle> getDetalleByCodBarrasProducto(long codBarras) {
        List <Producto>listaReal  =  productoService.listAllByCodBarras(codBarras);
        List<ProductoDetalle> listaDto = new ArrayList<>();

        ProductoDetalle productoDetalle ;
        for (Producto producto : listaReal) {
            productoDetalle = new ProductoDetalle();
            productoDetalle.setIdProducto(producto.getIdProducto());
            productoDetalle.setNombre(producto.getNombre());
            productoDetalle.setCodBarras(producto.getCodBarras());
            productoDetalle.setCategoria(producto.getCategoria());
            productoDetalle.setPrecioCompra(producto.getPrecioCompra());
            listaDto.add(productoDetalle);
        }

        return listaDto;
    }


}
