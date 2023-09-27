package com.tiendaonline.controller;

import com.tiendaonline.entity.DetallesOrdenEntity;
import com.tiendaonline.entity.OrdenEntity;
import com.tiendaonline.entity.ProductoEntity;
import com.tiendaonline.entity.UsuarioEntity;
import com.tiendaonline.security.CustomUserDetails;
import com.tiendaonline.service.DetalleOrdenService;
import com.tiendaonline.service.OrdenService;
import com.tiendaonline.service.OrdenServiceImpl;
import com.tiendaonline.service.ProductoService;
import com.tiendaonline.service.UsuarioService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComprasController {

    /*
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private OrdenService ordenService;
    @Autowired
    private DetalleOrdenService detalleOrdenService;
*/
    
    @Autowired
    private OrdenService ordenService;

    @Autowired
    private UsuarioService usuarioService;

    /*
    @PostMapping("/cart")
    public String agregarProductoAlCarrito(
            @RequestParam Long id_producto,
            @RequestParam Double cantidad,
            Model model,
            Authentication authentication) {
        // Obtener el usuario actual (puedes utilizar Spring Security para esto)

        // Obtener el nombre de usuario del usuario actual
        String username = authentication.getName();

        // Llamar al servicio para obtener el usuario actual por su correo electrónico (nombre de usuario)
        UsuarioEntity usuario = usuarioService.obtenerUsuarioPorEmail(username);
        
        // Verificar si se encontró al usuario
        if (usuario != null) {
            // Llamar al servicio para agregar el producto al carrito
            ordenService.agregarProductoAlCarrito(usuario.getId_usuario(), id_producto, cantidad);
        } else {
            // Manejar el caso en que el usuario no se encuentra
            // Puedes redirigir a una página de error o realizar otra acción según tus necesidades
        }
        
        // Redirigir a la página del carrito o a donde desees
        return "redirect:/carrito";
    }
    */
    /*
@GetMapping("/cart")
public String verCarrito(Model model, Authentication authentication) {
    // Obtener el nombre de usuario del usuario actual
    String username = authentication.getName();
    UsuarioEntity usuarioEntity = ordenService.encontrarPorUsuario(usuarioEntity);
    
    // Llamar al servicio para obtener el usuario actual por su correo electrónico (nombre de usuario)
    UsuarioEntity usuario = usuarioService.obtenerUsuarioPorEmail(username);

    // Verificar si se encontró al usuario
    if (usuario != null) {
        // Llamar al servicio para obtener el contenido del carrito del usuario
        List<DetallesOrdenEntity> contenidoCarrito = usuarioEntity(usuario);
        
        // Agregar el contenido del carrito al modelo
        model.addAttribute("carrito", contenidoCarrito);
    } else {
        // Manejar el caso en que el usuario no se encuentra
        // Puedes redirigir a una página de error o realizar otra acción según tus necesidades
    }

    // Devolver la vista de la página del carrito
    return "carrito";
}
*/
    
    
    
    
    
    
    // ALMACENAR LOS DETALLES DE LA ORDEN
    // List<DetallesOrdenEntity> detalles = new ArrayList<>();
    // DATOS DE LA ORDEN
    // OrdenEntity orden = new OrdenEntity();
    
    
    // ALMACENAR LOS DETALLES DE LA ORDEN
    // Set<DetallesOrdenEntity> detalles = new HashSet<>();
    // DATOS DE LA ORDEN
    // OrdenEntity orden = new OrdenEntity();

    /*
    @PostMapping("/cart")
    public String agregarAlCarrito(@RequestParam Long id_producto, @RequestParam Double cantidad, Model modelo) {
        DetallesOrdenEntity detalleOrden = new DetallesOrdenEntity();
        ProductoEntity producto = new ProductoEntity();
        double sumaTotal = 0;

        ProductoEntity optionalProducto = productoService.ObtenerProductoPorId(id_producto);

        double PrecioProducto = 0;
        if (optionalProducto.getPreciooferta() == null) {
            PrecioProducto = optionalProducto.getPrecionormal();
        } else {
            PrecioProducto = optionalProducto.getPreciooferta();
        }

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setNombre(optionalProducto.getNombre());
        detalleOrden.setPrecio(PrecioProducto);
        detalleOrden.setTotal(PrecioProducto * cantidad);
        detalleOrden.setProductoEntity(producto);
        

        detalles.add(detalleOrden);

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal_a_pagar(sumaTotal);
        modelo.addAttribute("cart", detalles);
        modelo.addAttribute("orden", orden);

        return "carrito.html";
    }
*/
    /*
    @PostMapping("/cart")
    public String agregarAlCarrito(@RequestParam Long id_producto, @RequestParam Double cantidad, Model modelo) {
        ProductoEntity optionalProducto = productoService.ObtenerProductoPorId(id_producto);

        double PrecioProducto = 0;
        if (optionalProducto.getPreciooferta() == null) {
            PrecioProducto = optionalProducto.getPrecionormal();
        } else {
            PrecioProducto = optionalProducto.getPreciooferta();
        }

        // Crear un detalle de orden
        DetallesOrdenEntity detalleOrden = new DetallesOrdenEntity();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setNombre(optionalProducto.getNombre());
        detalleOrden.setPrecio(PrecioProducto);
        detalleOrden.setTotal(PrecioProducto * cantidad);
        detalleOrden.setProductoEntity(optionalProducto);

        // Verificar si el producto ya está en el carrito (por ID)
        boolean productoYaEnCarrito = detalles.stream()
                .anyMatch(detalle -> Objects.equals(detalle.getProductoEntity().getId_producto(), id_producto));

        if (!productoYaEnCarrito) {
            // Agregar el detalle de orden al carrito
            detalles.add(detalleOrden);
        }

        // Calcular la suma total
        double sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal_a_pagar(sumaTotal);

        // Actualizar el modelo
        modelo.addAttribute("cart", detalles);
        modelo.addAttribute("orden", orden);

        return "carrito.html";
    }

    
    
    @GetMapping("/getCart")
    public String obtenerCarrito(Model modelo, Authentication authentication){
        modelo.addAttribute("cart", detalles);
        modelo.addAttribute("orden", orden);
        
        String username = authentication.getName();
        UsuarioEntity usuario = usuarioService.obtenerUsuarioPorEmail(username);
        modelo.addAttribute("usuario_nombre", usuario.getNombre());
        modelo.addAttribute("usuario_apellido", usuario.getApellido());
        
        return "carrito.html";
    }
    */
    
    /*
    @GetMapping("/cart")
    public String verCarrito(Model modelo, Authentication authentication) {
        List<DetallesOrdenEntity> detalles = obtenerDetallesDesdeAutenticacion(authentication);
        OrdenEntity orden = obtenerOrdenDesdeAutenticacion(authentication);

        modelo.addAttribute("cart", detalles);
        modelo.addAttribute("orden", orden);

        return "carrito.html";
    }

    @PostMapping("/cart")
    public String agregarAlCarrito(@RequestParam Long id_producto, @RequestParam Double cantidad, Model modelo, Authentication authentication) {
        ProductoEntity optionalProducto = productoService.ObtenerProductoPorId(id_producto);

        double PrecioProducto = 0;
        if (optionalProducto.getPreciooferta() == null) {
            PrecioProducto = optionalProducto.getPrecionormal();
        } else {
            PrecioProducto = optionalProducto.getPreciooferta();
        }

        // Crear un detalle de orden
        DetallesOrdenEntity detalleOrden = new DetallesOrdenEntity();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setNombre(optionalProducto.getNombre());
        detalleOrden.setPrecio(PrecioProducto);
        detalleOrden.setTotal(PrecioProducto * cantidad);
        detalleOrden.setProductoEntity(optionalProducto);

        List<DetallesOrdenEntity> detalles = obtenerDetallesDesdeAutenticacion(authentication);
        OrdenEntity orden = obtenerOrdenDesdeAutenticacion(authentication);

        // Verificar si el producto ya está en el carrito (por ID)
        boolean productoYaEnCarrito = detalles.stream()
                .anyMatch(detalle -> Objects.equals(detalle.getProductoEntity().getId_producto(), id_producto));

        if (!productoYaEnCarrito) {
            // Agregar el detalle de orden al carrito
            detalles.add(detalleOrden);

            // Actualizar la autenticación con los nuevos detalles y la orden
            actualizarAutenticacion(authentication, detalles, orden);

            // Calcular la suma total
            double sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
            orden.setTotal_a_pagar(sumaTotal);

            // Actualizar el modelo
            modelo.addAttribute("cart", detalles);
            modelo.addAttribute("orden", orden);
        }

        return "carrito.html";
    }

    */
    /*
    private List<DetallesOrdenEntity> obtenerDetallesDesdeAutenticacion(Authentication authentication) {
        // Aquí deberías implementar la lógica para obtener los detalles desde la autenticación.
        // Esto podría involucrar una base de datos o un repositorio específico para el carrito de compras.
        // Por simplicidad, asumimos que la autenticación almacena los detalles en un objeto UserDetails personalizado.
        // Debes adaptar esta parte a tu aplicación real.
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getDetallesOrden();
    }

    private OrdenEntity obtenerOrdenDesdeAutenticacion(Authentication authentication) {
        // Similar a obtenerDetallesDesdeAutenticacion, aquí debes implementar la lógica para obtener la orden desde la autenticación.
        // Debes adaptar esta parte a tu aplicación real.
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getOrden();
    }

    private void actualizarAutenticacion(Authentication authentication, List<DetallesOrdenEntity> detalles, OrdenEntity orden) {
        // Aquí debes implementar la lógica para actualizar la autenticación con los nuevos detalles y la orden.
        // Esto podría implicar la creación de un objeto UserDetails personalizado y la actualización de la autenticación actual.
        // Debes adaptar esta parte a tu aplicación real.
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        userDetails.setDetallesOrden(detalles);
        userDetails.setOrden(orden);
    }

    */

}
