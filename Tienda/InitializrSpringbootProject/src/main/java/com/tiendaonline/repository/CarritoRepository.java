package com.tiendaonline.repository;

import com.tiendaonline.entity.CarritoEntity;
import com.tiendaonline.entity.UsuarioEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoEntity, Long>{

    List<CarritoEntity> findByUsuarioEntity (UsuarioEntity usuarioEntity);
    List<CarritoEntity> findCarritoWithDetallesByUsuarioEntity (UsuarioEntity usuarioEntity);

    // public CarritoEntity findCarritoWithDetallesByUsuario(UsuarioEntity usuario);
    
}
    
    // BUSCAR CARRITO POR NUMERO DE ORDEN
    //CarritoEntity findByNumero(String numero);
    // busque el carrito asociado al usuario autenticado
    //CarritoEntity findByUsuarioEntityId(Long usuarioId);
    
    // BUSCAR CARRITO POR EL EMAIL DEL USUARIO
    // @Query("SELECT c FROM CarritoEntity c WHERE c.usuarioEntity.email = :emailUsuario")
    // List<CarritoEntity> findByUsuarioEmail(@Param("emailUsuario") String emailUsuario);
