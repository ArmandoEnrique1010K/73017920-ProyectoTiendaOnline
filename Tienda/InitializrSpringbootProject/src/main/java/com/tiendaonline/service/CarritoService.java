package com.tiendaonline.service;

import com.tiendaonline.entity.CarritoEntity;
import com.tiendaonline.entity.UsuarioEntity;

public interface CarritoService {
    
    public CarritoEntity guardarCarrito(CarritoEntity carritoEntity);
    public CarritoEntity obtenerCarritoPorUsuario(UsuarioEntity usuario);
    // IMPLEMENTAR EN PEDIDOSERVICE
    // public List<CarritoEntity> listarTodosLosCarritos();
    // public List<CarritoEntity> encontrarPorUsuario(CarritoEntity carritoEntity);
    // public Optional<CarritoEntity> encontrarPorId(Long id_carrito);
    public CarritoEntity obtenerCarritoConDetallesPorUsuario(UsuarioEntity usuario);
    public void CalcularTotalCarrito(CarritoEntity carrito);
}
