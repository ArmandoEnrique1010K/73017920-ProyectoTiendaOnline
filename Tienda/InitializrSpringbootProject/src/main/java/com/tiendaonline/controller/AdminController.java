package com.tiendaonline.controller;

import com.tiendaonline.entity.ImagenProductoEntity;
import com.tiendaonline.entity.ProductoEntity;
import com.tiendaonline.service.CategoriaService;
import com.tiendaonline.service.ImagenProductoService;
import com.tiendaonline.service.MarcaService;
import com.tiendaonline.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/productos")
public class AdminController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private ImagenProductoService imagenProductoService;

    // RUTAS PARA ADMINISTRADORES
    @GetMapping("/nuevo")
    public String mostrarFormularioDeNuevoProducto(Model modelo) {
        ProductoEntity productoEntity = new ProductoEntity();

        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());

        var listacategorias = categoriaService.ListarCategoriasByEstadoTrue();
        modelo.addAttribute("listacategorias", listacategorias);
        var listamarcas = marcaService.ListarMarcasByEstadoTrue();
        modelo.addAttribute("listamarcas", listamarcas);

        modelo.addAttribute("productoEntity", productoEntity);

        return "form_agregarproducto";
    }

    @PostMapping("/nuevo")
    public String guardarproducto(@ModelAttribute("productoEntity") ProductoEntity productoEntity) {
        productoService.GuardarProducto(productoEntity);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id_producto}")
    public String mostrarFormularioDeEditarProducto(@PathVariable Long id_producto, Model modelo) {
        modelo.addAttribute("categorias", categoriaService.ListarCategoriasByEstadoTrue());
        var varUnRegistro = productoService.ObtenerProductoPorId(id_producto);
        modelo.addAttribute("productoEntity", varUnRegistro);

        var listacategorias = categoriaService.ListarCategoriasByEstadoTrue();
        modelo.addAttribute("listacategorias", listacategorias);
        var listamarcas = marcaService.ListarMarcasByEstadoTrue();
        modelo.addAttribute("listamarcas", listamarcas);

        return "form_editarproducto";
    }

    @PostMapping("/editar/{id_producto}")
    public String actualizarProductoEditado(
            @PathVariable Long id_producto,
            @ModelAttribute("productoEntity") ProductoEntity productoEntity,
            @RequestParam("imagenProductoEntity.imagen") MultipartFile imagen,
            Model modelo) {
        productoEntity.getImagenProductoEntity().setImagen(imagen);
        productoService.ActualizarProducto(productoEntity);
        return "redirect:/productos";
    }

    @GetMapping("/cambioestadoafalse/{id_producto}")
    public String cambiarEstadoTrueaFalse(@PathVariable Long id_producto) {
        productoService.CambiarEstadoProductoAFalse(id_producto);
        return "redirect:/productos";
    }

    @GetMapping("/cambioestadoatrue/{id_producto}")
    public String cambiarEstadoFalseaTrue(@PathVariable Long id_producto) {
        productoService.CambiarEstadoProductoATrue(id_producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminardefinitivamente/{id_producto}")
    public String eliminarDefinitivamente(@PathVariable Long id_producto) {
        ProductoEntity personaEntity = productoService.ObtenerProductoPorId(id_producto);
        productoService.EliminarDefinitivamente(id_producto);
        imagenProductoService.eliminarImagen(personaEntity.getImagenProductoEntity().getRutaImagen());
        return "redirect:/productos";

    }
}
