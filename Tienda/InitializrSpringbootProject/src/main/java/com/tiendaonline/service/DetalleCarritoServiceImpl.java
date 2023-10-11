package com.tiendaonline.service;

import com.tiendaonline.entity.DetallesCarritoEntity;
import com.tiendaonline.repository.DetallesCarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DetalleCarritoServiceImpl implements DetalleCarritoService{
    
    @Autowired
    private DetallesCarritoRepository detallesCarritoRepository;

    @Override
    public DetallesCarritoEntity guardarDetalleCarrito(DetallesCarritoEntity detallesCarritoEntity) {
		return detallesCarritoRepository.save(detallesCarritoEntity);
    }

}
