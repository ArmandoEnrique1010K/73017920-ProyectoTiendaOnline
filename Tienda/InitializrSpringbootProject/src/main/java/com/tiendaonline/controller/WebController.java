package com.tiendaonline.controller;

import com.tiendaonline.entity.UsuarioEntity;
import com.tiendaonline.service.CategoriaService;
import com.tiendaonline.service.MarcaService;
import com.tiendaonline.service.ProductoService;
import com.tiendaonline.service.UsuarioService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    
	private final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private MarcaService marcaService;

    @Autowired
    private UsuarioService usuarioService;

    
    @GetMapping("/")
    public String inicio (Model modelo, Authentication authentication, HttpSession session){
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        /*
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        // session
        log.info("Sesion del usuario: {}", session.getAttribute("id_del_usuario"));
        modelo.addAttribute("sesion", session.getAttribute("id_del_usuario"));
        */
        // SI EL USUARIO ESTA REGISTRADO VA A MOSTRAR SU NOMBRE Y APELLIDO EN LA PAGINA DE INICIO
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            UsuarioEntity usuario = usuarioService.obtenerUsuarioPorEmail(username);
            modelo.addAttribute("usuario_nombre", usuario.getNombre());
            modelo.addAttribute("usuario_apellido", usuario.getApellido());
            modelo.addAttribute("usuario_roles", usuario.getRoles());
        }
         // log.info("Roles del usuario: {}", authentication.getAuthorities());

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
