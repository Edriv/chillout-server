package com.utdev.chilloutserver.service.interfaces;

import com.utdev.chilloutserver.model.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IVentaService {
    // Save full venta
    Venta saveVenta(Venta venta);

    // Build and save venta
    Venta saveVenta(String id, String userID, double total, int productos);

    // Find all ventas
    List<Venta> findAllVentas();

    // Find By ID
    Venta findById(String id);

    // Find with paging
    Page<Venta> findVentas(Pageable pageable);

    // Find by folio
    Page<Venta> findByFolio(long folio, Pageable pageable);

    // Find by fecha
    Page<Venta> findByFecha(LocalDateTime fecha, Pageable pageable);

    // Delete by id
    void deleteVenta(String id);
}
