package com.tiendaonline.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "detallescarrito")
public class DetallesCarritoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detallescarrito")
    private Long id_detallecarrito;
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_carrito")
    private CarritoEntity carritoEntity;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private ProductoEntity productoEntity;

}
    /*
    @OneToMany(mappedBy = "detallesCarritoEntity", cascade = CascadeType.ALL, 
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetallesCarrito_ProductoEntity> detallesCarrito_ProductoEntity;
*/
