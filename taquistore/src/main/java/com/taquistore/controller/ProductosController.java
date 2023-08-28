package com.taquistore.controller;

import com.taquistore.entity.CategoriaEntity;
import com.taquistore.service.CategoriaService;
import com.taquistore.service.MarcaService;
import com.taquistore.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    ){
        modelo.addAttribute("productos", productoService.ListarProductosHabilitadosPorFiltrosDeBusqueda(
                categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
        
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        modelo.addAttribute("marcas", marcaService.ListarMarcasByEstadoTrue());
        
        
        return "listaproductosgeneral.html";
    }
    
    @GetMapping("/{categoriaNombre}")
    public String productosporcategoria(
            @PathVariable String categoriaNombre,
            @RequestParam(value = "p-marcas", required = false) List<Long> marcaIds,
            @RequestParam(value = "p-min", required = false) Double minPrecio,
            @RequestParam(value = "p-max", required = false) Double maxPrecio,
            @RequestParam(value = "p-oferta", required = false) Boolean enOferta,
            @RequestParam(value = "p-palabraclave", required = false) String palabraClave,
            Model modelo

    ){
        CategoriaEntity categoria = categoriaService.ObtenerCategoriaPorNombre(categoriaNombre);
    
        modelo.addAttribute("productos", productoService.ListarProductosHabilitadosPorFiltrosDeBusqueda(
                categoria.getId_categoria(), marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
        
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
        /* Todos los productos están en oferta, por lo tanto no se va a requerir del parametro oferta */
        modelo.addAttribute("productos", productoService.ListarProductosHabilitadosPorFiltrosDeBusqueda(
                categoriaId, marcaIds, minPrecio, maxPrecio, true, palabraClave));
        
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
    ){
        modelo.addAttribute("productos", productoService.ListarProductosHabilitadosPorFiltrosDeBusqueda(
                categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
        
        modelo.addAttribute("contarproductos", productoService.ContarProductosHabilitadosPorFiltrosDeBusqueda(
                categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
        
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        modelo.addAttribute("marcas", marcaService.ListarMarcasByEstadoTrue());
        modelo.addAttribute("palabraClave", palabraClave);
        return "listaproductosbusqueda.html";
    }
    
}
