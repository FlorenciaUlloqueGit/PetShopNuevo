package com.thesis.FlorenciaUlloque.UTN.servicesImpl;


import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDto;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoDtos;
import com.thesis.FlorenciaUlloque.UTN.entiities.*;
import com.thesis.FlorenciaUlloque.UTN.repositories.*;
import com.thesis.FlorenciaUlloque.UTN.services.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;
    private final MarcaRepository marcaRepository;
    private final FormaVentaRepository formaVentaRepository;
    private final CategoriaRepository categoriaRepository;
    private final EdadRepository edadRepository;
    private final TamanoRepository tamanoRepository;
    private final TipoAnimalRepository tipoAnimalRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;

    public ProductoServiceImpl(ProductoRepository repository, MarcaRepository marcaRepository,
                               FormaVentaRepository formaVentaRepository,
                               CategoriaRepository categoriaRepository,
                               EdadRepository edadRepository, TamanoRepository tamanoRepository,
                               TipoAnimalRepository tipoAnimalRepository, UnidadMedidaRepository unidadMedidaRepository) {
        this.repository = repository;
        this.marcaRepository = marcaRepository;
        this.formaVentaRepository = formaVentaRepository;
        this.categoriaRepository = categoriaRepository;
        this.edadRepository = edadRepository;
        this.tamanoRepository = tamanoRepository;
        this.tipoAnimalRepository = tipoAnimalRepository;
        this.unidadMedidaRepository = unidadMedidaRepository;
    }


    @Override
    public Producto update(Producto producto) {
        Producto returnValue;

        Producto productoForUpdate = new Producto();

        productoForUpdate.setNombre(producto.getNombre());
        productoForUpdate.setIdProducto(producto.getIdProducto());
        productoForUpdate.setCodBarras(producto.getCodBarras());
        productoForUpdate.setFechaVencimiento(producto.getFechaVencimiento());
        productoForUpdate.setPrecioCompra(producto.getPrecioCompra());
        productoForUpdate.setPrecioVenta(producto.getPrecioVenta());
        productoForUpdate.setMarca(producto.getMarca());
        productoForUpdate.setFormaVenta(producto.getFormaVenta());
        productoForUpdate.setUnidadMedida(producto.getUnidadMedida());
        productoForUpdate.setPesoNeto(producto.getPesoNeto());
        productoForUpdate.setCategoria(producto.getCategoria());
        productoForUpdate.setTamano(producto.getTamano());
        productoForUpdate.setEdad(producto.getEdad());
        productoForUpdate.setTipoAnimal(producto.getTipoAnimal());


        returnValue = repository.save(productoForUpdate);
        return returnValue;
    }

    @Override
    public boolean delete(int id) {
        boolean existe = true;
        Producto producto = repository.findByIdProducto(id);
        if (producto == null) {
            existe = false;
        }
        repository.delete(producto);
        return existe;
    }

    @Override
    public List<ProductoDto> findAllProductos() {
        List <Producto>listaReal  = (List<Producto>) repository.findAll();
        List<ProductoDto> listaDto = new ArrayList<>();


        for (Producto producto: listaReal) {
            ProductoDto productoDto = new ProductoDto();
            productoDto.setIdProducto(producto.getIdProducto());
            productoDto.setCodBarras(producto.getCodBarras());
            productoDto.setNombre(producto.getNombre());
            productoDto.setFechaVencimiento(producto.getFechaVencimiento());
            productoDto.setPrecioCompra(producto.getPrecioCompra());
            productoDto.setPrecioVenta(producto.getPrecioVenta());
            productoDto.setMarca(producto.getMarca());
            productoDto.setFormaVenta(producto.getFormaVenta());
            productoDto.setCategoria(producto.getCategoria());
            productoDto.setTamano(producto.getTamano());
            productoDto.setEdad(producto.getEdad());
            productoDto.setTipoAnimal(producto.getTipoAnimal());
            productoDto.setPesoNeto(producto.getPesoNeto());
            productoDto.setUnidadMedida(producto.getUnidadMedida());
            listaDto.add(productoDto);
        }

        return listaDto;
    }

    @Override
    public List<Producto> listAllByName(String nombre) {
        return repository.findAllProductosByNameIndistinto(nombre);
    }

    @Override
    public List<Producto> listAllByNameXKG(String nombre) {
        return repository.findAllProductosByNameXGramo(nombre);
    }

    @Override
    public List<Producto> listAllByCodBarras(long codBarras) {
        return repository.findAllByCodBarras(codBarras);
    }

    @Override
    public boolean save(ProductoDtos productoDtos) {
        boolean registrado;
        Producto newProducto;
        long codBarras = productoDtos.getCodBarras();
        if (codBarras == 0 && repository.findByNombre(productoDtos.getNombre()) != null) {
            registrado = true;
            return registrado;
        }

        if (codBarras == 0 && repository.findByNombre(productoDtos.getNombre()) == null) {
            registrado = false;
            newProducto = new Producto(
                    productoDtos.getNombre(),
                    productoDtos.getFechaVencimiento(),
                    productoDtos.getPrecioCompra(),
                    productoDtos.getPrecioVenta(),
                    productoDtos.getMarca(),
                    productoDtos.getFormaVenta(),
                    productoDtos.getCategoria(),
                    productoDtos.getPesoNeto(),
                    productoDtos.getUnidadMedida(),
                    productoDtos.getTipoAnimal(),
                    productoDtos.getTamano(),
                    productoDtos.getEdad(), true);

            repository.save(newProducto);
        } else {
            if (repository.findByCodBarras(productoDtos.getCodBarras()) != null) {
                registrado = true;
            } else {
                registrado = false;
                newProducto = new Producto(
                        productoDtos.getCodBarras(),
                        productoDtos.getNombre(),
                        productoDtos.getFechaVencimiento(),
                        productoDtos.getPrecioCompra(),
                        productoDtos.getPrecioVenta(),
                        productoDtos.getMarca(),
                        productoDtos.getFormaVenta(),
                        productoDtos.getCategoria(),
                        productoDtos.getPesoNeto(),
                        productoDtos.getUnidadMedida(),
                        productoDtos.getTipoAnimal(),
                        productoDtos.getTamano(),
                        productoDtos.getEdad(), true);
                repository.save(newProducto);
            }

        }

        return registrado;
    }

    @Override
    public List<Marca> listadoMarcas() {
        List<Marca> marcaList;
        marcaList =  marcaRepository.findAllByOrderByNombreAsc();
        return  marcaList;
    }

    @Override
    public List<FormaVenta> listaFormaVenta() {
        List<FormaVenta> formaVentaList;
        formaVentaList = (List<FormaVenta>) formaVentaRepository.findAll();
        return  formaVentaList;
    }

    @Override
    public List<Categoria> listaCategoria() {
        List<Categoria> categoriaList;
        categoriaList = (List<Categoria>) categoriaRepository.findAll();
        return  categoriaList;
    }


    @Override
    public List<Tamano> listaTamano() {
        List<Tamano> tamanoList;
        tamanoList = (List<Tamano>) tamanoRepository.findAll();
        return  tamanoList;
    }

    @Override
    public List<Edad> listaEdadAnimal() {
        List<Edad> edadList;
        edadList = (List<Edad>) edadRepository.findAll();
        return  edadList;
    }

    @Override
    public List<TipoAnimal> listTipoAnimal() {
        List<TipoAnimal> tipoAnimals;
        tipoAnimals = (List<TipoAnimal>) tipoAnimalRepository.findAll();
        return  tipoAnimals;
    }

    @Override
    public Page<Producto> getAll(Pageable pageable) {
       return repository.findAllByOrderByNombreAsc(pageable);
    }

    @Override
    public Page<Producto> getAllBolsas(Pageable pageable) {
        return repository.findProductosVendidosBolsa(pageable);
    }

    @Override
    public Page<Producto> getAllKG(Pageable pageable) {
        return repository.findProductosVendidosKG(pageable);
    }

    @Override
    public Page<String> getAllProdVendidosHoy(Pageable pageable) {
        return repository.findAllProductosVendidosHoy(pageable);
    }


    @Override
    public List<UnidadMedida> listaUnidadMedida() {
        List<UnidadMedida> unidadMedidas;
        unidadMedidas = (List<UnidadMedida>) unidadMedidaRepository.findAll();
        return unidadMedidas;
    }


}
