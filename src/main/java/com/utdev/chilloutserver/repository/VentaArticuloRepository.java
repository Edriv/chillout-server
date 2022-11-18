package com.utdev.chilloutserver.repository;

import com.utdev.chilloutserver.model.VentaArticulo;
import com.utdev.chilloutserver.model.primaryKeys.PKVentaArticulos;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaArticuloRepository extends JpaRepository<VentaArticulo, PKVentaArticulos> {
    //List<VentaArticulo> findChilds(Example<VentaArticulo> ventaArticulo);
}
