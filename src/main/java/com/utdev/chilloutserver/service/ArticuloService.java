package com.utdev.chilloutserver.service;

import com.utdev.chilloutserver.model.Articulo;
import com.utdev.chilloutserver.repository.ArticuloRepository;
import com.utdev.chilloutserver.service.interfaces.IArticuloService;
import org.springframework.stereotype.Service;

@Service
public class ArticuloService implements IArticuloService {

    private final ArticuloRepository articuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    // Save new full Articulo
    @Override
    public Articulo saveArticulo(Articulo articulo){
        return articuloRepository.save(articulo);
    }
}
