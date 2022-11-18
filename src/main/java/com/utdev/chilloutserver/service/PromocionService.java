package com.utdev.chilloutserver.service;

import com.utdev.chilloutserver.model.Promocion;
import com.utdev.chilloutserver.repository.PromocionRepository;
import com.utdev.chilloutserver.service.interfaces.IPromocionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionService implements IPromocionService {

    private final PromocionRepository repository;

    public PromocionService(PromocionRepository repository) {
        this.repository = repository;
    }

    //region Zona de CREATE

    // Save full promocion
    @Override
    public Promocion savePromocion(Promocion promocion){ return repository.save(promocion); }

    //endregion

    //region Zona de READ

    // Get all promociones
    @Override
    public List<Promocion> findAllPromociones(){ return repository.findAll(); }

    // Get promocion by Id
    @Override
    public Promocion findById(String id){ return repository.findById(id).orElse(null); }

    // Get promociones by disponible
    @Override
    public List<Promocion> findByDisponible(boolean disponible){ return findByDisponible(disponible); }

    //endregion

    //region Zona de DELETE

    // Delete promocion by ID
    @Override
    public void deleteByID(String id){ repository.deleteById(id); }

    //endregion

}
