package com.taquistore.service;

import com.taquistore.entity.ImagenProductoEntity;
import com.taquistore.entity.ProductoEntity;
import java.util.List;


public interface ProductoService {

    public List<ProductoEntity> ListarProductosHabilitadosPorFiltrosDeBusqueda(
            Long categoriaId,
            List<Long> marcaIds,
            Double minPrecio,
            Double maxPrecio,
            Boolean enOferta,
            String palabraClave
    );
    
    public Long ContarProductosHabilitadosPorFiltrosDeBusqueda(
            Long categoriaId,
            List<Long> marcaIds,
            Double minPrecio,
            Double maxPrecio,
            Boolean enOferta,
            String palabraClave
    );
    
    
    
    
    public List<ProductoEntity> ListarTodo();
    
    public List<ProductoEntity> ListarProductosPorFiltrosDeBusqueda(
            Long categoriaId,
            List<Long> marcaIds,
            Double minPrecio,
            Double maxPrecio,
            Boolean enOferta,
            String palabraClave
    );
    
    public Long ContarProductosPorFiltrosDeBusqueda(
            Long categoriaId,
            List<Long> marcaIds,
            Double minPrecio,
            Double maxPrecio,
            Boolean enOferta,
            String palabraClave
    );

    public ProductoEntity GuardarProducto(ProductoEntity productoEntity);
    public ProductoEntity ObtenerProductoPorId(Long id);
    public ProductoEntity ActualizarProducto(ProductoEntity productoEntity);
    public void EliminarProducto(Long id);

}
