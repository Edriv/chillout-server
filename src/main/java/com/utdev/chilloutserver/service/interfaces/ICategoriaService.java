package com.utdev.chilloutserver.service.interfaces;

import com.utdev.chilloutserver.model.Categoria;

import java.util.List;

public interface ICategoriaService {
    //Save full categoria
    Categoria saveCategoria(Categoria categoria);

    // Get all categorias
    List<Categoria> findAllCategorias();

    // Get categoria by Name
    Categoria findByNombre(String nombre);

    // Get categoria by ID
    Categoria findByID(int id);

    // Delete categoria by ID
    void deleteByID(int id);
}
