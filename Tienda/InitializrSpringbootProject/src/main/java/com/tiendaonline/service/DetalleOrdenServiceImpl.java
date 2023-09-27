package com.tiendaonline.service;

import com.tiendaonline.entity.DetallesOrdenEntity;
import com.tiendaonline.repository.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService{

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;
    
    @Override
    public DetallesOrdenEntity save(DetallesOrdenEntity detallesOrdenEntity) {
        return detalleOrdenRepository.save(detallesOrdenEntity);
    }
    
    
}
