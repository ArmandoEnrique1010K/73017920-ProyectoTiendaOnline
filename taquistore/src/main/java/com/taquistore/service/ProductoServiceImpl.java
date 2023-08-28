package com.taquistore.service;

import com.taquistore.entity.ProductoEntity;
import com.taquistore.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;
    
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
    public List<ProductoEntity> ListarProductosPorFiltrosDeBusqueda(Long categoriaId, List<Long> marcaIds, Double minPrecio, Double maxPrecio, Boolean enOferta, String palabraClave) {
        return productoRepository.findAllByParams(categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave);
    }

    @Override
    public Long ContarProductosHabilitadosPorFiltrosDeBusqueda(Long categoriaId, List<Long> marcaIds, Double minPrecio, Double maxPrecio, Boolean enOferta, String palabraClave) {
        return productoRepository.countAllByEstadoTrueAndParams(categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave);
    }
    
}
