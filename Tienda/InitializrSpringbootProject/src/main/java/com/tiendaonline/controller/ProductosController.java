package com.tiendaonline.controller;

import com.tiendaonline.entity.CategoriaEntity;
import com.tiendaonline.entity.ProductoEntity;
import com.tiendaonline.service.CategoriaService;
import com.tiendaonline.service.MarcaService;
import com.tiendaonline.service.ProductoService;
// import com.tiendaonline.util.PageRender;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public String todoslosproductos(
            @RequestParam(value = "p-categoria", required = false) Long categoriaId,
            @RequestParam(value = "p-marcas", required = false) List<Long> marcaIds,
            @RequestParam(value = "p-min", required = false) Double minPrecio,
            @RequestParam(value = "p-max", required = false) Double maxPrecio,
            @RequestParam(value = "p-oferta", required = false) Boolean enOferta,
            @RequestParam(value = "p-palabraclave", required = false) String palabraClave,
            Model modelo
    ) {

        
        // ¿EL CLIENTE, ESTA AUTORIZADO COMO ADMINISTRADOR?
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication != null
                && authentication.isAuthenticated()
                && authentication.getAuthorities().stream()
                        .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin == true) {
            // SI ES UN ADMINISTRADOR...
            modelo.addAttribute("productos", productoService.ListarProductosPorFiltrosDeBusqueda(
                    categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
        } else {
            // SI ES UN USUARIO O NO ESTA REGISTRADO
            modelo.addAttribute("productos", productoService.ListarProductosHabilitadosPorFiltrosDeBusqueda(
                    categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
        }

        
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        modelo.addAttribute("marcas", marcaService.ListarMarcasByEstadoTrue());

        return "listaproductosgeneral.html";
    }

    /*
    @GetMapping
    public String todoslosproductos(
            @RequestParam(value = "p-categoria", required = false) Long categoriaId,
            @RequestParam(value = "p-marcas", required = false) List<Long> marcaIds,
            @RequestParam(value = "p-min", required = false) Double minPrecio,
            @RequestParam(value = "p-max", required = false) Double maxPrecio,
            @RequestParam(value = "p-oferta", required = false) Boolean enOferta,
            @RequestParam(value = "p-palabraclave", required = false) String palabraClave,
            
            @RequestParam(name = "page", defaultValue = "0") int page,
            Model modelo
    ){
        
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<ProductoEntity> productoEntity = productoService.ListarPorPaginasProductosHabilitadosPorFiltrosDeBusqueda(categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave, pageRequest);
        PageRender<ProductoEntity> pageRender = new PageRender<>("/productos", productoEntity);
        
        modelo.addAttribute("productos", productoEntity);
        modelo.addAttribute("page", pageRender);
        
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        modelo.addAttribute("marcas", marcaService.ListarMarcasByEstadoTrue());
        
        
        return "listaproductosgeneral.html";
    }
     */
    @GetMapping("/{categoriaNombre}")
    public String productosporcategoria(
            @PathVariable String categoriaNombre,
            @RequestParam(value = "p-marcas", required = false) List<Long> marcaIds,
            @RequestParam(value = "p-min", required = false) Double minPrecio,
            @RequestParam(value = "p-max", required = false) Double maxPrecio,
            @RequestParam(value = "p-oferta", required = false) Boolean enOferta,
            @RequestParam(value = "p-palabraclave", required = false) String palabraClave,
            Model modelo
    ) {
        CategoriaEntity categoria = categoriaService.ObtenerCategoriaPorNombre(categoriaNombre);

        // ¿EL CLIENTE, ESTA AUTORIZADO COMO ADMINISTRADOR?
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication != null
                && authentication.isAuthenticated()
                && authentication.getAuthorities().stream()
                        .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin == true) {
            // SI ES UN ADMINISTRADOR...
            modelo.addAttribute("productos", productoService.ListarProductosPorFiltrosDeBusqueda(
                    categoria.getId_categoria(), marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
        } else {
            // SI ES UN USUARIO O NO ESTA REGISTRADO
            modelo.addAttribute("productos", productoService.ListarProductosHabilitadosPorFiltrosDeBusqueda(
                    categoria.getId_categoria(), marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
        }

        
        
        
        
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        modelo.addAttribute("categoriaactual", categoriaService.ObtenerCategoriaPorNombre(categoriaNombre));
        modelo.addAttribute("marcas", marcaService.ListarDistintasMarcasByGrupoCategoriaAndEstadoTrue(categoriaNombre));
        return "listaproductoscategoria.html";
    }

    @GetMapping("/oferta")
    public String productosenoferta(
            @RequestParam(value = "p-categoria", required = false) Long categoriaId,
            @RequestParam(value = "p-marcas", required = false) List<Long> marcaIds,
            @RequestParam(value = "p-min", required = false) Double minPrecio,
            @RequestParam(value = "p-max", required = false) Double maxPrecio,
            @RequestParam(value = "p-palabraclave", required = false) String palabraClave,
            Model modelo
    ) {
        
        
        // ¿EL CLIENTE, ESTA AUTORIZADO COMO ADMINISTRADOR?
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication != null
                && authentication.isAuthenticated()
                && authentication.getAuthorities().stream()
                        .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin == true) {
            // SI ES UN ADMINISTRADOR...
            modelo.addAttribute("productos", productoService.ListarProductosPorFiltrosDeBusqueda(
                    categoriaId, marcaIds, minPrecio, maxPrecio, true, palabraClave));
        } else {
            // SI ES UN USUARIO O NO ESTA REGISTRADO
            modelo.addAttribute("productos", productoService.ListarProductosHabilitadosPorFiltrosDeBusqueda(
                    categoriaId, marcaIds, minPrecio, maxPrecio, true, palabraClave));
        }


        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        modelo.addAttribute("marcas", marcaService.ListarMarcasByEstadoTrue());
        return "listaproductosoferta.html";
    }

    @GetMapping("/buscar")
    public String productosbusqueda(
            @RequestParam(value = "p-categoria", required = false) Long categoriaId,
            @RequestParam(value = "p-marcas", required = false) List<Long> marcaIds,
            @RequestParam(value = "p-min", required = false) Double minPrecio,
            @RequestParam(value = "p-max", required = false) Double maxPrecio,
            @RequestParam(value = "p-oferta", required = false) Boolean enOferta,
            @RequestParam(value = "p-palabraclave", required = false) String palabraClave,
            Model modelo
    ) {
        
        
        // ¿EL CLIENTE, ESTA AUTORIZADO COMO ADMINISTRADOR?
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication != null
                && authentication.isAuthenticated()
                && authentication.getAuthorities().stream()
                        .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin == true) {
            // SI ES UN ADMINISTRADOR...
            modelo.addAttribute("productos", productoService.ListarProductosPorFiltrosDeBusqueda(
                    categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
            modelo.addAttribute("contarproductos", productoService.ContarProductosPorFiltrosDeBusqueda(
                    categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
        } else {
            // SI ES UN USUARIO O NO ESTA REGISTRADO
            modelo.addAttribute("productos", productoService.ListarProductosHabilitadosPorFiltrosDeBusqueda(
                    categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
            modelo.addAttribute("contarproductos", productoService.ContarProductosHabilitadosPorFiltrosDeBusqueda(
                    categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
        }
        

        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        modelo.addAttribute("marcas", marcaService.ListarMarcasByEstadoTrue());
        modelo.addAttribute("palabraClave", palabraClave);
        return "listaproductosbusqueda.html";
    }
    
    

}
