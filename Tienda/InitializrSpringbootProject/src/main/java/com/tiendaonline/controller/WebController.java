package com.tiendaonline.controller;

import com.tiendaonline.service.CategoriaService;
import com.tiendaonline.service.MarcaService;
import com.tiendaonline.service.ProductoService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    
    /* RUTA PARA INICIAR SESION */
    @GetMapping("/login")
    public String iniciarSesion(Model modelo){
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        return "login.html";
    }
    
    // RUTA PARA CERRAR SESION
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, Model modelo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate(); // Invalidar la sesión actual
        }
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        return "redirect:/login?logout"; // Redirigir a la página de inicio de sesión con un mensaje de cierre de sesión
    }

}
