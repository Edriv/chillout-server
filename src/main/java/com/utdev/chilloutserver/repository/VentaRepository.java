package com.utdev.chilloutserver.repository;

import com.utdev.chilloutserver.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VentaRepository extends JpaRepository<Venta, String> {
    Page<Venta> findByFolio(long folio, Pageable pageable);
    Page<Venta> findByFechaVenta(LocalDateTime fecha, Pageable pageable);
}
