package com.tiendaonline.service;

import com.tiendaonline.entity.CategoriaEntity;
import com.tiendaonline.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaEntity> ListarCategorias() {
        return categoriaRepository.findAll();
    }
    
    @Override
    public List<CategoriaEntity> ListarCategoriasByEstadoTrue() {
        return categoriaRepository.findAllByEstadoTrue();
    }

    @Override
    public CategoriaEntity ObtenerCategoriaPorNombre(String nombreCategoria) {
        return categoriaRepository.findAllByNombreParam(nombreCategoria);
    }
    

    
}
