package com.tiendaonline.controller;

import com.tiendaonline.service.ImagenProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/assets/productos")
public class AssetsProductosController {
    
    @Autowired
    private ImagenProductoServiceImpl imagenProductoServiceImpl;
    
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> obtenerRecurso(@PathVariable("filename") String filename) {
    Resource recurso = imagenProductoServiceImpl.cargarComoRecurso(filename);
    return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_JPEG)
            .contentType(MediaType.IMAGE_PNG)
            .contentType(MediaType.IMAGE_GIF)
            .body(recurso);
    }

    
}
