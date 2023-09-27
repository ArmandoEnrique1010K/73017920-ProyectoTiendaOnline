package com.tiendaonline.service;

import com.tiendaonline.entity.OrdenEntity;
import com.tiendaonline.entity.UsuarioEntity;
import com.tiendaonline.repository.OrdenRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenServiceImpl implements OrdenService{

    @Autowired
    private OrdenRepository ordenRepository;
    

    
    @Override
    public List<OrdenEntity> ListarTodo() {
        return ordenRepository.findAll();
    }

    @Override
    public OrdenEntity ListarPorID(Long id_orden) {
        return ordenRepository.findById(id_orden).orElse(null);
    }

    @Override
    public OrdenEntity GuardarOrden(OrdenEntity ordenEntity) {
        return ordenRepository.save(ordenEntity);
    }

    @Override
    public String generarNumeroOrden() {
        List<OrdenEntity> ordenes = ListarTodo();
        int numero = ordenes.isEmpty() ? 1 : ordenes.stream()
                .mapToInt(o -> Integer.parseInt(o.getNumero()))
                .max()
                .orElse(0) + 1;

        return String.format("%010d", numero);
    }

    @Override
    public List<OrdenEntity> encontrarPorUsuario(UsuarioEntity usuarioEntity) {
        return ordenRepository.findOrdenesByUsuario(usuarioEntity);
    }

}

    /*


import com.tiendaonline.entity.DetallesOrdenEntity;
import com.tiendaonline.entity.ProductoEntity;
import org.springframework.context.annotation.Lazy;
import java.util.Date;

    @Autowired
    private ProductoService productoService;
    
    @Autowired
    @Lazy
    private OrdenService ordenService;
    
    @Autowired
    private DetalleOrdenService detalleOrdenService;
    
    @Autowired
    private UsuarioService usuarioService;


@Override
public void agregarProductoAlCarrito(Long id_usuario, Long id_producto, Double cantidad) {
    // Obtener el usuario y el producto
    UsuarioEntity usuario = usuarioService.obtenerUsuarioPorId(id_usuario);
    ProductoEntity producto = productoService.ObtenerProductoPorId(id_producto);

    // Declarar la variable orden
    OrdenEntity orden = null;

    // Verificar si el usuario ya tiene una orden activa (carrito)
    List<OrdenEntity> ordenesDelUsuario = ordenService.encontrarPorUsuario(usuario);

    if (ordenesDelUsuario.isEmpty()) {
        // Si el usuario no tiene un carrito, crea una nueva orden
        orden = new OrdenEntity();
        orden.setUsuarioEntity(usuario);
        orden.setNumero(generarNumeroOrden()); // Debes implementar esta función
        orden.setFecha_recibida(new Date()); // Establece la fecha actual o la que desees
        orden.setTotal_a_pagar(0.0); // El total se actualizará a medida que se agreguen productos
    } else {
        orden = ordenesDelUsuario.get(ordenesDelUsuario.size() - 1);
    }

    // Verificar si el producto ya está en el carrito
    DetallesOrdenEntity detalleExistente = orden.getDetallesOrdenEntity().stream()
            .filter(detalle -> detalle.getProductoEntity().getId_producto().equals(id_producto))
            .findFirst()
            .orElse(null);

    if (detalleExistente != null) {
        // Si el producto ya está en el carrito, actualiza la cantidad
        detalleExistente.setCantidad(detalleExistente.getCantidad() + cantidad);
        detalleExistente.setTotal(detalleExistente.getPrecio() * detalleExistente.getCantidad());
    } else {
        // Si el producto no está en el carrito, crea un nuevo detalle de orden
        DetallesOrdenEntity nuevoDetalle = new DetallesOrdenEntity();
        nuevoDetalle.setProductoEntity(producto);
        nuevoDetalle.setCantidad(cantidad);

        // Seleccionar el precio de oferta si no es nulo
        double precioactual = 0;
        if (producto.getPreciooferta() == null) {
            precioactual = producto.getPrecionormal();
        } else {
            precioactual = producto.getPreciooferta();
        }

        nuevoDetalle.setPrecio(precioactual); // Debes implementar la lógica para obtener el precio actual
        nuevoDetalle.setTotal(nuevoDetalle.getPrecio() * cantidad);
        nuevoDetalle.setOrdenEntity(orden);

        orden.getDetallesOrdenEntity().add(nuevoDetalle);
    }

    // Actualiza el total de la orden
    double totalOrden = orden.getDetallesOrdenEntity().stream().mapToDouble(DetallesOrdenEntity::getTotal).sum();
    orden.setTotal_a_pagar(totalOrden);

    // Guarda la orden en la base de datos
    ordenService.GuardarOrden(orden);
}

    @Override
    public void eliminarProductoDelCarrito(Long id_usuario, Long id_producto) {
        // Obtener el usuario y el producto
        UsuarioEntity usuario = usuarioService.obtenerUsuarioPorId(id_usuario);
        ProductoEntity producto = productoService.ObtenerProductoPorId(id_producto);

        // Obtener la orden activa (carrito) del usuario
        OrdenEntity orden = (OrdenEntity) ordenService.encontrarPorUsuario(usuario);

        if (orden != null) {
            // Eliminar el detalle de orden correspondiente al producto del carrito
            orden.getDetallesOrdenEntity().removeIf(detalle -> detalle.getProductoEntity().getId_producto().equals(id_producto));

            // Actualizar el total de la orden
            double totalOrden = orden.getDetallesOrdenEntity().stream().mapToDouble(DetallesOrdenEntity::getTotal).sum();
            orden.setTotal_a_pagar(totalOrden);

            // Guardar la orden actualizada en la base de datos
            ordenService.GuardarOrden(orden);
        }
    }
*/
