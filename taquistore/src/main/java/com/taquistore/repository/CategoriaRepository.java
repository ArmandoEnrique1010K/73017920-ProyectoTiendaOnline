package com.taquistore.repository;

import com.taquistore.entity.CategoriaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{
    
    // LISTAR LAS CATEGORIAS QUE ESTAN HABILITADAS
    @Query("SELECT c FROM CategoriaEntity c WHERE c.estado = true")
    List<CategoriaEntity> findAllByEstadoTrue();
    
    @Query("SELECT c FROM CategoriaEntity c WHERE c.nombre = :nombreCategoria")
    CategoriaEntity findAllByNombreParam(String nombreCategoria);
}
