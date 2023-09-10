package com.tiendaonline.service;

import com.tiendaonline.entity.MarcaEntity;
import java.util.List;


public interface MarcaService {
    
    public List<MarcaEntity> ListarMarcas();
    public List<MarcaEntity> ListarMarcasByEstadoTrue();
    public List<MarcaEntity> ListarDistintasMarcasByGrupoCategoriaAndEstadoTrue(String nombreCategoria);
    public List<MarcaEntity> ListarDistintasMarcasByGrupoCategoria(String nombreCategoria);

    /*
    public List<MarcaEntity> ListarMarcasPresentesPorOferta();
*/
}
