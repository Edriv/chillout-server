package com.utdev.chilloutserver.service.interfaces;

import com.utdev.chilloutserver.model.Categoria;
import com.utdev.chilloutserver.model.Promocion;

import java.util.List;

public interface IPromocionService {
    // Save full promocion
    Promocion savePromocion(Promocion promocion);

    // Get all promociones
    List<Promocion> findAllPromociones();

    // Get promocion by Id
    Promocion findById(String id);

    // Get promociones by disponible
    List<Promocion> findByDisponible(boolean disponible);

    // Delete categoria by ID
    void deleteByID(String id);
}
