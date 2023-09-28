package com.tiendaonline.service;

import com.tiendaonline.dto.UsuarioDto;
import com.tiendaonline.entity.RolEntity;
import com.tiendaonline.entity.UsuarioEntity;
import com.tiendaonline.repository.UsuarioRepository;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    
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
        
        /* Corrección: Convertir la lista de roles a una lista de cadenas (nombres de roles) */
        List<String> roles = usuarioEntity.getRoles().stream()
            .map(role -> role.getNombre())
            .collect(Collectors.toList());
    
        log.info("BIENVENIDO USUARIO: " + username);
        log.info("TIENE EL ROL DE: {}", roles);

        /* Configurar los roles correctamente */
        return User.withUsername(usuarioEntity.getEmail())
                .password(usuarioEntity.getPassword())
                .roles(roles.toArray(String[]::new))
                .build();
    
        // return new User(usuarioEntity.getEmail(), usuarioEntity.getPassword(), mapearAutoridadesARoles(usuarioEntity.getRoles()));
    }
    
    
    private Collection <? extends GrantedAuthority> mapearAutoridadesARoles(Collection<RolEntity> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
        
    }

    // Método para obtener los datos del usuario por correo electrónico
    @Override
    public UsuarioEntity obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findUserByEmail(email);
    }

    /****************/
    @Override
    public UsuarioEntity obtenerUsuarioPorId(Long id_usuario) {
        return usuarioRepository.findById(id_usuario).orElse(null);
    }
    

}



        /* VERDADERO CODIGO :O */
        /*
        String[] roles = usuarioEntity.getRoles().stream()
                .map(role -> role.getNombre())
                .toArray(String[]::new);
        return User.withUsername(usuarioEntity.getEmail())
                .password(usuarioEntity.getPassword())
                .roles(roles)
                
                .build();
        */
        /* VERDADERO CODIGO :O */










