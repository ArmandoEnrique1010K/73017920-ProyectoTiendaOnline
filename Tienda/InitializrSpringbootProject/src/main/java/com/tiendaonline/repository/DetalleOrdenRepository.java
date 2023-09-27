package com.tiendaonline.repository;

import com.tiendaonline.entity.DetallesOrdenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetallesOrdenEntity, Long> {
    
}
