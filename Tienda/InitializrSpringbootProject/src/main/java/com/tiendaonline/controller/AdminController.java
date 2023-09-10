package com.tiendaonline.controller;

import com.tiendaonline.entity.ProductoEntity;
import com.tiendaonline.service.CategoriaService;
import com.tiendaonline.service.MarcaService;
import com.tiendaonline.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class AdminController {
    
    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaService marcaService;

    
    // RUTAS PARA ADMINISTRADORES
    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoProducto(Model modelo){
        ProductoEntity productoEntity = new ProductoEntity();
        modelo.addAttribute("categorias", categoriaService.ListarCategorias());
        var listacategorias = categoriaService.ListarCategoriasByEstadoTrue();
        modelo.addAttribute("listacategorias", listacategorias);
        var listamarcas = marcaService.ListarMarcasByEstadoTrue();
        modelo.addAttribute("listamarcas", listamarcas);
        modelo.addAttribute("productoEntity", productoEntity);

        return "form_agregarproducto";
    }

}
