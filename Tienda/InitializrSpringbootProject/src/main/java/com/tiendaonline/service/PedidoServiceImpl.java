package com.tiendaonline.service;

import com.tiendaonline.entity.PedidoEntity;
import com.tiendaonline.entity.UsuarioEntity;
import com.tiendaonline.repository.PedidoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Override
    public List<PedidoEntity> listarTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public List<PedidoEntity> encontrarPorUsuario(UsuarioEntity usuarioEntity) {
        return pedidoRepository.findPedidosByUsuario(usuarioEntity);
    }

    @Override
    public Optional<PedidoEntity> encontrarPorId(Long id_pedido) {
        return pedidoRepository.findById(id_pedido);
    }
    
}
