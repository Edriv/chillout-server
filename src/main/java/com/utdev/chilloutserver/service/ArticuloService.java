package com.utdev.chilloutserver.service;

import com.utdev.chilloutserver.model.Articulo;
import com.utdev.chilloutserver.repository.ArticuloRepository;
import com.utdev.chilloutserver.service.interfaces.IArticuloService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloService implements IArticuloService {

    private final ArticuloRepository repository;

    public ArticuloService(ArticuloRepository articuloRepository) {
        this.repository = articuloRepository;
    }

    // Save new full Articulo
    @Override
    public Articulo saveArticulo(Articulo articulo){
        return repository.save(articulo);
    }

    // Find Articulos
    @Override
    public List<Articulo> findAll(){
        return repository.findAll();
    }

    // Find Articulos By CodBarras
    @Override
    public Articulo findById(String id){
        return repository.findById(id).orElse(null);
    }

    // Find Articulos Paging
    @Override
    public Page<Articulo> findArticulos(Pageable pageable){
        return repository.findAll(pageable);
    }

    // Find by Nombre
    @Override
    public Page<Articulo> findByNombre(String nombre, Pageable pageable){
        return repository.findByNombreContaining(nombre, pageable);
    }

    // Find by Img
    @Override
    public Page<Articulo> findWithImg(boolean img, Pageable pageable){
        return repository.findByImg(img, pageable);
    }

    // Delete articulo
    @Override
    public void deleteArticulo(String codBarras){
        repository.deleteById(codBarras);
    }

    // Cancel articulo
    @Override
    public boolean cancelArticulo(String codBarras)
    {
        try {
            repository.findById(codBarras).ifPresentOrElse(articulo -> {
                articulo.setDisponible(!articulo.isDisponible());
                if (repository.save(articulo) == null)
                    throw new RuntimeException("No se pudo actualizar el producto");
            },() -> { throw new RuntimeException("No se encontro el producto especificado"); });
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean suspendArticulo(String codBarras)
    {
        try {
            repository.findById(codBarras).ifPresentOrElse(articulo -> {
                articulo.setVisible(!articulo.isVisible());
                if (repository.save(articulo) == null)
                    throw new RuntimeException("No se pudo actualizar el producto");
            },() -> { throw new RuntimeException("No se encontro el producto especificado"); });
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
