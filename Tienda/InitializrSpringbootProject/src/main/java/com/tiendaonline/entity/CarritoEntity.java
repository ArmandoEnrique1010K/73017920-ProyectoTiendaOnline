package com.tiendaonline.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "carrito")
public class CarritoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long id_carrito;
    @Column(name = "numero_orden")
    private String numero;
    @Column(name = "fecha_creacion")
    private Date fecha_creacion;   
    @Column(name = "fecha_recibida")
    private Date fecha_recibida;   
    @Column(name = "total_a_pagar")
    private Double total_a_pagar;
/*
Luego, cuando un usuario realice un pedido, puedes seguir estos pasos:
    Marcar el carrito actual como inactivo (estado_carrito = false).
    Crear un nuevo carrito para el usuario con estado_carrito = true.
*/
    @Column(name = "estado_carrito")
    private Boolean estado_carrito;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuarioEntity;
   
    @OneToMany(mappedBy = "carritoEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetallesCarritoEntity> detallesCarritoEntity = new ArrayList<>(); // Inicializa la lista

    @OneToOne(mappedBy = "carritoEntity", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private PedidoEntity pedidoEntity;

    /*
    @ManyToMany
    @JoinTable(
        name = "productos_en_carrito",
        joinColumns = @JoinColumn(name = "carrito_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<ProductoEntity> productosEnCarrito;
*/
    
}

