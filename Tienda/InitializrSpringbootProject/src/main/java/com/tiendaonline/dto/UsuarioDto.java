package com.tiendaonline.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
}
