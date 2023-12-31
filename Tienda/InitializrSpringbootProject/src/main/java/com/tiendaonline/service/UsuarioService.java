package com.tiendaonline.service;

import com.tiendaonline.dto.UsuarioDto;
import com.tiendaonline.entity.UsuarioEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService{
    
    public UsuarioEntity GuardarUsuario(UsuarioDto usuarioDto);
    public UsuarioEntity obtenerUsuarioPorEmail(String email);
    public UsuarioEntity obtenerUsuarioPorId(Long id_usuario);
}
