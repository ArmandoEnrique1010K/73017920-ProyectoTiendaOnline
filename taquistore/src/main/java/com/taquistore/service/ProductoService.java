package com.taquistore.service;

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
    
    
    public List<ProductoEntity> ListarTodo();
    
    public List<ProductoEntity> ListarProductosPorFiltrosDeBusqueda(
            Long categoriaId,
            List<Long> marcaIds,
            Double minPrecio,
            Double maxPrecio,
            Boolean enOferta,
            String palabraClave
    );
    
    
}
