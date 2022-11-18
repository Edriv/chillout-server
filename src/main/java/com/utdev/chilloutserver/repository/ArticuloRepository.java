package com.utdev.chilloutserver.repository;

import com.utdev.chilloutserver.model.Articulo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, String> {
    Page<Articulo> findByImg(boolean image, Pageable pageable);
    Page<Articulo> findByNombreContaining(String nombre, Pageable pageable);

}
