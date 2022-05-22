package com.thesis.FlorenciaUlloque.UTN.servicesImpl;



import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDetalleVenta;
import com.thesis.FlorenciaUlloque.UTN.entiities.DetalleEgreso;

import com.thesis.FlorenciaUlloque.UTN.entiities.Producto;
import com.thesis.FlorenciaUlloque.UTN.repositories.DetalleEgresoRepository;
import com.thesis.FlorenciaUlloque.UTN.services.DetalleEgresoService;
import com.thesis.FlorenciaUlloque.UTN.services.ProductoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleEgresoServiceImpl implements DetalleEgresoService {

    private final DetalleEgresoRepository repository;
    private final ProductoService productoService;

    public DetalleEgresoServiceImpl(DetalleEgresoRepository repository, ProductoService productoService) {
        this.repository = repository;
        this.productoService = productoService;
    }

    public List<DetalleEgreso> findAll(){
        List <DetalleEgreso> listaReal = (List<DetalleEgreso>) repository.findAll();
        return listaReal;
    }

    @Override
    public void save(DetalleEgreso detalleEgreso) {

        repository.save(detalleEgreso);

    }

    @Override
    public DetalleEgreso update(DetalleEgreso detalle) {
        DetalleEgreso returnValue;

        DetalleEgreso detalleForUpdate = new DetalleEgreso();

        detalleForUpdate.setIdDetalleEgreso(detalle.getIdDetalleEgreso());
        detalleForUpdate.setProducto(detalle.getProducto());
        detalleForUpdate.setSalidaProducto(detalle.getSalidaProducto());
        detalleForUpdate.setCantidad(detalle.getCantidad());
        detalleForUpdate.setPrecio(detalle.getPrecio());

        returnValue = repository.save(detalleForUpdate);
        return returnValue;
    }

    @Override
    public boolean delete(int id) {
        boolean existe = true;
        DetalleEgreso detalleEgreso = repository.findByIdDetalleEgreso(id);
        if (detalleEgreso == null) {
            existe = false;
        }
        repository.delete(detalleEgreso);
        return existe;
    }

    @Override
    public List<ProductoDetalleVenta> getDetalleByNombreProducto(String nombre) {
        List <Producto>listaReal  =  productoService.listAllByName(nombre);
        List<ProductoDetalleVenta> listaDto = new ArrayList<>();

        ProductoDetalleVenta productoDetalle ;
        for (Producto producto : listaReal) {
            productoDetalle = new ProductoDetalleVenta();
            productoDetalle.setIdProducto(producto.getIdProducto());
            productoDetalle.setNombre(producto.getNombre());
            productoDetalle.setCodBarras(producto.getCodBarras());
            productoDetalle.setCategoria(producto.getCategoria());
            productoDetalle.setPrecioVenta(producto.getPrecioVenta());
            listaDto.add(productoDetalle);
        }

        return listaDto;
    }

    @Override
    public List<ProductoDetalleVenta> getDetalleByCodBarrasProducto(long codBarras) {
        List <Producto>listaReal  =  productoService.listAllByCodBarras(codBarras);
        List<ProductoDetalleVenta> listaDto = new ArrayList<>();

        ProductoDetalleVenta productoDetalle ;
        for (Producto producto : listaReal) {
            productoDetalle = new ProductoDetalleVenta();
            productoDetalle.setIdProducto(producto.getIdProducto());
            productoDetalle.setNombre(producto.getNombre());
            productoDetalle.setCodBarras(producto.getCodBarras());
            productoDetalle.setCategoria(producto.getCategoria());
            productoDetalle.setPrecioVenta(producto.getPrecioVenta());
            listaDto.add(productoDetalle);
        }

        return listaDto;
    }

}
