package com.taquistore.repository;

import com.taquistore.entity.DetallesProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallesProductoRepository extends JpaRepository<DetallesProductoEntity, Long>{
    
}