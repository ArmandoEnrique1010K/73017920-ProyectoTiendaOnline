package com.taquistore.service;

import com.taquistore.entity.MarcaEntity;
import com.taquistore.repository.MarcaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl implements MarcaService{

    @Autowired
    private MarcaRepository marcaRepository;
    
    @Override
    public List<MarcaEntity> ListarMarcas() {
        return marcaRepository.findAll();
    }
    
    
    
/*
    // LISTAR CADA UNA DE LAS MARCAS QUE SE ENCUENTREN DENTRO DE UN GRUPO DE PRODUCTOS QUE PERTENECEN A LA MISMA CATEGORIA
    @Override
    public List<MarcaEntity> ListarMarcasPresentesPorCategoria(String nombreCategoria) {
        return marcaRepository.findMarcasByCategoriaAndEstadoTrue(nombreCategoria);
    }

    // LISTAR CADA UNA DE LAS MARCAS QUE SE ENCUENTREN DENTRO DEL GRUPO DE PRODUCTOS EN OFERTA
    @Override
    public List<MarcaEntity> ListarMarcasPresentesPorOferta() {
        return marcaRepository.findMarcasByEstadoTrue();
    }
  */  

    @Override
    public List<MarcaEntity> ListarMarcasByEstadoTrue() {
        return marcaRepository.findAllByEstadoTrue();
    }

    @Override
    public List<MarcaEntity> ListarDistintasMarcasByGrupoCategoriaAndEstadoTrue(String nombreCategoria) {
        return marcaRepository.findAllDistinctByCategoriaGroupAndEstadoTrue(nombreCategoria);
    }

    @Override
    public List<MarcaEntity> ListarDistintasMarcasByGrupoCategoria(String nombreCategoria) {
        return marcaRepository.findAllDistinctByCategoriaGroup(nombreCategoria);
    }
}
