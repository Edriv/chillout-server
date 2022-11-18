package com.utdev.chilloutserver.service;

import com.utdev.chilloutserver.model.Categoria;
import com.utdev.chilloutserver.repository.CategoriaRepository;
import com.utdev.chilloutserver.service.interfaces.ICategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    //region Zona de CREATE

    // Save full categoria
    @Override
    public Categoria saveCategoria(Categoria categoria){ return repository.save(categoria); }

    //endregion

    //region Zona de READ

    // Get all categorias
    @Override
    public List<Categoria> findAllCategorias(){ return repository.findAll(); }

    // Get categoria by Name
    @Override
    public  Categoria findByNombre(String nombre){ return  repository.findFirstByNombreIgnoreCase(nombre); }

    //endregion

    //region Zona de DELETE

    // Delete categoria by ID
    @Override
    public void deleteByID(int id){ repository.deleteById(id); }

    //endregion

}
