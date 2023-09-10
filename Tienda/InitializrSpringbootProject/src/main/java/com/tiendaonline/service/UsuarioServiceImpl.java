package com.tiendaonline.service;

import com.tiendaonline.dto.UsuarioDto;
import com.tiendaonline.entity.RolEntity;
import com.tiendaonline.entity.UsuarioEntity;
import com.tiendaonline.repository.UsuarioRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity GuardarUsuario(UsuarioDto usuarioDto) {

        UsuarioEntity usuarioEntity = new UsuarioEntity(usuarioDto.getNombre(), usuarioDto.getApellido(),
                usuarioDto.getEmail(), usuarioDto.getPassword(), Arrays.asList(new RolEntity("USER")));
        
        return usuarioRepository.save(usuarioEntity);

    }

}
