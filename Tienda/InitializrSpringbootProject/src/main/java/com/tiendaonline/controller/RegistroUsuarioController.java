package com.tiendaonline.controller;

import com.tiendaonline.dto.UsuarioDto;
import com.tiendaonline.service.CategoriaService;
import com.tiendaonline.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CategoriaService categoriaService;

    @ModelAttribute("usuario")
    public UsuarioDto retornarNuevoUsuario(){
        return new UsuarioDto();
    }
    
    @GetMapping
    public String mostrarFormularioDeRegistro(Model modelo){
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        return "registro.html";
    }
    
    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute ("usuario") UsuarioDto usuarioDto, Model modelo){
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        usuarioService.GuardarUsuario(usuarioDto);
        return "redirect:/registro?exito";
    }
    
    
    
}
