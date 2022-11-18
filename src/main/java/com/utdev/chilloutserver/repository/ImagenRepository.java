package com.utdev.chilloutserver.repository;

import com.utdev.chilloutserver.model.Imagen;
import com.utdev.chilloutserver.model.primaryKeys.PKImagen;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenRepository extends CrudRepository<Imagen, PKImagen> {
    List<Imagen> findAll(Example<Imagen> imagen);
}
