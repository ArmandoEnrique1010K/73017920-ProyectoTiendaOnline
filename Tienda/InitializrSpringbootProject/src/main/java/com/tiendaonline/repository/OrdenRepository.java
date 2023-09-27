package com.tiendaonline.repository;

import com.tiendaonline.entity.OrdenEntity;
import com.tiendaonline.entity.UsuarioEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<OrdenEntity, Long>{
    // CONSULTA PARA BUSCAR TODAS LAS ORDENDES POR EL OBJETO USUARIO
    @Query("SELECT o FROM OrdenEntity o WHERE o.usuarioEntity = :usuarioEntity")
    public List<OrdenEntity> findOrdenesByUsuario(@Param("usuarioEntity") UsuarioEntity usuarioEntity);

}
