package com.tiendaonline.controller;
/*
import com.tiendaonline.entity.CarritoEntity;
import com.tiendaonline.entity.DetallesCarritoEntity;
import com.tiendaonline.entity.UsuarioEntity;
import com.tiendaonline.service.CarritoService;
import com.tiendaonline.service.ProductoService;
import com.tiendaonline.service.UsuarioService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
*/
///
//@Controller
public class ComprasController {
}
/*
    private final Logger log = LoggerFactory.getLogger(ComprasController.class);
    
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CarritoService carritoService;


    @Autowired
    private UsuarioService usuarioService;
*/
	// para almacenar los detalles de la orden
	//List<DetallesCarritoEntity> detalles = new ArrayList<>();

	// datos de la orden
	// CarritoEntity orden = new CarritoEntity();

    // Ruta para mostrar el carrito de compras
        /*
@GetMapping("/cart")
public String mostrarCarrito(Model model, String nombreUsuario) {
    CarritoEntity carrito = carritoService.obtenerCarritoPorNombreUsuario(nombreUsuario);

    if (carrito != null) {
        // Carrito encontrado, agrega el carrito a la vista
        model.addAttribute("cart", carrito.getDetallesCarritoEntity()); // Asume que tienes un método getDetalles en CarritoEntity
        model.addAttribute("orden", carrito);
    } else {
        // Manejar el caso en el que no se encontró un carrito para el nombre de usuario
        model.addAttribute("cart", new ArrayList<DetallesCarritoEntity>()); // Carrito vacío
        model.addAttribute("orden", new CarritoEntity()); // Orden vacía
    }

    return "carrito.html";
}
        */
    /*
    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
            DetalleOrden detalleOrden = new DetalleOrden();
            Producto producto = new Producto();
            double sumaTotal = 0;

            Optional<Producto> optionalProducto = productoService.get(id);
            log.info("Producto añadido: {}", optionalProducto.get());
            log.info("Cantidad: {}", cantidad);
            producto = optionalProducto.get();

            detalleOrden.setCantidad(cantidad);
            detalleOrden.setPrecio(producto.getPrecio());
            detalleOrden.setNombre(producto.getNombre());
            detalleOrden.setTotal(producto.getPrecio() * cantidad);
            detalleOrden.setProducto(producto);

            //validar que le producto no se añada 2 veces
            Integer idProducto=producto.getId();
            boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);

            if (!ingresado) {
                    detalles.add(detalleOrden);
            }

            sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

            orden.setTotal(sumaTotal);
            model.addAttribute("cart", detalles);
            model.addAttribute("orden", orden);

            return "usuario/carrito";
    }
    */

    /*
    @Autowired
    private OrdenService ordenService;

    // para almacenar los detalles de la orden
    List<DetallesOrdenEntity> detalles = new ArrayList<>();

    // datos de la orden
    OrdenEntity orden = new OrdenEntity();
*/
    /*
    @PostMapping("/cart")
    public String addCart(@RequestParam Long id_producto, @RequestParam Double cantidad, Model model) {
        // Verifica si el producto ya está en el carrito
        Optional<DetallesOrdenEntity> existingDetail = detalles.stream()
                .filter(detail -> detail.getProductoEntity().getId_producto().equals(id_producto))
                .findFirst();

        if (existingDetail.isPresent()) {
            // Si el producto ya está en el carrito, actualiza la cantidad y el total
            DetallesOrdenEntity detalleOrdenExistente = existingDetail.get();
            // detalleOrdenExistente.setCantidad(detalleOrdenExistente.getCantidad() + cantidad);
            detalleOrdenExistente.setCantidad(detalleOrdenExistente.getCantidad());
            detalleOrdenExistente.setTotal(detalleOrdenExistente.getPrecio() * detalleOrdenExistente.getCantidad());
        } else {
            // Si el producto no está en el carrito, agrega uno nuevo
            ProductoEntity optionalProducto = productoService.ObtenerProductoPorId(id_producto);
            if (optionalProducto != null) {
                DetallesOrdenEntity detalleOrden = new DetallesOrdenEntity();
                double precioProducto = (optionalProducto.getPreciooferta() != null)
                        ? optionalProducto.getPreciooferta()
                        : optionalProducto.getPrecionormal();
                detalleOrden.setCantidad(cantidad);
                detalleOrden.setPrecio(precioProducto);
                detalleOrden.setNombre(optionalProducto.getNombre());
                detalleOrden.setTotal(precioProducto * cantidad);
                detalleOrden.setProductoEntity(optionalProducto);
                
                detalles.add(detalleOrden);
            }
        }

        // Calcula el total del carrito
        double sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal_a_pagar(sumaTotal);

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "carrito.html";
    }
*/
  /*  
    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session) {

            model.addAttribute("cart", detalles);
            model.addAttribute("orden", orden);

            //sesion
            model.addAttribute("sesion", session.getAttribute("id_usuario"));
            log.info("sesion: " + session.getId());
            return "carrito.html";
    }
*/
    
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

//}
