package com.taquistore.repository;

import com.taquistore.entity.ImagenProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenProductoRepository extends JpaRepository<ImagenProductoEntity, Long>{
    
}
