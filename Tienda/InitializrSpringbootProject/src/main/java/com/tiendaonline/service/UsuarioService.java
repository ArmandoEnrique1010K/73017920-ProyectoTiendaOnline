package com.tiendaonline.service;

import com.tiendaonline.dto.UsuarioDto;
import com.tiendaonline.entity.UsuarioEntity;

public interface UsuarioService {
    
    public UsuarioEntity GuardarUsuario(UsuarioDto usuarioDto);
    
}
