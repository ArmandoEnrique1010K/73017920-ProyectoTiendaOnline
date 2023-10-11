package com.tiendaonline.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "producto")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id_producto;
    @Column(name = "codigo")
    private String codigo;
    // Si no se establece la longitud maxima de caracteres, se establece automaticamente a 255
    @Column(name = "nombre", length = 1000)
    private String nombre;
    @Column(name = "oferta")
    private Boolean oferta;
    @Column(name = "precionormal")
    private Double precionormal;

    @Column(name = "preciooferta")
    private Double preciooferta;
    @Column(name = "estado")
    private Boolean estado;

    // Relacion hacia detallesproductos
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detallesproducto")
    private DetallesProductoEntity detallesproductoEntity;

    // Relacion hacia categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private CategoriaEntity categoriaEntity;

    // Relacion hacia marca
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca")
    private MarcaEntity marcaEntity;

    // Relacion hacia imagen
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imagen")
    private ImagenProductoEntity imagenProductoEntity;

    @OneToMany(mappedBy = "productoEntity", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetallesCarritoEntity> detallesCarritoEntity;

    
}

    /*
    @OneToMany(mappedBy = "productoEntity", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetallesCarrito_ProductoEntity> detallesCarrito_ProductoEntity;
    */



    
    /* Relacion hacia detallesorden */
    /*
    @OneToMany(mappedBy = "productoEntity", cascade = CascadeType.ALL, 
        orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetallesOrdenEntity> detallesOrdenEntity;
    
*/





























