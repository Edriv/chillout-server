package com.utdev.chilloutserver.service.interfaces;

import com.utdev.chilloutserver.model.VentaArticulo;
import com.utdev.chilloutserver.model.primaryKeys.PKVentaArticulos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVentaArticuloService {
    // CREATE & UPDATE
    VentaArticulo saveVentaArticulo(VentaArticulo venta);

    // Create venta articulo
    VentaArticulo createVentaArticulo(String idVenta, String codBarras, int cantidad);

    // Find all VentaArticulos
    List<VentaArticulo> findAll();

    // Find all Ventas paging
    Page<VentaArticulo> findAllPaging(Pageable page);

    // Find by full ID
    VentaArticulo findById(PKVentaArticulos id);

    // Find by partial ID
    List<VentaArticulo> findByVenta(String idVenta);

    // Delte VentaArticulo
    void deleteById(PKVentaArticulos id);
}
