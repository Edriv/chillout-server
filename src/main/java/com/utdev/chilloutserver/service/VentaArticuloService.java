package com.utdev.chilloutserver.service;

import com.utdev.chilloutserver.model.Articulo;
import com.utdev.chilloutserver.model.Promocion;
import com.utdev.chilloutserver.model.VentaArticulo;
import com.utdev.chilloutserver.model.primaryKeys.PKVentaArticulos;
import com.utdev.chilloutserver.repository.ArticuloRepository;
import com.utdev.chilloutserver.repository.PromocionRepository;
import com.utdev.chilloutserver.repository.VentaArticuloRepository;
import com.utdev.chilloutserver.service.interfaces.IVentaArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaArticuloService implements IVentaArticuloService {

    @Autowired
    private final VentaArticuloRepository repository;
    @Autowired
    private final ArticuloRepository articuloRepository;
    @Autowired
    private final PromocionRepository promocionRepository;

    public VentaArticuloService(VentaArticuloRepository repository, ArticuloRepository articuloRepository, PromocionRepository promocionRepository) {
        this.repository = repository;
        this.articuloRepository = articuloRepository;
        this.promocionRepository = promocionRepository;
    }

    // CREATE & UPDATE
    @Override
    public VentaArticulo saveVentaArticulo(VentaArticulo venta){
        Articulo articulo = articuloRepository.findById(venta.getVentaArticulosPK().getCod_barras()).orElse(null);
        if(articulo==null)
            return null;
        VentaArticulo va = repository.save(venta);
        if(va==null)
            return null;

        articulo.setStock(articulo.getStock()-venta.getCantidad());
        articulo.setLastUpdateInventory(LocalDateTime.now());
        articuloRepository.save(articulo);
        return va;
    }

    // Create venta articulo
    @Override
    public VentaArticulo createVentaArticulo(String idVenta, String codBarras, int cantidad){
        Articulo articulo = articuloRepository.findById(codBarras).orElse(null);
        if(articulo == null)
            return null;
        if(articulo.getStock() < cantidad)
            return null;
        articulo.setStock(articulo.getStock() - cantidad);
        articuloRepository.save(articulo);

        VentaArticulo va = new VentaArticulo();
        va.setVentaArticulosPK(new PKVentaArticulos(idVenta, codBarras));
        va.setOfertado((articulo.getPromoID()==null)?false:true);
        va.setPrecioRegular(articulo.getPrecioVenta());
        va.setIva(articulo.getIva());

        boolean isPromoFlag = false;
        if(articulo.getPromoID()!=null){
            Promocion promo = promocionRepository.findById(articulo.getPromoID()).orElse(null);
            if(promo != null){
                if(promo.getFechaFin().isAfter(LocalDateTime.now())){
                    va.setPrecioVenta(articulo.getPrecioVenta()*(1-(promo.getDescuento()/100)));
                    va.setPromoID(promo.getId());
                    isPromoFlag = true;
                }
            }
        }
        if(!isPromoFlag) va.setPrecioVenta(articulo.getPrecioVenta());

        return repository.save(va);
    }

    // Find all VentaArticulos
    @Override
    public List<VentaArticulo> findAll(){ return repository.findAll(); }

    // Find all Ventas paging
    @Override
    public Page<VentaArticulo> findAllPaging(Pageable page){
        return repository.findAll(page);
    }

    // Find by full ID
    @Override
    public VentaArticulo findById(PKVentaArticulos id){ return repository.findById(id).orElse(null); }

    // Find by partial ID
    @Override
    public List<VentaArticulo> findByVenta(String idVenta){
        VentaArticulo va = new VentaArticulo();
        PKVentaArticulos pkva = new PKVentaArticulos();
        va.setVentaArticulosPK(pkva);

        pkva.setId_venta(idVenta);
        Example<VentaArticulo> vaExample = Example.of(va);
        return repository.findAll(vaExample);
    }

    // Delte VentaArticulo
    @Override
    public void deleteById(PKVentaArticulos id){
        repository.deleteById(id);
    }

}
