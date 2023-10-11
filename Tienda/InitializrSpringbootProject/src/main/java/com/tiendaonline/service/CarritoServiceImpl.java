package com.tiendaonline.service;

import com.tiendaonline.entity.CarritoEntity;
import com.tiendaonline.entity.DetallesCarritoEntity;
import com.tiendaonline.entity.UsuarioEntity;
import com.tiendaonline.repository.CarritoRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoServiceImpl implements CarritoService{
    
    @Autowired
    private CarritoRepository carritoRepository;

    @Override
    @Transactional
    public CarritoEntity guardarCarrito(CarritoEntity carritoEntity) {
        return carritoRepository.save(carritoEntity);    
    }

    @Override
    public CarritoEntity obtenerCarritoPorUsuario(UsuarioEntity usuario) {
        List<CarritoEntity> carritos = carritoRepository.findByUsuarioEntity(usuario);
        if (carritos.isEmpty()) {
            return null; // O cualquier otro manejo que desees
        }
        return carritos.get(0);
    }

    @Override
    public CarritoEntity obtenerCarritoConDetallesPorUsuario(UsuarioEntity usuarioEntity) {
        List<CarritoEntity> carritos = carritoRepository.findCarritoWithDetallesByUsuarioEntity(usuarioEntity);
        if (!carritos.isEmpty()) {
            return carritos.get(0);
        }
        return null; // O cualquier otro manejo que desees para el caso en que no se encuentre un carrito
    }

    @Override
    public void CalcularTotalCarrito(CarritoEntity carrito) {
    double totalCarrito = 0.0;
    for (DetallesCarritoEntity detalle : carrito.getDetallesCarritoEntity()) {
        double precio = detalle.getProductoEntity().getPreciooferta() != null ?
                       detalle.getProductoEntity().getPreciooferta() :
                       detalle.getProductoEntity().getPrecionormal();
        totalCarrito += detalle.getCantidad() * precio;
    }
    carrito.setTotal_a_pagar(totalCarrito);
    carritoRepository.save(carrito);
    }
    
}
