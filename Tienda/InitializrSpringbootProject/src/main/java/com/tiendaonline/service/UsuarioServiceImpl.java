package com.tiendaonline.service;

import com.tiendaonline.dto.UsuarioDto;
import com.tiendaonline.entity.RolEntity;
import com.tiendaonline.entity.UsuarioEntity;
import com.tiendaonline.repository.UsuarioRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UsuarioEntity GuardarUsuario(UsuarioDto usuarioDto) {

        UsuarioEntity usuarioEntity = new UsuarioEntity(
                usuarioDto.getNombre(), 
                usuarioDto.getApellido(),
                usuarioDto.getEmail(), 
                passwordEncoder.encode(usuarioDto.getPassword()),
                Arrays.asList(new RolEntity("USER")));
        
        return usuarioRepository.save(usuarioEntity);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.findUserByEmail(username);
        if (usuarioEntity == null){
            throw new UsernameNotFoundException("Usuario o contraseña invalido(a)");
        }
        return new User(usuarioEntity.getEmail(), usuarioEntity.getPassword(), mapearAutoridadesARoles(usuarioEntity.getRoles()));
    }
    
    
    private Collection <? extends GrantedAuthority> mapearAutoridadesARoles(Collection<RolEntity> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
        
    }

}













