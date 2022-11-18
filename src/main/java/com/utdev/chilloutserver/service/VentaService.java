package com.utdev.chilloutserver.service;

import com.utdev.chilloutserver.model.Venta;
import com.utdev.chilloutserver.repository.VentaRepository;
import com.utdev.chilloutserver.service.interfaces.IVentaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    private final VentaRepository repository;

    public VentaService(VentaRepository repository) {
        this.repository = repository;
    }

    // Save full venta
    @Override
    public Venta saveVenta(Venta venta){
        return repository.save(venta);
    }

    // Build and save venta
    @Override
    public Venta saveVenta(String id, String userID, double total, int productos){
        return repository.save(new Venta(
                id,
                userID,
                Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime())),
                LocalDateTime.now(),
                total,
                false,
                productos
        ));
    }

    // Find all ventas
    @Override
    public List<Venta> findAllVentas(){ return repository.findAll(); }

    // Find By ID
    @Override
    public Venta findById(String id){ return repository.findById(id).orElse(null); }

    // Find with paging
    @Override
    public Page<Venta> findVentas(Pageable pageable){ return repository.findAll(pageable); }

    // Find by folio
    @Override
    public Page<Venta> findByFolio(long folio, Pageable pageable){ return repository.findByFolio(folio, pageable); }

    // Find by fecha
    @Override
    public Page<Venta> findByFecha(LocalDateTime fecha, Pageable pageable){
        return repository.findByFechaVenta(fecha, pageable);
    }

    // Delete by id
    @Override
    public void deleteVenta(String id){ repository.deleteById(id); }

    // Future feature get Venta and childs of Venta-Articulor


}
