package com.tiendaonline.service;

import com.tiendaonline.entity.OrdenEntity;
import com.tiendaonline.entity.UsuarioEntity;
import java.util.List;

public interface OrdenService {
    
    public List<OrdenEntity> ListarTodo();
    public OrdenEntity ListarPorID(Long id_orden);
    public OrdenEntity GuardarOrden(OrdenEntity ordenEntity);
    public String generarNumeroOrden();
    public List<OrdenEntity> encontrarPorUsuario(UsuarioEntity usuarioEntity);
    
    /*
    public void agregarProductoAlCarrito(Long id_usuario, Long id_producto, Double cantidad);
    public void eliminarProductoDelCarrito(Long id_usuario, Long id_producto) ;
*/
}
