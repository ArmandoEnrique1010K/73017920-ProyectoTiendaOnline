package com.tiendaonline.service;

import com.tiendaonline.entity.ImagenProductoEntity;
import com.tiendaonline.entity.ProductoEntity;
import com.tiendaonline.repository.ImagenProductoRepository;
import com.tiendaonline.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private ImagenProductoRepository imagenProductoRepository;
    
    @Autowired
    private ImagenProductoServiceImpl imagenProductoServiceImpl;
    
    @Override
    public List<ProductoEntity> ListarProductosHabilitadosPorFiltrosDeBusqueda(
            Long categoriaId,
            List<Long> marcaIds,
            Double minPrecio,
            Double maxPrecio,
            Boolean enOferta,
            String palabraClave) {
        return productoRepository.findAllByEstadoTrueAndParams(categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave);
    }

    // PRUEBA
    @Override
    public List<ProductoEntity> ListarTodo() {
        return productoRepository.findAll();
    }

    @Override
    public List<ProductoEntity> ListarProductosPorFiltrosDeBusqueda(
            Long categoriaId, 
            List<Long> marcaIds, 
            Double minPrecio, 
            Double maxPrecio, 
            Boolean enOferta, 
            String palabraClave) {
        return productoRepository.findAllByParams(categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave);
    }

    @Override
    public Long ContarProductosHabilitadosPorFiltrosDeBusqueda(Long categoriaId, List<Long> marcaIds, Double minPrecio, Double maxPrecio, Boolean enOferta, String palabraClave) {
        return productoRepository.countAllByEstadoTrueAndParams(categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave);
    }

    @Override
    public Long ContarProductosPorFiltrosDeBusqueda(Long categoriaId, List<Long> marcaIds, Double minPrecio, Double maxPrecio, Boolean enOferta, String palabraClave) {
        return productoRepository.countAllByParams(categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave);
    }

    @Override
    public ProductoEntity GuardarProducto(ProductoEntity productoEntity) {
        ImagenProductoEntity imagenProducto = imagenProductoRepository.save(productoEntity.getImagenProductoEntity());
        String rutaImagen = imagenProductoServiceImpl.almacenarUnaImagen(productoEntity.getImagenProductoEntity().getImagen());
        imagenProducto.setRutaImagen(rutaImagen);
        
        productoEntity.setEstado(true);
        productoEntity.setImagenProductoEntity(imagenProducto);
        return productoRepository.save(productoEntity);
    }

    @Override
    public ProductoEntity ObtenerProductoPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ProductoEntity ActualizarProducto(ProductoEntity productoEntity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void EliminarProducto(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Page<ProductoEntity> ListarPorPaginasProductosHabilitadosPorFiltrosDeBusqueda(
            Long categoriaId, List<Long> marcaIds, Double minPrecio, Double maxPrecio, 
            Boolean enOferta, String palabraClave, Pageable pageable) {
        
        
        
        return productoRepository.findAllPagesByEstadoTrueAndParams(categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave, pageable);
    }
    
    
    
}
