package com.taquistore.service;

import com.taquistore.entity.CategoriaEntity;
import java.util.List;

public interface CategoriaService {
    public List<CategoriaEntity> ListarCategorias();
    public List<CategoriaEntity> ListarCategoriasByEstadoTrue();
    public CategoriaEntity ObtenerCategoriaPorNombre(String nombreCategoria);

    /*
    public CategoriaEntity obtenerCategoriaPorId(Long id_categoria);
*/
}
