package com.taquistore.controller;

import com.taquistore.entity.CategoriaEntity;
import com.taquistore.entity.ProductoEntity;
import com.taquistore.service.CategoriaService;
import com.taquistore.service.MarcaService;
import com.taquistore.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public String menudeadministradores(
            Model modelo
    ) {
        modelo.addAttribute("categorias", categoriaService.ListarCategorias());
        return "admin/adminmenu";

    }

    @GetMapping("/productos")
    public String administradordetodoslosproductos(
            @RequestParam(value = "p-categoria", required = false) Long categoriaId,
            @RequestParam(value = "p-marcas", required = false) List<Long> marcaIds,
            @RequestParam(value = "p-min", required = false) Double minPrecio,
            @RequestParam(value = "p-max", required = false) Double maxPrecio,
            @RequestParam(value = "p-oferta", required = false) Boolean enOferta,
            @RequestParam(value = "p-palabraclave", required = false) String palabraClave,
            Model modelo
    ) {
        modelo.addAttribute("productos", productoService.ListarProductosPorFiltrosDeBusqueda(categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));
        modelo.addAttribute("categorias", categoriaService.ListarCategorias());
        modelo.addAttribute("marcas", marcaService.ListarMarcas());
        return "admin/adminlistaproductos";
    }

    @GetMapping("/productos/{categoriaNombre}")
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

        modelo.addAttribute("productos", productoService.ListarProductosPorFiltrosDeBusqueda(
                categoria.getId_categoria(), marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));

        modelo.addAttribute("categorias", categoriaService.ListarCategorias());
        modelo.addAttribute("categoriaactual", categoriaService.ObtenerCategoriaPorNombre(categoriaNombre));
        modelo.addAttribute("marcas", marcaService.ListarDistintasMarcasByGrupoCategoria(categoriaNombre));
        return "admin/adminlistaproductoscategoria";
    }

    @GetMapping("/productos/oferta")
    public String productosenoferta(
            @RequestParam(value = "p-categoria", required = false) Long categoriaId,
            @RequestParam(value = "p-marcas", required = false) List<Long> marcaIds,
            @RequestParam(value = "p-min", required = false) Double minPrecio,
            @RequestParam(value = "p-max", required = false) Double maxPrecio,
            @RequestParam(value = "p-palabraclave", required = false) String palabraClave,
            Model modelo
    ) {
        /* Todos los productos están en oferta, por lo tanto no se va a requerir del parametro oferta */
        modelo.addAttribute("productos", productoService.ListarProductosPorFiltrosDeBusqueda(
                categoriaId, marcaIds, minPrecio, maxPrecio, true, palabraClave));

        modelo.addAttribute("categorias", categoriaService.ListarCategorias());
        modelo.addAttribute("marcas", marcaService.ListarMarcas());
        return "admin/adminlistaproductosoferta.html";
    }

    @GetMapping("/productos/buscar")
    public String productosbusqueda(
            @RequestParam(value = "p-categoria", required = false) Long categoriaId,
            @RequestParam(value = "p-marcas", required = false) List<Long> marcaIds,
            @RequestParam(value = "p-min", required = false) Double minPrecio,
            @RequestParam(value = "p-max", required = false) Double maxPrecio,
            @RequestParam(value = "p-oferta", required = false) Boolean enOferta,
            @RequestParam(value = "p-palabraclave", required = false) String palabraClave,
            Model modelo
    ) {
        modelo.addAttribute("productos", productoService.ListarProductosPorFiltrosDeBusqueda(
                categoriaId, marcaIds, minPrecio, maxPrecio, enOferta, palabraClave));

        modelo.addAttribute("contarproductos", productoService.ContarProductosPorFiltrosDeBusqueda(categoriaId, marcaIds,
                minPrecio, maxPrecio, enOferta, palabraClave));

        modelo.addAttribute("categorias", categoriaService.ListarCategorias());
        modelo.addAttribute("marcas", marcaService.ListarMarcas());
        modelo.addAttribute("palabraClave", palabraClave);
        return "admin/adminlistaproductosbusqueda.html";
    }

    @GetMapping("/categorias")
    public String listacategorias(Model modelo) {
        modelo.addAttribute("categorias", categoriaService.ListarCategorias());
        return "admin/admincategorias.html";
    }

    @GetMapping("/productos/nuevo")
    public String mostrarformularioderegistrarproducto(Model modelo) {
        ProductoEntity productoEntity = new ProductoEntity();
        modelo.addAttribute("categorias", categoriaService.ListarCategorias());
        var listacategorias = categoriaService.ListarCategoriasByEstadoTrue();
        modelo.addAttribute("listacategorias", listacategorias);
        var listamarcas = marcaService.ListarMarcasByEstadoTrue();
        modelo.addAttribute("listamarcas", listamarcas);
        modelo.addAttribute("productoEntity", productoEntity);
                return "admin/adminagregarproducto.html";
    }

    @PostMapping("/productos/nuevo")
    public String guardarproducto(@ModelAttribute("productoEntity") ProductoEntity productoEntity) {
        productoService.GuardarProducto(productoEntity);
        return "redirect:/admin/productos";
    }

}
