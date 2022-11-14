package com.utdev.chilloutserver.service.interfaces;

import com.utdev.chilloutserver.model.Articulo;
import org.springframework.data.domain.Page;

public interface IArticuloService {

    // Save new full Articulo
    Articulo saveArticulo(Articulo articulo);
}
