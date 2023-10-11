package com.tiendaonline.controller;

import com.tiendaonline.entity.CarritoEntity;
import com.tiendaonline.entity.DetallesCarritoEntity;
import com.tiendaonline.entity.ProductoEntity;
import com.tiendaonline.entity.UsuarioEntity;
import com.tiendaonline.repository.CarritoRepository;
import com.tiendaonline.repository.UsuarioRepository;
import com.tiendaonline.service.CarritoService;
import com.tiendaonline.service.DetalleCarritoService;
import com.tiendaonline.service.ProductoService;
import com.tiendaonline.service.UsuarioService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarritoDeComprasController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private DetalleCarritoService detalleCarritoService;
    
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // para almacenar los detalles de la orden
    List<DetallesCarritoEntity> detalles = new ArrayList<>();

    // datos de la orden
    CarritoEntity orden = new CarritoEntity();

    @GetMapping("/cart")
    public String mostrarCarrito(Model model, Authentication authentication, HttpSession session) {

        // CarritoEntity carrito = (CarritoEntity) session.getAttribute("carrito");

        // Obtener el nombre de usuario de la autenticación
        String username = authentication.getName();

        // Obtener el usuario utilizando el nombre de usuario
        UsuarioEntity usuario = usuarioService.obtenerUsuarioPorEmail(username);

        // Obtener el carrito junto con los detalles
        CarritoEntity carrito = carritoService.obtenerCarritoConDetallesPorUsuario(usuario);

        // IDENTIFICADOR DEL USUARIO
        model.addAttribute("carrito", carrito);
        model.addAttribute("sesion", session.getAttribute("identificadordelusuario"));
        return "carrito.html";
    }

    @PostMapping("/cart")
    public String añadirProductoAlCarrito(Model model, @RequestParam Long id_producto, @RequestParam double cantidad, 
            HttpSession session, Authentication authentication, RedirectAttributes redirectAttributes) {
        // Obtén el carrito desde la sesión
        // CarritoEntity carrito = (CarritoEntity) session.getAttribute("carrito");
        // Obtener el nombre de usuario de la autenticación
        String username = authentication.getName();

        // Obtener el usuario utilizando el nombre de usuario
        UsuarioEntity usuario = usuarioService.obtenerUsuarioPorEmail(username);

        // Obtener el carrito junto con los detalles
        CarritoEntity carrito = carritoService.obtenerCarritoConDetallesPorUsuario(usuario);

        if (carrito == null) {
            // Crea un nuevo carrito si no existe en la sesión
            carrito = new CarritoEntity();
            carrito.setFecha_creacion(new Date());


            // Configura la relación entre el usuario y el carrito
            carrito.setUsuarioEntity(usuario);
            carrito.setEstado_carrito(true);

            // Guarda el carrito en la base de datos
            carritoRepository.save(carrito);

            // Asocia el carrito con la sesión
            session.setAttribute("carrito", carrito);
        }

        // Verifica si el producto ya existe en el carrito
        boolean productoExistente = false;
        ProductoEntity producto = productoService.ObtenerProductoPorId(id_producto);
        

        
        double precio = 0;

        for (DetallesCarritoEntity detalle : carrito.getDetallesCarritoEntity()) {
            Hibernate.initialize(detalle.getProductoEntity()); // Forzar la inicialización
            if (detalle.getProductoEntity().getId_producto().equals(id_producto)) {
                // El producto ya existe en el carrito, actualiza la cantidad
                double nuevaCantidad = detalle.getCantidad() + cantidad;
                if (producto.getPreciooferta() == null){
                    precio = producto.getPrecionormal();
                } else {
                    precio = producto.getPreciooferta();
                }
                
                

                double nuevoTotal = (detalle.getCantidad() + cantidad) * precio;
                detalle.setCantidad(nuevaCantidad);
                detalle.setTotal(nuevoTotal);
                productoExistente = true;

                break;
                
            }
        }

        // SI EL PRODUCTO NO SE REPITE
        if (!productoExistente) {
            
            // Manejo del precio real
            if (producto.getPreciooferta() == null){
                precio = producto.getPrecionormal();
            } else {
                precio = producto.getPreciooferta();
            }
                
            // Crea un nuevo detalle de carrito
            DetallesCarritoEntity detalleCarrito = new DetallesCarritoEntity();
            detalleCarrito.setProductoEntity(producto);
            detalleCarrito.setCantidad(cantidad);
            detalleCarrito.setTotal(precio * cantidad);
            // Establece la relación inversa desde DetallesCarritoEntity al carrito
            detalleCarrito.setCarritoEntity(carrito);
            // Agrega el detalle al carrito
            carrito.getDetallesCarritoEntity().add(detalleCarrito);

        }
        
        // CALCULO DEL TOTAL A PAGAR DEL USUARIO
        
        // Guarda el carrito y el detalle en la base de datos
        carritoService.guardarCarrito(carrito);
        carritoService.CalcularTotalCarrito(carrito);
        model.addAttribute("carrito", carrito);
        model.addAttribute("sesion", session.getAttribute("identificadordelusuario"));

        // Redirige al carrito para evitar reenviar datos en caso de recarga
        return "redirect:/cart";
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
        modelo.addAttribute("carrito", carrito);
        modelo.addAttribute("sesion", session.getAttribute("identificadordelusuario"));

     */

 /*
    @PostMapping("/cart")
    public String añadirProductoAlCarrito(@RequestParam Long id_producto, @RequestParam double cantidad, Model model, HttpSession session) {
        // Obtén o crea el carrito desde la sesión
        CarritoEntity carrito = (CarritoEntity) session.getAttribute("carrito");
        if (carrito == null) {
            // Si el carrito no existe en la sesión, crea uno nuevo
            carrito = new CarritoEntity();
        }

        // Recupera el producto que se quiere agregar al carrito
        ProductoEntity producto = productoService.ObtenerProductoPorId(id_producto);

        // Crea un detalle de carrito con la información del producto y cantidad
        DetallesCarritoEntity detalleCarrito = new DetallesCarritoEntity();
        detalleCarrito.setProducto(producto);
        detalleCarrito.setCantidad(cantidad);

        // Agrega el detalle al carrito
        carrito.getDetallesCarritoEntity().add(detalleCarrito);

        // Actualiza el carrito en la sesión
        session.setAttribute("carrito", carrito);

        // Redirige de nuevo al carrito
        return "redirect:/cart";
    }
     */
    
        /*
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        */

}
