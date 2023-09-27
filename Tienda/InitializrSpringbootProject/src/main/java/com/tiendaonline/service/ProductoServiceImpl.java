package com.tiendaonline.service;

import com.tiendaonline.entity.ImagenProductoEntity;
import com.tiendaonline.entity.ProductoEntity;
import com.tiendaonline.repository.ImagenProductoRepository;
import com.tiendaonline.repository.ProductoRepository;
import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ImagenProductoRepository imagenProductoRepository;

    @Autowired
    private ImagenProductoServiceImpl imagenProductoServiceImpl;

    // LISTAR TODOS LOS PRODUCTOS QUE ESTAN HABILITADOS POR FILTROS DE BUSQUEDA
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

    // CONTAR TODOS LOS PRODUCTOS QUE ESTAN HABILITADOS POR FILTROS DE BUSQUEDA
    @Override
    public Long ContarProductosHabilitadosPorFiltrosDeBusqueda(
            Long categoriaId,
            List<Long> marcaIds,
            Double minPrecio,
            Double maxPrecio,
            Boolean enOferta,
            String palabraClave) {
        return productoRepository.countAllByEstadoTrueAndParams(categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave);
    }

    @Override
    public Long ContarProductosPorFiltrosDeBusqueda(
            Long categoriaId,
            List<Long> marcaIds,
            Double minPrecio,
            Double maxPrecio,
            Boolean enOferta,
            String palabraClave) {
        return productoRepository.countAllByParams(categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave);
    }

    // METODOS PARA ADMINISTRADORES
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
    public ProductoEntity ObtenerProductoPorId(Long id_producto) {
        return productoRepository.findById(id_producto).get();

        // return productoRepository.findById(id_producto).orElse(null);
    }

    /*
    @Override
    public ProductoEntity ActualizarProducto(ProductoEntity productoEntity) {

        // 1. Recuperar el producto existente de la base de datos
        ProductoEntity productoExistente = productoRepository.findById(productoEntity.getId_producto()).orElse(null);

        if (productoExistente == null) {
            return null;
        }

        // 2. Actualizar los campos del producto existente con los nuevos valores
        productoExistente.setNombre(productoEntity.getNombre());
        productoExistente.setCodigo(productoEntity.getCodigo());
        productoExistente.setOferta(productoEntity.getOferta());
        productoExistente.setPrecionormal(productoEntity.getPrecionormal());
        productoExistente.setPreciooferta(productoEntity.getPreciooferta());
        productoExistente.setEstado(Boolean.TRUE);
        productoExistente.setCategoriaEntity(productoEntity.getCategoriaEntity());
        productoExistente.setMarcaEntity(productoEntity.getMarcaEntity());
        productoExistente.setDetallesproductoEntity(productoEntity.getDetallesproductoEntity());

    // 3. Manejar la imagen si se proporciona una nueva imagen en la solicitud
        MultipartFile nuevaImagen = productoEntity.getImagenProductoEntity().getImagen();
    
    if (nuevaImagen != null && !nuevaImagen.isEmpty()) {
        // Almacena la nueva imagen y actualiza la ruta
        String rutaNuevaImagen = imagenProductoServiceImpl.almacenarUnaImagen(nuevaImagen);

        // Eliminar la imagen anterior si existe
        if (productoExistente.getImagenProductoEntity() != null) {
            imagenProductoServiceImpl.eliminarImagen(productoExistente.getImagenProductoEntity().getRutaImagen());
        }

        // Actualiza la ruta de la nueva imagen y establece la entidad de imagen
        productoExistente.getImagenProductoEntity().setRutaImagen(rutaNuevaImagen);
    }

        // 4. Guardar el producto actualizado en la base de datos
        return productoRepository.save(productoEntity);
    }
     */
    @Override
    public ProductoEntity ActualizarProducto(ProductoEntity productoEntity) {
        // 1. Recuperar el producto existente de la base de datos
        ProductoEntity productoExistente = productoRepository.findById(productoEntity.getId_producto()).orElse(null);

        if (productoExistente == null) {
            return null;
        }

        // 2. Actualizar los campos del producto existente con los nuevos valores
        productoExistente.setNombre(productoEntity.getNombre());
        productoExistente.setCodigo(productoEntity.getCodigo());
        productoExistente.setOferta(productoEntity.getOferta());
        productoExistente.setPrecionormal(productoEntity.getPrecionormal());
        productoExistente.setPreciooferta(productoEntity.getPreciooferta());
        productoExistente.setEstado(Boolean.TRUE);
        productoExistente.setCategoriaEntity(productoEntity.getCategoriaEntity());
        productoExistente.setMarcaEntity(productoEntity.getMarcaEntity());
        productoExistente.setDetallesproductoEntity(productoEntity.getDetallesproductoEntity());

        // 3. Manejar la imagen si se proporciona una nueva imagen en la solicitud
        MultipartFile nuevaImagen = productoEntity.getImagenProductoEntity().getImagen();

        if (nuevaImagen != null && !nuevaImagen.isEmpty()) {

            /*
            if (productoExistente.getImagenProductoEntity() != null) {

                ImagenProductoEntity imagenProducto = new ImagenProductoEntity();
                productoEntity.setImagenProductoEntity(imagenProducto);

                imagenProductoServiceImpl.eliminarImagen(productoExistente.getImagenProductoEntity().getRutaImagen());
            }
            */

            String rutaImagen = imagenProductoServiceImpl.almacenarUnaImagen(nuevaImagen);
            // productoEntity.getImagenProductoEntity().setRutaImagen(rutaImagen);
            productoExistente.getImagenProductoEntity().setRutaImagen(rutaImagen);

        } else {
            // Si no se proporciona una nueva imagen, conserva la imagen existente
            productoEntity.getImagenProductoEntity().setRutaImagen(productoExistente.getImagenProductoEntity().getRutaImagen());
        }

        // 4. Guardar el producto actualizado en la base de datos
        return productoRepository.save(productoExistente);
    }

    @Override
    public void CambiarEstadoProductoAFalse(Long id_producto) {
        ProductoEntity productoEntity = productoRepository.findById(id_producto).orElse(null);
        if (productoEntity != null) {
            productoEntity.setEstado(Boolean.FALSE);
            productoRepository.save(productoEntity);
        }
    }

    /* NO UTILIZAR */
 /*
    @Override
    public Page<ProductoEntity> ListarPorPaginasProductosHabilitadosPorFiltrosDeBusqueda(
            Long categoriaId, List<Long> marcaIds, Double minPrecio, Double maxPrecio, 
            Boolean enOferta, String palabraClave, Pageable pageable) {
        
        
        
        return productoRepository.findAllPagesByEstadoTrueAndParams(categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave, pageable);
    }
     */

    @Override
    public void CambiarEstadoProductoATrue(Long id_producto) {
        ProductoEntity productoEntity = productoRepository.findById(id_producto).orElse(null);
        if (productoEntity != null) {
            productoEntity.setEstado(Boolean.TRUE);
            productoRepository.save(productoEntity);
        }
    }

    @Override
    public void EliminarDefinitivamente(Long id_producto) {
        ProductoEntity productoEntity = productoRepository.findById(id_producto).orElse(null);
        if (productoEntity != null) {
            // Eliminar el registro de la base de datos
            productoRepository.delete(productoEntity);
            // Obtener la ruta de la imagen asociada
            String rutaImagen = productoEntity.getImagenProductoEntity().getRutaImagen();
            // Eliminar físicamente el archivo de la imagen
            if (rutaImagen != null && !rutaImagen.isEmpty()) {
                File archivoImagen = new File(rutaImagen);
                if (archivoImagen.exists()) {
                    archivoImagen.delete();
                }
            }
        }

    }
}
