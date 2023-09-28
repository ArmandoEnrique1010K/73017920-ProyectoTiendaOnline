package com.tiendaonline.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orden")
public class OrdenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private Long id_orden;
    @Column(name = "numero_orden")
    private String numero;
    @Column(name = "fecha_creacion")
    private Date fecha_creacion;   
    @Column(name = "fecha_recibida")
    private Date fecha_recibida;   
    @Column(name = "total_a_pagar")
    private Double total_a_pagar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuarioEntity;
    
    @OneToMany(mappedBy = "ordenEntity", cascade = CascadeType.ALL, 
        orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetallesOrdenEntity> detallesOrdenEntity;
}
