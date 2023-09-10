package com.tiendaonline.service;

import com.tiendaonline.entity.ProductoEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductoService {
    
    // LISTAR TODOS LOS PRODUCTOS
    public List<ProductoEntity> ListarProductosHabilitadosPorFiltrosDeBusqueda(
            Long categoriaId,
            List<Long> marcaIds,
            Double minPrecio,
            Double maxPrecio,
            Boolean enOferta,
            String palabraClave
    );
    
    
    
    public Page<ProductoEntity> ListarPorPaginasProductosHabilitadosPorFiltrosDeBusqueda(
            Long categoriaId,
            List<Long> marcaIds,
            Double minPrecio,
            Double maxPrecio,
            Boolean enOferta,
            String palabraClave,
            Pageable pageable
    );
    
    // CONTAR TODOS LOS PRODUCTOS
    public Long ContarProductosHabilitadosPorFiltrosDeBusqueda(
            Long categoriaId,
            List<Long> marcaIds,
            Double minPrecio,
            Double maxPrecio,
            Boolean enOferta,
            String palabraClave
    );

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

    // METODOS PARA LOS ADMINISTRADORES
    public ProductoEntity GuardarProducto(ProductoEntity productoEntity);
    public ProductoEntity ObtenerProductoPorId(Long id);
    public ProductoEntity ActualizarProducto(ProductoEntity productoEntity);
    public void EliminarProducto(Long id);

}
