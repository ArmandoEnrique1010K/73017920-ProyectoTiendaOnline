package com.tiendaonline.controller;

import com.tiendaonline.entity.CarritoEntity;
import com.tiendaonline.entity.RolEntity;
import com.tiendaonline.entity.UsuarioEntity;
import com.tiendaonline.repository.CarritoRepository;
import com.tiendaonline.repository.UsuarioRepository;
import com.tiendaonline.service.CarritoService;
import com.tiendaonline.service.CategoriaService;
import com.tiendaonline.service.MarcaService;
import com.tiendaonline.service.ProductoService;
import com.tiendaonline.service.UsuarioService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private CarritoService carritoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String inicio(Model modelo, Authentication authentication, HttpSession session) {
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());

        // SI EL USUARIO ESTÁ REGISTRADO, MOSTRAR SU INFORMACIÓN
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            UsuarioEntity usuario = usuarioService.obtenerUsuarioPorEmail(username);

            // Recuperar roles del usuario
            List<RolEntity> roles = (List<RolEntity>) usuario.getRoles();
            // Haz algo con los roles aquí, por ejemplo, pasarlos al modelo

            // Recuperar el carrito
            CarritoEntity carrito = carritoService.obtenerCarritoPorUsuario(usuario);

            if (carrito == null) {
                // Si el usuario no tiene carritos, crea uno nuevo
                carrito = new CarritoEntity();
                carrito.setFecha_creacion(new Date());
                carrito.setUsuarioEntity(usuario);
                carrito.setEstado_carrito(true); // Marcar el carrito como activo
                carritoRepository.save(carrito);
            }

            // Asociar el carrito con la sesión
            session.setAttribute("carrito", carrito);

            modelo.addAttribute("usuario_nombre", usuario.getNombre());
            modelo.addAttribute("usuario_apellido", usuario.getApellido());
            modelo.addAttribute("usuario_roles", roles);
        }

        return "index.html";
    }

    /* RUTA PARA INICIAR SESION */
    @GetMapping("/login")
    public String iniciarSesion(Model modelo) {
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
/*
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        // session
        log.info("Sesion del usuario: {}", session.getAttribute("id_del_usuario"));
        modelo.addAttribute("sesion", session.getAttribute("id_del_usuario"));
 */
 /*
            CarritoEntity carrito = new CarritoEntity();
            carrito.setUsuarioEntity(usuario);
            carritoRepository.save(carrito);
            
            usuario.setCarritoEntity((List<CarritoEntity>) carrito);
            usuarioRepository.save(usuario);
 */
 /*
            // Crear un nuevo carrito
            CarritoEntity nuevoCarrito = new CarritoEntity();
            nuevoCarrito.setUsuarioEntity(usuario);

            // Agregar el carrito a la lista de carritos del usuario
            usuario.getCarritoEntity().add(nuevoCarrito);

            // Guardar el usuario en la base de datos (para que se actualice la relación)
            usuarioRepository.save(usuario);
 */
/**
 *
 */
// Verificar si el usuario ya tiene un carrito
/*
            if (usuario.getCarritoEntity()== null || usuario.getCarritoEntity().isEmpty()) {
                // Si el usuario no tiene carritos, crea uno nuevo
                CarritoEntity nuevoCarrito = new CarritoEntity();
                nuevoCarrito.setUsuarioEntity(usuario);
                nuevoCarrito.setEstado_carrito(true); // Marcar el carrito como activo
                carritoRepository.save(nuevoCarrito);

                // Agregar el carrito a la lista de carritos del usuario
                List<CarritoEntity> carritos = new ArrayList<>();
                carritos.add(nuevoCarrito);
                usuario.setCarritoEntity(carritos);
                usuarioRepository.save(usuario);

                // Asociar el carrito con la sesión
                session.setAttribute("carrito", nuevoCarrito);
               
            }
 */

 /*            if (usuario.getCarritoEntity() == null || usuario.getCarritoEntity().isEmpty()) {
                // Si el usuario no tiene carritos, crea uno nuevo
                CarritoEntity nuevoCarrito = new CarritoEntity();
                nuevoCarrito.setUsuarioEntity(usuario);
                nuevoCarrito.setEstado_carrito(true); // Marcar el carrito como activo
                carritoRepository.save(nuevoCarrito);

                // Agregar el carrito a la lista de carritos del usuario
                //List<CarritoEntity> carritos = new ArrayList<>();
                //carritos.add(nuevoCarrito);
                //usuario.setCarritoEntity(carritos);
                usuario.setCarritoEntity(nuevoCarrito);
                usuarioRepository.save(usuario);
            } 
 */
