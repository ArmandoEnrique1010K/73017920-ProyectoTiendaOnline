package com.tiendaonline.security;

import com.tiendaonline.entity.DetallesOrdenEntity;
import com.tiendaonline.entity.OrdenEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User {

    private List<DetallesOrdenEntity> detallesOrden;
    private OrdenEntity orden;

    public CustomUserDetails(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            List<DetallesOrdenEntity> detallesOrden,
            OrdenEntity orden) {
        super(username, password, authorities);
        this.detallesOrden = detallesOrden;
        this.orden = orden;
    }

    public List<DetallesOrdenEntity> getDetallesOrden() {
        return detallesOrden;
    }

    public void setDetallesOrden(List<DetallesOrdenEntity> detallesOrden) {
        this.detallesOrden = detallesOrden;
    }

    public OrdenEntity getOrden() {
        return orden;
    }

    public void setOrden(OrdenEntity orden) {
        this.orden = orden;
    }
}
