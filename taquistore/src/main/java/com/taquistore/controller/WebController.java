package com.taquistore.controller;

import com.taquistore.service.CategoriaService;
import com.taquistore.service.MarcaService;
import com.taquistore.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    
    @Autowired
    private ProductoService productoService;


    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/")
    public String inicio (Model modelo){
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        return "index.html";
    }
}
