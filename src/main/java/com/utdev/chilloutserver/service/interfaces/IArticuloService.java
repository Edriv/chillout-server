package com.utdev.chilloutserver.service.interfaces;

import com.utdev.chilloutserver.model.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticuloService {

    // Save new full Articulo
    Articulo saveArticulo(Articulo articulo);

    // Find Articulos
    List<Articulo> findAll();

    // Find Articulos By CodBarras
    Articulo findById(String id);

    // Find Articulos Paging
    Page<Articulo> findArticulos(Pageable pageable);

    // Find by Nombre
    Page<Articulo> findByNombre(String nombre, Pageable pageable);

    // Find by Img
    Page<Articulo> findWithImg(boolean img, Pageable pageable);

    // Delete articulo
    void deleteArticulo(String codBarras);

    // Cancel articulo
    boolean cancelArticulo(String codBarras);

    boolean suspendArticulo(String codBarras);
}
